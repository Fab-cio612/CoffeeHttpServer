package com.fab_cio612.CoffeeHttpServer.requests;

import java.util.ArrayList;

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

    public synchronized void addHandler(Handler handler){
        handlers.add(handler);
    }

    public Response processRequest(Request req){
        Response res = new Response();
        res.setCode("200");
        res.setMessage("OK");
        res.addHeader("Content-Length", "48");
        res.addHeader("Content-Type", "text/html");
        res.addHeader("Connection", "closed");
        res.setContent("<html><body><h1>Hello, World!</h1></body></html>");
        return res;
    }
}
