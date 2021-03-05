package com.frechsack.dev.observer.simple;

import com.frechsack.dev.observer.core.ObservableSingle;

public class SimpleObjectWrapper<E> extends AbstractWrapper<E>
{
    public SimpleObjectWrapper(ObservableSingle<E> observable)
    {
        super(observable);
    }
}
