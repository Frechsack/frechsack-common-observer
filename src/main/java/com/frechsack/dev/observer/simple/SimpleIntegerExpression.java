package com.frechsack.dev.observer.simple;

import com.frechsack.dev.observer.core.*;
import com.frechsack.dev.observer.core.BooleanExpression;
import com.frechsack.dev.observer.core.Expression;
import com.frechsack.dev.observer.core.NumberExpression;
import com.frechsack.dev.observer.core.StringExpression;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

public abstract class SimpleIntegerExpression extends AbstractNumberExpression<Integer> implements NumberExpression<Integer>
{
    /**
     * Never access directly
     */
    private int value;

    public SimpleIntegerExpression(int initialValue, Observable parent, EventHandler eventHandler)
    {
        super(initialValue, parent, eventHandler);
        value = initialValue;
    }

    public SimpleIntegerExpression(int initialValue, List<Observable> parentList, EventHandler eventHandler)
    {
        super(initialValue, parentList, eventHandler);
        value = initialValue;
    }

    public SimpleIntegerExpression(int initialValue, Observable parent)
    {
        super(initialValue, parent);
        value = initialValue;
    }

    public SimpleIntegerExpression(int initialValue, List<Observable> parentList)
    {
        super(initialValue, parentList);
        value = initialValue;
    }

    public SimpleIntegerExpression(int initialValue)
    {
        super(initialValue);
        value = initialValue;
    }

    @Override
    protected Integer getValue()
    {
        return value;
    }

    protected final void setValue(Integer value)
    {
        this.value = saveInt(value);
    }

    protected final void setValue(int value)
    {
        this.value = value;
    }


    @Override
    public NumberExpression<Integer> add(Number value)
    {
        int integer = saveInt(value);
        if (integer == 0) return this;
        return new SimpleIntegerExpression(getInt() + integer, this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(integer + SimpleIntegerExpression.this.getInt());
            }
        };
    }

    @Override
    public NumberExpression<Integer> add(Supplier<? extends Number> value)
    {
        return new SimpleIntegerExpression(getInt() + saveInt(value), this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(saveInt(value) + SimpleIntegerExpression.this.getInt());
            }
        };
    }

    @Override
    public NumberExpression<Integer> add(ObservableSingle<? extends Number> value)
    {
        return new SimpleIntegerExpression(getInt() + saveInt(value), List.of(this, value), getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(saveInt(value) + SimpleIntegerExpression.this.getInt());
            }
        };
    }

    @Override
    public NumberExpression<Integer> subtract(Number value)
    {
        int integer = saveInt(value);
        if (integer == 0) return this;
        return new SimpleIntegerExpression(getInt() - integer, this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleIntegerExpression.this.getInt() - integer);
            }
        };
    }

    @Override
    public NumberExpression<Integer> subtract(Supplier<? extends Number> value)
    {
        return new SimpleIntegerExpression(getInt() - saveInt(value), this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleIntegerExpression.this.getInt() - saveInt(value));
            }
        };
    }

    @Override
    public NumberExpression<Integer> subtract(ObservableSingle<? extends Number> value)
    {
        return new SimpleIntegerExpression(getInt() - saveInt(value), List.of(this, value), getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleIntegerExpression.this.getInt() - saveInt(value));
            }
        };
    }

    @Override
    public NumberExpression<Integer> multiply(Number value)
    {
        int integer = saveInt(value);
        if (integer == 0) constantExpression(0);
        if (integer == 1) return this;
        return new SimpleIntegerExpression(getInt() * integer, this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleIntegerExpression.this.getInt() * integer);
            }
        };
    }

    @Override
    public NumberExpression<Integer> multiply(Supplier<? extends Number> value)
    {
        return new SimpleIntegerExpression(getInt() * saveInt(value), this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(saveInt(value) * SimpleIntegerExpression.this.get());
            }
        };
    }

    @Override
    public NumberExpression<Integer> multiply(ObservableSingle<? extends Number> value)
    {
        return new SimpleIntegerExpression(getInt() * saveInt(value), List.of(this, value), getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(saveInt(value) * SimpleIntegerExpression.this.get());
            }
        };
    }

    @Override
    public NumberExpression<Integer> divide(Number value)
    {
        int integer = saveInt(value);
        if (integer == 0) throw divisionByZeroException();
        if (integer == 1) return this;
        return new SimpleIntegerExpression(getInt() / integer, this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleIntegerExpression.this.getInt() / integer);
            }
        };
    }

    @Override
    public NumberExpression<Integer> divide(Supplier<? extends Number> value)
    {
        int divisor = saveInt(value);
        return new SimpleIntegerExpression(divisor == 0 ? 0 : getInt() / divisor, this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                int divisor = saveInt(value);
                setValue(divisor == 0 ? 0 : SimpleIntegerExpression.this.getInt() / divisor);
            }
        };
    }

    @Override
    public NumberExpression<Integer> divide(ObservableSingle<? extends Number> value)
    {
        int divisor = saveInt(value);
        int initialValue = divisor == 0 ? 0 : getInt() / divisor;
        return new SimpleIntegerExpression(initialValue, List.of(this, value), getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                int divisor = saveInt(value);
                setValue(divisor == 0 ? 0 : SimpleIntegerExpression.this.getInt() / divisor);
            }
        };
    }

    @Override
    public NumberExpression<Integer> pow(Number value)
    {
        int expo = saveInt(value);
        if (expo == 0) return new SimpleIntegerExpression(1)
        {
            @Override
            protected void computeValue()
            {
                setValue(1);
            }
        };
        return new SimpleIntegerExpression((int) Math.pow(getInt(), expo), this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue((int) Math.pow(SimpleIntegerExpression.this.getInt(), saveInt(value)));
            }
        };
    }

    @Override
    public NumberExpression<Integer> pow(Supplier<? extends Number> value)
    {
        return new SimpleIntegerExpression((int) Math.pow(getInt(), saveInt(value)), this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue((int) Math.pow(SimpleIntegerExpression.this.getInt(), saveInt(value)));
            }
        };
    }

    @Override
    public NumberExpression<Integer> pow(ObservableSingle<? extends Number> value)
    {
        return new SimpleIntegerExpression((int) Math.pow(getInt(), saveInt(value)), List.of(this, value), getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue((int) Math.pow(SimpleIntegerExpression.this.getInt(), saveInt(value)));
            }
        };
    }

    @Override
    public NumberExpression<Integer> modulo(Number value)
    {
        int integer = saveInt(value);
        if (integer == 0) throw divisionByZeroException();
        return new SimpleIntegerExpression(getInt() % saveInt(value), this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleIntegerExpression.this.getInt() % integer);
            }
        };
    }

    @Override
    public NumberExpression<Integer> modulo(Supplier<? extends Number> value)
    {
        int integer = saveInt(value);
        return new SimpleIntegerExpression(integer == 0 ? 0 : getInt() % integer, this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                int integer = saveInt(value);
                setValue(integer == 0 ? 0 : SimpleIntegerExpression.this.getInt() % integer);
            }
        };
    }

    @Override
    public NumberExpression<Integer> modulo(ObservableSingle<? extends Number> value)
    {
        int integer = saveInt(value);
        return new SimpleIntegerExpression(integer == 0 ? 0 : getInt() % integer, List.of(this, value), getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                int integer = saveInt(value);
                setValue(integer == 0 ? 0 : SimpleIntegerExpression.this.getInt() % integer);
            }
        };
    }

    @Override
    public NumberExpression<Integer> sqrt()
    {
        return new SimpleIntegerExpression((int) Math.sqrt(getInt()), this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue((int) Math.sqrt(SimpleIntegerExpression.this.getInt()));
            }
        };
    }

    @Override
    public NumberExpression<Integer> invert()
    {
        return new SimpleIntegerExpression(-1 * getInt(), this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(-1 * SimpleIntegerExpression.this.getInt());
            }
        };
    }

    @Override
    public NumberExpression<Integer> positive()
    {
        return new SimpleIntegerExpression(getInt() < 0 ? -1 * getInt() : getInt(), this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleIntegerExpression.this.getInt() < 0 ? SimpleIntegerExpression.this.getInt() * -1 : SimpleIntegerExpression.this.getInt());
            }
        };
    }

    @Override
    public NumberExpression<Integer> negative()
    {
        return new SimpleIntegerExpression(getInt() > 0 ? -1 * getInt() : getInt(), this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleIntegerExpression.this.getInt() > 0 ? SimpleIntegerExpression.this.getInt() * -1 : SimpleIntegerExpression.this.getInt());
            }
        };
    }

    @Override
    public BooleanExpression isBiggerThan(Number value)
    {
        return new SimpleBooleanExpression(getInt() > saveInt(value), this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleIntegerExpression.this.getInt() > saveInt(value));
            }
        };
    }

    @Override
    public BooleanExpression isBiggerThan(Supplier<? extends Number> value)
    {
        return new SimpleBooleanExpression(getInt() > saveInt(value), this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleIntegerExpression.this.getInt() > saveInt(value));
            }
        };
    }

    @Override
    public BooleanExpression isBiggerThan(ObservableSingle<? extends Number> value)
    {
        return new SimpleBooleanExpression(getInt() > saveInt(value), List.of(this, value), getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleIntegerExpression.this.getInt() > saveInt(value));
            }
        };
    }

    @Override
    public BooleanExpression isBiggerThanOrEqualTo(Number value)
    {
        return new SimpleBooleanExpression(getInt() >= saveInt(value), this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleIntegerExpression.this.getInt() >= saveInt(value));
            }
        };
    }

    @Override
    public BooleanExpression isBiggerThanOrEqualTo(Supplier<? extends Number> value)
    {
        return new SimpleBooleanExpression(getInt() >= saveInt(value), this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleIntegerExpression.this.getInt() >= saveInt(value));
            }
        };
    }

    @Override
    public BooleanExpression isBiggerThanOrEqualTo(ObservableSingle<? extends Number> value)
    {
        return new SimpleBooleanExpression(getInt() >= saveInt(value), List.of(this, value), getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleIntegerExpression.this.getInt() >= saveInt(value));
            }
        };
    }

    @Override
    public BooleanExpression isLessThan(Number value)
    {
        return new SimpleBooleanExpression(getInt() < saveInt(value), this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleIntegerExpression.this.getInt() < saveInt(value));
            }
        };
    }

    @Override
    public BooleanExpression isLessThan(Supplier<? extends Number> value)
    {
        return new SimpleBooleanExpression(getInt() < saveInt(value), this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleIntegerExpression.this.getInt() < saveInt(value));
            }
        };
    }

    @Override
    public BooleanExpression isLessThan(ObservableSingle<? extends Number> value)
    {
        return new SimpleBooleanExpression(getInt() < saveInt(value), List.of(this, value), getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleIntegerExpression.this.getInt() < saveInt(value));
            }
        };
    }

    @Override
    public BooleanExpression isLessThanOrEqualTo(Number value)
    {
        return new SimpleBooleanExpression(getInt() <= saveInt(value), this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleIntegerExpression.this.getInt() <= saveInt(value));
            }
        };
    }

    @Override
    public BooleanExpression isLessThanOrEqualTo(Supplier<? extends Number> value)
    {
        return new SimpleBooleanExpression(getInt() <= saveInt(value), this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleIntegerExpression.this.getInt() <= saveInt(value));
            }
        };
    }

    @Override
    public BooleanExpression isLessThanOrEqualTo(ObservableSingle<? extends Number> value)
    {
        return new SimpleBooleanExpression(getInt() <= saveInt(value), List.of(this, value), getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleIntegerExpression.this.getInt() <= saveInt(value));
            }
        };
    }

    @Override
    public BooleanExpression isEqualTo(Number value)
    {
        return new SimpleBooleanExpression(getInt() == saveInt(value), this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleIntegerExpression.this.getInt() == saveInt(value));
            }
        };
    }

    @Override
    public BooleanExpression isEqualTo(Supplier<? extends Number> value)
    {
        return new SimpleBooleanExpression(getInt() == saveInt(value), this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleIntegerExpression.this.getInt() == saveInt(value));
            }
        };
    }

    @Override
    public BooleanExpression isEqualTo(ObservableSingle<? extends Number> value)
    {
        return new SimpleBooleanExpression(getInt() == saveInt(value), List.of(this, value), getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleIntegerExpression.this.getInt() == saveInt(value));
            }
        };
    }

    @Override
    public NumberExpression<Integer> mapInteger()
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
        return value;
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
        return new BigDecimal(getInt());
    }

    @Override
    public BigInteger getBigInteger()
    {
        return BigInteger.valueOf(getInt());
    }

}
