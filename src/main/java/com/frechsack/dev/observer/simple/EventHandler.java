package com.frechsack.dev.observer.simple;

public interface EventHandler
{
    void invalidate(SimpleObservable observable);

    void change(SimpleObservableSingle<?> observable);

    default void onInvalidate(SimpleObservable observable)
    {
        //System.out.println("Invalidate: "+ observable);
    }

    default void onChange(SimpleObservableSingle<?> observable)
    {
       // System.out.println("Change: "+ observable);
    }
}
