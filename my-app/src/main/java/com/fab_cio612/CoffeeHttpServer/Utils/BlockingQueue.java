package com.fab_cio612.CoffeeHttpServer.Utils;

public class BlockingQueue {
    
    private Runnable[] queue;
    private int putIndex;
    private int takeIndex;
    private boolean isFull;

    public BlockingQueue(int numOfSpots){
        queue = new Runnable[numOfSpots];
        putIndex = 0;
        takeIndex = 0;
        isFull = false;
    }

    public Runnable take() {
        return null;
    }

    public void put(Runnable r) {
        //todo
    }

    public boolean isFull(){
        return isFull;
    }
}
