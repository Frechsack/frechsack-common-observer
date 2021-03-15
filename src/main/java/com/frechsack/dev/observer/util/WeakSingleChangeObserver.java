package com.frechsack.dev.observer.util;

import com.frechsack.dev.observer.core.SingleChangeObserver;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.util.Objects;

public class WeakSingleChangeObserver<E> implements SingleChangeObserver<E>, Weak<SingleChangeObserver<E>>
{
    private final WeakReference<SingleChangeObserver<E>> observerRef;

    public WeakSingleChangeObserver(SingleChangeObserver<E> observer)
    {
        Objects.requireNonNull(observer, "A null observer can not be created.");
        this.observerRef = new WeakReference<>(observer);
    }

    @Override
    public void observed(SingleChangeEvent<? extends E> event)
    {
        SingleChangeObserver<E> observer = observerRef.get();
        if(observer == null) event.getSource().removeObserver(this);
        else observer.observed(event);
    }

    @Override
    public Reference<SingleChangeObserver<E>> getReferentReference()
    {
        return observerRef;
    }
}
