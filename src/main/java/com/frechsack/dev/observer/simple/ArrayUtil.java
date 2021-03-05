package com.frechsack.dev.observer.simple;

import java.util.HashMap;

class ArrayUtil
{
    static <E> E[] insertElement(E[] src, E[] dst, E element)
    {
        System.arraycopy(src, 0, dst, 0, src.length);
        dst[dst.length - 1] = element;
        return dst;
    }

    static <E> E[] removeElement(E[] src, E[] dst, E element)
    {
        return removeIndex(src, dst, indexOf(src, element));
    }

    static <E> boolean containsElement(E[] src, E element)
    {
        return indexOf(src, element) != -1;
    }

    static <E> E[] removeIndex(E[] src, E[] dst, int elementIndex)
    {
        if (elementIndex != -1) for (int srcIndex = 0, dstIndex = 0; srcIndex < src.length; srcIndex++, dstIndex++)
            if (srcIndex != elementIndex) dst[dstIndex] = src[srcIndex];
            else dstIndex--;
        return dst;
    }

    static <E> int indexOf(E[] src, E element)
    {
        if (src == null) return -1;
        for (int i = 0; i < src.length; i++) if (src[i] == element) return i;
        return -1;
    }
}
