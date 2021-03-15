package com.frechsack.dev.observer.simple;

import com.frechsack.dev.observer.core.BooleanExpression;
import com.frechsack.dev.observer.core.NumberExpression;
import com.frechsack.dev.observer.core.Observable;
import com.frechsack.dev.observer.core.ObservableSingle;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.function.Supplier;

public abstract class SimpleFloatExpression extends AbstractNumberExpression<Float> implements NumberExpression<Float>
{
    private float value;

    public SimpleFloatExpression(float initialValue, Observable parent, EventHandler eventHandler)
    {
        super(initialValue, parent, eventHandler);
        this.value = initialValue;
    }

    public SimpleFloatExpression(float initialValue, List<Observable> parentList, EventHandler eventHandler)
    {
        super(initialValue, parentList, eventHandler);
        this.value = initialValue;
    }

    public SimpleFloatExpression(float initialValue, Observable parent)
    {
        super(initialValue, parent);
        this.value = initialValue;
    }

    public SimpleFloatExpression(float initialValue, List<Observable> parentList)
    {
        super(initialValue, parentList);
        this.value = initialValue;
    }

    public SimpleFloatExpression(float initialValue)
    {
        super(initialValue);
        this.value = initialValue;
    }

    protected final void setValue(Float value)
    {
        this.value = saveFloat(value);
    }

    protected final void setValue(Number value)
    {
        this.value = saveFloat(value);
    }

    protected final void setValue(float value)
    {
        this.value = value;
    }

    @Override
    protected Float getValue()
    {
        return value;
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
                setValue(SimpleFloatExpression.this.getFloat() + saveFloat(value));
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
                setValue(SimpleFloatExpression.this.getFloat() + saveFloat(value));
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
                setValue(SimpleFloatExpression.this.getFloat() + saveFloat(value));
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
                setValue(SimpleFloatExpression.this.getFloat() - saveFloat(value));
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
                setValue(SimpleFloatExpression.this.getFloat() - saveFloat(value));
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
                setValue(SimpleFloatExpression.this.getFloat() - saveFloat(value));
            }
        };
    }

    @Override
    public NumberExpression<Float> multiply(Number value)
    {
        if (saveFloat(value) == 0) return of(0f);
        if (saveFloat(value) == 1) return this;
        return new SimpleFloatExpression(getFloat() * saveFloat(value), this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleFloatExpression.this.getFloat() * saveFloat(value));
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
                setValue(SimpleFloatExpression.this.getFloat() * saveFloat(value));
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
                setValue(SimpleFloatExpression.this.getFloat() * saveFloat(value));
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
                setValue(SimpleFloatExpression.this.getFloat() / saveFloat(value));
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
                setValue(divisor == 0 ? 0 : SimpleFloatExpression.this.getFloat() / divisor);
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
                setValue(divisor == 0 ? 0 : SimpleFloatExpression.this.getFloat() / divisor);
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
                setValue((float) Math.pow(SimpleFloatExpression.this.getFloat(), saveFloat(value)));
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
                setValue((float) Math.pow(SimpleFloatExpression.this.getFloat(), saveFloat(value)));
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
                setValue((float) Math.pow(SimpleFloatExpression.this.getFloat(), saveFloat(value)));
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
                setValue(SimpleFloatExpression.this.getFloat() % mod);
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
                setValue(mod == 0 ? 0 : SimpleFloatExpression.this.getFloat() % mod);
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
                setValue(mod == 0 ? 0 : SimpleFloatExpression.this.getFloat() % mod);
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
                setValue((float) Math.sqrt(SimpleFloatExpression.this.getFloat()));
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
                setValue(SimpleFloatExpression.this.getFloat() * -1);
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
                setValue(SimpleFloatExpression.this.getFloat() < 0 ? SimpleFloatExpression.this.getFloat() * -1 : SimpleFloatExpression.this.getFloat());
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
                setValue(SimpleFloatExpression.this.getFloat() > 0 ? SimpleFloatExpression.this.getFloat() * -1 : SimpleFloatExpression.this.getFloat());
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
                setValue(SimpleFloatExpression.this.getFloat() > saveFloat(value));
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
                setValue(SimpleFloatExpression.this.getFloat() > saveFloat(value));
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
                setValue(SimpleFloatExpression.this.getFloat() > saveFloat(value));
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
                setValue(SimpleFloatExpression.this.getFloat() >= saveFloat(value));
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
                setValue(SimpleFloatExpression.this.getFloat() >= saveFloat(value));
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
                setValue(SimpleFloatExpression.this.getFloat() >= saveFloat(value));
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
                setValue(SimpleFloatExpression.this.getFloat() < saveFloat(value));
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
                setValue(SimpleFloatExpression.this.getFloat() < saveFloat(value));
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
                setValue(SimpleFloatExpression.this.getFloat() < saveFloat(value));
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
                setValue(SimpleFloatExpression.this.getFloat() <= saveFloat(value));
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
                setValue(SimpleFloatExpression.this.getFloat() <= saveFloat(value));
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
                setValue(SimpleFloatExpression.this.getFloat() <= saveFloat(value));
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
                setValue(SimpleFloatExpression.this.getFloat() == saveFloat(value));
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
                setValue(SimpleFloatExpression.this.getFloat() == saveFloat(value));
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
                setValue(SimpleFloatExpression.this.getFloat() == saveFloat(value));
            }
        };
    }

    @Override
    public NumberExpression<Float> mapFloat()
    {
        return this;
    }

    @Override
    public byte getAsByte()
    {
        validate();
        return (byte) value;
    }

    @Override
    public short getAsShort()
    {
        validate();
        return (short) value;
    }

    @Override
    public int getAsInt()
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
    public double getAsDouble()
    {
        validate();
        return value;
    }

    @Override
    public long getAsLong()
    {
        validate();
        return (long) value;
    }

    @Override
    public BigDecimal getAsBigDecimal()
    {
        return BigDecimal.valueOf(getFloat());
    }

    @Override
    public BigInteger getAsBigInteger()
    {
        return BigInteger.valueOf(getAsLong());
    }
}
