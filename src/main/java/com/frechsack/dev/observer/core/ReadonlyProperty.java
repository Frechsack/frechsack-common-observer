package com.frechsack.dev.observer.core;

import java.io.Serializable;

/**
 * A Property wraps a value with a name and a source context.
 * <p>
 * The Property name should describe the value and it´s meaning.
 * <p>
 * The source should an Object that makes use of the Property.
 * <p>
 * If the Property´s value changes during runtime a Property should be used instead.
 *
 * @param <E> The value type.
 * @author Frechsack
 * @see Property
 */
public interface ReadonlyProperty<E> extends Readable<E>, Cloneable, Serializable
{
    /**
     * Gets the Property name.
     *
     * @return Returns the name.
     */
    String getName();

    /**
     * Gets the Property`s source.
     *
     * @return Returns the source.
     */
    Object getSource();
}
