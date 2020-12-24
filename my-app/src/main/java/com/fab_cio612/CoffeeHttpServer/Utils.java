package com.fab_cio612.CoffeeHttpServer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.fab_cio612.CoffeeHttpServer.requests.Request;

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

    //Does only return common MIME Types
    public static String getMIMEType(String res){
        if(res.endsWith(".html") || res.endsWith(".htm")){
            return "text/html";
        }else if(res.endsWith(".css")){
            return "text/css";
        }else if(res.endsWith(".js")){
            return "text/javascript";
        }else if(res.endsWith(".txt")){
            return "text/plain";
        }else if(res.endsWith(".xml")){
            return "text/xml";
        }else if(res.endsWith(".mp4")){
            return "video/mp4";
        }else if(res.endsWith(".mpeg") || res.endsWith(".mpg") || res.endsWith(".mpe")){
            return "video/mpeg";
        }else if(res.endsWith(".bmp")){
            return "image/bmp";
        }else if(res.endsWith(".gif")){
            return "image/gif";
        }else if(res.endsWith(".jpeg") || res.endsWith(".jpg") || res.endsWith(".jpe")){
            return "image/jpeg";
        }else if(res.endsWith(".png")){
            return "image/png";
        }else if(res.endsWith(".svg")){
            return "image/svg+xml";
        }else if(res.endsWith(".ico")){
            return "image/x-icon";
        }else if(res.endsWith(".mp3")){
            return "audio/mpeg";
        }else if(res.endsWith(".wav")){
            return "audio/wav";
        }
        return null;
    }
}
