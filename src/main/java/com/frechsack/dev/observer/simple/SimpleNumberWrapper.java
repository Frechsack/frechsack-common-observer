package com.frechsack.dev.observer.simple;

import com.frechsack.dev.observer.core.BooleanExpression;
import com.frechsack.dev.observer.core.NumberExpression;
import com.frechsack.dev.observer.core.ObservableSingle;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.function.Supplier;

public class SimpleNumberWrapper extends AbstractNumberWrapper<Number> implements NumberExpression<Number>
{
    public SimpleNumberWrapper(ObservableSingle<Number> observable)
    {
        super(observable);
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
                setValue(addOperation(SimpleNumberWrapper.this.get(), value));
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
                setValue(addOperation(SimpleNumberWrapper.this.get(), value.get()));
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
                setValue(addOperation(SimpleNumberWrapper.this.get(), value.get()));
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
                setValue(subtractOperation(SimpleNumberWrapper.this.get(), value));
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
                setValue(subtractOperation(SimpleNumberWrapper.this.get(), value.get()));
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
                setValue(subtractOperation(SimpleNumberWrapper.this.get(), value.get()));
            }
        };
    }

    @Override
    public NumberExpression<Number> multiply(Number value)
    {
        if (isZero(value)) return new SimpleNumberExpression(0)
        {
            @Override
            protected void computeValue()
            {
                setValue(0);
            }
        };
        return new SimpleNumberExpression(multiplyOperation(get(), value), this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(multiplyOperation(SimpleNumberWrapper.this.get(), value));
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
                setValue(multiplyOperation(SimpleNumberWrapper.this.get(), value.get()));
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
                setValue(multiplyOperation(SimpleNumberWrapper.this.get(), value.get()));
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
                setValue(divideOperation(SimpleNumberWrapper.this.get(), value));
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
                setValue(divideOperation(SimpleNumberWrapper.this.get(), value.get()));
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
                setValue(divideOperation(SimpleNumberWrapper.this.get(), value.get()));
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
                setValue(powOperation(SimpleNumberWrapper.this.get(), value));
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
                setValue(powOperation(SimpleNumberWrapper.this.get(), value.get()));
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
                setValue(powOperation(SimpleNumberWrapper.this.get(), value.get()));
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
                setValue(moduloOperation(SimpleNumberWrapper.this.get(), value));
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
                setValue(moduloOperation(SimpleNumberWrapper.this.get(), value.get()));
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
                setValue(moduloOperation(SimpleNumberWrapper.this.get(), value.get()));
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
                setValue(sqrtOperation(SimpleNumberWrapper.this.get()));
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
                setValue(invertOperation(SimpleNumberWrapper.this.get()));
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
                setValue(positiveOperation(SimpleNumberWrapper.this.get()));
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
                setValue(negativeOperation(SimpleNumberWrapper.this.get()));
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
                setValue(isBiggerThanOperation(SimpleNumberWrapper.this.get(), value));
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
                setValue(isBiggerThanOperation(SimpleNumberWrapper.this.get(), value.get()));
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
                setValue(isBiggerThanOperation(SimpleNumberWrapper.this.get(), value.get()));
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
                setValue(isBiggerThanOrEqualToOperation(SimpleNumberWrapper.this.get(), value));
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
                setValue(isBiggerThanOrEqualToOperation(SimpleNumberWrapper.this.get(), value.get()));
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
                setValue(isBiggerThanOrEqualToOperation(SimpleNumberWrapper.this.get(), value.get()));
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
                setValue(isLessThanOperation(SimpleNumberWrapper.this.get(), value));
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
                setValue(isLessThanOperation(SimpleNumberWrapper.this.get(), value.get()));
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
                setValue(isLessThanOperation(SimpleNumberWrapper.this.get(), value.get()));
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
                setValue(isLessThanOrEqualToOperation(SimpleNumberWrapper.this.get(), value));
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
                setValue(isLessThanOrEqualToOperation(SimpleNumberWrapper.this.get(), value.get()));
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
                setValue(isLessThanOrEqualToOperation(SimpleNumberWrapper.this.get(), value.get()));
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
                setValue(isEqualToOperation(SimpleNumberWrapper.this.get(), value));
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
                setValue(isEqualToOperation(SimpleNumberWrapper.this.get(), value.get()));
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
                setValue(isEqualToOperation(SimpleNumberWrapper.this.get(), value.get()));
            }
        };
    }

    @Override
    public Number get()
    {
        return saveNumber(super.get());
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
        Number value = get();
        if (isBigDecimal(value)) return (BigDecimal) value;
        if (isBigInteger(value)) return new BigDecimal((BigInteger) value);
        if (isLong(value)) return BigDecimal.valueOf(value.longValue());
        return BigDecimal.valueOf(value.doubleValue());
    }

    @Override
    public BigInteger getAsBigInteger()
    {
        Number value = get();
        if (isBigDecimal(value)) return ((BigDecimal) value).toBigInteger();
        if (isBigInteger(value)) return (BigInteger) value;
        return BigInteger.valueOf(value.longValue());
    }
}
