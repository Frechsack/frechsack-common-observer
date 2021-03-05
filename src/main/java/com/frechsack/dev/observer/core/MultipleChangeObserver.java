package com.frechsack.dev.observer.core;

import com.frechsack.dev.observer.util.Cursor;

import java.util.Collection;

public interface MultipleChangeObserver<E>
{
    void observed(MultipleChangeEvent<? extends E> event);

    interface MultipleChangeEvent<E> extends InvalidationObserver.InvalidationEvent, Cursor<E>
    {
        Collection<E> getRemovedElements();

        Collection<E> getAddedElements();

        boolean isAdded();

        boolean isRemoved();

        @Override
        ObservableMultiple<E> getSource();
    }
}
