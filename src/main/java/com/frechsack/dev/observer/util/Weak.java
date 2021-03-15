package com.frechsack.dev.observer.util;

import java.lang.ref.Reference;

public interface Weak<E>
{
    Reference<E> getReferentReference();

    default E getReferent()
    {
        return getReferentReference().get();
    }
}
