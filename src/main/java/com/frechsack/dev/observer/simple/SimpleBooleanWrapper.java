package com.frechsack.dev.observer.simple;

import com.frechsack.dev.observer.core.BooleanExpression;
import com.frechsack.dev.observer.core.ObservableSingle;

import java.util.List;
import java.util.function.Supplier;

public class SimpleBooleanWrapper extends AbstractWrapper<Boolean> implements BooleanExpression
{

    public SimpleBooleanWrapper(ObservableSingle<Boolean> observable)
    {
        super(observable);
    }

    @Override
    public BooleanExpression and(boolean value)
    {
        return !value ? of(false) : this;
    }

    @Override
    public BooleanExpression and(Supplier<Boolean> value)
    {
        return new SimpleBooleanExpression(getAsBoolean() && saveBoolean(value), this)
        {
            @Override
            protected void computeValue()
            {
                setValue(saveBoolean(value) && SimpleBooleanWrapper.this.getAsBoolean());
            }
        };
    }

    @Override
    public BooleanExpression and(ObservableSingle<Boolean> value)
    {
        return new SimpleBooleanExpression(getAsBoolean() && saveBoolean(value), List.of(this, value))
        {
            @Override
            protected void computeValue()
            {
                setValue(saveBoolean(value) && SimpleBooleanWrapper.this.getAsBoolean());
            }
        };
    }

    @Override
    public BooleanExpression or(boolean value)
    {
        return value ? of(false) : this;
    }

    @Override
    public BooleanExpression or(Supplier<Boolean> value)
    {
        return new SimpleBooleanExpression(getAsBoolean() || saveBoolean(value), this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(saveBoolean(value) || SimpleBooleanWrapper.this.getAsBoolean());
            }
        };
    }

    @Override
    public BooleanExpression or(ObservableSingle<Boolean> value)
    {
        return new SimpleBooleanExpression(getAsBoolean() || saveBoolean(value), List.of(this, value), getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(saveBoolean(value) || SimpleBooleanWrapper.this.getAsBoolean());
            }
        };
    }

    @Override
    public BooleanExpression xor(boolean value)
    {
        return new SimpleBooleanExpression(getAsBoolean() != value, this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(value != SimpleBooleanWrapper.this.getAsBoolean());
            }
        };
    }

    @Override
    public BooleanExpression xor(Supplier<Boolean> value)
    {
        return new SimpleBooleanExpression(getAsBoolean() != saveBoolean(value), this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(saveBoolean(value) != SimpleBooleanWrapper.this.getAsBoolean());
            }
        };
    }

    @Override
    public BooleanExpression xor(ObservableSingle<Boolean> value)
    {
        return new SimpleBooleanExpression(getAsBoolean() != saveBoolean(value), List.of(this, value), getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(saveBoolean(value) != SimpleBooleanWrapper.this.getAsBoolean());
            }
        };
    }

    @Override
    public BooleanExpression invert()
    {
        return new SimpleBooleanExpression(!getAsBoolean(), this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(!SimpleBooleanWrapper.this.getAsBoolean());
            }
        };
    }

    @Override
    public Boolean get()
    {
        return saveBoolean(super.get());
    }

    @Override
    public boolean getAsBoolean()
    {
        return get();
    }

    @Override
    public BooleanExpression valueIsNullExpression()
    {
        return of(false);
    }
}
