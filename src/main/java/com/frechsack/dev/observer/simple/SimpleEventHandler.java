package com.frechsack.dev.observer.simple;

import java.util.ArrayDeque;
import java.util.Queue;

public class SimpleEventHandler implements EventHandler
{
    private boolean isInvalidationRunning = false;
    private Queue<SimpleObservable> invalidationQueue;
    private Queue<SimpleObservableSingle<?>> changeQueue;

    @Override
    public void invalidate(SimpleObservable observable)
    {
        // Just run them
        if (isInvalidationRunning)
        {
            if (invalidationQueue == null) invalidationQueue = new ArrayDeque<>();
            invalidationQueue.add(observable);
        }
        else
        {
            // Call validation
            isInvalidationRunning = true;
            observable.handlerFireInvalidation();
            onInvalidate(observable);
            // Process queries in list
            if (invalidationQueue != null) while (!invalidationQueue.isEmpty())
            {
                SimpleObservable obs = invalidationQueue.poll();
                obs.handlerFireInvalidation();
                onInvalidate(obs);
            }
            isInvalidationRunning = false;

            // Check if changes are required
            if (changeQueue != null) while (!changeQueue.isEmpty())
            {
                SimpleObservableSingle<?> obsv = changeQueue.poll();
                obsv.handlerFireChange();
                onChange(obsv);
            }
        }
    }

    @Override
    public void change(SimpleObservableSingle<?> observable)
    {
        if (isInvalidationRunning)
        {
            // Wait until validation is completed.
            if (changeQueue == null) changeQueue = new ArrayDeque<>();
            changeQueue.add(observable);
        }
        else
        {
            observable.handlerFireChange();
            onChange(observable);
        }
    }
}
