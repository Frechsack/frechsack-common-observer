package com.frechsack.dev.observer.core;

/**
 * Marks an interface as writeable.
 * <p>
 * A writeable allows receiving a value and write it.
 * @param <E>
 */
@FunctionalInterface
public interface Writeable<E>
{
    /**
     * Writes the specified value.
     * @param value The value that should be written.
     * @return Returns true if the value was successfully written - else false.
     */
    boolean set(E value);
}
