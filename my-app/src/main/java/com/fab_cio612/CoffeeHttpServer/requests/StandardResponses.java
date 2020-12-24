package com.fab_cio612.CoffeeHttpServer.requests;

public class StandardResponses {
    
    private StandardResponses(){}

    public static Response create404Response(){
        Response res = new Response();
        res.setCode("404");
        res.setMessage("Not Found");
        return res;
    }
    
    public static Response create400Response(){
        Response res = new Response();
        res.setCode("400");
        res.setMessage("Bad Request");
        return res;
    }

    public static Response create500Response(){
        Response res = new Response();
        res.setCode("500");
        res.setMessage("Internal Server Error");
        return res;
    }
}
