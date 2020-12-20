package com.fab_cio612.CoffeeHttpServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.fab_cio612.CoffeeHttpServer.threading.SocketRunnable;
import com.fab_cio612.CoffeeHttpServer.threading.ThreadPool;

public class App {

    private static boolean running;
    private static ServerSocket socket;
    private static ThreadPool pool; 
    public static void main( String[] args ) {
        System.out.println("Starting CoffeeHttpServer...");
        //create Instances

        Configs cfg = Configs.getInstance();
        cfg.loadConfigs();

        pool = new ThreadPool(10, 20);//!use values from configs

        //start ServerSocket
        try {
            socket = new ServerSocket(8080); //use port from configs
            running = true;
            System.out.println("Server started!");
        } catch(IOException e){
            throw new RuntimeException("Could not start Server" + e.getMessage());
        }
        //Server loop
        while(running){
            Socket clientSocket = null;
            try {
                clientSocket = socket.accept();
            } catch (IOException e) {
                throw new RuntimeException("Error accepting client connection", e);
            }
            pool.execute(new SocketRunnable(clientSocket));
        }
    }

    public static void stopServer(){
        System.out.println("Stopping server...");
        running = false;
        try{
            socket.close();
            pool.stopThreadPool();
            System.out.println("Server stopped!");
        }catch(IOException e){
            throw new RuntimeException("Could not stop Server" + e.getMessage());
        }
    }
}
