package com.frechsack.dev.observer.core;

import com.frechsack.dev.observer.simple.*;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Expressions are used as a relation between a value and a mapping.
 * <p>
 * Unlike a {@link Property} an Expression is based on a parent. In common implementations the parent is an {@link ObservableSingle}.
 * <p>
 * When the parent`s value changes, this Expression should recalculate it´s value based on it´s mapping.
 * An Expression contains some basic functions to create other Expressions based on this one.
 *
 * @param <E> The value type.
 * @author Frechsack
 */
public interface Expression<E> extends ObservableSingle<E>
{
    /**
     * Creates a new {@link BooleanExpression} with the specified mapping function. The function specifies how this Expression will be converted to a boolean.
     * The returned BooleanExpression should update it´s value when this Expression changes it´s value.
     *
     * @param map The mapping function. It´s recommended that the mapping function returns a non null value.
     * @return Returns a new BooleanExpression with the specified mapping.
     * @implNote The default implementation will return a {@link SimpleBooleanExpression} with the default {@link com.frechsack.dev.observer.simple.EventHandler}.
     * @see BooleanExpression
     * @see SimpleObservables
     * @see SimpleBooleanExpression
     * @see com.frechsack.dev.observer.simple.EventHandler
     */
    default BooleanExpression mapBoolean(Function<Expression<E>, Boolean> map)
    {
        return new SimpleBooleanExpression(SimpleObservables.saveBoolean(map.apply(this)), this)
        {
            @Override
            protected void computeValue()
            {
                setValue(map.apply(Expression.this));
            }
        };
    }

    /**
     * Creates a new {@link StringExpression} with the specified mapping function. The function specifies how this Expression will be converted to a String.
     * The returned StringExpression should update it´s value when this Expression changes it´s value.
     *
     * @param map The mapping function. It´s recommended that the mapping function returns a non null value.
     * @return Returns a new StringExpression with the specified mapping.
     * @implNote The default implementation will return a {@link SimpleStringExpression} with the default {@link com.frechsack.dev.observer.simple.EventHandler}.
     * @see StringExpression
     * @see SimpleStringExpression
     * @see SimpleObservables
     * @see com.frechsack.dev.observer.simple.EventHandler
     */
    default StringExpression mapString(Function<Expression<E>, String> map)
    {
        return new SimpleStringExpression(SimpleObservables.saveString(map.apply(this)), this)
        {
            @Override
            protected void computeValue()
            {
                setValue(map.apply(Expression.this));
            }
        };
    }

    /**
     * Creates a new {@link NumberExpression} with the specified mapping function. The function specifies how this Expression will be converted to a Number.
     * The returned NumberExpression should update it´s value when this Expression changes it´s value.
     *
     * @param map The mapping function. It´s recommended that the mapping function returns a non null value.
     * @return Returns a new NumberExpression with the specified mapping.
     * @implNote The default implementation will return a {@link com.frechsack.dev.observer.simple.SimpleNumberExpression} with the default {@link com.frechsack.dev.observer.simple.EventHandler}.
     * @see NumberExpression
     * @see com.frechsack.dev.observer.simple.SimpleNumberExpression
     * @see SimpleObservables
     * @see com.frechsack.dev.observer.simple.EventHandler
     */
    default NumberExpression<?> mapNumber(Function<Expression<E>, ? extends Number> map)
    {
        return new SimpleNumberExpression(map.apply(this), this)
        {
            @Override
            protected void computeValue()
            {
                setValue(map.apply(Expression.this));
            }
        };
    }

    /**
     * Creates a new {@link NumberExpression} with the specified mapping function. The created expression calculates it´s value as an integer. The function specifies how this Expression will be converted to an integer.
     * The returned NumberExpression should update it´s value when this Expression changes it´s value.
     *
     * @param map The mapping function. It´s recommended that the mapping function returns a non null value.
     * @return Returns a new NumberExpression with the specified mapping.
     * @implNote The default implementation will return a {@link com.frechsack.dev.observer.simple.SimpleIntegerExpression} with the default {@link com.frechsack.dev.observer.simple.EventHandler}.
     * @see NumberExpression
     * @see com.frechsack.dev.observer.simple.SimpleIntegerExpression
     * @see SimpleObservables
     * @see com.frechsack.dev.observer.simple.EventHandler
     */
    default NumberExpression<Integer> mapInteger(Function<Expression<E>, Integer> map)
    {
        return new SimpleIntegerExpression(SimpleObservables.saveInt(map.apply(this)), this)
        {
            @Override
            protected void computeValue()
            {
                setValue(map.apply(Expression.this));
            }
        };
    }

    /**
     * Creates a new {@link NumberExpression} with the specified mapping function. The created expression calculates it´s value as a long. The function specifies how this Expression will be converted to a long.
     * The returned NumberExpression should update it´s value when this Expression changes it´s value.
     *
     * @param map The mapping function. It´s recommended that the mapping function returns a non null value.
     * @return Returns a new NumberExpression with the specified mapping.
     * @implNote The default implementation will return a {@link com.frechsack.dev.observer.simple.SimpleLongExpression} with the default {@link com.frechsack.dev.observer.simple.EventHandler}.
     * @see NumberExpression
     * @see SimpleLongExpression
     * @see SimpleObservables
     * @see EventHandler
     */
    default NumberExpression<Long> mapLong(Function<Expression<E>, Long> map)
    {
        return new SimpleLongExpression(SimpleObservables.saveLong(map.apply(this)), this)
        {
            @Override
            protected void computeValue()
            {
                setValue(map.apply(Expression.this));
            }
        };
    }

    /**
     * Creates a new {@link NumberExpression} with the specified mapping function. The created expression calculates it´s value as a float. The function specifies how this Expression will be converted to a float.
     * The returned NumberExpression should update it´s value when this Expression changes it´s value.
     *
     * @param map The mapping function. It´s recommended that the mapping function returns a non null value.
     * @return Returns a new NumberExpression with the specified mapping.
     * @implNote The default implementation will return a {@link com.frechsack.dev.observer.simple.SimpleFloatExpression} with the default {@link com.frechsack.dev.observer.simple.EventHandler}.
     * @see NumberExpression
     * @see SimpleFloatExpression
     * @see SimpleObservables
     * @see EventHandler
     */
    default NumberExpression<Float> mapFloat(Function<Expression<E>, Float> map)
    {
        return new SimpleFloatExpression(SimpleObservables.saveFloat(map.apply(this)), this)
        {
            @Override
            protected void computeValue()
            {
                setValue(map.apply(Expression.this));
            }
        };
    }

    /**
     * Creates a new {@link NumberExpression} with the specified mapping function. The created expression calculates it´s value as a double. The function specifies how this Expression will be converted to a double.
     * The returned NumberExpression should update it´s value when this Expression changes it´s value.
     *
     * @param map The mapping function. It´s recommended that the mapping function returns a non null value.
     * @return Returns a new NumberExpression with the specified mapping.
     * @implNote The default implementation will return a {@link com.frechsack.dev.observer.simple.SimpleDoubleExpression} with the default {@link com.frechsack.dev.observer.simple.EventHandler}.
     * @see NumberExpression
     * @see SimpleDoubleExpression
     * @see SimpleObservables
     * @see EventHandler
     */
    default NumberExpression<Double> mapDouble(Function<Expression<E>, Double> map)
    {
        return new SimpleDoubleExpression(SimpleObservables.saveDouble(map.apply(this)), this)
        {
            @Override
            protected void computeValue()
            {
                setValue(map.apply(Expression.this));
            }
        };
    }

    /**
     * Creates a new Expression with the specified mapping function. The function specified how this Expression will be converted.
     * The returned Expression should update it´s value when this Expression changes it´s value.
     *
     * @param map The mapping function.
     * @param <T> The result Expression´s type.
     * @return Returns a new Expression with the specified mapping.
     * @implNote The default implementation will return a {@link com.frechsack.dev.observer.simple.SimpleObjectExpression} with the default {@link com.frechsack.dev.observer.simple.EventHandler}.
     * @see SimpleObjectExpression
     * @see SimpleObservables
     * @see EventHandler
     */
    default <T> Expression<T> map(Function<Expression<E>, T> map)
    {
        return new SimpleObjectExpression<T>(map.apply(this), this)
        {
            @Override
            protected void computeValue()
            {
                setValue(map.apply(Expression.this));
            }
        };
    }

    /**
     * Returns an {@link StringExpression} that contains the result of {@link #toString()}.
     *
     * @return Returns a new Expression.
     * @implNote The default implementation will return a {@link com.frechsack.dev.observer.simple.SimpleStringExpression} with the default {@link com.frechsack.dev.observer.simple.EventHandler}.
     * @see StringExpression
     * @see SimpleStringExpression
     * @see SimpleObservables
     * @see EventHandler
     */
    default StringExpression toStringExpression()
    {
        return new SimpleStringExpression(toString(), this)
        {
            @Override
            protected void computeValue()
            {
                setValue(Expression.this.toString());
            }
        };
    }

    /**
     * Returns an {@link StringExpression} that contains the result of {@link #get()#toString()}.
     * If this Expression contains a null value, the returned expression should return a non null String.
     *
     * @return Returns a new StringExpression.
     * @implNote The default implementation will return a {@link com.frechsack.dev.observer.simple.SimpleStringExpression} with the default {@link com.frechsack.dev.observer.simple.EventHandler}.
     * @see StringExpression
     * @see SimpleStringExpression
     * @see SimpleObservables
     * @see EventHandler
     */
    default StringExpression valueToStringExpression()
    {
        return new SimpleStringExpression(Objects.toString(get()), this)
        {
            @Override
            protected void computeValue()
            {
                setValue(Objects.toString(Expression.this.get()));
            }
        };
    }

    /**
     * Creates a new {@link BooleanExpression} that contains the result of {@link java.util.Objects#equals(Object, Object)}.
     * If this Expression and the specified object are equal, the BooleanExpression will contain true - else false.
     *
     * @param obj The Object that will be checked if it´s equal to this Expression.
     * @return Returns a new BooleanExpression.
     * @implNote The default implementation will return a {@link com.frechsack.dev.observer.simple.SimpleBooleanExpression} with the default {@link com.frechsack.dev.observer.simple.EventHandler}.
     * @see BooleanExpression
     * @see SimpleBooleanExpression
     * @see SimpleObservables
     * @see EventHandler
     */
    default BooleanExpression equalsExpression(Object obj)
    {
        return new SimpleBooleanExpression(Objects.equals(obj, get()), this)
        {
            @Override
            protected void computeValue()
            {
                setValue(Objects.equals(Expression.this, obj));
            }
        };
    }

    /**
     * Creates a new {@link BooleanExpression} that contains the result of {@link java.util.Objects#equals(Object, Object)}.
     * If this Expression and the specified object are equal, the BooleanExpression will contain true - else false.
     *
     * @param obj The Supplier`s value will be checked if it´s equal to this Expression.
     * @return Returns a new BooleanExpression.
     * @implNote The default implementation will return a {@link com.frechsack.dev.observer.simple.SimpleBooleanExpression} with the default {@link com.frechsack.dev.observer.simple.EventHandler}.
     * @see BooleanExpression
     * @see SimpleBooleanExpression
     * @see SimpleObservables
     * @see EventHandler
     */
    default BooleanExpression equalsExpression(Supplier<?> obj)
    {
        return new SimpleBooleanExpression(Objects.equals(obj.get(), get()), this)
        {
            @Override
            protected void computeValue()
            {
                setValue(Objects.equals(Expression.this, obj.get()));
            }
        };
    }

    /**
     * Creates a new {@link BooleanExpression} that contains the result of {@link java.util.Objects#equals(Object, Object)}.
     * If this Expression and the specified object are equal, the BooleanExpression will contain true - else false.
     * The {@link ObservableSingle} will be observed. Anytime the observable changes it´s value, the returned Expression will update it´s value.
     *
     * @param obj The Supplier`s value will be checked if it´s equal to this Expression.
     * @return Returns a new BooleanExpression.
     * @implNote The default implementation will return a {@link com.frechsack.dev.observer.simple.SimpleBooleanExpression} with the default {@link com.frechsack.dev.observer.simple.EventHandler}.
     * @see BooleanExpression
     * @see SimpleBooleanExpression
     * @see SimpleObservables
     * @see EventHandler
     */
    default BooleanExpression equalsExpression(ObservableSingle<?> obj)
    {
        return new SimpleBooleanExpression(Objects.equals(get(), obj.get()), List.of(this, obj))
        {
            @Override
            protected void computeValue()
            {
                setValue(Objects.equals(Expression.this, obj.get()));
            }
        };
    }

    /**
     * Creates a new {@link BooleanExpression} that contains the result of {@link java.util.Objects#equals(Object, Object)}.
     * If the value of this Expression and the specified object are equal, the BooleanExpression will contain true - else false.
     *
     * @param obj The Object that will be checked if it´s equal to this Expression´s value.
     * @return Returns a new BooleanExpression.
     * @implNote The default implementation will return a {@link com.frechsack.dev.observer.simple.SimpleBooleanExpression} with the default {@link com.frechsack.dev.observer.simple.EventHandler}.
     * @see BooleanExpression
     * @see SimpleBooleanExpression
     * @see SimpleObservables
     * @see EventHandler
     */
    default BooleanExpression valueEqualsExpression(Object obj)
    {
        return new SimpleBooleanExpression(Objects.equals(obj, get()), this)
        {
            @Override
            protected void computeValue()
            {
                setValue(Objects.equals(Expression.this.get(), obj));
            }
        };
    }

    /**
     * Creates a new {@link BooleanExpression} that contains the result of {@link java.util.Objects#equals(Object, Object)}.
     * If the value of this Expression and the specified object are equal, the BooleanExpression will contain true - else false.
     *
     * @param obj The Supplier`s value will be checked if it´s equal to this Expression´s value.
     * @return Returns a new BooleanExpression.
     * @implNote The default implementation will return a {@link com.frechsack.dev.observer.simple.SimpleBooleanExpression} with the default {@link com.frechsack.dev.observer.simple.EventHandler}.
     * @see BooleanExpression
     * @see SimpleBooleanExpression
     * @see SimpleObservables
     * @see EventHandler
     */
    default BooleanExpression valueEqualsExpression(Supplier<?> obj)
    {
        return new SimpleBooleanExpression(Objects.equals(obj.get(), get()), this)
        {
            @Override
            protected void computeValue()
            {
                setValue(Objects.equals(Expression.this.get(), obj.get()));
            }
        };
    }

    /**
     * Creates a new {@link BooleanExpression} that contains the result of {@link java.util.Objects#equals(Object, Object)}.
     * If the value of this Expression and the specified object are equal, the BooleanExpression will contain true - else false.
     * The {@link ObservableSingle} will be observed. Anytime the observable changes it´s value, the returned Expression will update it´s value.
     *
     * @param obj The Supplier`s value will be checked if it´s equal to this Expression´s value.
     * @return Returns a new BooleanExpression.
     * @implNote The default implementation will return a {@link com.frechsack.dev.observer.simple.SimpleBooleanExpression} with the default {@link com.frechsack.dev.observer.simple.EventHandler}.
     * @see BooleanExpression
     * @see SimpleBooleanExpression
     * @see SimpleObservables
     * @see EventHandler
     */
    default BooleanExpression valueEqualsExpression(ObservableSingle<?> obj)
    {
        return new SimpleBooleanExpression(Objects.equals(get(), obj.get()), List.of(this, obj))
        {
            @Override
            protected void computeValue()
            {
                setValue(Objects.equals(Expression.this.get(), obj.get()));
            }
        };
    }

    /**
     * Creates a new {@link BooleanExpression} that checks if this Expression´s value is null.
     *
     * @return Returns a new BooleanExpression.
     * @implNote The default implementation will return a {@link com.frechsack.dev.observer.simple.SimpleBooleanExpression} with the default {@link com.frechsack.dev.observer.simple.EventHandler}.
     * @see BooleanExpression
     * @see SimpleBooleanExpression
     * @see SimpleObservables
     * @see EventHandler
     */
    default BooleanExpression valueIsNullExpression()
    {
        return new SimpleBooleanExpression(get() == null, this)
        {
            @Override
            protected void computeValue()
            {
                setValue(Expression.this.get() == null);
            }
        };
    }

    /**
     * Creates a new {@link NumberExpression} that contains the {@link Object#hashCode()} of this Expression.
     *
     * @return Returns a new NumberExpression.
     * @implNote The default implementation will return a {@link com.frechsack.dev.observer.simple.SimpleIntegerExpression} with the default {@link com.frechsack.dev.observer.simple.EventHandler}.
     * @see NumberExpression
     * @see SimpleIntegerExpression
     * @see SimpleObservables
     * @see EventHandler
     */
    default NumberExpression<Integer> hashCodeExpression()
    {
        return new SimpleIntegerExpression(Objects.hashCode(this), this)
        {
            @Override
            protected void computeValue()
            {
                setValue(Objects.hashCode(Expression.this));
            }
        };
    }

    /**
     * Creates a new {@link NumberExpression} that contains the {@link Object#hashCode()} of this Expression´s value.
     *
     * @return Returns a new NumberExpression.
     * @implNote The default implementation will return a {@link com.frechsack.dev.observer.simple.SimpleIntegerExpression} with the default {@link com.frechsack.dev.observer.simple.EventHandler}.
     * @see NumberExpression
     * @see SimpleIntegerExpression
     * @see SimpleObservables
     * @see EventHandler
     */
    default NumberExpression<Integer> valueHashCodeExpression()
    {
        return new SimpleIntegerExpression(Objects.hashCode(this), this)
        {
            @Override
            protected void computeValue()
            {
                setValue(Objects.hashCode(Expression.this));
            }
        };
    }
}
