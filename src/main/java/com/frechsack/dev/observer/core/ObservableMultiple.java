package com.frechsack.dev.observer.core;

import java.io.Serializable;

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
