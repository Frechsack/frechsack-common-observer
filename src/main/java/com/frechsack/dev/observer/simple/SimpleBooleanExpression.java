package com.frechsack.dev.observer.simple;

import com.frechsack.dev.observer.core.BooleanExpression;
import com.frechsack.dev.observer.core.Observable;
import com.frechsack.dev.observer.core.ObservableSingle;

import java.util.List;
import java.util.function.Supplier;

public abstract class SimpleBooleanExpression extends AbstractExpression<Boolean> implements BooleanExpression
{
    private boolean value;

    public SimpleBooleanExpression(boolean initialValue, Observable parent, EventHandler eventHandler)
    {
        super(initialValue, parent, eventHandler);
        this.value = initialValue;
    }

    public SimpleBooleanExpression(boolean initialValue, List<Observable> parentList, EventHandler eventHandler)
    {
        super(initialValue, parentList, eventHandler);
        this.value = initialValue;
    }

    public SimpleBooleanExpression(boolean initialValue, Observable parent)
    {
        super(initialValue, parent);
        this.value = initialValue;
    }

    public SimpleBooleanExpression(boolean initialValue, List<Observable> parentList)
    {
        super(initialValue, parentList);
        this.value = initialValue;
    }

    public SimpleBooleanExpression(boolean initialValue)
    {
        super(initialValue);
        this.value = initialValue;
    }

    @Override
    protected Boolean getValue()
    {
        return value;
    }

    protected final void setValue(Boolean value)
    {
        this.value = saveBoolean(value);
    }

    protected final void setValue(boolean value)
    {
        this.value = value;
    }


    @Override
    public BooleanExpression and(boolean value)
    {
        return !value ? constantExpression(false) : this;
    }

    @Override
    public BooleanExpression and(Supplier<Boolean> value)
    {
        return new SimpleBooleanExpression(getBoolean() && saveBoolean(value), this)
        {
            @Override
            protected void computeValue()
            {
                setValue(saveBoolean(value) && SimpleBooleanExpression.this.getBoolean());
            }
        };
    }

    @Override
    public BooleanExpression and(ObservableSingle<Boolean> value)
    {
        return new SimpleBooleanExpression(getBoolean() && saveBoolean(value), List.of(this, value))
        {
            @Override
            protected void computeValue()
            {
                setValue(saveBoolean(value) && SimpleBooleanExpression.this.getBoolean());
            }
        };
    }

    @Override
    public BooleanExpression or(boolean value)
    {
        return value ? constantExpression(false) : this;
    }

    @Override
    public BooleanExpression or(Supplier<Boolean> value)
    {
        return new SimpleBooleanExpression(getBoolean() || saveBoolean(value), this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(saveBoolean(value) || SimpleBooleanExpression.this.getBoolean());
            }
        };
    }

    @Override
    public BooleanExpression or(ObservableSingle<Boolean> value)
    {
        return new SimpleBooleanExpression(getBoolean() || saveBoolean(value), List.of(this, value), getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(saveBoolean(value) || SimpleBooleanExpression.this.getBoolean());
            }
        };
    }

    @Override
    public BooleanExpression xor(boolean value)
    {
        return new SimpleBooleanExpression(getBoolean() != value, this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(value != SimpleBooleanExpression.this.getBoolean());
            }
        };
    }

    @Override
    public BooleanExpression xor(Supplier<Boolean> value)
    {
        return new SimpleBooleanExpression(getBoolean() != saveBoolean(value), this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(saveBoolean(value) != SimpleBooleanExpression.this.getBoolean());
            }
        };
    }

    @Override
    public BooleanExpression xor(ObservableSingle<Boolean> value)
    {
        return new SimpleBooleanExpression(getBoolean() != saveBoolean(value), List.of(this, value), getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(saveBoolean(value) != SimpleBooleanExpression.this.getBoolean());
            }
        };
    }

    @Override
    public BooleanExpression invert()
    {
        return new SimpleBooleanExpression(!getBoolean(), this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(!SimpleBooleanExpression.this.getBoolean());
            }
        };
    }

    @Override
    public boolean getBoolean()
    {
        validate();
        return value;
    }

    @Override
    public BooleanExpression valueIsNullExpression()
    {
        return constantExpression(false);
    }
}
