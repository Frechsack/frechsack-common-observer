package com.frechsack.dev.observer.core;

import java.io.Serializable;
import java.util.List;

public interface ObservableList<E> extends ObservableCollection<E> , List<E>, Serializable
{}
