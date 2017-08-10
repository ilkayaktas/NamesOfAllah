package com.mobss.islamic.namesofAllah.events;


/**
 * Created by ilkay on 04/04/2017.
 */

public class FavorySelectedEvent<T> {
    public T data;
    
    public FavorySelectedEvent(T data) {
        this.data = data;
    }
}
