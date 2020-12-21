package com.fab_cio612.CoffeeHttpServer;

import com.fab_cio612.CoffeeHttpServer.requests.Request;

public class Utils {
    
    private Utils(){}

    public static Request buildRequest(String requestString){
        Request req = new Request();
        //split request and content and set content
        String[] arr = requestString.split("\r\n\r\n");
        req.setContent(arr[1]);
        //split request and all headers
        String[] arrTwo = arr[1].split("\n");
        //split request and set values
        String[] lineOne = arrTwo[0].split(" ");
        req.setMethod(lineOne[0]);
        req.setTarget(lineOne[1]);
        //add headers
        for(int i = 1; i < arrTwo.length; i++){
            String[] header = arrTwo[i].split(":");
            req.addHeader(header[0], header[1].trim());
        }
        return req;
    }
}
