package com.frechsack.dev.observer.simple;

import com.frechsack.dev.observer.core.ObservableSet;

import java.util.Set;

public class SimpleObservableSet<E> extends SimpleObservableCollection<E> implements ObservableSet<E>
{
    public SimpleObservableSet(Set<E> set)
    {
        super(set);
    }

    @Override
    public String toString()
    {
        return "SimpleObservableCollection{" + "set=" + collection  + '}';
    }
}
