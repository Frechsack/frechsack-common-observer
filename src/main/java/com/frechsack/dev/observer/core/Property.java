package com.frechsack.dev.observer.core;

/**
 * A Property is a modifiable version of {@link ReadonlyProperty}.
 * <p>
 * This Property allows observation, specified in {@link ObservableSingle}.
 * If a readonly Property is needed, use {@link ReadonlyProperty} instead.
 *
 * @param <E> The value type.
 * @author Frechsack
 * @see ReadonlyProperty
 * @see ObservableSingle
 */
public interface Property<E> extends ReadonlyProperty<E>, ObservableSingle<E>, Writeable<E>
{
    // TODO: Binding support.
}
