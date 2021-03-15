package com.frechsack.dev.observer.simple;

import com.frechsack.dev.observer.core.*;
import com.frechsack.dev.observer.core.BooleanExpression;
import com.frechsack.dev.observer.core.Expression;
import com.frechsack.dev.observer.core.NumberExpression;
import com.frechsack.dev.observer.core.StringExpression;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;

public abstract class AbstractWrapper<E> extends SimpleObservables implements Expression<E>
{
    protected final ObservableSingle<E> observable;

    private transient Reference<StringExpression> toStringExprRef;
    private transient Reference<StringExpression> valueToStringExprRef;
    private transient Reference<BooleanExpression> valueIsNullExprRef;

    public AbstractWrapper(ObservableSingle<E> observable) {this.observable = observable;}

    protected EventHandler getEventHandler()
    {
        return observable instanceof SimpleObservable ? ((SimpleObservable) observable).getEventHandler() : null;
    }


    @Override
    public final BooleanExpression mapBoolean(Function<Expression<E>, Boolean> map)
    {
        return new SimpleBooleanExpression(saveBoolean(map.apply(this)), this, getEventHandler())
        {
            @Override
            protected void computeValue()
            {
                setValue(map.apply(AbstractWrapper.this));
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
                setValue(map.apply(AbstractWrapper.this));
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
                setValue(map.apply(AbstractWrapper.this));
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
                setValue(map.apply(AbstractWrapper.this));
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
                setValue(map.apply(AbstractWrapper.this));
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
                setValue(map.apply(AbstractWrapper.this));
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
                setValue(map.apply(AbstractWrapper.this));
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
                setValue(Objects.equals(AbstractWrapper.this.get(), obj));
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
                setValue(Objects.equals(AbstractWrapper.this.get(), obj.get()));
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
                setValue(Objects.equals(AbstractWrapper.this.get(), obj.get()));
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
                setValue(Objects.equals(AbstractWrapper.this.get(),obj));
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
                setValue(Objects.equals(AbstractWrapper.this.get(),obj.get()));
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
                setValue(Objects.equals(AbstractWrapper.this.get(),obj.get()));
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
                setValue(AbstractWrapper.this.hashCode());
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
                setValue(Objects.hashCode(AbstractWrapper.this.get()));
            }
        };
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
                    setValue(AbstractWrapper.this.toString());
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
                    setValue(Objects.toString(AbstractWrapper.this.get()));
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
                    setValue(AbstractWrapper.this.get() == null);
                }
            };
            valueIsNullExprRef = new WeakReference<>(valueIsNullExpr);
        }
        return valueIsNullExpr;
    }

    @Override
    public boolean addObserver(SingleChangeObserver<? super E> observer)
    {
        return observable.addObserver(observer);
    }

    @Override
    public boolean removeObserver(SingleChangeObserver<? super E> observer)
    {
        return observable.removeObserver(observer);
    }

    @Override
    public boolean containsObserver(SingleChangeObserver<? super E> observer)
    {
        return observable.containsObserver(observer);
    }

    @Override
    public final Expression<E> toExpression()
    {
        return this;
    }

    @Override
    public boolean addObserver(InvalidationObserver observer)
    {
        return observable.addObserver(observer);
    }

    @Override
    public boolean removeObserver(InvalidationObserver observer)
    {
        return observable.removeObserver(observer);
    }

    @Override
    public boolean containsObserver(InvalidationObserver observer)
    {
        return observable.containsObserver(observer);
    }

    @Override
    public boolean isObserved()
    {
        return observable.isObserved();
    }

    @Override
    public E get()
    {
        return observable.get();
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
        AbstractWrapper<?> that = (AbstractWrapper<?>) o;
        return Objects.equals(observable, that.observable);
    }

    @Override
    public final int hashCode()
    {
        return Objects.hash(observable);
    }
}
