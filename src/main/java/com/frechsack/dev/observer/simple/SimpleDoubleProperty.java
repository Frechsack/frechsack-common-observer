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
        return set(saveDouble(value));
    }

    @Override
    public NumberExpression<Double> toExpression()
    {
        NumberExpression<Double> toExpr = toExprRef == null ? null : toExprRef.get();
        if (toExprRef == null) toExprRef = new WeakReference<>(toExpr = new SimpleDoubleWrapper(this));
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
        return (long) value;
    }

    @Override
    public BigDecimal getAsBigDecimal()
    {
        return BigDecimal.valueOf(getAsDouble());
    }

    @Override
    public BigInteger getAsBigInteger()
    {
        return BigInteger.valueOf(getAsLong());
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
