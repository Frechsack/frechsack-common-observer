package com.frechsack.dev.observer.core;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.function.Supplier;

public interface NumberExpression<E extends Number> extends Expression<E>
{
    NumberExpression<E> add(Number value);

    NumberExpression<E> add(Supplier<? extends Number> value);

    NumberExpression<E> add(ObservableSingle<? extends Number> value);

    NumberExpression<E> subtract(Number value);

    NumberExpression<E> subtract(Supplier<? extends Number> value);

    NumberExpression<E> subtract(ObservableSingle<? extends Number> value);

    NumberExpression<E> multiply(Number value);

    NumberExpression<E> multiply(Supplier<? extends Number> value);

    NumberExpression<E> multiply(ObservableSingle<? extends Number> value);

    NumberExpression<E> divide(Number value);

    NumberExpression<E> divide(Supplier<? extends Number> value);

    NumberExpression<E> divide(ObservableSingle<? extends Number> value);

    NumberExpression<E> pow(Number value);

    NumberExpression<E> pow(Supplier<? extends Number> value);

    NumberExpression<E> pow(ObservableSingle<? extends Number> value);

    NumberExpression<E> modulo(Number value);

    NumberExpression<E> modulo(Supplier<? extends Number> value);

    NumberExpression<E> modulo(ObservableSingle<? extends Number> value);

    NumberExpression<E> sqrt();

    NumberExpression<E> invert();

    NumberExpression<E> positive();

    NumberExpression<E> negative();

    BooleanExpression isBiggerThan(Number value);

    BooleanExpression isBiggerThan(Supplier<? extends Number> value);

    BooleanExpression isBiggerThan(ObservableSingle<? extends Number> value);

    BooleanExpression isBiggerThanOrEqualTo(Number value);

    BooleanExpression isBiggerThanOrEqualTo(Supplier<? extends Number> value);

    BooleanExpression isBiggerThanOrEqualTo(ObservableSingle<? extends Number> value);

    BooleanExpression isLessThan(Number value);

    BooleanExpression isLessThan(Supplier<? extends Number> value);

    BooleanExpression isLessThan(ObservableSingle<? extends Number> value);

    BooleanExpression isLessThanOrEqualTo(Number value);

    BooleanExpression isLessThanOrEqualTo(Supplier<? extends Number> value);

    BooleanExpression isLessThanOrEqualTo(ObservableSingle<? extends Number> value);

    BooleanExpression isEqualTo(Number value);

    BooleanExpression isEqualTo(Supplier<? extends Number> value);

    BooleanExpression isEqualTo(ObservableSingle<? extends Number> value);

    NumberExpression<Integer> mapInteger();

    NumberExpression<Long> mapLong();

    NumberExpression<Double> mapDouble();

    NumberExpression<Float> mapFloat();

    byte getByte();

    short getShort();

    int getInt();

    float getFloat();

    double getDouble();

    long getLong();

    BigDecimal getBigDecimal();

    BigInteger getBigInteger();
}
