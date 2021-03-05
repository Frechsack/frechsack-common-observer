package com.frechsack.dev.observer.simple;

import com.frechsack.dev.observer.core.BooleanExpression;
import com.frechsack.dev.observer.core.NumberExpression;
import com.frechsack.dev.observer.core.StringExpression;
import com.frechsack.dev.observer.core.ObservableSingle;

import java.util.List;
import java.util.function.Supplier;

public class SimpleStringWrapper extends AbstractWrapper<String> implements StringExpression
{
    public SimpleStringWrapper(ObservableSingle<String> observable)
    {
        super(observable);
    }

    @Override
    public StringExpression append(String value)
    {
        if (value == null || value.isEmpty()) return this;
        return new SimpleStringExpression(getString() + saveString(value), this)
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleStringWrapper.this.getString() + saveString(value));
            }
        };
    }

    @Override
    public StringExpression append(Supplier<String> value)
    {
        return new SimpleStringExpression(getString() + saveString(value.get()), this)
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleStringWrapper.this.getString() + saveString(value.get()));
            }
        };
    }

    @Override
    public StringExpression append(ObservableSingle<String> value)
    {
        return new SimpleStringExpression(getString() + saveString(value.get()), List.of(this, value))
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleStringWrapper.this.getString() + saveString(value.get()));
            }
        };
    }

    @Override
    public StringExpression toLowerCaseExpression()
    {
        return new SimpleStringExpression(getString().toLowerCase(), this)
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleStringWrapper.this.getString().toLowerCase());
            }
        };
    }

    @Override
    public StringExpression toUpperCaseExpression()
    {
        return new SimpleStringExpression(getString().toUpperCase(), this)
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleStringWrapper.this.getString().toUpperCase());
            }
        };
    }

    @Override
    public StringExpression trimExpression()
    {
        return new SimpleStringExpression(getString().trim(), this)
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleStringWrapper.this.getString().trim());
            }
        };
    }

    @Override
    public BooleanExpression isEmptyExpression()
    {
        return new SimpleBooleanExpression(false, this)
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleStringWrapper.this.getString().isEmpty());
            }
        };
    }

    @Override
    public NumberExpression<Integer> lengthExpression()
    {
        return new SimpleIntegerExpression(getString().length(),this,getEventHandler()) {
            @Override
            protected void computeValue()
            {
                setValue(SimpleStringWrapper.this.getString().length());
            }
        };
    }

    @Override
    public String getString()
    {
        return get();
    }

    @Override
    public String get()
    {
        return saveString(observable.get());
    }
}
