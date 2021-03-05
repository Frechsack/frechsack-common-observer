package com.frechsack.dev.observer.core;

import java.util.function.Supplier;

public interface StringExpression extends Expression<String>
{
    StringExpression append(String value);

    StringExpression append(Supplier<String> value);

    StringExpression append(ObservableSingle<String> value);

    StringExpression toLowerCaseExpression();

    StringExpression toUpperCaseExpression();

    StringExpression trimExpression();

    BooleanExpression isEmptyExpression();

    NumberExpression<Integer> lengthExpression();

    String getString();
}
