package com.frechsack.dev.observer.simple;

import com.frechsack.dev.observer.core.BooleanExpression;
import com.frechsack.dev.observer.core.NumberExpression;
import com.frechsack.dev.observer.core.Observable;
import com.frechsack.dev.observer.core.ObservableSingle;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.function.Supplier;

public abstract class SimpleDoubleExpression extends AbstractNumberExpression<Double> implements NumberExpression<Double>
{
    private double value;

    public SimpleDoubleExpression(double initialValue, Observable parent, EventHandler eventHandler)
    {
        super(initialValue, parent, eventHandler);
        this.value = initialValue;
    }

    public SimpleDoubleExpression(double initialValue, List<Observable> parentList, EventHandler eventHandler)
    {
        super(initialValue, parentList, eventHandler);
        this.value = initialValue;
    }

    public SimpleDoubleExpression(double initialValue, Observable parent)
    {
        super(initialValue, parent);
        this.value = initialValue;
    }

    public SimpleDoubleExpression(double initialValue, List<Observable> parentList)
    {
        super(initialValue, parentList);
        this.value = initialValue;
    }

    public SimpleDoubleExpression(double initialValue)
    {
        super(initialValue);
        this.value = initialValue;
    }


    @Override
    protected Double getValue()
    {
        return value;
    }

    protected final void setValue(Double value)
    {
        this.value = saveDouble(value);
    }

    protected final void setValue(double value)
    {
        this.value = value;
    }

    @Override
    public NumberExpression<Double> add(Number value)
    {
        if (saveNumber(value).doubleValue() == 0) return this;
        return new SimpleDoubleExpression(getDouble() + saveDouble(value), this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleDoubleExpression.this.getDouble() + saveDouble(value));
            }
        };
    }

    @Override
    public NumberExpression<Double> add(Supplier< ? extends Number> value)
    {
        return new SimpleDoubleExpression(getDouble() + saveDouble(value), this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleDoubleExpression.this.getDouble() + saveDouble(value));
            }
        };
    }

    @Override
    public NumberExpression<Double> add(ObservableSingle<? extends Number> value)
    {
        return new SimpleDoubleExpression(getDouble() + saveDouble(value), List.of(this, value), getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleDoubleExpression.this.getDouble() + saveDouble(value));
            }
        };
    }

    @Override
    public NumberExpression<Double> subtract(Number value)
    {
        if (saveDouble(value) == 0) return this;
        return new SimpleDoubleExpression(getDouble() - saveDouble(value), this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleDoubleExpression.this.getDouble() - saveDouble(value));
            }
        };
    }

    @Override
    public NumberExpression<Double> subtract(Supplier<? extends Number> value)
    {
        return new SimpleDoubleExpression(getDouble() - saveDouble(value), this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleDoubleExpression.this.getDouble() - saveDouble(value));
            }
        };
    }

    @Override
    public NumberExpression<Double> subtract(ObservableSingle<? extends Number> value)
    {
        return new SimpleDoubleExpression(getDouble() - saveDouble(value), List.of(this, value), getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleDoubleExpression.this.getDouble() - saveDouble(value));
            }
        };
    }

    @Override
    public NumberExpression<Double> multiply(Number value)
    {
        if (saveDouble(value) == 0) return constantExpression(0d);
        if (saveDouble(value) == 1) return this;
        return new SimpleDoubleExpression(getDouble() * saveDouble(value), this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleDoubleExpression.this.getDouble() * saveDouble(value));
            }
        };
    }

    @Override
    public NumberExpression<Double> multiply(Supplier<? extends Number> value)
    {
        return new SimpleDoubleExpression(getDouble() * saveDouble(value), this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleDoubleExpression.this.getDouble() * saveDouble(value));
            }
        };
    }

    @Override
    public NumberExpression<Double> multiply(ObservableSingle<? extends Number> value)
    {
        return new SimpleDoubleExpression(getDouble() * saveDouble(value), List.of(this, value), getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleDoubleExpression.this.getDouble() * saveDouble(value));
            }
        };
    }

    @Override
    public NumberExpression<Double> divide(Number value)
    {
        if (saveDouble(value) == 0) throw divisionByZeroException();
        if (saveDouble(value) == 1) return this;
        return new SimpleDoubleExpression(getDouble() / saveDouble(value), this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleDoubleExpression.this.getDouble() / saveDouble(value));
            }
        };
    }

    @Override
    public NumberExpression<Double> divide(Supplier<? extends Number> value)
    {
        double divisor = saveDouble(value);
        return new SimpleDoubleExpression(divisor == 0 ? 0 : getDouble() / divisor, this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                double divisor = saveDouble(value);
                setValue(divisor == 0 ? 0 : SimpleDoubleExpression.this.getDouble() / divisor);
            }
        };
    }

    @Override
    public NumberExpression<Double> divide(ObservableSingle<? extends Number> value)
    {
        double divisor = saveDouble(value);
        return new SimpleDoubleExpression(divisor == 0 ? 0 : getDouble() / divisor, List.of(this, value), getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                double divisor = saveDouble(value);
                setValue(divisor == 0 ? 0 : SimpleDoubleExpression.this.getDouble() / divisor);
            }
        };
    }

    @Override
    public NumberExpression<Double> pow(Number value)
    {
        return new SimpleDoubleExpression(Math.pow(getDouble(), saveDouble(value)), this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(Math.pow(SimpleDoubleExpression.this.getDouble(), saveDouble(value)));
            }
        };
    }

    @Override
    public NumberExpression<Double> pow(Supplier<? extends Number> value)
    {
        return new SimpleDoubleExpression(Math.pow(getDouble(), saveDouble(value)), this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(Math.pow(SimpleDoubleExpression.this.getDouble(), saveDouble(value)));
            }
        };
    }

    @Override
    public NumberExpression<Double> pow(ObservableSingle<? extends Number> value)
    {
        return new SimpleDoubleExpression(Math.pow(getDouble(), saveDouble(value)), List.of(this, value), getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(Math.pow(SimpleDoubleExpression.this.getDouble(), saveDouble(value)));
            }
        };
    }

    @Override
    public NumberExpression<Double> modulo(Number value)
    {
        double mod = saveDouble(value);
        if (mod == 0) throw divisionByZeroException();
        if (mod == 1) return this;
        return new SimpleDoubleExpression(getDouble() % mod, this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleDoubleExpression.this.getDouble() % mod);
            }
        };
    }

    @Override
    public NumberExpression<Double> modulo(Supplier<? extends Number> value)
    {
        double mod = saveDouble(value);
        return new SimpleDoubleExpression(mod == 0 ? 0 : getDouble() % mod, this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                double mod = saveDouble(value);
                setValue(mod == 0 ? 0 : SimpleDoubleExpression.this.getDouble() % mod);
            }
        };
    }

    @Override
    public NumberExpression<Double> modulo(ObservableSingle<? extends Number> value)
    {
        double mod = saveDouble(value);
        return new SimpleDoubleExpression(mod == 0 ? 0 : getDouble() % mod, List.of(this, value), getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                double mod = saveDouble(value);
                setValue(mod == 0 ? 0 : SimpleDoubleExpression.this.getDouble() % mod);
            }
        };
    }

    @Override
    public NumberExpression<Double> sqrt()
    {
        return new SimpleDoubleExpression(Math.sqrt(getDouble()), this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(Math.sqrt(SimpleDoubleExpression.this.getDouble()));
            }
        };
    }

    @Override
    public NumberExpression<Double> invert()
    {
        return new SimpleDoubleExpression(getDouble() * -1, this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleDoubleExpression.this.getDouble() * -1);
            }
        };
    }

    @Override
    public NumberExpression<Double> positive()
    {
        return new SimpleDoubleExpression(getDouble() >= 0 ? getDouble() : getDouble() * -1, this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleDoubleExpression.this.getDouble() >= 0 ? SimpleDoubleExpression.this.getDouble() : SimpleDoubleExpression.this.getDouble() * -1);
            }
        };
    }

    @Override
    public NumberExpression<Double> negative()
    {
        return new SimpleDoubleExpression(getDouble() <= 0 ? getDouble() : getDouble() * -1, this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleDoubleExpression.this.getDouble() <= 0 ? SimpleDoubleExpression.this.getDouble() : SimpleDoubleExpression.this.getDouble() * -1);
            }
        };
    }

    @Override
    public BooleanExpression isBiggerThan(Number value)
    {
        return new SimpleBooleanExpression(getDouble() > saveDouble(value), this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleDoubleExpression.this.getDouble() > saveDouble(value));
            }
        };
    }

    @Override
    public BooleanExpression isBiggerThan(Supplier<? extends Number> value)
    {
        return new SimpleBooleanExpression(getDouble() > saveDouble(value), this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleDoubleExpression.this.getDouble() > saveDouble(value));
            }
        };
    }

    @Override
    public BooleanExpression isBiggerThan(ObservableSingle<? extends Number> value)
    {
        return new SimpleBooleanExpression(getDouble() > saveDouble(value), List.of(this, value), getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleDoubleExpression.this.getDouble() > saveDouble(value));
            }
        };
    }

    @Override
    public BooleanExpression isBiggerThanOrEqualTo(Number value)
    {
        return new SimpleBooleanExpression(getDouble() >= saveDouble(value), this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleDoubleExpression.this.getDouble() >= saveDouble(value));
            }
        };
    }

    @Override
    public BooleanExpression isBiggerThanOrEqualTo(Supplier<? extends Number> value)
    {

        return new SimpleBooleanExpression(getDouble() >= saveDouble(value), this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleDoubleExpression.this.getDouble() >= saveDouble(value));
            }
        };
    }

    @Override
    public BooleanExpression isBiggerThanOrEqualTo(ObservableSingle<? extends Number> value)
    {
        return new SimpleBooleanExpression(getDouble() >= saveDouble(value), List.of(this, value), getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleDoubleExpression.this.getDouble() >= saveDouble(value));
            }
        };
    }

    @Override
    public BooleanExpression isLessThan(Number value)
    {
        return new SimpleBooleanExpression(getDouble() < saveDouble(value), this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleDoubleExpression.this.getDouble() < saveDouble(value));
            }
        };
    }

    @Override
    public BooleanExpression isLessThan(Supplier<? extends Number> value)
    {
        return new SimpleBooleanExpression(getDouble() < saveDouble(value), this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleDoubleExpression.this.getDouble() < saveDouble(value));
            }
        };
    }

    @Override
    public BooleanExpression isLessThan(ObservableSingle<? extends Number> value)
    {
        return new SimpleBooleanExpression(getDouble() < saveDouble(value), List.of(this, value), getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleDoubleExpression.this.getDouble() < saveDouble(value));
            }
        };
    }

    @Override
    public BooleanExpression isLessThanOrEqualTo(Number value)
    {
        return new SimpleBooleanExpression(getDouble() <= saveDouble(value), this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleDoubleExpression.this.getDouble() <= saveDouble(value));
            }
        };
    }

    @Override
    public BooleanExpression isLessThanOrEqualTo(Supplier<? extends Number> value)
    {
        return new SimpleBooleanExpression(getDouble() <= saveDouble(value), this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleDoubleExpression.this.getDouble() <= saveDouble(value));
            }
        };
    }

    @Override
    public BooleanExpression isLessThanOrEqualTo(ObservableSingle<? extends Number> value)
    {
        return new SimpleBooleanExpression(getDouble() <= saveDouble(value), List.of(this, value), getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleDoubleExpression.this.getDouble() <= saveDouble(value));
            }
        };
    }

    @Override
    public BooleanExpression isEqualTo(Number value)
    {
        return new SimpleBooleanExpression(getDouble() == saveDouble(value), this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleDoubleExpression.this.getDouble() == saveDouble(value));
            }
        };
    }

    @Override
    public BooleanExpression isEqualTo(Supplier<? extends Number> value)
    {
        return new SimpleBooleanExpression(getDouble() == saveDouble(value), this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleDoubleExpression.this.getDouble() == saveDouble(value));
            }
        };
    }

    @Override
    public BooleanExpression isEqualTo(ObservableSingle<? extends Number> value)
    {
        return new SimpleBooleanExpression(getDouble() == saveDouble(value), List.of(this, value), getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleDoubleExpression.this.getDouble() == saveDouble(value));
            }
        };
    }

    @Override
    public NumberExpression<Double> mapDouble()
    {
        return this;
    }

    @Override
    public byte getByte()
    {
        validate();
        return (byte) value;
    }

    @Override
    public short getShort()
    {
        validate();
        return (short) value;
    }

    @Override
    public int getInt()
    {
        validate();
        return (int) value;
    }

    @Override
    public float getFloat()
    {
        validate();
        return (float) value;
    }

    @Override
    public double getDouble()
    {
        validate();
        return value;
    }

    @Override
    public long getLong()
    {
        validate();
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

}
