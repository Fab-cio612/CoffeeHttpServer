package com.fab_cio612.CoffeeHttpServer.threading;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

import com.fab_cio612.CoffeeHttpServer.Utils;
import com.fab_cio612.CoffeeHttpServer.requests.RequestManager;

public class SocketRunnable implements Runnable {
    
    private Socket socket;
    private RequestManager manager;

    public SocketRunnable(Socket socket){
        this.socket = socket;
        manager = RequestManager.getInstance();
    }

    public void run(){
        //read Input
        String inputLine;
        StringBuilder strBld = new StringBuilder();
        String inputMsg = "";

        try{
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            OutputStream output = socket.getOutputStream();

            while((inputLine = in.readLine()) != null){
                if(inputLine.isEmpty()) break;
                strBld.append(inputLine + "\n");
            }

            inputMsg = strBld.toString();

            //create Request Object and pass to RequestManager
            byte[] response = manager.processRequest(Utils.buildRequest(inputMsg));
            //send response and close connection
            output.write(response);
            output.flush();
            in.close();
            output.close();
        }catch(IOException e){
            System.out.println("Error reading and writing from client" + e.getMessage());
        }
    }
}