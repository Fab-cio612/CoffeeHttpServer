package com.fab_cio612.CoffeeHttpServer;

import java.util.HashMap;

public class Configs {
    
    private HashMap<String, Object> cnfgs;

    public Configs(){
        cnfgs = new HashMap<>();
    }

    public Object getConfig(String key){
        return cnfgs.get(key);
    }

    public void loadConfigs(){
        //todo
    }
}
