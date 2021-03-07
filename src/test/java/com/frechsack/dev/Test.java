package com.frechsack.dev;


import com.frechsack.dev.observer.core.*;
import com.frechsack.dev.observer.simple.SimpleIntegerProperty;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class Test
{

    public static void main(String[] args) throws InterruptedException, CloneNotSupportedException
    {
        SimpleIntegerProperty a = new SimpleIntegerProperty(2,"a",Test.class);
        SimpleIntegerProperty b = new SimpleIntegerProperty(4,"b",Test.class);


        SimpleIntegerProperty c = (SimpleIntegerProperty) a.clone();
        a.addObserver((SingleChangeObserver<Integer>) System.out::println);
        c.set(99);
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);

    }

    private static void overloadMem()
    {
        ArrayList<Object> list = new ArrayList<>();
        for (long i = 0; i < 95000000L; i++)
        {
            list.add(i);
        }
        System.out.println("End Rem");
        System.out.println("Free Memory: " + Runtime.getRuntime().freeMemory());
    }
}
