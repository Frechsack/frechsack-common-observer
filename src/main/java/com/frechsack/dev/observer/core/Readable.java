package com.frechsack.dev.observer.core;

import java.util.function.Supplier;

/**
 * Marks an Object as Readable.
 * @param <E> The readable type.
 */
@FunctionalInterface
public interface Readable<E> extends Supplier<E>
{
    /**
     * Gets the current value of this Object.
     * @return Returns the current value.
     */
    @Override
    E get();
}
