package com.frechsack.dev.observer.simple;

import com.frechsack.dev.observer.core.BooleanExpression;
import com.frechsack.dev.observer.core.NumberExpression;
import com.frechsack.dev.observer.core.ObservableSingle;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.function.Supplier;

public class SimpleDoubleWrapper extends AbstractNumberWrapper<Double> implements NumberExpression<Double>
{
    public SimpleDoubleWrapper(ObservableSingle<Double> observable)
    {
        super(observable);
    }

    @Override
    public NumberExpression<Double> add(Number value)
    {
        if (saveDouble(value) == 0) return this;
        return new SimpleDoubleExpression(getAsDouble() + saveDouble(value), this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleDoubleWrapper.this.getAsDouble() + saveDouble(value));
            }
        };
    }

    @Override
    public NumberExpression<Double> add(Supplier<? extends Number> value)
    {
        return new SimpleDoubleExpression(getAsDouble() + saveDouble(value), this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleDoubleWrapper.this.getAsDouble() + saveDouble(value));
            }
        };
    }

    @Override
    public NumberExpression<Double> add(ObservableSingle<? extends Number> value)
    {
        return new SimpleDoubleExpression(getAsDouble() + saveDouble(value), List.of(this, value), getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleDoubleWrapper.this.getAsDouble() + saveDouble(value));
            }
        };
    }

    @Override
    public NumberExpression<Double> subtract(Number value)
    {
        if (saveDouble(value) == 0) return this;
        return new SimpleDoubleExpression(getAsDouble() - saveDouble(value), this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleDoubleWrapper.this.getAsDouble() - saveDouble(value));
            }
        };
    }

    @Override
    public NumberExpression<Double> subtract(Supplier<? extends Number> value)
    {
        return new SimpleDoubleExpression(getAsDouble() - saveDouble(value), this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleDoubleWrapper.this.getAsDouble() - saveDouble(value));
            }
        };
    }

    @Override
    public NumberExpression<Double> subtract(ObservableSingle<? extends Number> value)
    {
        return new SimpleDoubleExpression(getAsDouble() - saveDouble(value), List.of(this, value), getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleDoubleWrapper.this.getAsDouble() - saveDouble(value));
            }
        };
    }

    @Override
    public NumberExpression<Double> multiply(Number value)
    {
        if (saveDouble(value) == 0) return new SimpleDoubleExpression(0)
        {
            @Override
            protected void computeValue()
            {
                setValue(0);
            }
        };
        if (saveDouble(value) == 1) return this;
        return new SimpleDoubleExpression(getAsDouble() * saveDouble(value), this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleDoubleWrapper.this.getAsDouble() * saveDouble(value));
            }
        };
    }

    @Override
    public NumberExpression<Double> multiply(Supplier<? extends Number> value)
    {
        return new SimpleDoubleExpression(getAsDouble() * saveDouble(value), this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleDoubleWrapper.this.getAsDouble() * saveDouble(value));
            }
        };
    }

    @Override
    public NumberExpression<Double> multiply(ObservableSingle<? extends Number> value)
    {
        return new SimpleDoubleExpression(getAsDouble() * saveDouble(value), List.of(this, value), getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleDoubleWrapper.this.getAsDouble() * saveDouble(value));
            }
        };
    }

    @Override
    public NumberExpression<Double> divide(Number value)
    {
        if (saveDouble(value) == 0) throw divisionByZeroException();
        if (saveDouble(value) == 1) return this;
        return new SimpleDoubleExpression(getAsDouble() / saveDouble(value), this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleDoubleWrapper.this.getAsDouble() / saveDouble(value));
            }
        };
    }

    @Override
    public NumberExpression<Double> divide(Supplier<? extends Number> value)
    {
        double divisor = saveDouble(value);
        return new SimpleDoubleExpression(divisor == 0 ? 0 : getAsDouble() / divisor, this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                double divisor = saveDouble(value);
                setValue(divisor == 0 ? 0 : SimpleDoubleWrapper.this.getAsDouble() / divisor);
            }
        };
    }

    @Override
    public NumberExpression<Double> divide(ObservableSingle<? extends Number> value)
    {
        double divisor = saveDouble(value);
        return new SimpleDoubleExpression(divisor == 0 ? 0 : getAsDouble() / divisor, List.of(this, value), getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                double divisor = saveDouble(value);
                setValue(divisor == 0 ? 0 : SimpleDoubleWrapper.this.getAsDouble() / divisor);
            }
        };
    }

    @Override
    public NumberExpression<Double> pow(Number value)
    {
        return new SimpleDoubleExpression(Math.pow(getAsDouble(), saveDouble(value)), this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(Math.pow(SimpleDoubleWrapper.this.getAsDouble(), saveDouble(value)));
            }
        };
    }

    @Override
    public NumberExpression<Double> pow(Supplier<? extends Number> value)
    {
        return new SimpleDoubleExpression(Math.pow(getAsDouble(), saveDouble(value)), this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(Math.pow(SimpleDoubleWrapper.this.getAsDouble(), saveDouble(value)));
            }
        };
    }

    @Override
    public NumberExpression<Double> pow(ObservableSingle<? extends Number> value)
    {
        return new SimpleDoubleExpression(Math.pow(getAsDouble(), saveDouble(value)), List.of(this, value), getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(Math.pow(SimpleDoubleWrapper.this.getAsDouble(), saveDouble(value)));
            }
        };
    }

    @Override
    public NumberExpression<Double> modulo(Number value)
    {
        double mod = saveDouble(value);
        if (mod == 0) throw divisionByZeroException();
        if (mod == 1) return this;
        return new SimpleDoubleExpression(getAsDouble() % mod, this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleDoubleWrapper.this.getAsDouble() % mod);
            }
        };
    }

    @Override
    public NumberExpression<Double> modulo(Supplier<? extends Number> value)
    {
        double mod = saveDouble(value);
        return new SimpleDoubleExpression(mod == 0 ? 0 : getAsDouble() % mod, this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                double mod = saveDouble(value);
                setValue(mod == 0 ? 0 : SimpleDoubleWrapper.this.getAsDouble() % mod);
            }
        };
    }

    @Override
    public NumberExpression<Double> modulo(ObservableSingle<? extends Number> value)
    {
        double mod = saveDouble(value);
        return new SimpleDoubleExpression(mod == 0 ? 0 : getAsDouble() % mod, List.of(this, value), getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                double mod = saveDouble(value);
                setValue(mod == 0 ? 0 : SimpleDoubleWrapper.this.getAsDouble() % mod);
            }
        };
    }

    @Override
    public NumberExpression<Double> sqrt()
    {
        return new SimpleDoubleExpression(Math.sqrt(getAsDouble()), this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(Math.sqrt(SimpleDoubleWrapper.this.getAsDouble()));
            }
        };
    }

    @Override
    public NumberExpression<Double> invert()
    {
        return new SimpleDoubleExpression(getAsDouble() * -1, this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleDoubleWrapper.this.getAsDouble() * -1);
            }
        };
    }

    @Override
    public NumberExpression<Double> positive()
    {
        return new SimpleDoubleExpression(getAsDouble() >= 0 ? getAsDouble() : getAsDouble() * -1, this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleDoubleWrapper.this.getAsDouble() >= 0 ? SimpleDoubleWrapper.this.getAsDouble() : SimpleDoubleWrapper.this.getAsDouble() * -1);
            }
        };
    }

    @Override
    public NumberExpression<Double> negative()
    {
        return new SimpleDoubleExpression(getAsDouble() <= 0 ? getAsDouble() : getAsDouble() * -1, this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleDoubleWrapper.this.getAsDouble() <= 0 ? SimpleDoubleWrapper.this.getAsDouble() : SimpleDoubleWrapper.this.getAsDouble() * -1);
            }
        };
    }

    @Override
    public BooleanExpression isBiggerThan(Number value)
    {
        return new SimpleBooleanExpression(getAsDouble() > saveDouble(value), this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleDoubleWrapper.this.getAsDouble() > saveDouble(value));
            }
        };
    }

    @Override
    public BooleanExpression isBiggerThan(Supplier<? extends Number> value)
    {
        return new SimpleBooleanExpression(getAsDouble() > saveDouble(value), this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleDoubleWrapper.this.getAsDouble() > saveDouble(value));
            }
        };
    }

    @Override
    public BooleanExpression isBiggerThan(ObservableSingle<? extends Number> value)
    {
        return new SimpleBooleanExpression(getAsDouble() > saveDouble(value), List.of(this, value), getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleDoubleWrapper.this.getAsDouble() > saveDouble(value));
            }
        };
    }

    @Override
    public BooleanExpression isBiggerThanOrEqualTo(Number value)
    {
        return new SimpleBooleanExpression(getAsDouble() >= saveDouble(value), this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleDoubleWrapper.this.getAsDouble() >= saveDouble(value));
            }
        };
    }

    @Override
    public BooleanExpression isBiggerThanOrEqualTo(Supplier<? extends Number> value)
    {

        return new SimpleBooleanExpression(getAsDouble() >= saveDouble(value), this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleDoubleWrapper.this.getAsDouble() >= saveDouble(value));
            }
        };
    }

    @Override
    public BooleanExpression isBiggerThanOrEqualTo(ObservableSingle<? extends Number> value)
    {
        return new SimpleBooleanExpression(getAsDouble() >= saveDouble(value), List.of(this, value), getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleDoubleWrapper.this.getAsDouble() >= saveDouble(value));
            }
        };
    }

    @Override
    public BooleanExpression isLessThan(Number value)
    {
        return new SimpleBooleanExpression(getAsDouble() < saveDouble(value), this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleDoubleWrapper.this.getAsDouble() < saveDouble(value));
            }
        };
    }

    @Override
    public BooleanExpression isLessThan(Supplier<? extends Number> value)
    {
        return new SimpleBooleanExpression(getAsDouble() < saveDouble(value), this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleDoubleWrapper.this.getAsDouble() < saveDouble(value));
            }
        };
    }

    @Override
    public BooleanExpression isLessThan(ObservableSingle<? extends Number> value)
    {
        return new SimpleBooleanExpression(getAsDouble() < saveDouble(value), List.of(this, value), getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleDoubleWrapper.this.getAsDouble() < saveDouble(value));
            }
        };
    }

    @Override
    public BooleanExpression isLessThanOrEqualTo(Number value)
    {
        return new SimpleBooleanExpression(getAsDouble() <= saveDouble(value), this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleDoubleWrapper.this.getAsDouble() <= saveDouble(value));
            }
        };
    }

    @Override
    public BooleanExpression isLessThanOrEqualTo(Supplier<? extends Number> value)
    {
        return new SimpleBooleanExpression(getAsDouble() <= saveDouble(value), this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleDoubleWrapper.this.getAsDouble() <= saveDouble(value));
            }
        };
    }

    @Override
    public BooleanExpression isLessThanOrEqualTo(ObservableSingle<? extends Number> value)
    {
        return new SimpleBooleanExpression(getAsDouble() <= saveDouble(value), List.of(this, value), getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleDoubleWrapper.this.getAsDouble() <= saveDouble(value));
            }
        };
    }

    @Override
    public BooleanExpression isEqualTo(Number value)
    {
        return new SimpleBooleanExpression(getAsDouble() == saveDouble(value), this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleDoubleWrapper.this.getAsDouble() == saveDouble(value));
            }
        };
    }

    @Override
    public BooleanExpression isEqualTo(Supplier<? extends Number> value)
    {
        return new SimpleBooleanExpression(getAsDouble() == saveDouble(value), this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleDoubleWrapper.this.getAsDouble() == saveDouble(value));
            }
        };
    }

    @Override
    public BooleanExpression isEqualTo(ObservableSingle<? extends Number> value)
    {
        return new SimpleBooleanExpression(getAsDouble() == saveDouble(value), List.of(this, value), getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleDoubleWrapper.this.getAsDouble() == saveDouble(value));
            }
        };
    }

    @Override
    public NumberExpression<Double> mapDouble()
    {
        return this;
    }

    @Override
    public Double get()
    {
        return saveDouble(observable.get());
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
        return get();
    }

    @Override
    public long getAsLong()
    {
        return get().longValue();
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
}
