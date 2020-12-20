package com.fab_cio612.CoffeeHttpServer.threading;

public class ThreadPool {
    
    private BlockingQueue queue;
    private PoolThread[] pool;

    public ThreadPool(int numOfThreads, int numOfSpots) {
        queue = new BlockingQueue(numOfSpots);
        pool = new PoolThread[numOfThreads];

        //create and start Threads
        for(int i = 0; i < numOfThreads; i++){
            pool[i] = new PoolThread(queue);
            pool[i].start();
        }
    }

    public void execute(Runnable r){
        synchronized (queue) {
            queue.put(r);
            queue.notifyAll();
        }
    }

    public void stopThreadPool(){
        for(PoolThread thread : pool){
            thread.stopThread();
        }
    }
}