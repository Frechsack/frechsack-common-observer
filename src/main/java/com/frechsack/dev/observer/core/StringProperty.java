package com.frechsack.dev.observer.core;

/**
 * A specialised version of {@link Property} that contains a {@link String} as value.
 * @author Frechsack
 * @see ObservableSingle
 * @see String
 */
public interface StringProperty extends Property<String>
{
    /**
     * {@inheritDoc}
     */
    StringExpression toExpression();
}
