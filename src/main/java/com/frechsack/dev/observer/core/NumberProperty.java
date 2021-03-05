package com.frechsack.dev.observer.core;

import java.math.BigDecimal;
import java.math.BigInteger;

public interface NumberProperty<E extends Number> extends Property<E>
{
    @Override
    NumberExpression<E> toExpression();

    byte getByte();

    short getShort();

    int getInt();

    float getFloat();

    double getDouble();

    long getLong();

    BigDecimal getBigDecimal();

    BigInteger getBigInteger();
}
