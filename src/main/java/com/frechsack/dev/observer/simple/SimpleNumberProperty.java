package com.frechsack.dev.observer.simple;

import com.frechsack.dev.observer.core.NumberExpression;
import com.frechsack.dev.observer.core.NumberProperty;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.math.BigDecimal;
import java.math.BigInteger;

public class SimpleNumberProperty extends AbstractProperty<Number> implements NumberProperty<Number>
{
    private Number value;

    private transient Reference<NumberExpression<Number>> toExprRef;

    public SimpleNumberProperty(Number initialValue, String name, Object source, EventHandler eventHandler)
    {
        super(AbstractExpression.saveNumber(initialValue), name, source, eventHandler);
        this.value = AbstractExpression.saveNumber(initialValue);
    }

    public SimpleNumberProperty(Number initialValue, String name, Object source)
    {
        super(AbstractExpression.saveNumber(initialValue), name, source);
        this.value = AbstractExpression.saveNumber(initialValue);
    }

    @Override
    public NumberExpression<Number> toExpression()
    {
        NumberExpression<Number> toExpr = toExprRef == null ? null : toExprRef.get();
        if (toExpr == null) toExprRef = new WeakReference<>(toExpr = new SimpleNumberWrapper(this));
        return toExpr;
    }

    @Override
    public byte getAsByte()
    {
        return value.byteValue();
    }

    @Override
    public short getAsShort()
    {
        return value.shortValue();
    }

    @Override
    public int getAsInt()
    {
        return value.intValue();
    }

    @Override
    public float getFloat()
    {
        return value.floatValue();
    }

    @Override
    public double getAsDouble()
    {
        return value.doubleValue();
    }

    @Override
    public long getAsLong()
    {
        return value.longValue();
    }

    @Override
    public BigDecimal getAsBigDecimal()
    {
        if (isBigDecimal(value)) return (BigDecimal) value;
        if (isBigInteger(value)) return new BigDecimal((BigInteger) value);
        if (isDouble(value) || isFloat(value)) return BigDecimal.valueOf(value.doubleValue());
        return BigDecimal.valueOf(value.longValue());
    }

    @Override
    public BigInteger getAsBigInteger()
    {
        if (isBigInteger(value)) return (BigInteger) value;
        if (isBigDecimal(value)) return ((BigDecimal) value).toBigInteger();
        return BigInteger.valueOf(value.longValue());
    }

    @Override
    public Number get()
    {
        return value;
    }

    @Override
    public boolean set(Number value)
    {
        if (isEqualToOperation(this.value, value)) return false;
        this.value = value;
        invalidate();
        change();
        return true;
    }

    @Override
    public Object clone() throws CloneNotSupportedException
    {
        SimpleNumberProperty property = (SimpleNumberProperty) super.clone();
        property.toExprRef = null;
        return property;
    }
}
