package com.frechsack.dev.observer.simple;

import com.frechsack.dev.observer.core.BooleanExpression;
import com.frechsack.dev.observer.core.NumberExpression;
import com.frechsack.dev.observer.core.Observable;
import com.frechsack.dev.observer.core.ObservableSingle;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.function.Supplier;

public abstract class SimpleLongExpression extends AbstractNumberExpression<Long> implements NumberExpression<Long>
{
    private long value;

    public SimpleLongExpression(long initialValue, Observable parent, EventHandler eventHandler)
    {
        super(initialValue, parent, eventHandler);
        value = initialValue;
    }

    public SimpleLongExpression(long initialValue, List<Observable> parentList, EventHandler eventHandler)
    {
        super(initialValue, parentList, eventHandler);
        value = initialValue;
    }

    public SimpleLongExpression(long initialValue, Observable parent)
    {
        super(initialValue, parent);
        value = initialValue;
    }

    public SimpleLongExpression(long initialValue, List<Observable> parentList)
    {
        super(initialValue, parentList);
        value = initialValue;
    }

    public SimpleLongExpression(long initialValue)
    {
        super(initialValue);
        value = initialValue;
    }

    protected final void setValue(Long value)
    {
        this.value = saveLong(value);
    }

    protected final void setValue(long value)
    {
        this.value = value;
    }

    @Override
    protected Long getValue()
    {
        return value;
    }

    @Override
    public NumberExpression<Long> add(Number value)
    {
        if (saveLong(value) == 0) return this;
        return new SimpleLongExpression(getLong() + saveLong(value), this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleLongExpression.this.getLong() + saveLong(value));
            }
        };
    }

    @Override
    public NumberExpression<Long> add(Supplier<? extends Number> value)
    {
        return new SimpleLongExpression(getLong() + saveLong(value), this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleLongExpression.this.getLong() + saveLong(value));
            }
        };
    }

    @Override
    public NumberExpression<Long> add(ObservableSingle<? extends Number> value)
    {
        return new SimpleLongExpression(getLong() + saveLong(value), List.of(this, value), getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleLongExpression.this.getLong() + saveLong(value));
            }
        };
    }

    @Override
    public NumberExpression<Long> subtract(Number value)
    {
        if (saveLong(value) == 0) return this;
        return new SimpleLongExpression(getLong() - saveLong(value), this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleLongExpression.this.getLong() - saveLong(value));
            }
        };
    }

    @Override
    public NumberExpression<Long> subtract(Supplier<? extends Number> value)
    {
        return new SimpleLongExpression(getLong() - saveLong(value), this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleLongExpression.this.getLong() - saveLong(value));
            }
        };
    }

    @Override
    public NumberExpression<Long> subtract(ObservableSingle<? extends Number> value)
    {
        return new SimpleLongExpression(getLong() - saveLong(value), List.of(this, value), getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleLongExpression.this.getLong() - saveLong(value));
            }
        };
    }

    @Override
    public NumberExpression<Long> multiply(Number value)
    {
        if (saveLong(value) == 0) constantExpression(0L);
        if (saveLong(value) == 1) return this;
        return new SimpleLongExpression(getLong() * saveLong(value), this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleLongExpression.this.getLong() * saveLong(value));
            }
        };
    }

    @Override
    public NumberExpression<Long> multiply(Supplier<? extends Number> value)
    {
        return new SimpleLongExpression(getLong() * saveLong(value), this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleLongExpression.this.getLong() * saveLong(value));
            }
        };
    }

    @Override
    public NumberExpression<Long> multiply(ObservableSingle<? extends Number> value)
    {
        return new SimpleLongExpression(getLong() * saveLong(value), List.of(this, value), getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleLongExpression.this.getLong() * saveLong(value));
            }
        };
    }

    @Override
    public NumberExpression<Long> divide(Number value)
    {
        if (saveLong(value) == 0) throw divisionByZeroException();
        if (saveLong(value) == 1) return this;
        return new SimpleLongExpression(getLong() / saveLong(value), this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(getLong() / saveLong(value));
            }
        };
    }

    @Override
    public NumberExpression<Long> divide(Supplier<? extends Number> value)
    {
        long divisor = saveLong(value);
        return new SimpleLongExpression(divisor == 0 ? 0 : SimpleLongExpression.this.getLong() / divisor, this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                long divisor = saveLong(value);
                setValue(divisor == 0 ? 0 : SimpleLongExpression.this.getLong() / divisor);
            }
        };
    }

    @Override
    public NumberExpression<Long> divide(ObservableSingle<? extends Number> value)
    {
        long divisor = saveLong(value);
        return new SimpleLongExpression(divisor == 0 ? 0 : SimpleLongExpression.this.getLong() / divisor, List.of(this, value), getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                long divisor = saveLong(value);
                setValue(divisor == 0 ? 0 : SimpleLongExpression.this.getLong() / divisor);
            }
        };
    }

    @Override
    public NumberExpression<Long> pow(Number value)
    {
        return new SimpleLongExpression((long) Math.pow(getLong(), saveLong(value)), this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue((long) Math.pow(SimpleLongExpression.this.getLong(), saveLong(value)));
            }
        };
    }

    @Override
    public NumberExpression<Long> pow(Supplier<? extends Number> value)
    {

        return new SimpleLongExpression((long) Math.pow(getLong(), saveLong(value)), this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue((long) Math.pow(SimpleLongExpression.this.getLong(), saveLong(value)));
            }
        };
    }

    @Override
    public NumberExpression<Long> pow(ObservableSingle<? extends Number> value)
    {
        return new SimpleLongExpression((long) Math.pow(getLong(), saveLong(value)), List.of(this, value), getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue((long) Math.pow(SimpleLongExpression.this.getLong(), saveLong(value)));
            }
        };
    }

    @Override
    public NumberExpression<Long> modulo(Number value)
    {
        long mod = saveLong(value);
        if (mod == 0) throw divisionByZeroException();
        if (mod == 1) return this;
        return new SimpleLongExpression(getLong() % mod, this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleLongExpression.this.getLong() % mod);
            }
        };
    }

    @Override
    public NumberExpression<Long> modulo(Supplier<? extends Number> value)
    {
        long mod = saveLong(value);
        return new SimpleLongExpression(mod == 0 ? 0 : getLong() % mod, this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                long mod = saveLong(value);
                setValue(SimpleLongExpression.this.getLong() % mod);
            }
        };
    }

    @Override
    public NumberExpression<Long> modulo(ObservableSingle<? extends Number> value)
    {
        long mod = saveLong(value);
        return new SimpleLongExpression(mod == 0 ? 0 : getLong() % mod, List.of(this, value), getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                long mod = saveLong(value);
                setValue(SimpleLongExpression.this.getLong() % mod);
            }
        };
    }

    @Override
    public NumberExpression<Long> sqrt()
    {
        return new SimpleLongExpression((long) Math.sqrt(getLong()), this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue((long) Math.sqrt(SimpleLongExpression.this.getLong()));
            }
        };
    }

    @Override
    public NumberExpression<Long> invert()
    {
        return new SimpleLongExpression(getLong() * -1, this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleLongExpression.this.getLong() * -1);
            }
        };
    }

    @Override
    public NumberExpression<Long> positive()
    {
        return new SimpleLongExpression(getLong() < 0 ? getLong() * -1 : getLong(), this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleLongExpression.this.getLong() < 0 ? SimpleLongExpression.this.getLong() * -1 : SimpleLongExpression.this.getLong());
            }
        };
    }

    @Override
    public NumberExpression<Long> negative()
    {
        return new SimpleLongExpression(getLong() > 0 ? getLong() * -1 : getLong(), this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleLongExpression.this.getLong() > 0 ? SimpleLongExpression.this.getLong() * -1 : SimpleLongExpression.this.getLong());
            }
        };
    }

    @Override
    public BooleanExpression isBiggerThan(Number value)
    {
        return new SimpleBooleanExpression(getLong() > saveLong(value), this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleLongExpression.this.getLong() > saveLong(value));
            }
        };
    }

    @Override
    public BooleanExpression isBiggerThan(Supplier<? extends Number> value)
    {
        return new SimpleBooleanExpression(getLong() > saveLong(value), this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleLongExpression.this.getLong() > saveLong(value));
            }
        };
    }

    @Override
    public BooleanExpression isBiggerThan(ObservableSingle<? extends Number> value)
    {
        return new SimpleBooleanExpression(getLong() > saveLong(value), List.of(this, value), getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleLongExpression.this.getLong() > saveLong(value));
            }
        };
    }

    @Override
    public BooleanExpression isBiggerThanOrEqualTo(Number value)
    {
        return new SimpleBooleanExpression(getLong() >= saveLong(value), this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleLongExpression.this.getLong() >= saveLong(value));
            }
        };
    }

    @Override
    public BooleanExpression isBiggerThanOrEqualTo(Supplier<? extends Number> value)
    {
        return new SimpleBooleanExpression(getLong() >= saveLong(value), this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleLongExpression.this.getLong() >= saveLong(value));
            }
        };
    }

    @Override
    public BooleanExpression isBiggerThanOrEqualTo(ObservableSingle<? extends Number> value)
    {
        return new SimpleBooleanExpression(getLong() >= saveLong(value), List.of(this, value), getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleLongExpression.this.getLong() >= saveLong(value));
            }
        };
    }

    @Override
    public BooleanExpression isLessThan(Number value)
    {
        return new SimpleBooleanExpression(getLong() < saveLong(value), this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleLongExpression.this.getLong() < saveLong(value));
            }
        };
    }

    @Override
    public BooleanExpression isLessThan(Supplier<? extends Number> value)
    {
        return new SimpleBooleanExpression(getLong() < saveLong(value), this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleLongExpression.this.getLong() < saveLong(value));
            }
        };
    }

    @Override
    public BooleanExpression isLessThan(ObservableSingle<? extends Number> value)
    {
        return new SimpleBooleanExpression(getLong() < saveLong(value), List.of(this, value), getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleLongExpression.this.getLong() < saveLong(value));
            }
        };
    }

    @Override
    public BooleanExpression isLessThanOrEqualTo(Number value)
    {
        return new SimpleBooleanExpression(getLong() <= saveLong(value), this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleLongExpression.this.getLong() <= saveLong(value));
            }
        };
    }

    @Override
    public BooleanExpression isLessThanOrEqualTo(Supplier<? extends Number> value)
    {
        return new SimpleBooleanExpression(getLong() <= saveLong(value), this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleLongExpression.this.getLong() <= saveLong(value));
            }
        };
    }

    @Override
    public BooleanExpression isLessThanOrEqualTo(ObservableSingle<? extends Number> value)
    {
        return new SimpleBooleanExpression(getLong() <= saveLong(value), List.of(this, value), getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleLongExpression.this.getLong() <= saveLong(value));
            }
        };
    }

    @Override
    public BooleanExpression isEqualTo(Number value)
    {
        return new SimpleBooleanExpression(getLong() == saveLong(value), this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleLongExpression.this.getLong() == saveLong(value));
            }
        };
    }

    @Override
    public BooleanExpression isEqualTo(Supplier<? extends Number> value)
    {
        return new SimpleBooleanExpression(getLong() == saveLong(value), this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleLongExpression.this.getLong() == saveLong(value));
            }
        };
    }

    @Override
    public BooleanExpression isEqualTo(ObservableSingle<? extends Number> value)
    {
        return new SimpleBooleanExpression(getLong() == saveLong(value), List.of(this, value), getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleLongExpression.this.getLong() == saveLong(value));
            }
        };
    }

    @Override
    public NumberExpression<Long> mapLong()
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
        return value;
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
        return value;
    }

    @Override
    public BigDecimal getBigDecimal()
    {
        return BigDecimal.valueOf(getLong());
    }

    @Override
    public BigInteger getBigInteger()
    {
        return BigInteger.valueOf(getLong());
    }
}
