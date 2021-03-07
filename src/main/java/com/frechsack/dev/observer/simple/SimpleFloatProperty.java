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
