package com.frechsack.dev.observer.simple;

import com.frechsack.dev.observer.core.ReadonlyProperty;

import java.util.Objects;

public class SimpleReadonlyProperty<E> implements ReadonlyProperty<E>
{
    private final E value;
    private final String name;
    private final Object source;

    public SimpleReadonlyProperty(E value, String name, Object source)
    {
        this.value = value;
        this.name = name;
        this.source = source;
    }

    @Override
    public String getName()
    {
        return name;
    }

    @Override
    public Object getSource()
    {
        return source;
    }

    @Override
    public E get()
    {
        return value;
    }

    @Override
    public String toString()
    {
        return "SimpleReadonlyProperty{" + "value=" + value + ", name='" + name + '\'' + ", source=" + source + '}';
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SimpleReadonlyProperty<?> that = (SimpleReadonlyProperty<?>) o;
        return Objects.equals(value, that.value) && Objects.equals(name, that.name) && Objects.equals(source, that.source);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(value, name, source);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException
    {
        // This Object is immutable - just return it.
        return this;
    }
}
