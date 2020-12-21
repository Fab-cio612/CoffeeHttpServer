package com.fab_cio612.CoffeeHttpServer.requests;

import java.util.HashMap;

public class Request {
    private String method;
    private String target;
    
    private HashMap<String, String> headers;
    
    private String content;

    public Request(){
        method = "GET";
        target = "/";
        headers = new HashMap<>();
        content = "";
    }

    public void setMethod(String s){
        method = s;
    }

    public void setTarget(String s){
        target = s;
    }

    public void addHeader(String header, String value){
        headers.put(header, value);
    }

    public void setContent(String s){
        content = s;
    }

    public String getMethod(){
        return method;
    }

    public String getTarget(){
        return target;
    }

    public String getHeader(String key){
        return headers.get(key);
    }

    public String getContent(){
        return content;
    }
}
