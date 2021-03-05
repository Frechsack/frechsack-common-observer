package com.frechsack.dev.observer.simple;

import com.frechsack.dev.observer.core.Observable;

import java.util.List;

public abstract class SimpleObjectExpression<E> extends AbstractExpression<E>
{
    private E value;

    public SimpleObjectExpression(E initialValue, Observable parent, EventHandler eventHandler)
    {
        super(initialValue, parent, eventHandler);
        this.value = initialValue;
    }

    public SimpleObjectExpression(E initialValue, List<Observable> parentList, EventHandler eventHandler)
    {
        super(initialValue, parentList, eventHandler);
        this.value = initialValue;
    }

    public SimpleObjectExpression(E initialValue, Observable parent)
    {
        super(initialValue, parent);
        this.value = initialValue;
    }

    public SimpleObjectExpression(E initialValue, List<Observable> parentList)
    {
        super(initialValue, parentList);
        this.value = initialValue;
    }

    public SimpleObjectExpression(E initialValue)
    {
        super(initialValue);
        this.value = initialValue;
    }

    @Override
    protected E getValue()
    {
        return value;
    }

    protected final void setValue(E value)
    {
        this.value = value;
    }

}
