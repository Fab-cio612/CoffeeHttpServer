package com.fab_cio612.CoffeeHttpServer.requests;

public interface Handler {
    
    //returns path (i.e. http://www.example.com/ "foo/bar" ) this handler responds to
    public String getHandlerPath();
    
    //Functions for each Http method
    public void get();

    public void head();

    public void post();

    public void put();
    
    public void delete();

    public void connect();

    public void options();

    public void trace();

    public void patch();
}
