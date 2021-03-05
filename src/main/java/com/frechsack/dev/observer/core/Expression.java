package com.frechsack.dev.observer.core;

import java.util.function.Function;
import java.util.function.Supplier;

public interface Expression<E> extends ObservableSingle<E>
{
    BooleanExpression mapBoolean(Function<Expression<E>, Boolean> map);

    StringExpression mapString(Function<Expression<E>, String> map);

    NumberExpression<?> mapNumber(Function<Expression<E>, ? extends Number> map);

    NumberExpression<Integer> mapInteger(Function<Expression<E>, Integer> map);

    NumberExpression<Long> mapLong(Function<Expression<E>, Long> map);

    NumberExpression<Float> mapFloat(Function<Expression<E>, Float> map);

    NumberExpression<Double> mapDouble(Function<Expression<E>, Double> map);

    <T> Expression<T> map(Function<Expression<E>, T> map);

    StringExpression toStringExpression();

    StringExpression valueToStringExpression();

    BooleanExpression equalsExpression(Object obj);

    BooleanExpression equalsExpression(Supplier<?> obj);

    BooleanExpression equalsExpression(ObservableSingle<?> obj);

    BooleanExpression valueEqualsExpression(Object obj);

    BooleanExpression valueEqualsExpression(Supplier<?> obj);

    BooleanExpression valueEqualsExpression(ObservableSingle<?> obj);

    BooleanExpression valueIsNullExpression();

    NumberExpression<Integer> hashCodeExpression();

    NumberExpression<Integer> valueHashCodeExpression();
}
