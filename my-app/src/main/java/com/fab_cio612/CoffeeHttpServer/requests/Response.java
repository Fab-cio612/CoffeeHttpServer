package com.fab_cio612.CoffeeHttpServer.requests;

import java.util.HashMap;
import java.util.Map;

public class Response {
    
    private String code;
    private String message;
    private HashMap<String, String> headers;
    private String content;
    private byte[] compressedContent;

    public Response(){
        code = null;
        message = null;
        headers = new HashMap<>();
        content = "";
        compressedContent = null;
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

    public void setCompressedContent(byte[] b){
        compressedContent = b;
    }

    public String getCode(){
        return code;
    }

    public String getContent(){
        return content;
    }

    public String getHeader(String key){
        return headers.get(key);
    }

    public byte[] toBytes(){
        StringBuilder strBld = new StringBuilder();
        byte[] res;
        //add first line
        strBld.append("HTTP/1.1 " + code + " " + message + "\r\n");
        //add headers
        for(Map.Entry<String, String> entry : headers.entrySet()){
            strBld.append(entry.getKey() + ": " + entry.getValue() + "\r\n");
        }
        //add CRLF
        strBld.append("\r\n");
        //check if compressedContent exists
        if(compressedContent == null){
            strBld.append(content);
            res = strBld.toString().getBytes();
        }else{
            String partOne = strBld.toString();
            res = new byte[partOne.length() + compressedContent.length];
            System.arraycopy(partOne.getBytes(), 0, res, 0, partOne.length());
            System.arraycopy(compressedContent, 0, res, partOne.length(), compressedContent.length);
        }
        return res;
    }
}
