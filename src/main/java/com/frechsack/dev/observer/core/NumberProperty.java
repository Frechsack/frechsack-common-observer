package com.frechsack.dev.observer.core;

/**
 * A specialised version of {@link Property} that contains a {@link Number} as value.
 * @author Frechsack
 * @see ObservableSingle
 * @see Boolean
 */
public interface NumberProperty<E extends Number> extends Property<E>, NumberSupplier<E>
{
    /**
     * {@inheritDoc}
     */
    @Override
    NumberExpression<E> toExpression();
}
