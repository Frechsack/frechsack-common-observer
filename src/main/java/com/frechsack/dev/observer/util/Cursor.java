package com.frechsack.dev.observer.util;

import java.util.Iterator;

public interface Cursor<E> extends Iterator<E>
{
    boolean hasNext();

    boolean hasPrevious();

    E next();

    E previous();

    E last();

    E first();
}
