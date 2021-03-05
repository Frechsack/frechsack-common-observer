package com.frechsack.dev.observer.simple;

public class EventHandlers
{
    private static SimpleEventHandler defaultEventHandler;

    protected static EventHandler getHandler(EventHandler eventHandler)
    {
        if (eventHandler != null) return eventHandler;
        if (defaultEventHandler == null) defaultEventHandler = new SimpleEventHandler();
        return defaultEventHandler;
    }

}
