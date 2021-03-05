package com.frechsack.dev.observer.simple;

import com.frechsack.dev.observer.core.BooleanExpression;
import com.frechsack.dev.observer.core.NumberExpression;
import com.frechsack.dev.observer.core.Observable;
import com.frechsack.dev.observer.core.ObservableSingle;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

public abstract class AbstractNumberExpression<E extends Number> extends AbstractExpression<E> implements NumberExpression<E>
{
    private transient Reference<NumberExpression<Integer>> mapIntegerExprRef;
    private transient Reference<NumberExpression<Double>> mapDoubleExprRef;
    private transient Reference<NumberExpression<Float>> mapFloatExprRef;
    private transient Reference<NumberExpression<Long>> mapLongExprRef;

    public AbstractNumberExpression(E initialValue, Observable parent, EventHandler eventHandler)
    {
        super(initialValue, parent, eventHandler);
    }

    public AbstractNumberExpression(E initialValue, List<Observable> parentList, EventHandler eventHandler)
    {
        super(initialValue, parentList, eventHandler);
    }

    public AbstractNumberExpression(E initialValue, Observable parent)
    {
        super(initialValue, parent);
    }

    public AbstractNumberExpression(E initialValue, List<Observable> parentList)
    {
        super(initialValue, parentList);
    }

    public AbstractNumberExpression(E initialValue)
    {
        super(initialValue);
    }

    @Override
    public NumberExpression<Integer> mapInteger()
    {
        NumberExpression<Integer> mapIntegerExpr = mapIntegerExprRef == null ? null : mapIntegerExprRef.get();
        if(mapIntegerExpr == null)
        {
            mapIntegerExpr = new SimpleIntegerExpression(getInt(),this,getEventHandler()) {
                @Override
                protected void computeValue()
                {
                    setValue(AbstractNumberExpression.this.getInt());
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
            mapLongExpr = new SimpleLongExpression(getLong(),this,getEventHandler()) {
                @Override
                protected void computeValue()
                {
                    setValue(AbstractNumberExpression.this.getLong());
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
            mapDoubleExpr = new SimpleDoubleExpression(getDouble(),this,getEventHandler()) {
                @Override
                protected void computeValue()
                {
                    setValue(AbstractNumberExpression.this.getDouble());
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
                    setValue(AbstractNumberExpression.this.getFloat());
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
                boolean isValueEqual = obj.get() instanceof Number ? isEqualToOperation(AbstractNumberExpression.this.get(), (Number) obj.get()) : Objects.equals(AbstractNumberExpression.this.get(), obj.get());
                setValue(isValueEqual);
            }
        };
    }

    @Override
    public final BooleanExpression valueEqualsExpression(ObservableSingle<?> obj)
    {
        boolean isValueEqual = obj.get() instanceof Number ? isEqualToOperation(get(), (Number) obj.get()) : Objects.equals(get(), obj.get());
        return new SimpleBooleanExpression(isValueEqual,List.of(this,obj),getEventHandler()) {
            @Override
            protected void computeValue()
            {
                boolean isValueEqual = obj.get() instanceof Number ? isEqualToOperation(AbstractNumberExpression.this.get(), (Number) obj.get()) : Objects.equals(AbstractNumberExpression.this.get(), obj.get());
                setValue(isValueEqual);
            }
        };
    }

    @Override
    public final Object clone() throws CloneNotSupportedException
    {
        @SuppressWarnings("unchecked") AbstractNumberExpression<E> clone = (AbstractNumberExpression<E>) super.clone();
        clone.mapIntegerExprRef = null;
        clone.mapFloatExprRef = null;
        clone.mapLongExprRef = null;
        clone.mapDoubleExprRef = null;
        return clone;
    }
}
