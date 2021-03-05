package com.frechsack.dev.observer.util;

import com.frechsack.dev.observer.core.MultipleChangeObserver;

import java.lang.ref.WeakReference;

public class WeakMultipleChangeObserver<E> implements MultipleChangeObserver<E>
{
    private final WeakReference<MultipleChangeObserver<E>> observerReference;

    public WeakMultipleChangeObserver(MultipleChangeObserver<E> observer)
    {
        this.observerReference = new WeakReference<>(observer);
    }

    @Override
    public void observed(MultipleChangeEvent<? extends E> event)
    {
        MultipleChangeObserver<E> observer = observerReference.get();
        if(observer == null) event.getSource().removeObserver(this);
        else observer.observed(event);
    }
}
