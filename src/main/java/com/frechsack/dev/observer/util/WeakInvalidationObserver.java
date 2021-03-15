package com.frechsack.dev.observer.util;

import com.frechsack.dev.observer.core.InvalidationObserver;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.util.Objects;

public class WeakInvalidationObserver implements InvalidationObserver, Weak<InvalidationObserver>
{
    private final WeakReference<InvalidationObserver> observerRef;

    public WeakInvalidationObserver(InvalidationObserver observer)
    {
        Objects.requireNonNull(observer, "A null observer can not be created.");
        this.observerRef = new WeakReference<>(observer);
    }

    @Override
    public void observed(InvalidationEvent event)
    {
        InvalidationObserver observer = observerRef.get();
        if(observer == null) event.getSource().removeObserver(this);
        else observer.observed(event);
    }

    @Override
    public Reference<InvalidationObserver> getReferentReference()
    {
        return observerRef;
    }
}
