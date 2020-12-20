package com.fab_cio612.CoffeeHttpServer.threading;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketRunnable implements Runnable {
    
    private Socket socket;

    public SocketRunnable(Socket socket){
        this.socket = socket;
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

            output.println("HTTP/1.1 200 OK\nContent-Length: 46\nContent-Type: text/html\nConnection: Closed\n\r\n<html><body><h1>Hello, World!</h1></body></html>");
            in.close();
            output.close();
        }catch(IOException e){
            System.out.println("Error reading and writing from client" + e.getMessage());
        }
    }
}