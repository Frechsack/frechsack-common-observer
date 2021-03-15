package com.frechsack.dev.observer.util;

import com.frechsack.dev.observer.core.MultipleChangeObserver;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.util.Objects;

public class WeakMultipleChangeObserver<E> implements MultipleChangeObserver<E>, Weak<MultipleChangeObserver<E>>
{
    private final WeakReference<MultipleChangeObserver<E>> observerRef;

    public WeakMultipleChangeObserver(MultipleChangeObserver<E> observer)
    {
        Objects.requireNonNull(observer, "A null observer can not be created.");
        this.observerRef = new WeakReference<>(observer);
    }

    @Override
    public void observed(MultipleChangeEvent<? extends E> event)
    {
        MultipleChangeObserver<? super E> observer = observerRef.get();
        if(observer == null) event.getSource().removeObserver(this);
        else observer.observed(event);
    }

    @Override
    public Reference<MultipleChangeObserver<E>> getReferentReference()
    {
        return observerRef;
    }
}
