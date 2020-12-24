package com.fab_cio612.CoffeeHttpServer.requests;

import java.util.ArrayList;

import com.fab_cio612.CoffeeHttpServer.Configs;
import com.fab_cio612.CoffeeHttpServer.Utils;
import com.fab_cio612.CoffeeHttpServer.requests.handlers.*;

public class RequestManager {
    
    private static RequestManager instance = null;
    private ArrayList<Handler> handlers;

    private RequestManager(){
        handlers = new ArrayList<>();
    }

    public static RequestManager getInstance(){
        if(instance == null) instance = new RequestManager();
        return instance;
    }

    public synchronized void loadHandlers(){
        //Add all handlers here
        handlers.add(new RootHandler());
    }

    public Response processRequest(Request req){
        Configs cfg = Configs.getInstance();
        Response res = new Response();

        String content = Utils.readFile(cfg.getConfig("Directory") + "\\index.html");
        if(content.equals("404")){
            res.setCode("404");
            res.setMessage("Not Found");
        }else if(content.equals("500")){
            res.setCode("500");
            res.setMessage("Internal Server Error");
        }else{
            res.setCode("200");
            res.setMessage("OK");
            res.addHeader("Content-Length", String.valueOf(content.length()));
            res.addHeader("Content-Type", "text/html");
            res.setContent(content);
        }
        res.addHeader("Connection", "closed");
        return res;
    }
}
