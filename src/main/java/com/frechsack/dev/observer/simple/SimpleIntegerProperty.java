package com.frechsack.dev.observer.simple;


import com.frechsack.dev.observer.core.NumberExpression;
import com.frechsack.dev.observer.core.NumberProperty;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.math.BigDecimal;
import java.math.BigInteger;

public class SimpleIntegerProperty extends AbstractProperty<Integer> implements NumberProperty<Integer>
{
    private int value;
    private transient Reference<NumberExpression<Integer>> toExprRef;

    public SimpleIntegerProperty(int initialValue, String name, Object source, EventHandler eventHandler)
    {
        super(initialValue, name, source, eventHandler);
    }

    public SimpleIntegerProperty(int initialValue, String name, Object source)
    {
        super(initialValue, name, source);
        this.value = initialValue;
    }

    @Override
    public Integer get()
    {
        return value;
    }


    public boolean set(int value)
    {
        if (this.value == value) return false;
        this.value = value;
        invalidate();
        change();
        return true;
    }

    @Override
    public boolean set(Integer value)
    {
        return set(saveInt(value));
    }

    @Override
    public NumberExpression<Integer> toExpression()
    {
        NumberExpression<Integer> toExpr = toExprRef == null ? null : toExprRef.get();
        if (toExpr == null) toExprRef = new WeakReference<>(toExpr = new SimpleIntegerWrapper(this));
        return toExpr;
    }

    @Override
    public byte getAsByte()
    {
        return (byte) value;
    }

    @Override
    public short getAsShort()
    {
        return (short) value;
    }

    @Override
    public int getAsInt()
    {
        return value;
    }

    @Override
    public double getAsDouble()
    {
        return value;
    }

    @Override
    public long getAsLong()
    {
        return value;
    }

    @Override
    public BigDecimal getAsBigDecimal()
    {
        return BigDecimal.valueOf(getAsLong());
    }

    @Override
    public BigInteger getAsBigInteger()
    {
        return BigInteger.valueOf(getAsLong());
    }

    @Override
    public float getFloat()
    {
        return value;
    }

    @Override
    public Object clone() throws CloneNotSupportedException
    {
        SimpleIntegerProperty property = (SimpleIntegerProperty) super.clone();
        property.toExprRef = null;
        return property;
    }
}
