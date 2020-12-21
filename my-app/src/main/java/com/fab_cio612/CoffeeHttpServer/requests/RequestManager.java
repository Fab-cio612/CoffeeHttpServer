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

    public void addHandler(Handler handler){
        handlers.add(handler);
    }

    public Response processRequest(Request req){
        return null;
    }
}
