package com.frechsack.dev.observer.simple;

import com.frechsack.dev.observer.core.Property;

import java.util.Objects;

public class SimpleObjectProperty<E> extends AbstractProperty<E> implements Property<E>
{
    private E value;

    public SimpleObjectProperty(E initialValue, String name, Object source, EventHandler eventHandler)
    {
        super(initialValue, name, source, eventHandler);
        this.value = initialValue;
    }

    public SimpleObjectProperty(E initialValue, String name, Object source)
    {
        super(initialValue, name, source);
        this.value = initialValue;
    }

    @Override
    public E get()
    {
        return value;
    }

    @Override
    public boolean set(E value)
    {
        if (Objects.equals(this.value, value)) return false;
        this.value = value;
        invalidate();
        change();
        return true;
    }
}
