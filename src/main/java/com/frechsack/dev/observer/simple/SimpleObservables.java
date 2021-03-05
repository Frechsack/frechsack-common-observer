package com.frechsack.dev.observer.simple;

import com.frechsack.dev.observer.core.*;

import java.util.function.Supplier;

public class SimpleObservables extends SimpleNumberProcessor
{
    public static BooleanExpression constantExpression(boolean b)
    {
        return new SimpleBooleanExpression(b)
        {
            @Override
            protected void computeValue()
            {
            }

            @Override
            public boolean addObserver(SingleChangeObserver<? super Boolean> observer)
            {
                return false;
            }
            @Override
            public boolean addObserver(InvalidationObserver observer)
            {
                return false;
            }
        };
    }

    public static NumberExpression<Float> constantExpression(float n)
    {
        return new SimpleFloatExpression(n)
        {
            @Override
            protected void computeValue()
            {
            }
            @Override
            public boolean addObserver(InvalidationObserver observer)
            {
                return false;
            }
            @Override
            public boolean addObserver(SingleChangeObserver<? super Float> observer)
            {
                return false;
            }
        };
    }
    public static NumberExpression<Integer> constantExpression(int n)
    {
        return new SimpleIntegerExpression(n)
        {
            @Override
            protected void computeValue()
            {
            }
            @Override
            public boolean addObserver(InvalidationObserver observer)
            {
                return false;
            }
            @Override
            public boolean addObserver(SingleChangeObserver<? super Integer> observer)
            {
                return false;
            }
        };
    }
    public static NumberExpression<Double> constantExpression(double n)
    {
        return new SimpleDoubleExpression(n)
        {
            @Override
            protected void computeValue()
            {
            }
            @Override
            public boolean addObserver(InvalidationObserver observer)
            {
                return false;
            }
            @Override
            public boolean addObserver(SingleChangeObserver<? super Double> observer)
            {
                return false;
            }
        };
    }
    public static NumberExpression<Long> constantExpression(long n)
    {
        return new SimpleLongExpression(n)
        {
            @Override
            protected void computeValue()
            {
            }
            @Override
            public boolean addObserver(InvalidationObserver observer)
            {
                return false;
            }
            @Override
            public boolean addObserver(SingleChangeObserver<? super Long> observer)
            {
                return false;
            }
        };
    }
    public static NumberExpression<Number> constantExpression(Number n){
        return new SimpleNumberExpression(saveNumber(n)) {
            @Override
            protected void computeValue()
            {
            }

            @Override
            public boolean addObserver(InvalidationObserver observer)
            {
                return false;
            }

            @Override
            public boolean addObserver(SingleChangeObserver<? super Number> observer)
            {
                return false;
            }
        };
    }


    public static <E> Expression<E> constantExpression(E e)
    {
        return new SimpleObjectExpression<>(e)
        {
            @Override
            protected void computeValue()
            {
            }

            @Override
            public boolean addObserver(SingleChangeObserver<? super E> observer)
            {
                return false;
            }

            @Override
            public boolean addObserver(InvalidationObserver observer)
            {
                return false;
            }
        };
    }

    public static StringExpression constantExpression(String s)
    {
        return new SimpleStringExpression(saveString(s))
        {
            @Override
            protected void computeValue()
            {
            }

            @Override
            public boolean addObserver(InvalidationObserver observer)
            {
                return false;
            }

            @Override
            public boolean addObserver(SingleChangeObserver<? super String> observer)
            {
                return false;
            }
        };
    }

    private static String classNameFor(Class<?> clazz)
    {
        String className = clazz.getName();
        if (className.contains("."))
            if (className.contains("$")) return className.substring(className.lastIndexOf('.') + 1, className.lastIndexOf('$'));
            else return className.substring(className.lastIndexOf('.') + 1);
        return className;
    }

    protected static String toStringTemplate(SimpleObservable observable)
    {
        return classNameFor(observable.getClass()) + "{invalidationObserverCount:" + observable.getInvalidationObserverCount() + "}";
    }

    protected static String toStringTemplate(SimpleObservableSingle<?> observable)
    {
        return classNameFor(observable.getClass()) +
               "{invalidationObserverCount:" +
               observable.getInvalidationObserverCount() +
               "" +
               ", changeObserverCount: " +
               observable.getChangeObserverCount() +
               ", value: " +
               observable.get() +
               "}";
    }

    protected static String toStringTemplate(AbstractProperty<?> property)
    {
        return classNameFor(property.getClass()) +
               "{invalidationObserverCount:" +
               property.getInvalidationObserverCount() +
               "" +
               ", changeObserverCount: " +
               property.getChangeObserverCount() +
               ", value: " +
               property.get() +
               ", name: " +
               property.getName() +
               ", source: " +
               property.getSource() +
               "}";
    }

    protected static String toStringTemplate(AbstractExpression<?> expression)
    {
        return classNameFor(expression.getClass()) +
               "{invalidationObserverCount:" +
               expression.getInvalidationObserverCount() +
               "" +
               ", changeObserverCount: " +
               expression.getChangeObserverCount() +
               ", value: " +
               expression.get() +
               "}";
    }

    protected static String toStringTemplate(AbstractWrapper<?> wrapper)
    {
        return classNameFor(wrapper.getClass()) + "{wrapper: " + wrapper.observable + "}";
    }

    protected static System.Logger logger = System.getLogger("SimpleObservables");

    protected static Number saveNumber(Supplier<? extends Number> value)
    {
        return saveNumber(value.get());
    }

    protected static Number saveNumber(Number value)
    {
        return value == null ? 0 : value;
    }

    protected static int saveInt(Supplier<? extends Number> value)
    {
        return saveInt(value.get());
    }

    protected static int saveInt(Integer value)
    {
        return value == null ? 0 : value;
    }

    protected static int saveInt(Number value)
    {
        return value == null ? 0 : value.intValue();
    }

    protected static long saveLong(Supplier<? extends Number> value)
    {
        return saveLong(value.get());
    }

    protected static long saveLong(Long value)
    {
        return value == null ? 0 : value;
    }

    protected static long saveLong(Number value)
    {
        return value == null ? 0 : value.longValue();
    }

    protected static float saveFloat(Supplier<? extends Number> value)
    {
        return saveFloat(value.get());
    }

    protected static float saveFloat(Float value)
    {
        return value == null ? 0 : value;
    }

    protected static float saveFloat(Number value)
    {
        return value == null ? 0 : value.floatValue();
    }

    protected static double saveDouble(Supplier<? extends Number> value)
    {
        return saveDouble(value.get());
    }

    protected static double saveDouble(Double value)
    {
        return value == null ? 0 : value;
    }

    protected static double saveDouble(Number value)
    {
        return value == null ? 0 : value.doubleValue();
    }

    protected static boolean saveBoolean(Supplier<Boolean> value)
    {
        return saveBoolean(value.get());
    }

    protected static boolean saveBoolean(Boolean value)
    {
        return value != null && value;
    }

    protected static String saveString(Supplier<String> value)
    {
        return saveString(value.get());
    }

    protected static String saveString(String value)
    {
        return value == null ? "" : value;
    }

}
