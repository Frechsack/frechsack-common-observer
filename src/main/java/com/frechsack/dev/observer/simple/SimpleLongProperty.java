package com.frechsack.dev.observer.simple;


import com.frechsack.dev.observer.core.NumberExpression;
import com.frechsack.dev.observer.core.NumberProperty;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.math.BigDecimal;
import java.math.BigInteger;

public class SimpleLongProperty extends AbstractProperty<Long> implements NumberProperty<Long>
{
    private long value;
    private transient Reference<NumberExpression<Long>> toExprRef;

    public SimpleLongProperty(long initialValue, String name, Object source, EventHandler eventHandler)
    {
        super(initialValue, name, source, eventHandler);
        this.value = initialValue;
    }

    public SimpleLongProperty(long initialValue, String name, Object source)
    {
        super(initialValue, name, source);
        this.value = initialValue;
    }

    @Override
    public Long get()
    {
        return value;
    }


    public boolean set(long value)
    {
        if (this.value == value) return false;
        this.value = value;
        invalidate();
        change();
        return true;
    }

    @Override
    public boolean set(Long value)
    {
        return set(saveLong(value));
    }

    @Override
    public NumberExpression<Long> toExpression()
    {
        NumberExpression<Long> toExpr = toExprRef == null ? null : toExprRef.get();
        if (toExpr == null) toExprRef = new WeakReference<>(toExpr = new SimpleLongWrapper(this));
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
        return (int) value;
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
        return BigInteger.valueOf(getAsInt());
    }

    @Override
    public float getFloat()
    {
        return value;
    }

    @Override
    public Object clone() throws CloneNotSupportedException
    {
        SimpleLongProperty property = (SimpleLongProperty) super.clone();
        property.toExprRef = null;
        return property;
    }
}
