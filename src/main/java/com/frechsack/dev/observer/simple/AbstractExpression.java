package com.frechsack.dev.observer.simple;

import com.frechsack.dev.observer.core.*;
import com.frechsack.dev.observer.util.WeakInvalidationObserver;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;

public abstract class AbstractExpression<E> extends SimpleObservableSingle<E> implements Expression<E>, Cloneable
{
    private boolean isValid = true;
    private InvalidationObserver invalidationObserver;

    private transient Reference<StringExpression> toStringExprRef;
    private transient Reference<StringExpression> valueToStringExprRef;
    private transient Reference<BooleanExpression> valueIsNullExprRef;

    public AbstractExpression(E initialValue, Observable parent, EventHandler eventHandler)
    {
        super(initialValue, eventHandler);
        invalidationObserver = event -> parentInvalidated();
        parent.addObserver(new WeakInvalidationObserver(invalidationObserver));
    }

    public AbstractExpression(E initialValue, List<Observable> parentList, EventHandler eventHandler)
    {
        super(initialValue, eventHandler);
        invalidationObserver = event -> parentInvalidated();
        WeakInvalidationObserver weakInvalidationObserver = new WeakInvalidationObserver(invalidationObserver);
        for (Observable parent : parentList) parent.addObserver(weakInvalidationObserver);
    }

    public AbstractExpression(E initialValue, Observable parent)
    {
        super(initialValue);
        invalidationObserver = event -> parentInvalidated();
        parent.addObserver(new WeakInvalidationObserver(invalidationObserver));
    }

    public AbstractExpression(E initialValue, List<Observable> parentList)
    {
        super(initialValue);
        invalidationObserver = event -> parentInvalidated();
        WeakInvalidationObserver weakInvalidationObserver = new WeakInvalidationObserver(invalidationObserver);
        for (Observable parent : parentList) parent.addObserver(weakInvalidationObserver);
    }

    public AbstractExpression(E initialValue)
    {
        super(initialValue);
    }

    /**
     * Called by the observer, that observes the parent.
     */
    private void parentInvalidated()
    {
        isValid = false;
        invalidate();
        change();
    }

    /**
     * Requests this expression to be become valid. If the expression is already valid, this method has no effect.
     */
    protected final void validate()
    {
        if (isValid) return;
        // isValid is set to true, by default it´s assumed, that an expression becomes valid after computeValue().
        // To avoid an endless loop, isValid is set to true.
        // This way, the current value of this expression can be obtained by get() unlike specific may unknown functions.
        isValid = true;
        try
        {
            computeValue();
        }
        catch (Exception e)
        {
            isValid = false;
        }
        if (isValid) logger.log(System.Logger.Level.DEBUG, "Expression: \"" + toString() + "\" was validated");
        if (!isValid) logger.log(System.Logger.Level.DEBUG, "Expression: \"" + toString() + "\" was not successfully validated.");
        change();
    }

    /**
     * Must be called when an unregular update happened. This Expression marks itself as invalid, forces an invalidation and then change.
     */
    protected final void markUpdate()
    {
        isValid = true;
        invalidate();
        change();
    }

    /**
     * Marks an Expression as valid and force a change update.
     */
    protected final void markValid()
    {
        isValid = true;
        change();
    }

    /**
     * Explicit mark this Expression as Invalid
     */
    protected final void markInvalid()
    {
        isValid = false;
        invalidate();
    }

    /**
     * Requests this expression to recalculate it's value.
     */
    protected abstract void computeValue();

    @Override
    public final E get()
    {
        validate();
        return getValue();
    }

    protected final boolean isValid()
    {
        return isValid;
    }

    /**
     * Should return the current value of this expression.
     *
     * @return The current value.
     */
    protected abstract E getValue();

    protected abstract void setValue(E value);

    @Override
    public final BooleanExpression mapBoolean(Function<Expression<E>, Boolean> map)
    {
        return new SimpleBooleanExpression(saveBoolean(map.apply(this)), this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(map.apply(AbstractExpression.this));
            }
        };
    }

    @Override
    public final <T> Expression<T> map(Function<Expression<E>, T> map)
    {
        return new SimpleObjectExpression<>(map.apply(this), this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(map.apply(AbstractExpression.this));
            }
        };
    }

    @Override
    public final StringExpression mapString(Function<Expression<E>, String> map)
    {
        return new SimpleStringExpression(saveString(map.apply(this)), this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(map.apply(AbstractExpression.this));
            }
        };
    }

    @Override
    public final NumberExpression<?> mapNumber(Function<Expression<E>, ? extends Number> map)
    {
        return mapInteger(expr -> saveNumber(map.apply(expr)).intValue());
    }

    @Override
    public final NumberExpression<Double> mapDouble(Function<Expression<E>, ? extends Number> map)
    {
        return new SimpleDoubleExpression(saveDouble(map.apply(this)), this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(map.apply(AbstractExpression.this));
            }
        };
    }

    @Override
    public final NumberExpression<Float> mapFloat(Function<Expression<E>, ? extends Number> map)
    {
        return new SimpleFloatExpression(saveFloat(map.apply(this)), this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(map.apply(AbstractExpression.this));
            }
        };
    }

    @Override
    public final NumberExpression<Integer> mapInteger(Function<Expression<E>, ? extends Number> map)
    {
        return new SimpleIntegerExpression(saveInt(map.apply(this)), this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(map.apply(AbstractExpression.this));
            }
        };
    }

    @Override
    public final NumberExpression<Long> mapLong(Function<Expression<E>, ? extends Number> map)
    {
        return new SimpleLongExpression(saveLong(map.apply(this)), this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(map.apply(AbstractExpression.this));
            }
        };
    }

    @Override
    public final BooleanExpression equalsExpression(Object obj)
    {
        return new SimpleBooleanExpression(Objects.equals(get(), obj), this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(Objects.equals(AbstractExpression.this.get(), obj));
            }
        };
    }

    @Override
    public final BooleanExpression equalsExpression(Supplier<?> obj)
    {
        return new SimpleBooleanExpression(Objects.equals(get(), obj.get()), this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(Objects.equals(AbstractExpression.this.get(), obj.get()));
            }
        };
    }

    @Override
    public final BooleanExpression equalsExpression(ObservableSingle<?> obj)
    {
        return new SimpleBooleanExpression(Objects.equals(get(), obj.get()), List.of(this, obj), getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(Objects.equals(AbstractExpression.this.get(), obj.get()));
            }
        };
    }

    @Override
    public BooleanExpression valueEqualsExpression(Object obj)
    {
        return new SimpleBooleanExpression(Objects.equals(get(),obj),this,getEventHandler()) {
            @Override
            protected void computeValue()
            {
                setValue(Objects.equals(AbstractExpression.this.get(),obj));
            }
        };
    }

    @Override
    public BooleanExpression valueEqualsExpression(Supplier<?> obj)
    {
        return new SimpleBooleanExpression(Objects.equals(get(),obj.get()),this,getEventHandler()) {
            @Override
            protected void computeValue()
            {
                setValue(Objects.equals(AbstractExpression.this.get(),obj.get()));
            }
        };
    }

    @Override
    public BooleanExpression valueEqualsExpression(ObservableSingle<?> obj)
    {
        return new SimpleBooleanExpression(Objects.equals(get(),obj.get()),this,getEventHandler()) {
            @Override
            protected void computeValue()
            {
                setValue(Objects.equals(AbstractExpression.this.get(),obj.get()));
            }
        };
    }

    @Override
    public final NumberExpression<Integer> hashCodeExpression()
    {
        return new SimpleIntegerExpression(hashCode(),this,getEventHandler()) {
            @Override
            protected void computeValue()
            {
                setValue(AbstractExpression.this.hashCode());
            }
        };
    }

    @Override
    public final NumberExpression<Integer> valueHashCodeExpression()
    {
        return new SimpleIntegerExpression(Objects.hashCode(get()),this,getEventHandler()) {
            @Override
            protected void computeValue()
            {
                setValue(Objects.hashCode(AbstractExpression.this.get()));
            }
        };
    }

    @Override
    public final Expression<E> toExpression()
    {
        return this;
    }

    @Override
    public final StringExpression toStringExpression()
    {
        StringExpression toStringExpr = toStringExprRef == null ? null : toStringExprRef.get();
        if(toStringExpr == null)
        {
            toStringExpr = new SimpleStringExpression(toString(),this,getEventHandler()) {
                @Override
                protected void computeValue()
                {
                    setValue(AbstractExpression.this.toString());
                }
            };
            toStringExprRef = new WeakReference<>(toStringExpr);
        }
        return toStringExpr;
    }

    @Override
    public final StringExpression valueToStringExpression()
    {
        StringExpression valueToStringExpr = valueToStringExprRef == null ? null : valueToStringExprRef.get();
        if(valueToStringExpr == null)
        {
            valueToStringExpr = new SimpleStringExpression(Objects.toString(get()),this,getEventHandler()) {
                @Override
                protected void computeValue()
                {
                    setValue(Objects.toString(AbstractExpression.this.get()));
                }
            };
            valueToStringExprRef = new WeakReference<>(valueToStringExpr);
        }
        return valueToStringExpr;
    }

    @Override
    public BooleanExpression valueIsNullExpression()
    {
        BooleanExpression valueIsNullExpr = valueIsNullExprRef == null ? null : valueIsNullExprRef.get();
        if(valueIsNullExpr == null)
        {
            valueIsNullExpr = new SimpleBooleanExpression(get() == null,this,getEventHandler()) {
                @Override
                protected void computeValue()
                {
                    setValue(AbstractExpression.this.get() == null);
                }
            };
            valueIsNullExprRef = new WeakReference<>(valueIsNullExpr);
        }
        return valueIsNullExpr;
    }

    @Override
    public Object clone() throws CloneNotSupportedException
    {
        @SuppressWarnings("unchecked") AbstractExpression<E> clone = (AbstractExpression<E>) super.clone();
        clone.toStringExprRef = null;
        clone.valueToStringExprRef = null;
        clone.valueIsNullExprRef = null;
        return clone;
    }

    @Override
    public final String toString()
    {
        return toStringTemplate(this);
    }

    @Override
    public final boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        AbstractExpression<?> that = (AbstractExpression<?>) o;
        return Objects.equals(get(),that.get()) && Objects.equals(invalidationObserver, that.invalidationObserver);
    }

    @Override
    public final int hashCode()
    {
        return Objects.hash(super.hashCode(), get(), invalidationObserver);
    }
}
