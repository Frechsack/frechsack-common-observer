package com.frechsack.dev.observer.util;

import com.frechsack.dev.observer.core.SingleChangeObserver;

import java.lang.ref.WeakReference;

public class WeakSingleChangeObserver<E> implements SingleChangeObserver<E>
{
    private final WeakReference<SingleChangeObserver<E>> observerReference;

    public WeakSingleChangeObserver(SingleChangeObserver<E> observer)
    {
        this.observerReference = new WeakReference<>(observer);
    }

    @Override
    public void observed(SingleChangeEvent<? extends E> event)
    {
        SingleChangeObserver<E> observer = observerReference.get();
        if(observer == null) event.getSource().removeObserver(this);
        else observer.observed(event);
    }
}
