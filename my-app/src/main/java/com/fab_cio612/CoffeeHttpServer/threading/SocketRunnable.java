package com.fab_cio612.CoffeeHttpServer.threading;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import com.fab_cio612.CoffeeHttpServer.Utils;
import com.fab_cio612.CoffeeHttpServer.requests.RequestManager;
import com.fab_cio612.CoffeeHttpServer.requests.Response;

public class SocketRunnable implements Runnable {
    
    private Socket socket;
    private RequestManager manager;

    public SocketRunnable(Socket socket){
        this.socket = socket;
        manager = new RequestManager();
    }

    public void run(){
        //read Input
        String inputLine;
        StringBuilder strBld = new StringBuilder();
        String inputMsg = "";

        try{
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

            while((inputLine = in.readLine()) != null){
                if(inputLine.isEmpty()) break;
                strBld.append(inputLine + "\n");
            }

            inputMsg = strBld.toString();

            //create Request Object and pass to RequestManager
            Response response = manager.processRequest(Utils.buildRequest(inputMsg));
            output.println(response.toString());
            in.close();
            output.close();
        }catch(IOException e){
            System.out.println("Error reading and writing from client" + e.getMessage());
        }
    }
}