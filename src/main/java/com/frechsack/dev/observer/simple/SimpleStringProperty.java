package com.frechsack.dev.observer.simple;

import com.frechsack.dev.observer.core.StringExpression;
import com.frechsack.dev.observer.core.StringProperty;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.util.Objects;


public class SimpleStringProperty extends AbstractProperty<String> implements StringProperty
{
    private String value;
    private transient Reference<StringExpression> toExprRef;

    public SimpleStringProperty(String initialValue, String name, Object source, EventHandler eventHandler)
    {
        super(AbstractExpression.saveString(initialValue), name, source, eventHandler);
        this.value = AbstractExpression.saveString(initialValue);
    }

    public SimpleStringProperty(String initialValue, String name, Object source)
    {
        super(AbstractExpression.saveString(initialValue), name, source);
        this.value = AbstractExpression.saveString(initialValue);
    }

    @Override
    public String get()
    {
        return value;
    }

    @Override
    public StringExpression toExpression()
    {
        StringExpression toExpr = toExprRef == null ? null : toExprRef.get();
        if (toExpr == null) toExprRef = new WeakReference<>(toExpr = new SimpleStringWrapper(this));
        return toExpr;
    }

    @Override
    public boolean set(String value)
    {
        if (Objects.equals(this.value, value)) return false;
        this.value = AbstractExpression.saveString(value);
        invalidate();
        change();
        return true;
    }

    @Override
    public Object clone() throws CloneNotSupportedException
    {
        SimpleStringProperty property = (SimpleStringProperty) super.clone();
        property.toExprRef = null;
        return property;
    }
}
