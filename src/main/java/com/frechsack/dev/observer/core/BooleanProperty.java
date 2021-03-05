package com.frechsack.dev.observer.core;

public interface BooleanProperty extends Property<Boolean>
{
    @Override
    BooleanExpression toExpression();

}
