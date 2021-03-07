package com.frechsack.dev.observer.simple;

import com.frechsack.dev.observer.core.BooleanExpression;
import com.frechsack.dev.observer.core.NumberExpression;
import com.frechsack.dev.observer.core.Observable;
import com.frechsack.dev.observer.core.ObservableSingle;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.function.Supplier;

public abstract class SimpleNumberExpression extends AbstractNumberExpression<Number> implements NumberExpression<Number>
{
    private Number value;

    public SimpleNumberExpression(Number initialValue, Observable parent, EventHandler eventHandler)
    {
        super(saveNumber(initialValue), parent, eventHandler);
        setValue(saveNumber(initialValue));
    }

    public SimpleNumberExpression(Number initialValue, List<Observable> parentList, EventHandler eventHandler)
    {
        super(saveNumber(initialValue), parentList, eventHandler);
        setValue(saveNumber(initialValue));
    }

    public SimpleNumberExpression(Number initialValue, Observable parent)
    {
        super(saveNumber(initialValue), parent);
        setValue(saveNumber(initialValue));
    }

    public SimpleNumberExpression(Number initialValue, List<Observable> parentList)
    {
        super(saveNumber(initialValue), parentList);
        setValue(saveNumber(initialValue));
    }

    public SimpleNumberExpression(Number initialValue)
    {
        super(saveNumber(initialValue));
        setValue(saveNumber(initialValue));
    }

    public void setValue(Number value)
    {
        this.value = value == null ? 0 : value;
    }

    @Override
    protected Number getValue()
    {
        return value;
    }

    @Override
    public NumberExpression<Number> add(Number value)
    {
        if (isZero(value)) return this;
        return new SimpleNumberExpression(addOperation(get(), value), this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(addOperation(SimpleNumberExpression.this.get(), value));
            }
        };
    }

    @Override
    public NumberExpression<Number> add(Supplier<? extends Number> value)
    {
        return new SimpleNumberExpression(addOperation(get(), value.get()), this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(addOperation(SimpleNumberExpression.this.get(), value.get()));
            }
        };
    }

    @Override
    public NumberExpression<Number> add(ObservableSingle<? extends Number> value)
    {
        return new SimpleNumberExpression(addOperation(get(), value.get()), List.of(this, value), getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(addOperation(SimpleNumberExpression.this.get(), value.get()));
            }
        };
    }

    @Override
    public NumberExpression<Number> subtract(Number value)
    {
        if (isZero(value)) return this;
        return new SimpleNumberExpression(subtractOperation(get(), value), this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(subtractOperation(SimpleNumberExpression.this.get(), value));
            }
        };
    }

    @Override
    public NumberExpression<Number> subtract(Supplier<? extends Number> value)
    {
        return new SimpleNumberExpression(subtractOperation(get(), value.get()), this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(subtractOperation(SimpleNumberExpression.this.get(), value.get()));
            }
        };
    }

    @Override
    public NumberExpression<Number> subtract(ObservableSingle<? extends Number> value)
    {
        return new SimpleNumberExpression(subtractOperation(get(), value.get()), List.of(this, value), getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(subtractOperation(SimpleNumberExpression.this.get(), value.get()));
            }
        };
    }

    @Override
    public NumberExpression<Number> multiply(Number value)
    {
        if (isZero(value)) return constantExpression(saveNumber(value));
        return new SimpleNumberExpression(multiplyOperation(get(), value), this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(multiplyOperation(SimpleNumberExpression.this.get(), value));
            }
        };
    }

    @Override
    public NumberExpression<Number> multiply(Supplier<? extends Number> value)
    {
        return new SimpleNumberExpression(multiplyOperation(get(), value.get()), this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(multiplyOperation(SimpleNumberExpression.this.get(), value.get()));
            }
        };
    }

    @Override
    public NumberExpression<Number> multiply(ObservableSingle<? extends Number> value)
    {
        return new SimpleNumberExpression(multiplyOperation(get(), value.get()), List.of(this, value), getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(multiplyOperation(SimpleNumberExpression.this.get(), value.get()));
            }
        };
    }

    @Override
    public NumberExpression<Number> divide(Number value)
    {
        if (isZero(value)) return new SimpleNumberExpression(0)
        {
            @Override
            protected void computeValue()
            {
                setValue(0);
            }
        };
        return new SimpleNumberExpression(divideOperation(get(), value), this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(divideOperation(SimpleNumberExpression.this.get(), value));
            }
        };
    }

    @Override
    public NumberExpression<Number> divide(Supplier<? extends Number> value)
    {
        return new SimpleNumberExpression(divideOperation(get(), value.get()), this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(divideOperation(SimpleNumberExpression.this.get(), value.get()));
            }
        };
    }

    @Override
    public NumberExpression<Number> divide(ObservableSingle<? extends Number> value)
    {
        return new SimpleNumberExpression(divideOperation(get(), value.get()), List.of(this, value), getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(divideOperation(SimpleNumberExpression.this.get(), value.get()));
            }
        };
    }

    @Override
    public NumberExpression<Number> pow(Number value)
    {
        return new SimpleNumberExpression(powOperation(get(), value), this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(powOperation(SimpleNumberExpression.this.get(), value));
            }
        };
    }

    @Override
    public NumberExpression<Number> pow(Supplier<? extends Number> value)
    {
        return new SimpleNumberExpression(powOperation(get(), value.get()), this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(powOperation(SimpleNumberExpression.this.get(), value.get()));
            }
        };
    }

    @Override
    public NumberExpression<Number> pow(ObservableSingle<? extends Number> value)
    {
        return new SimpleNumberExpression(powOperation(get(), value.get()), List.of(this, value), getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(powOperation(SimpleNumberExpression.this.get(), value.get()));
            }
        };
    }

    @Override
    public NumberExpression<Number> modulo(Number value)
    {
        if (isZero(value)) return new SimpleNumberExpression(0)
        {
            @Override
            protected void computeValue()
            {
                setValue(0);
            }
        };
        return new SimpleNumberExpression(moduloOperation(get(), value), this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(moduloOperation(SimpleNumberExpression.this.get(), value));
            }
        };
    }

    @Override
    public NumberExpression<Number> modulo(Supplier<? extends Number> value)
    {
        return new SimpleNumberExpression(moduloOperation(get(), value.get()), this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(moduloOperation(SimpleNumberExpression.this.get(), value.get()));
            }
        };
    }

    @Override
    public NumberExpression<Number> modulo(ObservableSingle<? extends Number> value)
    {
        return new SimpleNumberExpression(moduloOperation(get(), value.get()), this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(moduloOperation(SimpleNumberExpression.this.get(), value.get()));
            }
        };
    }

    @Override
    public NumberExpression<Number> sqrt()
    {
        return new SimpleNumberExpression(sqrtOperation(get()), this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(sqrtOperation(SimpleNumberExpression.this.get()));
            }
        };
    }

    @Override
    public NumberExpression<Number> invert()
    {
        return new SimpleNumberExpression(invertOperation(get()), this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(invertOperation(SimpleNumberExpression.this.get()));
            }
        };
    }

    @Override
    public NumberExpression<Number> positive()
    {
        return new SimpleNumberExpression(positiveOperation(get()), this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(positiveOperation(SimpleNumberExpression.this.get()));
            }
        };
    }

    @Override
    public NumberExpression<Number> negative()
    {
        return new SimpleNumberExpression(negativeOperation(get()), this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(negativeOperation(SimpleNumberExpression.this.get()));
            }
        };
    }

    @Override
    public BooleanExpression isBiggerThan(Number value)
    {
        return new SimpleBooleanExpression(isBiggerThanOperation(get(), value), this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(isBiggerThanOperation(SimpleNumberExpression.this.get(), value));
            }
        };
    }

    @Override
    public BooleanExpression isBiggerThan(Supplier<? extends Number> value)
    {
        return new SimpleBooleanExpression(isBiggerThanOperation(get(), value.get()), this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(isBiggerThanOperation(SimpleNumberExpression.this.get(), value.get()));
            }
        };
    }

    @Override
    public BooleanExpression isBiggerThan(ObservableSingle<? extends Number> value)
    {
        return new SimpleBooleanExpression(isBiggerThanOperation(get(), value.get()), List.of(this, value), getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(isBiggerThanOperation(SimpleNumberExpression.this.get(), value.get()));
            }
        };
    }

    @Override
    public BooleanExpression isBiggerThanOrEqualTo(Number value)
    {
        return new SimpleBooleanExpression(isBiggerThanOrEqualToOperation(get(), value), this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(isBiggerThanOrEqualToOperation(SimpleNumberExpression.this.get(), value));
            }
        };
    }

    @Override
    public BooleanExpression isBiggerThanOrEqualTo(Supplier<? extends Number> value)
    {
        return new SimpleBooleanExpression(isBiggerThanOrEqualToOperation(get(), value.get()), this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(isBiggerThanOrEqualToOperation(SimpleNumberExpression.this.get(), value.get()));
            }
        };
    }

    @Override
    public BooleanExpression isBiggerThanOrEqualTo(ObservableSingle<? extends Number> value)
    {
        return new SimpleBooleanExpression(isBiggerThanOrEqualToOperation(get(), value.get()), List.of(this, value), getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(isBiggerThanOrEqualToOperation(SimpleNumberExpression.this.get(), value.get()));
            }
        };
    }

    @Override
    public BooleanExpression isLessThan(Number value)
    {
        return new SimpleBooleanExpression(isLessThanOperation(get(), value), this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(isLessThanOperation(SimpleNumberExpression.this.get(), value));
            }
        };
    }

    @Override
    public BooleanExpression isLessThan(Supplier<? extends Number> value)
    {
        return new SimpleBooleanExpression(isLessThanOperation(get(), value.get()), this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(isLessThanOperation(SimpleNumberExpression.this.get(), value.get()));
            }
        };
    }

    @Override
    public BooleanExpression isLessThan(ObservableSingle<? extends Number> value)
    {
        return new SimpleBooleanExpression(isLessThanOperation(get(), value.get()), List.of(this, value), getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(isLessThanOperation(SimpleNumberExpression.this.get(), value.get()));
            }
        };
    }

    @Override
    public BooleanExpression isLessThanOrEqualTo(Number value)
    {
        return new SimpleBooleanExpression(isLessThanOrEqualToOperation(get(), value), this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(isLessThanOrEqualToOperation(SimpleNumberExpression.this.get(), value));
            }
        };
    }

    @Override
    public BooleanExpression isLessThanOrEqualTo(Supplier<? extends Number> value)
    {
        return new SimpleBooleanExpression(isLessThanOrEqualToOperation(get(), value.get()), this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(isLessThanOrEqualToOperation(SimpleNumberExpression.this.get(), value.get()));
            }
        };
    }

    @Override
    public BooleanExpression isLessThanOrEqualTo(ObservableSingle<? extends Number> value)
    {
        return new SimpleBooleanExpression(isLessThanOrEqualToOperation(get(), value.get()), List.of(this, value), getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(isLessThanOrEqualToOperation(SimpleNumberExpression.this.get(), value.get()));
            }
        };
    }

    @Override
    public BooleanExpression isEqualTo(Number value)
    {
        return new SimpleBooleanExpression(isEqualToOperation(get(), value), this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(isEqualToOperation(SimpleNumberExpression.this.get(), value));
            }
        };
    }

    @Override
    public BooleanExpression isEqualTo(Supplier<? extends Number> value)
    {
        return new SimpleBooleanExpression(isEqualToOperation(get(), value.get()), this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(isEqualToOperation(SimpleNumberExpression.this.get(), value.get()));
            }
        };
    }

    @Override
    public BooleanExpression isEqualTo(ObservableSingle<? extends Number> value)
    {
        return new SimpleBooleanExpression(isEqualToOperation(get(), value.get()), List.of(this, value), getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(isEqualToOperation(SimpleNumberExpression.this.get(), value.get()));
            }
        };
    }

    @Override
    public byte getAsByte()
    {
        return get().byteValue();
    }

    @Override
    public short getAsShort()
    {
        return get().shortValue();
    }

    @Override
    public int getAsInt()
    {
        return get().intValue();
    }

    @Override
    public float getFloat()
    {
        return get().floatValue();
    }

    @Override
    public double getAsDouble()
    {
        return get().doubleValue();
    }

    @Override
    public long getAsLong()
    {
        return get().longValue();
    }

    @Override
    public BigDecimal getAsBigDecimal()
    {
        validate();
        if (isBigDecimal(value)) return (BigDecimal) value;
        if (isBigInteger(value)) return new BigDecimal((BigInteger) value);
        if (isLong(value)) return BigDecimal.valueOf(value.longValue());
        return BigDecimal.valueOf(value.doubleValue());
    }

    @Override
    public BigInteger getAsBigInteger()
    {
        validate();
        if (isBigDecimal(value)) return ((BigDecimal) value).toBigInteger();
        if (isBigInteger(value)) return (BigInteger) value;
        return BigInteger.valueOf(value.longValue());
    }
}
