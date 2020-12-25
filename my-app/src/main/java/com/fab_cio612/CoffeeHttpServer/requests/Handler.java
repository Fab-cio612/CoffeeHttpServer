package com.fab_cio612.CoffeeHttpServer.requests;

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
    //return 405 response if method is not allowed
    //if allowed default 403 response will be sent
    public Response get(Request req){
        if(!cfg.getConfig("AllowedMethods").contains("GET")) return StandardResponses.create405Response();
        return StandardResponses.create403Response();
    }

    public Response head(Request req){
        if(!cfg.getConfig("AllowedMethods").contains("HEAD")) return StandardResponses.create405Response();
        return StandardResponses.create403Response();
    }

    public Response post(Request req){
        if(!cfg.getConfig("AllowedMethods").contains("POST")) return StandardResponses.create405Response();
        return StandardResponses.create403Response();
    }

    public Response put(Request req){
        if(!cfg.getConfig("AllowedMethods").contains("PUT")) return StandardResponses.create405Response();
        return StandardResponses.create403Response();
    }
    
    public Response delete(Request req){
        if(!cfg.getConfig("AllowedMethods").contains("DELETE")) return StandardResponses.create405Response();
        return StandardResponses.create403Response();
    }

    public Response connect(Request req){
        if(!cfg.getConfig("AllowedMethods").contains("CONNECT")) return StandardResponses.create405Response();
        return StandardResponses.create403Response();
    }

    public Response options(Request req){
        if(!cfg.getConfig("AllowedMethods").contains("OPTIONS")) return StandardResponses.create405Response();
        return StandardResponses.create403Response();
    }

    public Response trace(Request req){
        if(!cfg.getConfig("AllowedMethods").contains("TRACE")) return StandardResponses.create405Response();
        return StandardResponses.create403Response();
    }

    public Response patch(Request req){
        if(!cfg.getConfig("AllowedMethods").contains("PATCH")) return StandardResponses.create405Response();
        return StandardResponses.create403Response();
    }
}
