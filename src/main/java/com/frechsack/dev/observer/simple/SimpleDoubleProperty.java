package com.frechsack.dev.observer.simple;


import com.frechsack.dev.observer.core.NumberExpression;
import com.frechsack.dev.observer.core.NumberProperty;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.math.BigDecimal;
import java.math.BigInteger;

public class SimpleDoubleProperty extends AbstractProperty<Double> implements NumberProperty<Double>
{
    private double value;

    private transient Reference<NumberExpression<Double>> toExprRef;

    public SimpleDoubleProperty(double initialValue, String name, Object source, EventHandler eventHandler)
    {
        super(initialValue, name, source, eventHandler);
        this.value = initialValue;
    }

    public SimpleDoubleProperty(double initialValue, String name, Object source)
    {
        super(initialValue, name, source);
        this.value = initialValue;
    }

    @Override
    public Double get()
    {
        return value;
    }


    public boolean set(double value)
    {
        if (this.value == value) return false;
        this.value = value;
        invalidate();
        change();
        return true;
    }

    @Override
    public boolean set(Double value)
    {
        return set(value == null ? 0 : value);
    }

    @Override
    public NumberExpression<Double> toExpression()
    {
        NumberExpression<Double> toExpr = toExprRef == null ? null : toExprRef.get();
        if (toExprRef == null) toExprRef = new WeakReference<>(toExpr = new SimpleDoubleWrapper(this));
        return toExpr;
    }

    @Override
    public byte getByte()
    {
        return (byte) value;
    }

    @Override
    public short getShort()
    {
        return (short) value;
    }

    @Override
    public int getInt()
    {
        return (int) value;
    }

    @Override
    public double getDouble()
    {
        return value;
    }

    @Override
    public long getLong()
    {
        return (long) value;
    }

    @Override
    public BigDecimal getBigDecimal()
    {
        return BigDecimal.valueOf(getDouble());
    }

    @Override
    public BigInteger getBigInteger()
    {
        return BigInteger.valueOf(getLong());
    }

    @Override
    public float getFloat()
    {
        return (float) value;
    }

    @Override
    public Object clone() throws CloneNotSupportedException
    {
        SimpleDoubleProperty property = (SimpleDoubleProperty) super.clone();
        property.toExprRef = null;
        return property;
    }
}
