package com.fab_cio612.CoffeeHttpServer.threading;

public class PoolThread extends Thread{

    private boolean running;
    private BlockingQueue queue;

    public PoolThread(BlockingQueue queue){
        super();
        this.queue = queue;
    }

    @Override
    public void run(){
        Runnable task;

        running = true;
        while(running){
            synchronized (queue) {
                //take Task from queue if available
                while(queue.isEmpty()){
                    try {
                        queue.wait();
                    } catch (InterruptedException e) {
                        System.out.println("An error occurred while queue is waiting: " + e.getMessage());
                        this.interrupt();
                    }
                }
                task = queue.take();
            }
            //run task
            try {
                task.run();
            } catch (RuntimeException e) {
                System.out.println("Error running Task: " + e.getMessage());
                e.printStackTrace();
            }
        }

    }

    public void stopThread(){
        running = false;
    }
}
