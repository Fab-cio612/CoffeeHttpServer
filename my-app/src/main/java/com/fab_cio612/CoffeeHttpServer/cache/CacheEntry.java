package com.fab_cio612.CoffeeHttpServer.cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.fab_cio612.CoffeeHttpServer.requests.Response;

public class CacheEntry {
    
    //Cacheentry represents resource in multiple languages/encodings
    private HashMap<String, Response> responses;

    public CacheEntry(){
        responses = new HashMap<>();
    }

    public void addResponse(String encLang, Response res){
        //example key gzipen => resource is compressed with gzip and in english
        responses.put(encLang, res);
    }

    public Response getResponse(String key){
        return responses.get(key);
    }

    public void removeResponse(String key){
        responses.remove(key);
    }

    public ArrayList<Response> getAllResponses(){
        ArrayList<Response> list = new ArrayList<>();
        for(Map.Entry<String, Response> entry: responses.entrySet()){
            list.add(entry.getValue());
        }
        return list;
    }
}
