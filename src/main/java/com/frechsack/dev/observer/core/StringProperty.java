package com.frechsack.dev.observer.core;

public interface StringProperty extends Property<String>
{
    StringExpression toExpression();
}
