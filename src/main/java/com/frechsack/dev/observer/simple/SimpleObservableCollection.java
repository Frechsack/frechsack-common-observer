package com.frechsack.dev.observer.simple;

import com.frechsack.dev.observer.core.BooleanExpression;
import com.frechsack.dev.observer.core.NumberExpression;
import com.frechsack.dev.observer.core.ObservableCollection;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class SimpleObservableCollection<E> extends AbstractObservableMultiple<E> implements ObservableCollection<E>, Cloneable
{
    final Collection<E> collection;
    private Reference<BooleanExpression> isEmptyExpressionRef;
    private Reference<NumberExpression<Integer>> sizeExpressionRef;

    public SimpleObservableCollection(Collection<E> collection) {this.collection = collection;}


    @Override
    public BooleanExpression isEmptyExpression()
    {
        BooleanExpression isEmptyExpression = isEmptyExpressionRef == null ? null : isEmptyExpressionRef.get();
        if (isEmptyExpression == null)
        {
            isEmptyExpression = new SimpleBooleanExpression(collection.isEmpty(), this, getEventHandler())
            {
                @Override
                protected void computeValue()
                {
                    setValue(SimpleObservableCollection.this.collection.isEmpty());
                }
            };
            isEmptyExpressionRef = new WeakReference<>(isEmptyExpression);
        }
        return isEmptyExpression;
    }

    @Override
    public NumberExpression<Integer> sizeExpression()
    {
        NumberExpression<Integer> sizeExpression = sizeExpressionRef == null ? null : sizeExpressionRef.get();
        if (sizeExpression == null)
        {
            sizeExpression = new SimpleIntegerExpression(collection.size(), this, getEventHandler())
            {
                @Override
                protected void computeValue()
                {
                    setValue(SimpleObservableCollection.this.collection.size());
                }
            };
            sizeExpressionRef = new WeakReference<>(sizeExpression);
        }
        return sizeExpression;
    }
    @SafeVarargs
    @SuppressWarnings("DuplicatedCode")
    @Override
    public final boolean addAll(E... es)
    {
        if (es.length == 0) return false;
        if (es.length == 1) return add(es[0]);
        final ChangeCollector<E> collector = new ChangeCollector<>();
        boolean isModified = false;
        for (E e : es) if (addOperation(e, collector)) isModified = true;
        if (!isModified) return false;
        invalidate();
        change(collector);
        return true;
    }

    @SafeVarargs
    @SuppressWarnings("DuplicatedCode")
    @Override
    public final boolean removeAll(E... c)
    {
        if (c.length == 0) return false;
        if (c.length == 1) remove(c[0]);
        boolean isModified = false;
        final ChangeCollector<E> collector = new ChangeCollector<>();
        for (Object o : c) //noinspection unchecked
            if (removeOperation((E) o, collector)) isModified = true;
        if(!isModified) return false;
        invalidate();
        change(collector);
        return true;
    }

    @SafeVarargs
    public final boolean retainAll(E... es)
    {
        return retainAll(List.of(es));
    }

    @Override
    public int size()
    {
        return collection.size();
    }

    @Override
    public boolean isEmpty()
    {
        return collection.isEmpty();
    }

    @Override
    public boolean contains(Object o)
    {
        return collection.contains(o);
    }

    @Override
    public Iterator<E> iterator()
    {
        final Iterator<E> iter = collection.iterator();
        return new Iterator<>()
        {
            private E element;

            @Override
            public boolean hasNext()
            {
                return iter.hasNext();
            }

            @Override
            public E next()
            {
                return element = iter.next();
            }

            @Override
            public void remove()
            {
                int oldSize = collection.size();
                try
                {
                    iter.remove();
                    if (oldSize != collection.size())
                    {
                        invalidate();
                        change(element, false);
                    }
                }
                catch (Exception ignored)
                {
                }
            }

            @Override
            public void forEachRemaining(Consumer<? super E> action)
            {
                iter.forEachRemaining(action);
            }

            @Override
            public String toString()
            {
                return iter.toString();
            }

            @Override
            public boolean equals(Object o)
            {
                return iter.equals(o);
            }

            @Override
            public int hashCode()
            {
                return iter.hashCode();
            }
        };
    }

    @Override
    public void forEach(Consumer<? super E> action)
    {
        collection.forEach(action);
    }

    @Override
    public Object[] toArray()
    {
        return collection.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a)
    {
        //noinspection SuspiciousToArrayCall
        return collection.toArray(a);
    }

    @Override
    public <T> T[] toArray(IntFunction<T[]> generator)
    {
        //noinspection SuspiciousToArrayCall
        return collection.toArray(generator);
    }

    @Override
    public boolean add(E e)
    {
        if (collection.add(e))
        {
            invalidate();
            change(e, true);
            return true;
        }
        return false;
    }

    @Override
    public boolean remove(Object o)
    {
        if (collection.remove(o)) try
        {
            invalidate();
            //noinspection unchecked
            change((E) o, false);
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c)
    {
        return collection.containsAll(c);
    }

    @SuppressWarnings("DuplicatedCode")
    @Override
    public boolean addAll(Collection<? extends E> c)
    {
        if (c.isEmpty()) return false;
        if (c.size() == 1) return add(c.iterator().next());
        final ChangeCollector<E> collector = new ChangeCollector<>();
        boolean isModified = false;
        for (E e : c) if (addOperation(e, collector)) isModified = true;
        if (!isModified) return false;
        invalidate();
        change(collector);
        return true;
    }

    protected boolean addOperation(E element, ChangeCollector<E> collector)
    {
        if (!collection.add(element)) return false;
        collector.addChange(element);
        return true;
    }

    protected boolean removeOperation(E element, ChangeCollector<E> collector)
    {
        if (!collection.remove(element)) return false;
        collector.removeChange(element);
        return true;
    }

    @SuppressWarnings("DuplicatedCode")
    @Override
    public boolean removeAll(Collection<?> c)
    {
        if (c.isEmpty()) return false;
        if (c.size() == 1) remove(c.iterator().next());
        boolean isModified = false;
        final ChangeCollector<E> collector = new ChangeCollector<>();
        for (Object o : c) //noinspection unchecked
            if (removeOperation((E) o, collector)) isModified = true;
        if(!isModified) return false;
        invalidate();
        change(collector);
        return true;
    }

    @Override
    public boolean removeIf(Predicate<? super E> filter)
    {
        if (isEmpty()) return false;
        if (size() == 1)
        {
            E element = iterator().next();
            if (filter.test(element)) return remove(element);
            return false;
        }
        E element;
        boolean isModified = false;
        final ChangeCollector<E> collector = new ChangeCollector<>();
        for (Iterator<E> iterator = collection.iterator(); iterator.hasNext(); )
        {
            element = iterator.next();
            if (!filter.test(element)) continue;
            try
            {
                iterator.remove();
                collector.removeChange(element);
                isModified = true;
            }
            catch (Exception ignored)
            {
            }
        }
        if(!isModified) return false;
        invalidate();
        change(collector);
        return true;
    }

    @Override
    public boolean retainAll(Collection<?> c)
    {
        if (isEmpty()) return false;
        if (size() == 1)
        {
            E element = iterator().next();
            if (c.contains(element)) return remove(element);
            return false;
        }
        boolean isModified = false;
        final ChangeCollector<E> collector = new ChangeCollector<>();
        E element;
        for (Iterator<E> iterator = collection.iterator(); iterator.hasNext(); )
        {
            element = iterator.next();
            if (c.contains(element)) continue;
            try
            {
                iterator.remove();
                isModified = true;
                collector.removeChange(element);
            }
            catch (Exception ignored)
            {
            }
        }
        if(!isModified) return false;
        invalidate();
        change(collector);
        return true;
    }

    @Override
    public void clear()
    {
        if (collection.size() <= 0) return;
        if (collection.size() == 1)
        {
            E element = iterator().next();
            invalidate();
            change(element, false);
        }
        else
        {
            ChangeCollector<E> collector = new ChangeCollector<>();
            E element;
            for (Iterator<E> iterator = collection.iterator(); iterator.hasNext(); )
            {
                element = iterator.next();
                try
                {
                    iterator.remove();
                    collector.removeChange(element);
                }
                catch (Exception ignored)
                {
                }
            }
            invalidate();
            change(collector);
        }
    }

    @Override
    public Spliterator<E> spliterator()
    {
        return collection.spliterator();
    }

    @Override
    public Stream<E> stream()
    {
        return collection.stream();
    }

    @Override
    public Stream<E> parallelStream()
    {
        return collection.parallelStream();
    }

    @Override
    public String toString()
    {
        return "SimpleObservableCollection{" + "collection=" + collection + '}';
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        SimpleObservableCollection<?> that = (SimpleObservableCollection<?>) o;
        return Objects.equals(collection, that.collection);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(super.hashCode(), collection);
    }

    @Override
    public Object clone() throws CloneNotSupportedException
    {
        @SuppressWarnings("unchecked") SimpleObservableCollection<E> clone = (SimpleObservableCollection<E>) super.clone();
        clone.sizeExpressionRef = null;
        clone.isEmptyExpressionRef = null;
        return clone;
    }
}
