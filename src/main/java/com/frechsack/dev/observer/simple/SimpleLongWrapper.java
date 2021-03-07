package com.frechsack.dev.observer.simple;

import com.frechsack.dev.observer.core.BooleanExpression;
import com.frechsack.dev.observer.core.NumberExpression;
import com.frechsack.dev.observer.core.ObservableSingle;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.function.Supplier;

public class SimpleLongWrapper extends AbstractNumberWrapper<Long> implements NumberExpression<Long>
{
    public SimpleLongWrapper(ObservableSingle<Long> observable)
    {
        super(observable);
    }

    @Override
    public NumberExpression<Long> add(Number value)
    {
        if (saveLong(value) == 0) return this;
        return new SimpleLongExpression(getAsLong() + saveLong(value), this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleLongWrapper.this.getAsLong() + saveLong(value));
            }
        };
    }

    @Override
    public NumberExpression<Long> add(Supplier<? extends Number> value)
    {
        return new SimpleLongExpression(getAsLong() + saveLong(value), this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleLongWrapper.this.getAsLong() + saveLong(value));
            }
        };
    }

    @Override
    public NumberExpression<Long> add(ObservableSingle<? extends Number> value)
    {
        return new SimpleLongExpression(getAsLong() + saveLong(value), List.of(this, value), getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleLongWrapper.this.getAsLong() + saveLong(value));
            }
        };
    }

    @Override
    public NumberExpression<Long> subtract(Number value)
    {
        if (saveLong(value) == 0) return this;
        return new SimpleLongExpression(getAsLong() - saveLong(value), this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleLongWrapper.this.getAsLong() - saveLong(value));
            }
        };
    }

    @Override
    public NumberExpression<Long> subtract(Supplier<? extends Number> value)
    {
        return new SimpleLongExpression(getAsLong() - saveLong(value), this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleLongWrapper.this.getAsLong() - saveLong(value));
            }
        };
    }

    @Override
    public NumberExpression<Long> subtract(ObservableSingle<? extends Number> value)
    {
        return new SimpleLongExpression(getAsLong() - saveLong(value), List.of(this, value), getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleLongWrapper.this.getAsLong() - saveLong(value));
            }
        };
    }

    @Override
    public NumberExpression<Long> multiply(Number value)
    {
        if (saveLong(value) == 0) return new SimpleLongExpression(0L)
        {
            @Override
            protected void computeValue()
            {
                setValue(0L);
            }
        };
        if (saveLong(value) == 1) return this;
        return new SimpleLongExpression(getAsLong() * saveLong(value), this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleLongWrapper.this.getAsLong() * saveLong(value));
            }
        };
    }

    @Override
    public NumberExpression<Long> multiply(Supplier<? extends Number> value)
    {
        return new SimpleLongExpression(getAsLong() * saveLong(value), this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleLongWrapper.this.getAsLong() * saveLong(value));
            }
        };
    }

    @Override
    public NumberExpression<Long> multiply(ObservableSingle<? extends Number> value)
    {
        return new SimpleLongExpression(getAsLong() * saveLong(value), List.of(this, value), getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleLongWrapper.this.getAsLong() * saveLong(value));
            }
        };
    }

    @Override
    public NumberExpression<Long> divide(Number value)
    {
        if (saveLong(value) == 0) throw divisionByZeroException();
        if (saveLong(value) == 1) return this;
        return new SimpleLongExpression(getAsLong() / saveLong(value), this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(getAsLong() / saveLong(value));
            }
        };
    }

    @Override
    public NumberExpression<Long> divide(Supplier<? extends Number> value)
    {
        long divisor = saveLong(value);
        return new SimpleLongExpression(divisor == 0 ? 0 : SimpleLongWrapper.this.getAsLong() / divisor, this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                long divisor = saveLong(value);
                setValue(divisor == 0 ? 0 : SimpleLongWrapper.this.getAsLong() / divisor);
            }
        };
    }

    @Override
    public NumberExpression<Long> divide(ObservableSingle<? extends Number> value)
    {
        long divisor = saveLong(value);
        return new SimpleLongExpression(divisor == 0 ? 0 : SimpleLongWrapper.this.getAsLong() / divisor, List.of(this, value), getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                long divisor = saveLong(value);
                setValue(divisor == 0 ? 0 : SimpleLongWrapper.this.getAsLong() / divisor);
            }
        };
    }

    @Override
    public NumberExpression<Long> pow(Number value)
    {
        return new SimpleLongExpression((long) Math.pow(getAsLong(), saveLong(value)), this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue((long) Math.pow(SimpleLongWrapper.this.getAsLong(), saveLong(value)));
            }
        };
    }

    @Override
    public NumberExpression<Long> pow(Supplier<? extends Number> value)
    {

        return new SimpleLongExpression((long) Math.pow(getAsLong(), saveLong(value)), this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue((long) Math.pow(SimpleLongWrapper.this.getAsLong(), saveLong(value)));
            }
        };
    }

    @Override
    public NumberExpression<Long> pow(ObservableSingle<? extends Number> value)
    {
        return new SimpleLongExpression((long) Math.pow(getAsLong(), saveLong(value)), List.of(this, value), getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue((long) Math.pow(SimpleLongWrapper.this.getAsLong(), saveLong(value)));
            }
        };
    }

    @Override
    public NumberExpression<Long> modulo(Number value)
    {
        long mod = saveLong(value);
        if (mod == 0) throw divisionByZeroException();
        if (mod == 1) return this;
        return new SimpleLongExpression(getAsLong() % mod, this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleLongWrapper.this.getAsLong() % mod);
            }
        };
    }

    @Override
    public NumberExpression<Long> modulo(Supplier<? extends Number> value)
    {
        long mod = saveLong(value);
        return new SimpleLongExpression(mod == 0 ? 0 : getAsLong() % mod, this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                long mod = saveLong(value);
                setValue(SimpleLongWrapper.this.getAsLong() % mod);
            }
        };
    }

    @Override
    public NumberExpression<Long> modulo(ObservableSingle<? extends Number> value)
    {
        long mod = saveLong(value);
        return new SimpleLongExpression(mod == 0 ? 0 : getAsLong() % mod, List.of(this, value), getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                long mod = saveLong(value);
                setValue(SimpleLongWrapper.this.getAsLong() % mod);
            }
        };
    }

    @Override
    public NumberExpression<Long> sqrt()
    {
        return new SimpleLongExpression((long) Math.sqrt(getAsLong()), this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue((long) Math.sqrt(SimpleLongWrapper.this.getAsLong()));
            }
        };
    }

    @Override
    public NumberExpression<Long> invert()
    {
        return new SimpleLongExpression(getAsLong() * -1, this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleLongWrapper.this.getAsLong() * -1);
            }
        };
    }

    @Override
    public NumberExpression<Long> positive()
    {
        return new SimpleLongExpression(getAsLong() < 0 ? getAsLong() * -1 : getAsLong(), this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleLongWrapper.this.getAsLong() < 0 ? SimpleLongWrapper.this.getAsLong() * -1 : SimpleLongWrapper.this.getAsLong());
            }
        };
    }

    @Override
    public NumberExpression<Long> negative()
    {
        return new SimpleLongExpression(getAsLong() > 0 ? getAsLong() * -1 : getAsLong(), this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleLongWrapper.this.getAsLong() > 0 ? SimpleLongWrapper.this.getAsLong() * -1 : SimpleLongWrapper.this.getAsLong());
            }
        };
    }

    @Override
    public BooleanExpression isBiggerThan(Number value)
    {
        return new SimpleBooleanExpression(getAsLong() > saveLong(value), this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleLongWrapper.this.getAsLong() > saveLong(value));
            }
        };
    }

    @Override
    public BooleanExpression isBiggerThan(Supplier<? extends Number> value)
    {
        return new SimpleBooleanExpression(getAsLong() > saveLong(value), this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleLongWrapper.this.getAsLong() > saveLong(value));
            }
        };
    }

    @Override
    public BooleanExpression isBiggerThan(ObservableSingle<? extends Number> value)
    {
        return new SimpleBooleanExpression(getAsLong() > saveLong(value), List.of(this, value), getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleLongWrapper.this.getAsLong() > saveLong(value));
            }
        };
    }

    @Override
    public BooleanExpression isBiggerThanOrEqualTo(Number value)
    {
        return new SimpleBooleanExpression(getAsLong() >= saveLong(value), this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleLongWrapper.this.getAsLong() >= saveLong(value));
            }
        };
    }

    @Override
    public BooleanExpression isBiggerThanOrEqualTo(Supplier<? extends Number> value)
    {
        return new SimpleBooleanExpression(getAsLong() >= saveLong(value), this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleLongWrapper.this.getAsLong() >= saveLong(value));
            }
        };
    }

    @Override
    public BooleanExpression isBiggerThanOrEqualTo(ObservableSingle<? extends Number> value)
    {
        return new SimpleBooleanExpression(getAsLong() >= saveLong(value), List.of(this, value), getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleLongWrapper.this.getAsLong() >= saveLong(value));
            }
        };
    }

    @Override
    public BooleanExpression isLessThan(Number value)
    {
        return new SimpleBooleanExpression(getAsLong() < saveLong(value), this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleLongWrapper.this.getAsLong() < saveLong(value));
            }
        };
    }

    @Override
    public BooleanExpression isLessThan(Supplier<? extends Number> value)
    {
        return new SimpleBooleanExpression(getAsLong() < saveLong(value), this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleLongWrapper.this.getAsLong() < saveLong(value));
            }
        };
    }

    @Override
    public BooleanExpression isLessThan(ObservableSingle<? extends Number> value)
    {
        return new SimpleBooleanExpression(getAsLong() < saveLong(value), List.of(this, value), getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleLongWrapper.this.getAsLong() < saveLong(value));
            }
        };
    }

    @Override
    public BooleanExpression isLessThanOrEqualTo(Number value)
    {
        return new SimpleBooleanExpression(getAsLong() <= saveLong(value), this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleLongWrapper.this.getAsLong() <= saveLong(value));
            }
        };
    }

    @Override
    public BooleanExpression isLessThanOrEqualTo(Supplier<? extends Number> value)
    {
        return new SimpleBooleanExpression(getAsLong() <= saveLong(value), this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleLongWrapper.this.getAsLong() <= saveLong(value));
            }
        };
    }

    @Override
    public BooleanExpression isLessThanOrEqualTo(ObservableSingle<? extends Number> value)
    {
        return new SimpleBooleanExpression(getAsLong() <= saveLong(value), List.of(this, value), getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleLongWrapper.this.getAsLong() <= saveLong(value));
            }
        };
    }

    @Override
    public BooleanExpression isEqualTo(Number value)
    {
        return new SimpleBooleanExpression(getAsLong() == saveLong(value), this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleLongWrapper.this.getAsLong() == saveLong(value));
            }
        };
    }

    @Override
    public BooleanExpression isEqualTo(Supplier<? extends Number> value)
    {
        return new SimpleBooleanExpression(getAsLong() == saveLong(value), this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleLongWrapper.this.getAsLong() == saveLong(value));
            }
        };
    }

    @Override
    public BooleanExpression isEqualTo(ObservableSingle<? extends Number> value)
    {
        return new SimpleBooleanExpression(getAsLong() == saveLong(value), List.of(this, value), getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleLongWrapper.this.getAsLong() == saveLong(value));
            }
        };
    }

    @Override
    public NumberExpression<Long> mapLong()
    {
        return this;
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
        return get();
    }

    @Override
    public BigDecimal getAsBigDecimal()
    {
        return BigDecimal.valueOf(getAsLong());
    }

    @Override
    public BigInteger getAsBigInteger()
    {
        return BigInteger.valueOf(getAsLong());
    }

}
