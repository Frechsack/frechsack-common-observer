package com.frechsack.dev.observer.simple;

import com.frechsack.dev.observer.core.Property;

import java.util.Objects;

public abstract class AbstractProperty<E> extends SimpleObservableSingle<E> implements Property<E>, Cloneable
{
    private final String name;
    private final Object source;

    public AbstractProperty(E initial,String name, Object source) {
        super(initial);
        this.name = name;
        this.source = source;
    }

    public AbstractProperty(E initial,String name, Object source, EventHandler eventHandler) {
        super(initial,eventHandler);
        this.name = name;
        this.source = source;
    }

    @Override
    public final String getName()
    {
        return name;
    }

    @Override
    public final Object getSource()
    {
        return source;
    }

    @Override
    public final boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        AbstractProperty<?> that = (AbstractProperty<?>) o;
        return Objects.equals(name, that.name) && Objects.equals(source, that.source) && Objects.equals(get(),that.get());
    }

    @Override
    public final int hashCode()
    {
        return Objects.hash(super.hashCode(), name, source,get());
    }

    @Override
    public final String toString()
    {
        return toStringTemplate(this);
    }
}
