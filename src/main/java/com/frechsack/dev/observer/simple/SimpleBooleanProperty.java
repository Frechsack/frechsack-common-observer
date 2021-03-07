package com.frechsack.dev.observer.simple;

import com.frechsack.dev.observer.core.BooleanExpression;
import com.frechsack.dev.observer.core.BooleanProperty;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

public class SimpleBooleanProperty extends AbstractProperty<Boolean> implements BooleanProperty
{
    private boolean value;
    private transient Reference<BooleanExpression> toExprRef;

    public SimpleBooleanProperty(boolean initialValue, String name, Object source, EventHandler eventHandler)
    {
        super(initialValue,name,source, eventHandler);
        this.value = initialValue;
    }

    public SimpleBooleanProperty(boolean initialValue, String name, Object source)
    {
        super(initialValue,name,source);
        this.value = initialValue;
    }

    @Override
    public BooleanExpression toExpression()
    {
        BooleanExpression toExpr = toExprRef == null ? null : toExprRef.get();
        if(toExpr == null)
        {
            toExpr = new SimpleBooleanWrapper(this);
            toExprRef = new WeakReference<>(toExpr);
        }
        return toExpr;
    }

    @Override
    public Boolean get()
    {
        return value;
    }

    public boolean set(boolean value)
    {
        if (this.value == value) return false;
        this.value = value;
        invalidate();
        change();
        return true;
    }

    @Override
    public boolean set(Boolean value)
    {
        return set(value != null && value);
    }

    @Override
    public Object clone() throws CloneNotSupportedException
    {
        SimpleBooleanProperty property = (SimpleBooleanProperty) super.clone();
        property.toExprRef = null;
        return property;
    }

    @Override
    public boolean getAsBoolean()
    {
        return value;
    }
}
