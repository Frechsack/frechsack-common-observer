package com.frechsack.dev.observer.simple;

import com.frechsack.dev.observer.core.Expression;
import com.frechsack.dev.observer.core.ObservableSingle;
import com.frechsack.dev.observer.core.SingleChangeObserver;

import java.io.Serializable;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.util.Arrays;
import java.util.Objects;

public abstract class SimpleObservableSingle<E> extends SimpleObservable implements ObservableSingle<E>, Serializable
{
    private transient SingleChangeObserver<? super E>[] observers;
    private transient E lastValue;
    private transient Reference<Expression<E>> toExpressionRef;

    public SimpleObservableSingle(E initialValue, EventHandler eventHandler)
    {
        super(eventHandler);
        this.lastValue = initialValue;
    }

    public SimpleObservableSingle(E initialValue)
    {
        super();
        this.lastValue = initialValue;
    }


    @SuppressWarnings("unused")
    protected void onChange(E value, E lastValue)
    {
    }

    /**
     * A SimpleValueObservable will check itself, if the value changed - unlike invalidate();
     */
    protected final void change()
    {
        if (isChangeObserved()) EventHandlers.getHandler(getEventHandler()).change(this);
    }

    final void handlerFireChange()
    {
        E value = get();
        // Check if the current value changed.
        if (Objects.equals(value, lastValue)) return;
        onChange(value, lastValue);
        E eventLastValue = this.lastValue;
        this.lastValue = value;
        if (observers == null) return;
        SimpleSingleChangeEvent event = new SimpleSingleChangeEvent(value, eventLastValue);
        for (SingleChangeObserver<? super E> observer : observers)
            try
            {
                observer.observed(event);
            }
            catch (Exception e)
            {
                logger.log(System.Logger.Level.INFO, "Exception occurred while processing SingleChangeObserver.");
            }
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean addObserver(SingleChangeObserver<? super E> observer)
    {
        Objects.requireNonNull(observer, "A null observer can not be added.");

        if (observers == null)
        {
            observers = new SingleChangeObserver[]{observer};
            return true;
        }
        if (containsElement(observers, observer)) return false;
        observers = insertElement(observers, new SingleChangeObserver[observers.length + 1], observer);
        return true;
    }

    @SuppressWarnings({"DuplicatedCode", "unchecked"})
    @Override
    public boolean removeObserver(SingleChangeObserver<? super E> observer)
    {
        if (observers == null || observer == null) return false;
        int observerIndex = indexOf(observers, observer);
        if (observerIndex == -1) return false;
        if (observers.length == 1)
        {
            observers = null;
            return true;
        }
        observers = removeIndex(observers, new SingleChangeObserver[observers.length - 1], observerIndex);
        return true;
    }

    @Override
    public boolean containsObserver(SingleChangeObserver<? super E> observer)
    {
        if (observers == null || observer == null) return false;
        return containsElement(observers, observer);
    }

    @Override
    public Expression<E> toExpression()
    {
        Expression<E> toExpression = toExpressionRef == null ? null : toExpressionRef.get();
        if (toExpression == null)
        {
            toExpression = new SimpleObjectWrapper<>(this);
            toExpressionRef = new WeakReference<>(toExpression);
        }
        return toExpression;
    }

    @Override
    protected int getObserverCount()
    {
        return super.getObserverCount() + (observers == null ? 0 : observers.length);
    }

    protected boolean isChangeObserved()
    {
        return observers != null;
    }

    private class SimpleSingleChangeEvent implements SingleChangeObserver.SingleChangeEvent<E>
    {
        private final E value;
        private final E lastValue;

        public SimpleSingleChangeEvent(E value, E lastValue)
        {
            this.value = value;
            this.lastValue = lastValue;
        }

        @Override
        public E get()
        {
            return value;
        }

        @Override
        public E getLast()
        {
            return lastValue;
        }

        @Override
        public ObservableSingle<? extends E> getSource()
        {
            return SimpleObservableSingle.this;
        }

        @Override
        public String toString()
        {
            return "SimpleSingleChangeEvent{" + "value=" + value + ", lastValue=" + lastValue + '}';
        }

        @Override
        public boolean equals(Object o)
        {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            @SuppressWarnings("unchecked") SimpleSingleChangeEvent that = (SimpleSingleChangeEvent) o;
            return Objects.equals(value, that.value) && Objects.equals(lastValue, that.lastValue);
        }

        @Override
        public int hashCode()
        {
            return Objects.hash(value, lastValue);
        }
    }

    @Override
    public String toString()
    {
        return toStringTemplate(this);
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        SimpleObservableSingle<?> that = (SimpleObservableSingle<?>) o;
        return Arrays.equals(observers, that.observers) && Objects.equals(lastValue, that.lastValue) && Objects.equals(get(), that.get());
    }

    @Override
    public int hashCode()
    {
        int result = Objects.hash(super.hashCode(), lastValue);
        result = 31 * result + Arrays.hashCode(observers);
        return result;
    }

    @Override
    public Object clone() throws CloneNotSupportedException
    {
        @SuppressWarnings("unchecked") SimpleObservableSingle<E> clone = (SimpleObservableSingle<E>) super.clone();
        clone.toExpressionRef = null;
        clone.observers = null;
        if (observers != null) for (SingleChangeObserver<? super E> observer : observers) clone.addObserver(observer);
        return clone;
    }
}
