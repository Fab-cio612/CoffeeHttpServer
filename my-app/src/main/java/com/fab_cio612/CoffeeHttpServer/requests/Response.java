package com.fab_cio612.CoffeeHttpServer.requests;

import java.util.HashMap;
import java.util.Map;

public class Response {
    
    private String code;
    private String message;
    private HashMap<String, String> headers;
    private String content;

    public Response(){
        code = "200";
        message = "OK";
        headers = new HashMap<>();
        content = "";
    }

    public void setCode(String s){
        code = s;
    }

    public void setMessage(String s){
        message = s;
    }

    public void addHeader(String header, String value){
        headers.put(header, value);
    }

    public void setContent(String s){
        content = s;
    }

    public String toString(){
        StringBuilder strBld = new StringBuilder();
        //add first line
        strBld.append("HTTP/1.1 " + code + " " + message + "\r\n");
        //add headers
        for(Map.Entry<String, String> entry : headers.entrySet()){
            strBld.append(entry.getKey() + ": " + entry.getValue() + "\r\n");
        }
        //add CRLF and content
        strBld.append("\r\n" + content);
        return strBld.toString();
    }
}
