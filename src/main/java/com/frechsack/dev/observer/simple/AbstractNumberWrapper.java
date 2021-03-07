package com.frechsack.dev.observer.simple;

import com.frechsack.dev.observer.core.BooleanExpression;
import com.frechsack.dev.observer.core.NumberExpression;
import com.frechsack.dev.observer.core.ObservableSingle;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

public abstract class AbstractNumberWrapper<E extends Number> extends AbstractWrapper<E> implements NumberExpression<E>
{
    private transient Reference<NumberExpression<Integer>> mapIntegerExprRef;
    private transient Reference<NumberExpression<Double>> mapDoubleExprRef;
    private transient Reference<NumberExpression<Float>> mapFloatExprRef;
    private transient Reference<NumberExpression<Long>> mapLongExprRef;

    public AbstractNumberWrapper(ObservableSingle<E> observable)
    {
        super(observable);
    }

    @Override
    public NumberExpression<Integer> mapInteger()
    {
        NumberExpression<Integer> mapIntegerExpr = mapIntegerExprRef == null ? null : mapIntegerExprRef.get();
        if(mapIntegerExpr == null)
        {
            mapIntegerExpr = new SimpleIntegerExpression(getAsInt(), this, getEventHandler()) {
                @Override
                protected void computeValue()
                {
                    setValue(AbstractNumberWrapper.this.getAsInt());
                }
            };
            mapIntegerExprRef = new WeakReference<>(mapIntegerExpr);
        }
        return mapIntegerExpr;
    }

    @Override
    public  NumberExpression<Long> mapLong()
    {
        NumberExpression<Long> mapLongExpr = mapLongExprRef == null ? null : mapLongExprRef.get();
        if(mapLongExpr == null)
        {
            mapLongExpr = new SimpleLongExpression(getAsLong(), this, getEventHandler()) {
                @Override
                protected void computeValue()
                {
                    setValue(AbstractNumberWrapper.this.getAsLong());
                }
            };
            mapLongExprRef = new WeakReference<>(mapLongExpr);
        }
        return mapLongExpr;
    }

    @Override
    public NumberExpression<Double> mapDouble()
    {
        NumberExpression<Double> mapDoubleExpr = mapDoubleExprRef == null ? null : mapDoubleExprRef.get();
        if(mapDoubleExpr == null)
        {
            mapDoubleExpr = new SimpleDoubleExpression(getAsDouble(), this, getEventHandler()) {
                @Override
                protected void computeValue()
                {
                    setValue(AbstractNumberWrapper.this.getAsDouble());
                }
            };
            mapDoubleExprRef = new WeakReference<>(mapDoubleExpr);
        }
        return mapDoubleExpr;
    }

    @Override
    public NumberExpression<Float> mapFloat()
    {
        NumberExpression<Float> mapFloatExpr = mapFloatExprRef == null ? null : mapFloatExprRef.get();
        if(mapFloatExpr == null)
        {
            mapFloatExpr = new SimpleFloatExpression(getFloat(),this,getEventHandler()) {
                @Override
                protected void computeValue()
                {
                    setValue(AbstractNumberWrapper.this.getFloat());
                }
            };
            mapFloatExprRef = new WeakReference<>(mapFloatExpr);
        }
        return mapFloatExpr;
    }

    @Override
    public final BooleanExpression valueIsNullExpression()
    {
        return constantExpression(false);
    }

    @Override
    public final BooleanExpression valueEqualsExpression(Object obj)
    {
        if(obj instanceof Number) return isEqualTo((Number) obj);
        return super.valueEqualsExpression(obj);
    }

    @Override
    public final BooleanExpression valueEqualsExpression(Supplier<?> obj)
    {
        boolean isValueEqual = obj.get() instanceof Number ? isEqualToOperation(get(), (Number) obj.get()) : Objects.equals(get(), obj.get());
        return new SimpleBooleanExpression(isValueEqual,this,getEventHandler()) {
            @Override
            protected void computeValue()
            {
                boolean isValueEqual = obj.get() instanceof Number ? isEqualToOperation(AbstractNumberWrapper.this.get(), (Number) obj.get()) : Objects.equals(AbstractNumberWrapper.this.get(), obj.get());
                setValue(isValueEqual);
            }
        };
    }

    @Override
    public final BooleanExpression valueEqualsExpression(ObservableSingle<?> obj)
    {
        boolean isValueEqual = obj.get() instanceof Number ? isEqualToOperation(get(), (Number) obj.get()) : Objects.equals(get(), obj.get());
        return new SimpleBooleanExpression(isValueEqual, List.of(this, obj), getEventHandler()) {
            @Override
            protected void computeValue()
            {
                boolean isValueEqual = obj.get() instanceof Number ? isEqualToOperation(AbstractNumberWrapper.this.get(), (Number) obj.get()) : Objects.equals(AbstractNumberWrapper.this.get(), obj.get());
                setValue(isValueEqual);
            }
        };
    }
}
