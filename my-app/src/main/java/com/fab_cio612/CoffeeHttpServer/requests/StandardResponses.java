package com.fab_cio612.CoffeeHttpServer.requests;

import com.fab_cio612.CoffeeHttpServer.Configs;

public class StandardResponses {
    
    private StandardResponses(){}

    public static Response create400Response(){
        Response res = new Response();
        res.setCode("400");
        res.setMessage("Bad Request");
        return res;
    }

    public static Response create403Response(){
        Response res = new Response();
        res.setCode("403");
        res.setMessage("Forbidden");
        return res;
    }

    public static Response create404Response(){
        Response res = new Response();
        res.setCode("404");
        res.setMessage("Not Found");
        return res;
    }
    
    public static Response create405Response(){
        Response res = new Response();
        res.setCode("405");
        res.setMessage("Method Not Allowed");
        res.addHeader("Allow", Configs.getInstance().getConfig("AllowedMethods"));
        return res;
    }

    public static Response create500Response(){
        Response res = new Response();
        res.setCode("500");
        res.setMessage("Internal Server Error");
        return res;
    }
}
