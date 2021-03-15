package com.frechsack.dev.observer.simple;

import com.frechsack.dev.observer.core.MultipleChangeObserver;
import com.frechsack.dev.observer.core.ObservableMultiple;
import com.frechsack.dev.cursor.Cursor;
import com.frechsack.dev.cursor.SingleCursor;

import java.util.*;

public abstract class AbstractObservableMultiple<E> extends SimpleObservable implements ObservableMultiple<E>, Cloneable
{
    private MultipleChangeObserver<? super E>[] observers;

    @SuppressWarnings("unchecked")
    @Override
    public boolean addObserver(MultipleChangeObserver<? super E> observer)
    {
        Objects.requireNonNull(observer, "A null observer can not be added.");

        if (observers == null)
        {
            observers = new MultipleChangeObserver[]{observer};
            return true;
        }
        if (ArrayUtil.containsElement(observers, observer)) return false;
        observers = ArrayUtil.insertElement(observers, new MultipleChangeObserver[observers.length + 1], observer);
        return true;
    }

    @SuppressWarnings({"DuplicatedCode", "unchecked"})
    @Override
    public boolean removeObserver(MultipleChangeObserver<? super E> observer)
    {
        if (observers == null || observer == null) return false;
        int observerIndex = ArrayUtil.indexOf(observers, observer);
        if (observerIndex == -1) return false;
        if (observers.length == 1)
        {
            observers = null;
            return true;
        }
        observers = ArrayUtil.removeIndex(observers, new MultipleChangeObserver[observers.length - 1], observerIndex);
        return true;
    }

    @Override
    public boolean containsObserver(MultipleChangeObserver<? super E> observer)
    {
        if (observers == null || observer == null) return false;
        return ArrayUtil.containsElement(observers, observer);
    }

    protected boolean isChangeObserved()
    {
        return observers != null;
    }

    protected int getChangeObserverCount()
    {
        return observers == null ? 0 : observers.length;
    }

    protected void onChange(MultipleChangeObserver.MultipleChangeEvent<E> event)
    {
    }

    protected void change(E element, boolean isAdded)
    {
        change(new SimpleMultipleSingleChangeEvent(element, isAdded));
    }

    protected void change(ChangeCollector<E> collector)
    {
        if (collector.containsChange()) change(new SimpleMultipleCollectionChangeEvent(collector));
    }

    private void change(MultipleChangeObserver.MultipleChangeEvent<E> event)
    {
        if (!isChangeObserved()) return;
        onChange(event);
        if (observers == null) return;
        for (MultipleChangeObserver<? super E> observer : observers)
            try
            {
                observer.observed(event);
            }
            catch (Exception e)
            {
                logger.log(System.Logger.Level.INFO, "Exception occurred while processing MultipleChangeObserver.");
            }
    }



    protected final static class ChangeCollector<E>
    {
        private List<E> addedElementList;
        private List<E> removedElementList;
        private E removedElement;
        private boolean isRemovedElementSet;
        private E addedElement;
        private boolean isAddedElementSet;

        protected final boolean containsChange()
        {
            if (addedElementList != null) return true;
            if (removedElementList != null) return true;
            return isRemovedElementSet || isAddedElementSet;
        }

        protected final List<E> getRemovedElements()
        {
            if (removedElementList != null) return removedElementList;
            if (isRemovedElementSet) return List.of(removedElement);
            return List.of();
        }

        protected final List<E> getAddedElements()
        {
            if (addedElementList != null) return addedElementList;
            if (isAddedElementSet) return List.of(addedElement);
            return List.of();
        }

        protected final void addChange(E element)
        {
            //noinspection DuplicatedCode
            if (addedElementList != null) addedElementList.add(element);
            else if (isAddedElementSet)
            {
                addedElementList = new ArrayList<>(2);
                addedElementList.add(addedElement);
                addedElementList.add(element);
            }
            else
            {
                addedElement = element;
                isAddedElementSet = true;
            }
        }

        protected final void removeChange(E element)
        {
            //noinspection DuplicatedCode
            if (removedElementList != null) removedElementList.add(element);
            else if (isRemovedElementSet)
            {
                removedElementList = new ArrayList<>(2);
                removedElementList.add(removedElement);
                removedElementList.add(element);
            }
            else
            {
                removedElement = element;
                isRemovedElementSet = true;
            }
        }

        @Override
        public String toString()
        {
            return "ChangeCollector{" + "addedElementList=" + getAddedElements() + ", removedElementList=" + getRemovedElements() + '}';
        }

        @Override
        public boolean equals(Object o)
        {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            ChangeCollector<?> that = (ChangeCollector<?>) o;
            return isRemovedElementSet == that.isRemovedElementSet &&
                   isAddedElementSet == that.isAddedElementSet &&
                   Objects.equals(addedElementList, that.addedElementList) &&
                   Objects.equals(removedElementList, that.removedElementList) &&
                   Objects.equals(removedElement, that.removedElement) &&
                   Objects.equals(addedElement, that.addedElement);
        }

        @Override
        public int hashCode()
        {
            return Objects.hash(addedElementList, removedElementList, removedElement, isRemovedElementSet, addedElement, isAddedElementSet);
        }
    }


    protected class SimpleMultipleCollectionChangeEvent implements MultipleChangeObserver.MultipleChangeEvent<E>
    {
        private final List<E> addedElementList;
        private final List<E> removedElementList;
        private int index = -1;

        public SimpleMultipleCollectionChangeEvent(ChangeCollector<E> collector)
        {
            this.addedElementList = collector.getAddedElements();
            this.removedElementList = collector.getRemovedElements();
        }

        @Override
        public Collection<E> getRemovedElements()
        {
            return removedElementList;
        }

        @Override
        public Collection<E> getAddedElements()
        {
            return addedElementList;
        }

        @Override
        public boolean isAdded()
        {
            if (index == -1) throw new ArrayIndexOutOfBoundsException(index);
            // Added elements are queried first.
            return index < addedElementList.size();
        }

        @Override
        public boolean isRemoved()
        {
            return !isAdded();
        }

        @Override
        public ObservableMultiple<E> getSource()
        {
            return AbstractObservableMultiple.this;
        }

        @Override
        public boolean hasNext()
        {
            return index < addedElementList.size() + removedElementList.size() - 1;
        }

        @Override
        public boolean hasPrevious()
        {
            return index > 0;
        }

        @Override
        public E next()
        {
            index++;
            if (isAdded()) return addedElementList.get(index);
            return removedElementList.get(index);
        }

        @Override
        public E previous()
        {
            index--;
            if (isAdded()) return addedElementList.get(index);
            return removedElementList.get(index);
        }

        @Override
        public E last()
        {
            return removedElementList.get(removedElementList.size() + addedElementList.size() - 1);
        }

        @Override
        public E first()
        {
            return addedElementList.get(index = 0);
        }

        @Override
        public String toString()
        {
            return "SimpleMultipleChangeEvent{" + "addedElementList=" + getAddedElements() + ", removedElementList=" + getRemovedElements() + '}';
        }

        @Override
        public boolean equals(Object o)
        {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            @SuppressWarnings("unchecked") SimpleMultipleCollectionChangeEvent that = (SimpleMultipleCollectionChangeEvent) o;
            return Objects.equals(addedElementList, that.addedElementList) && Objects.equals(removedElementList, that.removedElementList);
        }

        @Override
        public int hashCode()
        {
            return Objects.hash(addedElementList, removedElementList);
        }
    }

    private class SimpleMultipleSingleChangeEvent implements MultipleChangeObserver.MultipleChangeEvent<E>
    {
        private final Cursor<E> cursor;
        private final boolean isAdded;
        private final E element;

        public SimpleMultipleSingleChangeEvent(E element, boolean isAdded)
        {
            this.cursor = new SingleCursor<>(element);
            this.element = element;
            this.isAdded = isAdded;
        }

        @Override
        public Collection<E> getRemovedElements()
        {
            if (isAdded) return List.of();
            return List.of(element);
        }

        @Override
        public Collection<E> getAddedElements()
        {
            if (isAdded) return List.of(element);
            return List.of();
        }

        @Override
        public boolean isAdded()
        {
            return isAdded;
        }

        @Override
        public boolean isRemoved()
        {
            return !isAdded;
        }

        @Override
        public ObservableMultiple<E> getSource()
        {
            return AbstractObservableMultiple.this;
        }

        @Override
        public boolean hasNext()
        {
            return cursor.hasNext();
        }

        @Override
        public boolean hasPrevious()
        {
            return cursor.hasPrevious();
        }

        @Override
        public E next()
        {
            return cursor.next();
        }

        @Override
        public E previous()
        {
            return cursor.previous();
        }

        @Override
        public E last()
        {
            return cursor.last();
        }

        @Override
        public E first()
        {
            return cursor.first();
        }

        @Override
        public String toString()
        {
            return "SimpleMultipleChangeEvent{" + "addedElementList=" + getAddedElements() + ", removedElementList=" + getRemovedElements() + '}';
        }

        @Override
        public boolean equals(Object o)
        {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            @SuppressWarnings("unchecked") SimpleMultipleSingleChangeEvent that = (SimpleMultipleSingleChangeEvent) o;
            return isAdded == that.isAdded && Objects.equals(cursor, that.cursor) && Objects.equals(element, that.element);
        }

        @Override
        public int hashCode()
        {
            return Objects.hash(cursor, isAdded, element);
        }
    }

    @Override
    public Object clone() throws CloneNotSupportedException
    {
        AbstractObservableMultiple<E> clone = (AbstractObservableMultiple<E>) super.clone();
        clone.observers = null;
        if(observers != null) for (MultipleChangeObserver<? super E> observer : observers)   clone.addObserver(observer);
        return clone;
    }
}
