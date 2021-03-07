package com.frechsack.dev.observer.core;

import java.io.Serializable;

/**
 * Marks an Object as Observable. This interface specifies that this {@link Observable} contains multiple values.
 * An ObservableMultiple should perform it´s invalidation process in the following order:
 * <ol>
 * <li>Add or remove an element to the Observable</li>
 * <li>Mark this Observable as invalid an notify {@link InvalidationObserver}.</li>
 * <li>Notify {@link MultipleChangeObserver}.</li>
 * </ol>
 *
 * @param <E> The value type.
 * @author Frechsack
 * @see Observable
 * @see MultipleChangeObserver
 * @see com.frechsack.dev.observer.core.MultipleChangeObserver.MultipleChangeEvent
 */
public interface ObservableMultiple<E> extends Observable, Serializable
{
    boolean addObserver(MultipleChangeObserver<? super E> observer);

    boolean removeObserver(MultipleChangeObserver<? super E> observer);

    boolean containsObserver(MultipleChangeObserver<? super E> observer);

    BooleanExpression isEmptyExpression();

    NumberExpression<Integer> sizeExpression();

    // TODO: Collection expressions

    // Common Operations
    boolean addAll(E... es);

    boolean removeAll(E... es);
}
