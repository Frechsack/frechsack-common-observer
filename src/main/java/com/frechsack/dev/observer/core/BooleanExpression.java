package com.frechsack.dev.observer.core;

import java.util.function.Supplier;

public interface BooleanExpression extends Expression<Boolean>
{
    BooleanExpression and(boolean value);

    BooleanExpression and(Supplier<Boolean> value);

    BooleanExpression and(ObservableSingle<Boolean> value);

    BooleanExpression or(boolean value);

    BooleanExpression or(Supplier<Boolean> value);

    BooleanExpression or(ObservableSingle<Boolean> value);

    BooleanExpression xor(boolean value);

    BooleanExpression xor(Supplier<Boolean> value);

    BooleanExpression xor(ObservableSingle<Boolean> value);

    BooleanExpression invert();

    boolean getBoolean();
}
