package com.fab_cio612.CoffeeHttpServer;

import java.util.HashMap;

public class Configs {
    
    private static Configs instance = null;
    private HashMap<String, Object> cnfgs;

    private Configs(){
        cnfgs = new HashMap<>();
    }

    public static Configs getInstance(){
        if(instance == null){
            instance = new Configs();
        }
        return instance;
    }

    public Object getConfig(String key){
        return cnfgs.get(key);
    }

    public void loadConfigs(){
        //todo
    }
}
