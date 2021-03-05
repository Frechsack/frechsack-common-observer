package com.frechsack.dev.observer.simple;

import com.frechsack.dev.observer.core.*;
import com.frechsack.dev.observer.core.BooleanExpression;
import com.frechsack.dev.observer.core.NumberExpression;
import com.frechsack.dev.observer.core.StringExpression;

import java.util.List;
import java.util.function.Supplier;

public abstract class SimpleStringExpression extends AbstractExpression<String> implements StringExpression
{
    private String value;

    public SimpleStringExpression(String initialValue, Observable parent, EventHandler eventHandler)
    {
        super(saveString(initialValue), parent, eventHandler);
        this.value = saveString(initialValue);
    }

    public SimpleStringExpression(String initialValue, List<Observable> parentList, EventHandler eventHandler)
    {
        super(saveString(initialValue), parentList, eventHandler);
        this.value = saveString(initialValue);
    }

    public SimpleStringExpression(String initialValue, Observable parent)
    {
        super(saveString(initialValue), parent);
        this.value = saveString(initialValue);
    }

    public SimpleStringExpression(String initialValue, List<Observable> parentList)
    {
        super(saveString(initialValue), parentList);
        this.value = saveString(initialValue);
    }

    public SimpleStringExpression(String initialValue)
    {
        super(saveString(initialValue));
        this.value = saveString(initialValue);
    }


    protected void setValue(String value)
    {
        this.value = value == null ? "" : value;
    }

    @Override
    protected String getValue()
    {
        return value;
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
                setValue(SimpleStringExpression.this.getString() + saveString(value));
            }
        };
    }

    @Override
    public StringExpression append(Supplier<String> value)
    {
        return new SimpleStringExpression(getString() + saveString(value), this)
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleStringExpression.this.getString() + saveString(value.get()));
            }
        };
    }

    @Override
    public StringExpression append(ObservableSingle<String> value)
    {
        return new SimpleStringExpression(getString() + saveString(value), List.of(this, value))
        {
            @Override
            protected void computeValue()
            {
                setValue(SimpleStringExpression.this.getString() + saveString(value.get()));
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
                setValue(SimpleStringExpression.this.getString().toLowerCase());
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
                setValue(SimpleStringExpression.this.getString().toUpperCase());
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
                setValue(SimpleStringExpression.this.getString().trim());
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
                setValue(SimpleStringExpression.this.getString().isEmpty());
            }
        };
    }

    @Override
    public String getString()
    {
        validate();
        return value;
    }

    @Override
    public NumberExpression<Integer> lengthExpression()
    {
        return new SimpleIntegerExpression(getString().length(),this,getEventHandler()) {
            @Override
            protected void computeValue()
            {
                setValue(SimpleStringExpression.this.getString().length());
            }
        };
    }
}
