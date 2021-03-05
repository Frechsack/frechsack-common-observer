package com.frechsack.dev.observer.simple;

import com.frechsack.dev.observer.core.BooleanExpression;
import com.frechsack.dev.observer.core.NumberExpression;
import com.frechsack.dev.observer.core.StringExpression;
import com.frechsack.dev.observer.core.ObservableSingle;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

public class SimpleFloatWrapper extends AbstractNumberWrapper<Float> implements NumberExpression<Float>
{

    public SimpleFloatWrapper(ObservableSingle<Float> observable)
    {
        super(observable);
    }

    @Override
    public NumberExpression<Float> add(Number value)
    {
        if (saveFloat(value) == 0) return this;
        return new SimpleFloatExpression(getFloat() + saveFloat(value), this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleFloatWrapper.this.getFloat() + saveFloat(value));
            }
        };
    }

    @Override
    public NumberExpression<Float> add(Supplier<? extends Number> value)
    {
        return new SimpleFloatExpression(getFloat() + saveFloat(value), this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleFloatWrapper.this.getFloat() + saveFloat(value));
            }
        };
    }

    @Override
    public NumberExpression<Float> add(ObservableSingle<? extends Number> value)
    {
        return new SimpleFloatExpression(getFloat() + saveFloat(value), List.of(this, value), getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleFloatWrapper.this.getFloat() + saveFloat(value));
            }
        };
    }

    @Override
    public NumberExpression<Float> subtract(Number value)
    {
        if (saveFloat(value) == 0) return this;
        return new SimpleFloatExpression(getFloat() - saveFloat(value), this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleFloatWrapper.this.getFloat() - saveFloat(value));
            }
        };
    }

    @Override
    public NumberExpression<Float> subtract(Supplier<? extends Number> value)
    {
        return new SimpleFloatExpression(getFloat() - saveFloat(value), this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleFloatWrapper.this.getFloat() - saveFloat(value));
            }
        };
    }

    @Override
    public NumberExpression<Float> subtract(ObservableSingle<? extends Number> value)
    {
        return new SimpleFloatExpression(getFloat() - saveFloat(value), this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleFloatWrapper.this.getFloat() - saveFloat(value));
            }
        };
    }

    @Override
    public NumberExpression<Float> multiply(Number value)
    {
        if (saveFloat(value) == 0) return new SimpleFloatExpression(0F)
        {
            @Override
            protected void computeValue()
            {
                setValue(0F);
            }
        };
        if (saveFloat(value) == 1) return this;
        return new SimpleFloatExpression(getFloat() * saveFloat(value), this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleFloatWrapper.this.getFloat() * saveFloat(value));
            }
        };
    }

    @Override
    public NumberExpression<Float> multiply(Supplier<? extends Number> value)
    {
        return new SimpleFloatExpression(getFloat() * saveFloat(value), this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleFloatWrapper.this.getFloat() * saveFloat(value));
            }
        };
    }

    @Override
    public NumberExpression<Float> multiply(ObservableSingle<? extends Number> value)
    {
        return new SimpleFloatExpression(getFloat() * saveFloat(value), List.of(this, value), getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleFloatWrapper.this.getFloat() * saveFloat(value));
            }
        };
    }

    @Override
    public NumberExpression<Float> divide(Number value)
    {
        if (saveFloat(value) == 0) throw divisionByZeroException();
        if (saveFloat(value) == 1) return this;
        return new SimpleFloatExpression(getFloat() / saveFloat(value), this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleFloatWrapper.this.getFloat() / saveFloat(value));
            }
        };
    }

    @Override
    public NumberExpression<Float> divide(Supplier<? extends Number> value)
    {
        float divisor = saveFloat(value);
        return new SimpleFloatExpression(divisor == 0 ? 0 : getFloat() / divisor, this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                float divisor = saveFloat(value);
                setValue(divisor == 0 ? 0 : SimpleFloatWrapper.this.getFloat() / divisor);
            }
        };
    }

    @Override
    public NumberExpression<Float> divide(ObservableSingle<? extends Number> value)
    {
        float divisor = saveFloat(value);
        return new SimpleFloatExpression(divisor == 0 ? 0 : getFloat() / divisor, List.of(this, value), getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                float divisor = saveFloat(value);
                setValue(divisor == 0 ? 0 : SimpleFloatWrapper.this.getFloat() / divisor);
            }
        };
    }

    @Override
    public NumberExpression<Float> pow(Number value)
    {
        return new SimpleFloatExpression((float) Math.pow(getFloat(), saveFloat(value)), this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue((float) Math.pow(SimpleFloatWrapper.this.getFloat(), saveFloat(value)));
            }
        };
    }

    @Override
    public NumberExpression<Float> pow(Supplier<? extends Number> value)
    {
        return new SimpleFloatExpression((float) Math.pow(getFloat(), saveFloat(value)), this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue((float) Math.pow(SimpleFloatWrapper.this.getFloat(), saveFloat(value)));
            }
        };
    }

    @Override
    public NumberExpression<Float> pow(ObservableSingle<? extends Number> value)
    {
        return new SimpleFloatExpression((float) Math.pow(getFloat(), saveFloat(value)), List.of(this, value), getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue((float) Math.pow(SimpleFloatWrapper.this.getFloat(), saveFloat(value)));
            }
        };
    }

    @Override
    public NumberExpression<Float> modulo(Number value)
    {
        float mod = saveFloat(value);
        if (mod == 0) throw divisionByZeroException();
        if (mod == 1) return this;
        return new SimpleFloatExpression(getFloat() % mod, this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleFloatWrapper.this.getFloat() % mod);
            }
        };
    }

    @Override
    public NumberExpression<Float> modulo(Supplier<? extends Number> value)
    {
        float mod = saveFloat(value);
        return new SimpleFloatExpression(mod == 0 ? 0 : getFloat() % mod, this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                float mod = saveFloat(value);
                setValue(mod == 0 ? 0 : SimpleFloatWrapper.this.getFloat() % mod);
            }
        };
    }

    @Override
    public NumberExpression<Float> modulo(ObservableSingle<? extends Number> value)
    {
        float mod = saveFloat(value);
        return new SimpleFloatExpression(mod == 0 ? 0 : getFloat() % mod, List.of(this, value), getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                float mod = saveFloat(value);
                setValue(mod == 0 ? 0 : SimpleFloatWrapper.this.getFloat() % mod);
            }
        };
    }

    @Override
    public NumberExpression<Float> sqrt()
    {
        return new SimpleFloatExpression((float) Math.sqrt(getFloat()), this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue((float) Math.sqrt(SimpleFloatWrapper.this.getFloat()));
            }
        };
    }

    @Override
    public NumberExpression<Float> invert()
    {
        return new SimpleFloatExpression(getFloat() * -1, this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleFloatWrapper.this.getFloat() * -1);
            }
        };
    }

    @Override
    public NumberExpression<Float> positive()
    {
        return new SimpleFloatExpression(getFloat() < 0 ? getFloat() * -1 : getFloat(), this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleFloatWrapper.this.getFloat() < 0 ? SimpleFloatWrapper.this.getFloat() * -1 : SimpleFloatWrapper.this.getFloat());
            }
        };
    }

    @Override
    public NumberExpression<Float> negative()
    {
        return new SimpleFloatExpression(getFloat() > 0 ? getFloat() * -1 : getFloat(), this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleFloatWrapper.this.getFloat() > 0 ? SimpleFloatWrapper.this.getFloat() * -1 : SimpleFloatWrapper.this.getFloat());
            }
        };
    }

    @Override
    public BooleanExpression isBiggerThan(Number value)
    {
        return new SimpleBooleanExpression(getFloat() > saveFloat(value), this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleFloatWrapper.this.getFloat() > saveFloat(value));
            }
        };
    }

    @Override
    public BooleanExpression isBiggerThan(Supplier<? extends Number> value)
    {
        return new SimpleBooleanExpression(getFloat() > saveFloat(value), this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleFloatWrapper.this.getFloat() > saveFloat(value));
            }
        };
    }

    @Override
    public BooleanExpression isBiggerThan(ObservableSingle<? extends Number> value)
    {
        return new SimpleBooleanExpression(getFloat() > saveFloat(value), List.of(this, value), getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleFloatWrapper.this.getFloat() > saveFloat(value));
            }
        };
    }

    @Override
    public BooleanExpression isBiggerThanOrEqualTo(Number value)
    {
        return new SimpleBooleanExpression(getFloat() >= saveFloat(value), this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleFloatWrapper.this.getFloat() >= saveFloat(value));
            }
        };
    }

    @Override
    public BooleanExpression isBiggerThanOrEqualTo(Supplier<? extends Number> value)
    {
        return new SimpleBooleanExpression(getFloat() >= saveFloat(value), this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleFloatWrapper.this.getFloat() >= saveFloat(value));
            }
        };
    }

    @Override
    public BooleanExpression isBiggerThanOrEqualTo(ObservableSingle<? extends Number> value)
    {
        return new SimpleBooleanExpression(getFloat() >= saveFloat(value), List.of(this, value), getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleFloatWrapper.this.getFloat() >= saveFloat(value));
            }
        };
    }

    @Override
    public BooleanExpression isLessThan(Number value)
    {
        return new SimpleBooleanExpression(getFloat() < saveFloat(value), this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleFloatWrapper.this.getFloat() < saveFloat(value));
            }
        };
    }

    @Override
    public BooleanExpression isLessThan(Supplier<? extends Number> value)
    {
        return new SimpleBooleanExpression(getFloat() < saveFloat(value), this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleFloatWrapper.this.getFloat() < saveFloat(value));
            }
        };
    }

    @Override
    public BooleanExpression isLessThan(ObservableSingle<? extends Number> value)
    {
        return new SimpleBooleanExpression(getFloat() < saveFloat(value), List.of(this, value), getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleFloatWrapper.this.getFloat() < saveFloat(value));
            }
        };
    }

    @Override
    public BooleanExpression isLessThanOrEqualTo(Number value)
    {
        return new SimpleBooleanExpression(getFloat() <= saveFloat(value), this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleFloatWrapper.this.getFloat() <= saveFloat(value));
            }
        };
    }

    @Override
    public BooleanExpression isLessThanOrEqualTo(Supplier<? extends Number> value)
    {
        return new SimpleBooleanExpression(getFloat() <= saveFloat(value), this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleFloatWrapper.this.getFloat() <= saveFloat(value));
            }
        };
    }

    @Override
    public BooleanExpression isLessThanOrEqualTo(ObservableSingle<? extends Number> value)
    {
        return new SimpleBooleanExpression(getFloat() <= saveFloat(value), List.of(this, value), getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleFloatWrapper.this.getFloat() <= saveFloat(value));
            }
        };
    }

    @Override
    public BooleanExpression isEqualTo(Number value)
    {
        return new SimpleBooleanExpression(getFloat() == saveFloat(value), this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleFloatWrapper.this.getFloat() == saveFloat(value));
            }
        };
    }

    @Override
    public BooleanExpression isEqualTo(Supplier<? extends Number> value)
    {
        return new SimpleBooleanExpression(getFloat() == saveFloat(value), this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleFloatWrapper.this.getFloat() == saveFloat(value));
            }
        };
    }

    @Override
    public BooleanExpression isEqualTo(ObservableSingle<? extends Number> value)
    {
        return new SimpleBooleanExpression(getFloat() == saveFloat(value), List.of(this, value), getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleFloatWrapper.this.getFloat() == saveFloat(value));
            }
        };
    }

    @Override
    public NumberExpression<Float> mapFloat()
    {
        return this;
    }

    @Override
    public Float get()
    {
        return saveFloat(observable.get());
    }

    @Override
    public byte getByte()
    {
        return get().byteValue();
    }

    @Override
    public short getShort()
    {
        return get().shortValue();
    }

    @Override
    public int getInt()
    {
        return get().intValue();
    }

    @Override
    public float getFloat()
    {
        return get();
    }

    @Override
    public double getDouble()
    {
        return get().doubleValue();
    }

    @Override
    public long getLong()
    {
        return get().longValue();
    }

    @Override
    public BigDecimal getBigDecimal()
    {
        return BigDecimal.valueOf(getFloat());
    }

    @Override
    public BigInteger getBigInteger()
    {
        return BigInteger.valueOf(getLong());
    }
}
