package com.frechsack.dev.observer.simple;

import com.frechsack.dev.observer.core.InvalidationObserver;
import com.frechsack.dev.observer.core.Observable;

import java.io.Serializable;
import java.lang.ref.Reference;
import java.lang.ref.SoftReference;
import java.util.Arrays;
import java.util.Objects;

public class SimpleObservable extends SimpleObservables implements Observable, Serializable
{
    private transient InvalidationObserver[] observers;
    private transient Reference<InvalidationObserver.InvalidationEvent> invalidationEventReference;
    private final EventHandler eventHandler;

    public SimpleObservable(EventHandler eventHandler)
    {
        this.eventHandler = eventHandler;
    }

    public SimpleObservable()
    {
        this.eventHandler = null;
    }

    protected void onInvalidation()
    {
    }

    EventHandler getEventHandler()
    {
        return eventHandler;
    }

    /**
     * Marks this Observable as invalid. The user has to check, if this Object should be marked as invalid.
     */
    protected final void invalidate()
    {
        if (isInvalidationObserved()) EventHandlers.getHandler(eventHandler).invalidate(this);
    }

    /**
     * Called by EventHandler
     */
    final void handlerFireInvalidation()
    {
        onInvalidation();
        // Prepare event
        if (observers == null) return;
        if (invalidationEventReference == null || invalidationEventReference.get() == null)
            invalidationEventReference = new SoftReference<>(new InvalidationEvent());
        InvalidationObserver.InvalidationEvent event = invalidationEventReference.get();
        for (InvalidationObserver observer : observers)
            try
            {
                observer.observed(event);
            }
            catch (Exception e)
            {
                logger.log(System.Logger.Level.INFO,"Exception occurred while processing InvalidationObserver.");
            }
    }

    @Override
    public boolean addObserver(InvalidationObserver observer)
    {
        Objects.requireNonNull(observer, "A null observer can not be added.");

        if (observers == null)
        {
            observers = new InvalidationObserver[]{observer};
            return true;
        }
        if (containsElement(observers, observer)) return false;
        observers = insertElement(observers, new InvalidationObserver[observers.length + 1], observer);
        return true;
    }

    @SuppressWarnings("DuplicatedCode")
    @Override
    public boolean removeObserver(InvalidationObserver observer)
    {
        if (observers == null || observer == null) return false;
        int observerIndex = indexOf(observers, observer);
        if (observerIndex == -1) return false;
        if (observers.length == 1)
        {
            observers = null;
            return true;
        }
        observers = removeIndex(observers, new InvalidationObserver[observers.length - 1], observerIndex);
        return true;
    }

    @Override
    public boolean containsObserver(InvalidationObserver observer)
    {
        if (observers == null || observer == null) return false;
        return containsElement(observers, observer);
    }

    protected boolean isInvalidationObserved()
    {
        return observers != null;
    }

    private class InvalidationEvent implements InvalidationObserver.InvalidationEvent
    {
        @Override
        public Observable getSource()
        {
            return SimpleObservable.this;
        }

        @Override
        public boolean equals(Object obj)
        {
            return super.equals(obj);
        }

        @Override
        public String toString()
        {
            return super.toString();
        }

        @Override
        public int hashCode()
        {
            return super.hashCode();
        }
    }

    protected int getObserverCount()
    {
        return observers == null ? 0 : observers.length;
    }

    @Override
    public String toString()
    {
        return toStringTemplate(this);
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SimpleObservable that = (SimpleObservable) o;
        return Arrays.equals(observers, that.observers) && Objects.equals(eventHandler, that.eventHandler);
    }

    @Override
    public int hashCode()
    {
        int result = Objects.hash(eventHandler);
        result = 31 * result + Arrays.hashCode(observers);
        return result;
    }

    @Override
    public Object clone() throws CloneNotSupportedException
    {
        SimpleObservable clone = (SimpleObservable) super.clone();
        clone.observers = null;
        clone.invalidationEventReference = null;
        if(observers != null) for (InvalidationObserver observer : observers)  clone.addObserver(observer);
        return clone;
    }
}
