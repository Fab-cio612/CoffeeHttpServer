package com.fab_cio612.CoffeeHttpServer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.fab_cio612.CoffeeHttpServer.requests.Request;
import com.fab_cio612.CoffeeHttpServer.requests.Response;

public class Utils {
    
    private Utils(){}

    public static Request buildRequest(String requestString){
        Request req = new Request();
        //split request and content and set content
        String[] arr = requestString.split("\r\n");
        if(arr.length > 1) req.setContent(arr[1]);
        //split request and all headers
        String[] arrTwo = arr[0].split("\n");
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

    public static String readFile(String path){
        try(
            BufferedReader reader = new BufferedReader(new FileReader(path));
        ){
            String line;
            StringBuilder strBld = new StringBuilder();

            while((line = reader.readLine()) != null){
                strBld.append(line);
            }

            return strBld.toString();
        }catch(FileNotFoundException e){
            e.printStackTrace();
            return "404";
        }catch(IOException e){
            e.printStackTrace();
            return "500";
        }
    }

    public static Response create404Response(){
        Response res = new Response();
        res.setCode("404");
        res.setMessage("Not Found");
        return res;
    }
}
