package com.frechsack.dev.observer.core;

import com.frechsack.dev.observer.simple.*;

import java.util.function.Supplier;

/**
 * Specialised version of {@link Expression}.
 * <p>
 * A NumberExpression adds support for common numerical operations.
 * <p>
 * How this Expression handles arithmetic depends on the implementation.
 * It´s assumed that this Interface performs it´s operations based on it´s generic type.
 * When the generic is an Integer, the arithmetic should be performed on Integer values.
 * The same counts for any other Object that extends {@link Number}.
 * <p>
 * By default supported generic type´s should be:
 * <ul>
 *     <li>Double</li> <li>Long</li> <li>Float</li> <li>Integer</li>
 * </ul>
 *
 * @param <E> The value type. This type defines the arithmetic behaviour.
 * @author Frechsack
 * @see Expression
 * @see Number
 * @see Double
 * @see Long
 * @see Integer
 */
public interface NumberExpression<E extends Number> extends Expression<E>, NumberSupplier<E>
{
    /**
     * Creates a new NumberExpression with the result of an add operation with this Expression´s value and the specified value.
     * <p>
     * The operation is equal to: expression + value.
     * </p>
     * How the value will be treated, related to arithmetic, should be defined by the generic type of this Expression.
     *
     * @param value The value.
     * @return Returns a new Expression.
     */
    NumberExpression<E> add(Number value);

    /**
     * Creates a new NumberExpression with the result of an add operation with this Expression´s value and the specified Supplier´s value.
     * <p>
     * The operation is equal to: expression + value.
     * </p>
     * How the value will be treated, related to arithmetic, should be defined by the generic type of this Expression.
     *
     * @param value The value.
     * @return Returns a new Expression.
     */
    NumberExpression<E> add(Supplier<? extends Number> value);

    /**
     * Creates a new NumberExpression with the result of an add operation with this Expression´s value and the specified Supplier´s value.
     * The {@link ObservableSingle} will be observed. Anytime the observable changes it´s value, the returned Expression will update it´s value.
     * <p>
     * The operation is equal to: expression + value.
     * </p>
     * <p>
     * How the value will be treated, related to arithmetic, should be defined by the generic type of this Expression.
     *
     * @param value The value.
     * @return Returns a new Expression.
     */
    NumberExpression<E> add(ObservableSingle<? extends Number> value);

    /**
     * Creates a new NumberExpression with the result of a subtract operation with this Expression´s value and the specified value.
     * <p>
     * The operation is equal to: expression - value.
     * </p>
     * How the value will be treated, related to arithmetic, should be defined by the generic type of this Expression.
     *
     * @param value The value.
     * @return Returns a new Expression.
     */
    NumberExpression<E> subtract(Number value);


    /**
     * Creates a new NumberExpression with the result of a subtract operation with this Expression´s value and the specified Supplier´s value.
     * <p>
     * The operation is equal to: expression - value.
     * </p>
     * How the value will be treated, related to arithmetic, should be defined by the generic type of this Expression.
     *
     * @param value The value.
     * @return Returns a new Expression.
     */
    NumberExpression<E> subtract(Supplier<? extends Number> value);


    /**
     * Creates a new NumberExpression with the result of a subtract operation with this Expression´s value and the specified Supplier´s value.
     * The {@link ObservableSingle} will be observed. Anytime the observable changes it´s value, the returned Expression will update it´s value.
     * <p>
     * The operation is equal to: expression - value.
     * </p>
     * <p>
     * How the value will be treated, related to arithmetic, should be defined by the generic type of this Expression.
     *
     * @param value The value.
     * @return Returns a new Expression.
     */
    NumberExpression<E> subtract(ObservableSingle<? extends Number> value);

    /**
     * Creates a new NumberExpression with the result of a multiplication operation with this Expression´s value and the specified value.
     * <p>
     * The operation is equal to: expression * value.
     * </p>
     * How the value will be treated, related to arithmetic, should be defined by the generic type of this Expression.
     *
     * @param value The value.
     * @return Returns a new Expression.
     */
    NumberExpression<E> multiply(Number value);

    /**
     * Creates a new NumberExpression with the result of a multiplication operation with this Expression´s value and the specified Supplier´s value.
     * <p>
     * The operation is equal to: expression * value.
     * </p>
     * How the value will be treated, related to arithmetic, should be defined by the generic type of this Expression.
     *
     * @param value The value.
     * @return Returns a new Expression.
     */
    NumberExpression<E> multiply(Supplier<? extends Number> value);

    /**
     * Creates a new NumberExpression with the result of a multiplication operation with this Expression´s value and the specified Supplier´s value.
     * The {@link ObservableSingle} will be observed. Anytime the observable changes it´s value, the returned Expression will update it´s value.
     * <p>
     * The operation is equal to: expression * value.
     * </p>
     * <p>
     * How the value will be treated, related to arithmetic, should be defined by the generic type of this Expression.
     *
     * @param value The value.
     * @return Returns a new Expression.
     */
    NumberExpression<E> multiply(ObservableSingle<? extends Number> value);

    /**
     * Creates a new NumberExpression with the result of a division operation with this Expression´s value and the specified Supplier´s value.
     * The {@link ObservableSingle} will be observed. Anytime the observable changes it´s value, the returned Expression will update it´s value.
     * <p>
     * The operation is equal to: expression / value.
     * </p>
     * <p>
     * How the value will be treated, related to arithmetic, should be defined by the generic type of this Expression.
     *
     * @param value The value.
     * @return Returns a new Expression.
     * @apiNote In case the operation could not be performed because of invalid math operations, the returned expression should contain a value of zero.
     */
    NumberExpression<E> divide(Number value);

    /**
     * Creates a new NumberExpression with the result of a division operation with this Expression´s value and the specified Supplier´s value.
     * <p>
     * The operation is equal to: expression / value.
     * </p>
     * How the value will be treated, related to arithmetic, should be defined by the generic type of this Expression.
     *
     * @param value The value.
     * @return Returns a new Expression.
     * @apiNote In case the operation could not be performed because of invalid math operations, the returned expression should contain a value of zero.
     */
    NumberExpression<E> divide(Supplier<? extends Number> value);

    /**
     * Creates a new NumberExpression with the result of a division operation with this Expression´s value and the specified Supplier´s value.
     * The {@link ObservableSingle} will be observed. Anytime the observable changes it´s value, the returned Expression will update it´s value.
     * <p>
     * The operation is equal to: expression / value.
     * </p>
     * <p>
     * How the value will be treated, related to arithmetic, should be defined by the generic type of this Expression.
     *
     * @param value The value.
     * @return Returns a new Expression.
     * @apiNote In case the operation could not be performed because of invalid math operations, the returned expression should contain a value of zero.
     */
    NumberExpression<E> divide(ObservableSingle<? extends Number> value);

    /**
     * Creates a new NumberExpression with the result of a pow operation with this Expression´s value and the specified value.
     * The value of this Expression should be treated as the base and the specified value as the exponent.
     * <p>
     * The operation is equal to: expression ^ value.
     * </p>
     * How the value will be treated, related to arithmetic, should be defined by the generic type of this Expression.
     *
     * @param value The value.
     * @return Returns a new Expression.
     * @apiNote In case the operation could not be performed because of invalid math operations, the returned expression should contain a value of zero.
     */
    NumberExpression<E> pow(Number value);

    /**
     * Creates a new NumberExpression with the result of a pow operation with this Expression´s value and the specified Supplier´s value.
     * The value of this Expression should be treated as the base and the specified value as the exponent.
     * <p>
     * The operation is equal to: expression ^ value.
     * </p>
     * How the value will be treated, related to arithmetic, should be defined by the generic type of this Expression.
     *
     * @param value The value.
     * @return Returns a new Expression.
     * @apiNote In case the operation could not be performed because of invalid math operations, the returned expression should contain a value of zero.
     */
    NumberExpression<E> pow(Supplier<? extends Number> value);

    /**
     * Creates a new NumberExpression with the result of a pow operation with this Expression´s value and the specified Supplier´s value.
     * The {@link ObservableSingle} will be observed. Anytime the observable changes it´s value, the returned Expression will update it´s value.
     * <p>
     * The operation is equal to: expression ^ value.
     * </p>
     * <p>
     * How the value will be treated, related to arithmetic, should be defined by the generic type of this Expression.
     *
     * @param value The value.
     * @return Returns a new Expression.
     * @apiNote In case the operation could not be performed because of invalid math operations, the returned expression should contain a value of zero.
     */
    NumberExpression<E> pow(ObservableSingle<? extends Number> value);

    /**
     * Creates a new NumberExpression with the result of a modulo operation with this Expression´s value and the specified value.
     * <p>
     * The operation is equal to: expression % value.
     * </p>
     * How the value will be treated, related to arithmetic, should be defined by the generic type of this Expression.
     *
     * @param value The value.
     * @return Returns a new Expression.
     * @apiNote In case the operation could not be performed because of invalid math operations, the returned expression should contain a value of zero.
     */
    NumberExpression<E> modulo(Number value);

    /**
     * Creates a new NumberExpression with the result of a modulo operation with this Expression´s value and the specified Supplier´s value.
     * <p>
     * The operation is equal to: expression % value.
     * </p>
     * How the value will be treated, related to arithmetic, should be defined by the generic type of this Expression.
     *
     * @param value The value.
     * @return Returns a new Expression.
     * @apiNote In case the operation could not be performed because of invalid math operations, the returned expression should contain a value of zero.
     */
    NumberExpression<E> modulo(Supplier<? extends Number> value);

    /**
     * Creates a new NumberExpression with the result of a modulo operation with this Expression´s value and the specified Supplier´s value.
     * The {@link ObservableSingle} will be observed. Anytime the observable changes it´s value, the returned Expression will update it´s value.
     * <p>
     * The operation is equal to: expression % value.
     * </p>
     * <p>
     * How the value will be treated, related to arithmetic, should be defined by the generic type of this Expression.
     *
     * @param value The value.
     * @return Returns a new Expression.
     * @apiNote In case the operation could not be performed because of invalid math operations, the returned expression should contain a value of zero.
     */
    NumberExpression<E> modulo(ObservableSingle<? extends Number> value);

    /**
     * Creates a new NumberExpression with the square root of this Expression´s value.
     * <p>
     * The operation is equal to: sqrt(expression)
     * </p>
     * <p>
     * How the value will be treated, related to arithmetic, should be defined by the generic type of this Expression.
     *
     * @return Returns a new Expression.
     * @apiNote In case the operation could not be performed because of invalid math operations, the returned expression should contain a value of zero.
     */
    NumberExpression<E> sqrt();

    /**
     * Creates a new NumberExpression with the opposite sign of this Expression´s value.
     * <p>
     * The operation is equal to: expression * (-1)
     * </p>
     * <p>
     * How the value will be treated, related to arithmetic, should be defined by the generic type of this Expression.
     *
     * @return Returns a new Expression.
     */
    NumberExpression<E> invert();

    /**
     * Creates a new NumberExpression with a positive sign of this Expression´s value.
     * <p>
     * The operation is equal to: expression < 0 ? expression * (-1) : expression
     * </p>
     * <p>
     * How the value will be treated, related to arithmetic, should be defined by the generic type of this Expression.
     *
     * @return Returns a new Expression.
     */
    NumberExpression<E> positive();

    /**
     * Creates a new NumberExpression with a negative sign of this Expression´s value.
     * <p>
     * The operation is equal to: expression > 0 ? expression * (-1) : expression
     * </p>
     * <p>
     * How the value will be treated, related to arithmetic, should be defined by the generic type of this Expression.
     *
     * @return Returns a new Expression.
     */
    NumberExpression<E> negative();

    /**
     * Creates new a {@link BooleanExpression} that contains true if this Expression´s value is greater than the specified value - else false.
     *
     * @param value The value.
     * @return Returns a new Expression.
     * @see BooleanExpression
     */
    BooleanExpression isBiggerThan(Number value);

    /**
     * Creates new a {@link BooleanExpression} that contains true if this Expression´s value is greater than the specified Supplier´s value - else false.
     *
     * @param value The value.
     * @return Returns a new Expression.
     * @see BooleanExpression
     */
    BooleanExpression isBiggerThan(Supplier<? extends Number> value);

    /**
     * Creates new a {@link BooleanExpression} that contains true if this Expression´s value is greater than the specified Supplier´s value - else false.
     * The {@link ObservableSingle} will be observed. Anytime the observable changes it´s value, the returned Expression will update it´s value.
     *
     * @param value The value.
     * @return Returns a new Expression.
     * @see BooleanExpression
     */
    BooleanExpression isBiggerThan(ObservableSingle<? extends Number> value);

    /**
     * Creates new a {@link BooleanExpression} that contains true if this Expression´s value is greater than or equal to the specified value - else false.
     *
     * @param value The value.
     * @return Returns a new Expression.
     * @see BooleanExpression
     */
    BooleanExpression isBiggerThanOrEqualTo(Number value);

    /**
     * Creates new a {@link BooleanExpression} that contains true if this Expression´s value is greater than or equal to the specified Supplier´s value - else false.
     *
     * @param value The value.
     * @return Returns a new Expression.
     * @see BooleanExpression
     */
    BooleanExpression isBiggerThanOrEqualTo(Supplier<? extends Number> value);

    /**
     * Creates new a {@link BooleanExpression} that contains true if this Expression´s value is greater than or equal to the specified Supplier´s value - else false.
     * The {@link ObservableSingle} will be observed. Anytime the observable changes it´s value, the returned Expression will update it´s value.
     *
     * @param value The value.
     * @return Returns a new Expression.
     * @see BooleanExpression
     */
    BooleanExpression isBiggerThanOrEqualTo(ObservableSingle<? extends Number> value);

    /**
     * Creates new a {@link BooleanExpression} that contains true if this Expression´s value is less than the specified Supplier´s value - else false.
     * The {@link ObservableSingle} will be observed. Anytime the observable changes it´s value, the returned Expression will update it´s value.
     *
     * @param value The value.
     * @return Returns a new Expression.
     * @see BooleanExpression
     */
    BooleanExpression isLessThan(Number value);

    /**
     * Creates new a {@link BooleanExpression} that contains true if this Expression´s value is less than the specified Supplier´s value - else false.
     *
     * @param value The value.
     * @return Returns a new Expression.
     * @see BooleanExpression
     */
    BooleanExpression isLessThan(Supplier<? extends Number> value);

    /**
     * Creates new a {@link BooleanExpression} that contains true if this Expression´s value is less than the specified Supplier´s value - else false.
     * The {@link ObservableSingle} will be observed. Anytime the observable changes it´s value, the returned Expression will update it´s value.
     *
     * @param value The value.
     * @return Returns a new Expression.
     * @see BooleanExpression
     */
    BooleanExpression isLessThan(ObservableSingle<? extends Number> value);

    /**
     * Creates new a {@link BooleanExpression} that contains true if this Expression´s value is less than or equal the specified Supplier´s value - else false.
     * The {@link ObservableSingle} will be observed. Anytime the observable changes it´s value, the returned Expression will update it´s value.
     *
     * @param value The value.
     * @return Returns a new Expression.
     * @see BooleanExpression
     */
    BooleanExpression isLessThanOrEqualTo(Number value);

    /**
     * Creates new a {@link BooleanExpression} that contains true if this Expression´s value is less than or equal to the specified Supplier´s value - else false.
     *
     * @param value The value.
     * @return Returns a new Expression.
     * @see BooleanExpression
     */
    BooleanExpression isLessThanOrEqualTo(Supplier<? extends Number> value);

    /**
     * Creates new a {@link BooleanExpression} that contains true if this Expression´s value is less than or equal to the specified Supplier´s value - else false.
     * The {@link ObservableSingle} will be observed. Anytime the observable changes it´s value, the returned Expression will update it´s value.
     *
     * @param value The value.
     * @return Returns a new Expression.
     * @see BooleanExpression
     */
    BooleanExpression isLessThanOrEqualTo(ObservableSingle<? extends Number> value);

    /**
     * Creates new a {@link BooleanExpression} that contains true if this Expression´s value is equal to the specified Supplier´s value - else false.
     * The {@link ObservableSingle} will be observed. Anytime the observable changes it´s value, the returned Expression will update it´s value.
     *
     * @param value The value.
     * @return Returns a new Expression.
     * @see BooleanExpression
     */
    BooleanExpression isEqualTo(Number value);

    /**
     * Creates new a {@link BooleanExpression} that contains true if this Expression´s value is equal to the specified Supplier´s value - else false.
     *
     * @param value The value.
     * @return Returns a new Expression.
     * @see BooleanExpression
     */
    BooleanExpression isEqualTo(Supplier<? extends Number> value);

    /**
     * Creates new a {@link BooleanExpression} that contains true if this Expression´s value is equal to the specified Supplier´s value - else false.
     * The {@link ObservableSingle} will be observed. Anytime the observable changes it´s value, the returned Expression will update it´s value.
     *
     * @param value The value.
     * @return Returns a new Expression.
     * @see BooleanExpression
     */
    BooleanExpression isEqualTo(ObservableSingle<? extends Number> value);

    /**
     * Creates a new {@link NumberExpression} that maps the value of this Expression to an {@link Integer}. The returned Expression will use integer arithmetic.
     *
     * @return Returns a new Expression.
     * @implNote The default implementation will return a {@link com.frechsack.dev.observer.simple.SimpleIntegerExpression} with the default {@link com.frechsack.dev.observer.simple.EventHandler}.
     * @see SimpleIntegerExpression
     * @see SimpleObservables
     * @see com.frechsack.dev.observer.simple.EventHandler
     */
    default NumberExpression<Integer> mapInteger()
    {
        return new SimpleIntegerExpression(getAsInt(), this)
        {
            @Override
            protected void computeValue()
            {
                setValue(NumberExpression.this.getAsInt());
            }
        };
    }

    /**
     * Creates a new {@link NumberExpression} that maps the value of this Expression to a {@link Long}. The returned Expression will use long arithmetic.
     *
     * @return Returns a new Expression.
     * @implNote The default implementation will return a {@link com.frechsack.dev.observer.simple.SimpleLongExpression} with the default {@link com.frechsack.dev.observer.simple.EventHandler}.
     * @see SimpleIntegerExpression
     * @see SimpleObservables
     * @see com.frechsack.dev.observer.simple.EventHandler
     */
    default NumberExpression<Long> mapLong()
    {
        return new SimpleLongExpression(getAsLong(), this)
        {
            @Override
            protected void computeValue()
            {
                setValue(NumberExpression.this.getAsLong());
            }
        };
    }

    /**
     * Creates a new {@link NumberExpression} that maps the value of this Expression to a {@link Double}. The returned Expression will use double arithmetic.
     *
     * @return Returns a new Expression.
     * @implNote The default implementation will return a {@link com.frechsack.dev.observer.simple.SimpleDoubleExpression} with the default {@link com.frechsack.dev.observer.simple.EventHandler}.
     * @see com.frechsack.dev.observer.simple.SimpleDoubleExpression
     * @see SimpleObservables
     * @see com.frechsack.dev.observer.simple.EventHandler
     */
    default NumberExpression<Double> mapDouble()
    {
        return new SimpleDoubleExpression(getAsDouble(), this)
        {
            @Override
            protected void computeValue()
            {
                setValue(NumberExpression.this.getAsDouble());
            }
        };
    }

    /**
     * Creates a new {@link NumberExpression} that maps the value of this Expression to a {@link Float}. The returned Expression will use float arithmetic.
     *
     * @return Returns a new Expression.
     * @implNote The default implementation will return a {@link com.frechsack.dev.observer.simple.SimpleFloatExpression} with the default {@link com.frechsack.dev.observer.simple.EventHandler}.
     * @see com.frechsack.dev.observer.simple.SimpleFloatExpression
     * @see SimpleObservables
     * @see com.frechsack.dev.observer.simple.EventHandler
     */
    default NumberExpression<Float> mapFloat()
    {
        return new SimpleFloatExpression(getFloat(), this)
        {
            @Override
            protected void computeValue()
            {
                setValue(NumberExpression.this.getFloat());
            }
        };
    }

    /**
     * Returns the value of this Expression. The returned Number shouldn´t be null.
     * @return Returns this Expression´s value.
     */
    @Override
    E get();
}
