package com.fab_cio612.CoffeeHttpServer.requests.handlers;

import com.fab_cio612.CoffeeHttpServer.Utils;
import com.fab_cio612.CoffeeHttpServer.requests.Handler;
import com.fab_cio612.CoffeeHttpServer.requests.Request;
import com.fab_cio612.CoffeeHttpServer.requests.Response;
import com.fab_cio612.CoffeeHttpServer.requests.StandardResponses;

public class RootHandler extends Handler {
    
    public RootHandler(){
        handlerPath = "/";
    }

    @Override
    public Response get(Request req){
        Response res;
        //check if index.html is requested
        if(req.getTarget().equals("/")){
            req.setTarget("/index.html");
        }
        //read resource
        String file = Utils.readFile(cfg.getConfig("Directory") + req.getTarget().replace("/", "\\"));
        //check if successful
        if(file.equals("404")){
            res = StandardResponses.create404Response();
        }else if(file.equals("500")){
            res = StandardResponses.create500Response();
        }else{
            res = new Response();
            res.setCode("200");
            res.setMessage("OK");
            res.addHeader("Content-Type", Utils.getMIMEType(req.getTarget()));
            res.setContent(file);
        }
        return res;
    }
}
