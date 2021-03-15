package com.frechsack.dev.observer.simple;

import com.frechsack.dev.observer.core.ObservableList;

import java.util.Collection;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;

public class SimpleObservableList<E> extends SimpleObservableCollection<E> implements ObservableList<E>
{
    private final List<E> list;

    public SimpleObservableList(List<E> list)
    {
        super(list);
        this.list = list;
    }

    protected void addOperation(int index, E element, ChangeCollector<E> collector)
    {
        list.add(index, element);
        collector.addChange(element);
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c)
    {
        if (c.isEmpty()) return false;
        if (c.size() == 1)
        {
            E element = c.iterator().next();
            add(index, element);
            change(element,true);
            // Assume true
            return true;
        }
        ChangeCollector<E> collector = new ChangeCollector<>();
        for (E e : c)
        {
            addOperation(index, e, collector);
            index++;
        }
        if (!collector.containsChange()) return false;
        invalidate();
        change(collector);
        return true;
    }

    @Override
    public E get(int index)
    {
        return list.get(index);
    }

    @Override
    public E set(int index, E element)
    {
        E last = list.set(index,element);
        boolean isModified = !Objects.equals(last,element);
        if(!isModified) return last;
        invalidate();
        ChangeCollector<E> collector = new ChangeCollector<>();
        collector.addChange(element);
        collector.removeChange(last);
        change(collector);
        return last;
    }

    @Override
    public void add(int index, E element)
    {
        list.add(index,element);
        invalidate();
        change(element,true);
    }

    @Override
    public E remove(int index)
    {
        E last = list.remove(index);
        invalidate();
        change(last,false);
        return last;
    }

    @Override
    public int indexOf(Object o)
    {
        return list.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o)
    {
        return list.lastIndexOf(o);
    }

    private final class SimpleListIterator implements ListIterator<E>
    {
        private final ListIterator<E> iterator;
        private E element;

        private SimpleListIterator(ListIterator<E> iterator) {this.iterator = iterator;}

        @Override
        public boolean hasNext()
        {
            return iterator.hasNext();
        }

        @Override
        public E next()
        {
            return element = iterator.next();
        }

        @Override
        public boolean hasPrevious()
        {
            return iterator.hasPrevious();
        }

        @Override
        public E previous()
        {
            return element =iterator.previous();
        }

        @Override
        public int nextIndex()
        {
            return iterator.nextIndex();
        }

        @Override
        public int previousIndex()
        {
            return iterator.previousIndex();
        }

        @Override
        public void remove()
        {
            iterator.remove();
            change(element,false);
        }

        @Override
        public void set(E e)
        {
            iterator.set(e);
            ChangeCollector<E> collector = new ChangeCollector<>();
            collector.removeChange(element);
            collector.addChange(e);
            change(collector);
        }

        @Override
        public void add(E e)
        {
            iterator.add(e);
            change(e,true);
        }

        @Override
        public String toString()
        {
            return "SimpleListIterator{}";
        }

        @Override
        public boolean equals(Object o)
        {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            SimpleListIterator that = (SimpleListIterator) o;
            return Objects.equals(iterator, that.iterator) && Objects.equals(element, that.element);
        }

        @Override
        public int hashCode()
        {
            return Objects.hash(iterator, element);
        }
    }

    @Override
    public ListIterator<E> listIterator()
    {
        return new SimpleListIterator(list.listIterator());
    }

    @Override
    public ListIterator<E> listIterator(int index)
    {
        return new SimpleListIterator(list.listIterator(index));
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex)
    {
        return list.subList(fromIndex,toIndex);
    }

    @Override
    public String toString()
    {
        return "SimpleObservableList{" + "list=" + list + '}';
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        SimpleObservableList<?> that = (SimpleObservableList<?>) o;
        return Objects.equals(list, that.list);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(super.hashCode(), list);
    }
}
