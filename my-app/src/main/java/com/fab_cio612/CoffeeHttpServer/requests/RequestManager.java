package com.fab_cio612.CoffeeHttpServer.requests;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import com.fab_cio612.CoffeeHttpServer.cache.Cache;
import com.fab_cio612.CoffeeHttpServer.requests.handlers.*;

public class RequestManager {
    
    private static RequestManager instance = null;
    private ArrayList<Handler> handlers;
    private SimpleDateFormat dateFormat;
    private Cache cache;

    private RequestManager(){
        handlers = new ArrayList<>();
        cache = Cache.getInstance();

        //create DateFormat for Date header
        dateFormat = new SimpleDateFormat("E, dd MMM yyyy HH:mm:ss", Locale.US);
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
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
                        //if get check cache
                        res = cache.getFromCache(req);
                        if(res != null) return res.toBytes();
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
                        //return 400 Bad request if invalid method
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
            if(!(type.contains("audio") || type.contains("video") || type.contains("image"))){
                //Choose correct encoding
                String encodings = req.getHeader("Accept-Encoding");
                if(encodings.contains("gzip")){
                    res.setCompressedContent(Compressor.encodeGzip(res.getContent()));
                    res.addHeader("Content-Encoding", "gzip");
                }else if(encodings.contains("deflate")){
                    res.setCompressedContent(Compressor.encodeDeflate(res.getContent()));
                    res.addHeader("Content-Encoding", "deflate");
                }
            }
        }
        //add headers

        //contentLength
        if(res.getCompressedContent() != null){
            res.addHeader("Content-Length", String.valueOf(res.getCompressedContent().length));
        }else if(!res.getContent().equals("")){
            res.addHeader("Content-Length", String.valueOf(res.getContent().length()));
        }

        res.addHeader("Connection", "closed");
        res.addHeader("Server", "CoffeeServer v0.7.1");
        res.addHeader("Date", dateFormat.format(new Date()) + " GMT");

        //add to cache if method was get;
        if(req.getMethod().equals("GET")){
            cache.addToCache(req.getTarget(), res);
        }
        return res.toBytes();
    }
}
