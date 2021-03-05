package com.frechsack.dev.observer.util;

import com.frechsack.dev.observer.core.InvalidationObserver;
import com.frechsack.dev.observer.core.MultipleChangeObserver;

import java.lang.ref.WeakReference;

public class WeakInvalidationObserver implements InvalidationObserver
{
    private final WeakReference<InvalidationObserver> observerReference;

    public WeakInvalidationObserver(InvalidationObserver observer)
    {
        this.observerReference = new WeakReference<>(observer);
    }

    @Override
    public void observed(InvalidationEvent event)
    {
        InvalidationObserver observer = observerReference.get();
        if(observer == null) event.getSource().removeObserver(this);
        else observer.observed(event);
    }
}
