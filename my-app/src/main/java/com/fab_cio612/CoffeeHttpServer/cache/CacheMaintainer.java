package com.fab_cio612.CoffeeHttpServer.cache;

import java.util.HashMap;
import java.util.Locale;
import java.util.Date;
import java.util.Map;
import java.util.TimeZone;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import com.fab_cio612.CoffeeHttpServer.Configs;
import com.fab_cio612.CoffeeHttpServer.requests.Response;

public class CacheMaintainer extends Thread {

    private Cache cache;
    private SimpleDateFormat dateFormat;

    public CacheMaintainer() {
        cache = Cache.getInstance();
        this.setDaemon(true);

        dateFormat = new SimpleDateFormat("E, dd MMM yyyy HH:mm:ss", Locale.US);
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
    }

    @Override
    public void run() {
        while (true) {
            HashMap<String, CacheEntry> map = cache.getCacheMap();
            //iterate cache
            for (Map.Entry<String, CacheEntry> entry : map.entrySet()) {
                ArrayList<Response> responses = entry.getValue().getAllResponses();
                for (Response res : responses) {
                    //check if responses are fresh or remove
                    try {
                        Date date = dateFormat.parse(res.getHeader("Date"));
                        if(new Date().getTime() - date.getTime() > Integer.parseInt(Configs.getInstance().getConfig("CacheMaxAge"))*1000){
                            //if stale get key and remove
                            String enc = res.getHeader("Content-Encoding") != null ? res.getHeader("Content-Encoding") : "none";
                            String lang = res.getHeader("Content-Language") != null ? res.getHeader("Content-Language") : "none";

                            entry.getValue().removeResponse(enc + lang);
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                //delete entry from cache if empty
                if(responses.isEmpty()){
                    synchronized(map){
                        map.remove(entry.getKey());
                    }
                }
            }
            //wait before next clean up
            try{
                sleep(Integer.parseInt(Configs.getInstance().getConfig("CacheMaxAge")));
            }catch(InterruptedException e){
                interrupt();
                e.printStackTrace();
            }
        }
    }
}