package com.frechsack.dev.observer.core;

import java.io.Serializable;
import java.util.Map;

public interface ObservableMap<K,V> extends Observable, ObservableMultiple<Map.Entry<K,V>>, Map<K,V>
{
}
