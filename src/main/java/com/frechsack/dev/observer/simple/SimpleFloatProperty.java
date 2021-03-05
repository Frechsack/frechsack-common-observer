package com.frechsack.dev.observer.simple;

import com.frechsack.dev.observer.core.NumberExpression;
import com.frechsack.dev.observer.core.NumberProperty;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.math.BigDecimal;
import java.math.BigInteger;

public class SimpleFloatProperty extends AbstractProperty<Float> implements NumberProperty<Float>
{
    private float value;

    private transient Reference<NumberExpression<Float>> toExprRef;

    public SimpleFloatProperty(float initialValue, String name, Object source,EventHandler eventHandler) {
        super(initialValue,name,source ,eventHandler);
        this.value = initialValue;
    }
    public SimpleFloatProperty(float initialValue, String name, Object source) {
        super(initialValue,name,source);
        this.value = initialValue;
    }
    @Override
    public NumberExpression<Float> toExpression()
    {
        NumberExpression<Float> toExpr = toExprRef == null ? null : toExprRef.get();
        if(toExpr == null) toExprRef = new WeakReference<>(toExpr = new SimpleFloatWrapper(this));
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
        return value;
    }

    @Override
    public Float get()
    {
        return value;
    }

    @Override
    public boolean set(Float value)
    {
        return set(value == null ? 0 : value);
    }

    public boolean set(float value)
    {
        if(this.value == value) return false;
        this.value = value;
        invalidate();
        change();
        return true;
    }

    @Override
    public Object clone() throws CloneNotSupportedException
    {
        SimpleFloatProperty property = (SimpleFloatProperty) super.clone();
        property.toExprRef = null;
        return property;
    }
}
