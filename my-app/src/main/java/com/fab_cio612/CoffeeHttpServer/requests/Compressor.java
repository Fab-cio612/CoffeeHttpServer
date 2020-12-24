package com.fab_cio612.CoffeeHttpServer.requests;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.Deflater;
import java.util.zip.GZIPOutputStream;

public class Compressor {
    
    private Compressor(){}

    //method for encodings: gzip, deflate

    public static String encodeGzip(String s){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try{
            GZIPOutputStream gzip = new GZIPOutputStream(baos);
            gzip.write(s.getBytes());
            gzip.close();
        }catch(IOException e){
            e.printStackTrace();
        }
        return new String(baos.toByteArray());
    }

    public static String encodeDeflate(String s){
        Deflater def = new Deflater();
        def.setInput(s.getBytes());
        def.finish();

        byte[] out = new byte[1024];
        int size = def.deflate(out);
        def.end();

        if(size > 1024) return null;

        return new String(out);
    }
}
