package com.frechsack.dev.observer.core;

import java.io.Serializable;

/**
 * A Property
 * @param <E>
 */
public interface ReadonlyProperty<E> extends Readable<E>, Cloneable, Serializable
{
    String getName();

    Object getSource();
}
