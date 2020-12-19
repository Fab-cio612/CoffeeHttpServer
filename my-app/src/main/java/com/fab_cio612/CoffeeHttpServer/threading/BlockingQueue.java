package com.fab_cio612.CoffeeHttpServer.threading;

public class BlockingQueue {
    
    private Runnable[] queue;
    private int putPointer;
    private int takePointer;
    private int count;
    private int numOfSpots;

    public BlockingQueue(int numOfSpots){
        this.numOfSpots = numOfSpots;
        queue = new Runnable[numOfSpots];
        putPointer = 0;
        takePointer = 0;
        count = 0;
    }

    //removes element from the queue
    public synchronized Runnable take() {
        if(isEmpty()){
            System.out.println("Error: Queue empty!");
        }else{
            Runnable r = queue[takePointer];
            count--;
            takePointer++;
            if(takePointer == numOfSpots) takePointer = 0;
            return r;
        }
        return null;
    }

    //adds element to the queue
    public synchronized void put(Runnable r) {
        if(isFull()){
            System.out.println("Error: Queue full!");
        }else {
            queue[putPointer] = r;
            putPointer++;
            count++;
            if(putPointer == numOfSpots) putPointer = 0;
        }
    }

    public boolean isFull(){
        return count == numOfSpots;
    }

    public boolean isEmpty(){
        return count == 0;
    }
}