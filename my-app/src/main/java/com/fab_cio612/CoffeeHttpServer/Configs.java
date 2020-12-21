package com.fab_cio612.CoffeeHttpServer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class Configs {
    
    private static Configs instance = null;
    private HashMap<String, String> cnfgs;

    private Configs(){
        cnfgs = new HashMap<>();
    }

    public static Configs getInstance(){
        if(instance == null){
            instance = new Configs();
        }
        return instance;
    }

    public String getConfig(String key){
        String value = cnfgs.get(key.toLowerCase());
        if(value != null){
            return cnfgs.get(key.toLowerCase());
        }else{
            throw new RuntimeException("Key not found");
        }
    }

    public void loadConfigs(){
        String line;

        //Read File
        try(
            BufferedReader reader = new BufferedReader(new FileReader("..\\config.cfg"));
        ){
            while((line = reader.readLine()) != null){
                //break down lines and load configs into Hashmap
                String[] arr = line.split(":");
                cnfgs.put(arr[0].trim().toLowerCase(), arr[1].trim().toLowerCase());
            }
        }catch(FileNotFoundException e){
            System.out.println("Config file not found" + e.getMessage());
        }catch(IOException e){
            System.out.println("Error reading configs" + e.getMessage());
        }
    }
}
