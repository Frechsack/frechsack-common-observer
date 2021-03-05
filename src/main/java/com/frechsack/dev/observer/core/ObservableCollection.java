package com.frechsack.dev.observer.core;

import java.util.Collection;

public interface ObservableCollection<E> extends Observable, ObservableMultiple<E>, Collection<E>
{
}
