package com.frechsack.dev.observer.core;

public interface Property<E> extends ReadonlyProperty<E>, ObservableSingle<E>, Writeable<E>
{
}
