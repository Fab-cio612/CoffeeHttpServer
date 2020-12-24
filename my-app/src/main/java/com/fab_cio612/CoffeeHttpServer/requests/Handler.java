package com.fab_cio612.CoffeeHttpServer.requests;

import java.io.ObjectInputFilter.Config;

import com.fab_cio612.CoffeeHttpServer.Configs;

public class Handler {
    
    //path (i.e. http://www.example.com/ "foo/bar" ) this handler responds to
    protected String handlerPath;
    protected Configs cfg;

    public Handler(){
        handlerPath = "/";
        cfg = Configs.getInstance();
    }

    public String getHandlerPath(){
        return handlerPath;
    }
    
    //Functions for each Http method
    public Response get(Request req){
        return null;
    }

    public Response head(Request req){
        return null;
    }

    public Response post(Request req){
        return null;
    }

    public Response put(Request req){
        return null;
    }
    
    public Response delete(Request req){
        return null;
    }

    public Response connect(Request req){
        return null;
    }

    public Response options(Request req){
        return null;
    }

    public Response trace(Request req){
        return null;
    }

    public Response patch(Request req){
        return null;
    }
}
