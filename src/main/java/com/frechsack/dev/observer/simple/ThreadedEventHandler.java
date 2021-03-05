package com.frechsack.dev.observer.simple;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadedEventHandler implements EventHandler
{
    private ExecutorService exec;
    private final Runnable invalidationTask = () ->
    {

        setInvalidationRunning(true);
        SimpleObservable observable;
        while (!getInvalidationQueue().isEmpty())
        {
            observable = getInvalidationQueue().poll();
            if (observable != null) observable.handlerFireInvalidation();
        }
        setInvalidationRunning(false);
        // Execute any updates that got collected while invalidation.
        SimpleObservableSingle<?> valueObservable;
        while (!getChangeQueue().isEmpty())
        {
            valueObservable = getChangeQueue().poll();
            if (valueObservable != null) valueObservable.handlerFireChange();
        }
    };
    private final Runnable changeTask = () ->
    {

        SimpleObservableSingle<?> valueObservable;
        while (!getChangeQueue().isEmpty())
        {
            if (isInvalidationRunning()) return;
            valueObservable = getChangeQueue().poll();
            if (valueObservable != null) valueObservable.handlerFireChange();
        }
    };
    private boolean isInvalidationRunning = false;
    private final Queue<SimpleObservable> invalidationQueue = new ArrayDeque<>();
    private final Queue<SimpleObservableSingle<?>> changeQueue = new ArrayDeque<>();


    private synchronized Queue<SimpleObservable> getInvalidationQueue()
    {
        return invalidationQueue;
    }

    private synchronized Queue<SimpleObservableSingle<?>> getChangeQueue()
    {
        return changeQueue;
    }

    private synchronized void setExec(ExecutorService exec)
    {
        this.exec = exec;
    }

    private synchronized ExecutorService getExec()
    {
        return exec;
    }

    private synchronized boolean isInvalidationRunning()
    {
        return isInvalidationRunning;
    }

    private synchronized void setInvalidationRunning(boolean invalidationRunning)
    {
        isInvalidationRunning = invalidationRunning;
    }

    private Runnable getInvalidationTask()
    {
        return invalidationTask;
    }

    private synchronized Runnable getChangeTask()
    {
        return changeTask;
    }

    private void runInvalidation()
    {
        if (isInvalidationRunning()) return;
        if (getExec() == null) setExec(Executors.newCachedThreadPool());
        // Submit an invalidation Task
        getExec().execute(getInvalidationTask());
    }

    private void runUpdate()
    {
        if (isInvalidationRunning()) return;
        if (getExec() == null) setExec(Executors.newCachedThreadPool());
        // Submit an update Task
        getExec().execute(getChangeTask());
    }

    @Override
    public void invalidate(SimpleObservable observable)
    {
        getInvalidationQueue().offer(observable);
        runInvalidation();
    }

    @Override
    public void change(SimpleObservableSingle<?> observable)
    {
        getChangeQueue().offer(observable);
        runUpdate();
    }

    public void shutdown()
    {
        try
        {
            if (getExec() != null) getExec().shutdown();
        }
        catch (Exception e)
        {
        }

    }
}
