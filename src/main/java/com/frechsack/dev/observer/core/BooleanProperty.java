package com.frechsack.dev.observer.core;

import java.util.function.BooleanSupplier;

/**
 * A specialised version of {@link Property} that contains a boolean as value.
 * @author Frechsack
 * @see ObservableSingle
 * @see Boolean
 */
public interface BooleanProperty extends Property<Boolean>, BooleanSupplier
{
    /**
     * {@inheritDoc}
     */
    @Override
    BooleanExpression toExpression();
}
