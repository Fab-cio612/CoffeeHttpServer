package com.fab_cio612.CoffeeHttpServer.requests;

import java.util.ArrayList;

import com.fab_cio612.CoffeeHttpServer.requests.handlers.*;

public class RequestManager {
    
    private static RequestManager instance = null;
    private ArrayList<Handler> handlers;

    private RequestManager(){
        handlers = new ArrayList<>();
    }

    public static RequestManager getInstance(){
        if(instance == null) instance = new RequestManager();
        return instance;
    }

    public synchronized void loadHandlers(){
        //Add all handlers here
        handlers.add(new RootHandler());
    }

    public byte[] processRequest(Request req){
        Response res = new Response();
        //Check if handler exists for requested resource
        for(Handler h : handlers){
            if(req.getTarget().contains(h.getHandlerPath())){
                //choose method
                switch(req.getMethod()){
                    case "GET":
                        res = h.get(req);
                        break;
                    case "HEAD":
                        res = h.head(req);
                        break;
                    case "POST":
                        res = h.post(req);
                        break;
                    case "PUT":
                        res = h.put(req);
                        break;
                    case "DELETE":
                        res = h.delete(req);
                        break;
                    case "CONNECT":
                        res = h.connect(req);
                        break;
                    case "OPTIONS":
                        res = h.options(req);
                        break;
                    case "TRACE":
                        res = h.trace(req);
                        break;
                    case "PATCH":
                        res = h.patch(req);
                        break;
                    default:
                        res = StandardResponses.create400Response();
                        break;
                }
            }
        }
        //if no handler was found make 404 response
        if(res.getCode() == null) res = StandardResponses.create404Response();

        //if response has body compress it
        if(!res.getContent().equals("")){
            //check if mime type should be compressed
            String type = res.getHeader("Content-Type");
            if(!type.contains("audio") || !type.contains("video") || !type.contains("image")){
                //Choose correct encoding
                String encodings = req.getHeader("Accept-Encoding");
                if(encodings.contains("gzip")){
                    res.setCompressedContent(Compressor.encodeGzip(res.getContent()));
                    res.addHeader("Content-Encoding", "gzip");
                }else if(encodings.contains("deflate")){
                    //res.setCompressedContent(Compressor.encodeDeflate(res.getContent()));
                    res.addHeader("Content-Encoding", "deflate");
                }
            }
        }

        res.addHeader("Content-Length", String.valueOf(res.getContent().length()));
        res.addHeader("Connection", "closed");

        return res.toBytes();
    }
}
