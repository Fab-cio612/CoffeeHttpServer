package com.fab_cio612.CoffeeHttpServer.cache;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import com.fab_cio612.CoffeeHttpServer.requests.Request;
import com.fab_cio612.CoffeeHttpServer.requests.Response;

public class Cache {

    private static Cache instance;
    private HashMap<String, CacheEntry> cacheMap;

    private Cache(){
        cacheMap = new HashMap<>();
    }

    //gets all key possibilities for cacheEntry Hashmap
    private String[] getAllKeys(Request req){
        String[] encodings;
        String[] languages;
        String enc = req.getHeader("Accept-Encoding");
        String lang = req.getHeader("Accept-Language");
        
        //load values
        if(enc != null){
            encodings = enc.split(",");
        }else{
            encodings = new String[0];
        }
        if(lang != null){
            if(lang.contains(";q")){
                String temp = lang.split(";q")[0];
                languages = temp.split(",");
            }else{
                languages = lang.split(",");
            }
        }else{
            languages = new String[0];
        }
        //add none if not already
        ArrayList<String> encList = new ArrayList<>(Arrays.asList(encodings));
        ArrayList<String> langList = new ArrayList<>(Arrays.asList(languages));

        encList.add("none");
        langList.add("none");

        //create all possibilities
        String[] poss = new String[langList.size() * encList.size()];
        int i = 0;
        for(String e : encList){
            for(String l : langList){
                poss[i] = e + l;
                i++;
            }
        }
        return poss;
    }

    public static Cache getInstance(){
        if(instance == null) instance = new Cache();
        return instance;
    }

    public void startCacheMaintainer(){
        //create new daemon Thread that runs maintainer runnable
        CacheMaintainer maintainer = new CacheMaintainer();
        maintainer.start();
    }

    public HashMap<String, CacheEntry> getCacheMap(){
        return cacheMap;
    }

    public synchronized Response getFromCache(Request req){
        //check if resource is in cache
        CacheEntry entry = cacheMap.get(req.getTarget());
        if(entry == null) return null;
        //check if resource has correct encoding and language
        for(String key : getAllKeys(req)){
            Response res = entry.getResponse(key);
            if(res != null) return res;
        }
        //return null if entry does not exist
        return null;
    }

    public synchronized void addToCache(String resource, Response res){
        //create entry
        CacheEntry entry = new CacheEntry();
        cacheMap.put(resource, entry);
        //create key for response
        String enc = res.getHeader("Content-Encoding");
        String lang = res.getHeader("Content-Language");

        if(enc == null) enc = "none";
        if(lang == null) lang = "none";
        //add response to entry
        entry.addResponse(enc + lang, res);
    }
}
