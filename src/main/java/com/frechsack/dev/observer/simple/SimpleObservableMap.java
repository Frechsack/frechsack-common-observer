package com.frechsack.dev.observer.simple;

import com.frechsack.dev.observer.core.BooleanExpression;
import com.frechsack.dev.observer.core.NumberExpression;
import com.frechsack.dev.observer.core.ObservableMap;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.util.*;
import java.util.function.*;
import java.util.stream.Stream;

public class SimpleObservableMap<K, V> extends AbstractObservableMultiple<Map.Entry<K, V>> implements Map<K, V>, ObservableMap<K, V>, Cloneable
{
    private final Map<K, V> map;
    private Reference<BooleanExpression> isEmptyExpressionRef;
    private Reference<NumberExpression<Integer>> sizeExpressionRef;
    private Reference<Set<Entry<K, V>>> entrySetRef;

    public SimpleObservableMap(Map<K, V> map) {this.map = map;}

    @Override
    public BooleanExpression isEmptyExpression()
    {
        BooleanExpression isEmptyExpression = isEmptyExpressionRef == null ? null : isEmptyExpressionRef.get();
        if (isEmptyExpression == null)
        {
            isEmptyExpression = new SimpleBooleanExpression(map.isEmpty(), this, getEventHandler())
            {
                @Override
                protected void computeValue()
                {
                    setValue(SimpleObservableMap.this.map.isEmpty());
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
            sizeExpression = new SimpleIntegerExpression(map.size(), this, getEventHandler())
            {
                @Override
                protected void computeValue()
                {
                    setValue(SimpleObservableMap.this.map.size());
                }
            };
            sizeExpressionRef = new WeakReference<>(sizeExpression);
        }
        return sizeExpression;
    }

    @SafeVarargs
    @Override
    public final boolean addAll(Entry<K, V>... entries)
    {
        if (entries.length == 0) return false;
        if (entries.length == 1)
        {
            V value = entries[0].getValue();
            return put(entries[0].getKey(), entries[0].getValue()) != value;
        }
        ChangeCollector<Entry<K, V>> collector = new ChangeCollector<>();
        for (Entry<? extends K, ? extends V> entry : entries) putOperation(entry.getKey(), entry.getValue(), collector);
        if (!collector.containsChange()) return false;
        invalidate();
        change(collector);
        return true;
    }

    @SafeVarargs
    @Override
    public final boolean removeAll(Entry<K, V>... entries)
    {
        if (entries.length == 0) return false;
        V value;
        K key;
        if (entries.length == 1)
        {
            value = entries[0].getValue();
            key = entries[0].getKey();
            return value == null ? remove(key) == null ? true : true : remove(key, value);
        }
        final ChangeCollector<Entry<K, V>> collector = new ChangeCollector<>();
        boolean isModified = false;
        for (Entry<K, V> entry : entries)
        {
            value = entry.getValue();
            key = entry.getKey();
            if (value == null)
            {
                if (removeOperation(key, collector) == null) isModified = true;
            }
            else if (removeOperation(key, value, collector)) isModified = true;
        }
        if(!isModified) return false;
        invalidate();
        change(collector);
        return true;
    }


    @Override
    public int size()
    {
        return map.size();
    }

    @Override
    public boolean isEmpty()
    {
        return map.isEmpty();
    }

    @Override
    public boolean containsKey(Object key)
    {
        return map.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value)
    {
        return map.containsValue(value);
    }

    @Override
    public V get(Object key)
    {
        return map.get(key);
    }

    private V putOperation(K key, V value, ChangeCollector<Entry<K, V>> collector)
    {
        boolean containsKey = map.containsKey(key);
        if (!containsKey)
        {
            map.put(key, value);
            collector.addChange(new SimpleEntry(key, value));
            return null;
        }
        final V last = map.put(key, value);
        if (Objects.equals(last, value)) return last;
        collector.addChange(new SimpleEntry(key, value));
        collector.removeChange(new SimpleEntry(key, last));
        return last;
    }

    private boolean removeOperation(K key, V value, ChangeCollector<Entry<K, V>> collector)
    {
        boolean containsKey = map.containsKey(key);
        if (containsKey)
        {
            boolean isModified = map.remove(key, value);
            collector.removeChange(new SimpleEntry(key, value));
            return isModified;
        }
        return false;
    }

    private V removeOperation(K key, ChangeCollector<Entry<K, V>> collector)
    {
        boolean containsKey = map.containsKey(key);
        if (containsKey)
        {
            final V last = map.remove(key);
            collector.removeChange(new SimpleEntry(key, last));
            return last;
        }
        return null;
    }

    @Override
    public String toString()
    {
        return "SimpleObservableMap{" + "map=" + map + '}';
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        SimpleObservableMap<?, ?> that = (SimpleObservableMap<?, ?>) o;
        return Objects.equals(map, that.map);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(super.hashCode(), map);
    }

    private class SimpleEntry implements Entry<K, V>
    {
        private final K key;
        private final V value;

        private SimpleEntry(K key, V value)
        {
            this.key = key;
            this.value = value;
        }

        @Override
        public K getKey()
        {
            return key;
        }

        @Override
        public V getValue()
        {
            return value;
        }

        @Override
        public V setValue(V value)
        {
            return put(key, value);
        }

        @Override
        public String toString()
        {
            return "SimpleEntry{" + "key=" + key + ", value=" + value + '}';
        }

        @Override
        public boolean equals(Object o)
        {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            @SuppressWarnings("unchecked") SimpleEntry that = (SimpleEntry) o;
            return Objects.equals(key, that.key) && Objects.equals(value, that.value);
        }

        @Override
        public int hashCode()
        {
            return Objects.hash(key, value);
        }
    }

    @Override
    public V put(K key, V value)
    {
        if (map.containsKey(key))
        {
            V last = map.put(key, value);
            if (Objects.equals(last, value)) return last;
            ChangeCollector<Entry<K, V>> collector = new ChangeCollector<>();
            collector.removeChange(new SimpleEntry(key, last));
            collector.addChange(new SimpleEntry(key, value));
            invalidate();
            change(collector);
            return last;
        }
        map.put(key, value);
        invalidate();
        change(new SimpleEntry(key, value), true);
        return null;
    }

    @Override
    public V remove(Object key)
    {
        if (map.containsKey(key))
        {
            V last = map.remove(key);
            invalidate();
            try
            {
                //noinspection unchecked
                change(new SimpleEntry((K) key, last), false);
            }
            catch (Exception ignored)
            {
            }
            return last;
        }
        return null;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m)
    {
        if (m.isEmpty()) return;
        if (m.size() == 1)
        {
            Entry<? extends K, ? extends V> entry = m.entrySet().iterator().next();
            put(entry.getKey(), entry.getValue());
            return;
        }
        ChangeCollector<Entry<K, V>> collector = new ChangeCollector<>();
        for (Entry<? extends K, ? extends V> entry : m.entrySet()) putOperation(entry.getKey(), entry.getValue(), collector);
        if (!collector.containsChange()) return;
        invalidate();
        change(collector);
    }

    @Override
    public void clear()
    {
        if (isEmpty()) return;
        if (size() == 1)
        {
            Entry<K, V> entry = entrySet().iterator().next();
            SimpleEntry simpleEntry = new SimpleEntry(entry.getKey(), entry.getValue());
            map.clear();
            invalidate();
            change(simpleEntry, false);
            return;
        }
        ChangeCollector<Entry<K, V>> collector = new ChangeCollector<>();
        for (Entry<K, V> entry : map.entrySet()) collector.removeChange(new SimpleEntry(entry.getKey(), entry.getValue()));
        map.clear();
        invalidate();
        change(collector);
    }

    @Override
    public Set<K> keySet()
    {
        return map.keySet();
    }

    @Override
    public Collection<V> values()
    {
        return map.values();
    }

    @Override
    public Set<Entry<K, V>> entrySet()
    {
        Set<Entry<K, V>> entrySet = entrySetRef == null ? null : entrySetRef.get();
        if (entrySet == null)
        {
            entrySet = new SimpleEntrySet();
            entrySetRef = new WeakReference<>(entrySet);
        }
        return entrySet;
    }

    @Override
    public V getOrDefault(Object key, V defaultValue)
    {
        return map.getOrDefault(key, defaultValue);
    }

    @Override
    public void forEach(BiConsumer<? super K, ? super V> action)
    {
        map.forEach(action);
    }

    @Override
    public void replaceAll(BiFunction<? super K, ? super V, ? extends V> function)
    {
        if (isEmpty()) return;
        if (size() == 1)
        {
            K key = keySet().iterator().next();
            V value = get(key);
            put(key, function.apply(key, value));
            return;
        }
        ChangeCollector<Entry<K, V>> collector = new ChangeCollector<>();
        for (K key : keySet())
        {
            V value = get(key);
            putOperation(key, function.apply(key, value), collector);
        }
        if (!collector.containsChange()) return;
        invalidate();
        change(collector);
    }

    @Override
    public V putIfAbsent(K key, V value)
    {
        V last = get(key);
        if (last == null) put(key, value);
        return last;
    }

    @Override
    public boolean remove(Object key, Object value)
    {
        if (isEmpty()) return false;
        if (map.remove(key, value))
        {
            invalidate();
            try
            {
                //noinspection unchecked
                change(new SimpleEntry((K) key, (V) value), false);
            }
            catch (Exception ignored)
            {
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean replace(K key, V oldValue, V newValue)
    {
        V lastValue = get(key);
        if (!Objects.equals(lastValue, oldValue)) return false;
        if (Objects.equals(newValue, lastValue)) return false;
        put(key, newValue);
        return true;
    }

    @Override
    public V replace(K key, V value)
    {
        if (!containsKey(key)) return null;
        V lastValue = get(key);
        if (Objects.equals(value, lastValue)) return lastValue;
        put(key, value);
        return lastValue;
    }

    private class SimpleEntrySet extends AbstractSet<Entry<K, V>> implements Set<Entry<K, V>>
    {
        @SuppressWarnings("unchecked")
        @Override
        public boolean remove(Object o)
        {
            if (o instanceof Entry)
            {
                try
                {
                    K key = ((Entry<K, V>) o).getKey();
                    V value = ((Entry<K, V>) o).getValue();
                    if (value == null) SimpleObservableMap.this.remove(key);
                    else SimpleObservableMap.this.remove(key, value);
                    return true;
                }
                catch (Exception e)
                {
                    return false;
                }
            }
            else
            {
                try
                {
                    SimpleObservableMap.this.remove(o);
                    return true;
                }
                catch (Exception e)
                {
                    return false;
                }
            }
        }

        @SuppressWarnings("unchecked")
        @Override
        public boolean removeAll(Collection<?> c)
        {
            if (c.isEmpty()) return false;
            if (c.size() == 1)
            {
                try
                {
                    Object o = c.iterator().next();
                    return SimpleObservableMap.this.remove(((Entry<K, V>) o).getKey(), ((Entry<K, V>) o).getValue());
                }
                catch (Exception ignored)
                {
                    return false;
                }
            }
            final ChangeCollector<Entry<K, V>> collector = new ChangeCollector<>();
            K key;
            V value;
            boolean isModified = false;
            for (Object o : c)
            {
                if (o instanceof Entry)
                {
                    try
                    {
                        key = ((Entry<K, V>) o).getKey();
                        value = ((Entry<K, V>) o).getValue();
                        if (removeOperation(key, value, collector)) isModified = true;
                    }
                    catch (Exception ignored)
                    {
                    }
                }
            }
            return isModified;
        }

        @Override
        public Iterator<Entry<K, V>> iterator()
        {
            final Iterator<Entry<K, V>> iterator = map.entrySet().iterator();
            return new Iterator<>()
            {
                Entry<K, V> element;

                @Override
                public boolean hasNext()
                {
                    return iterator.hasNext();
                }

                @Override
                public Entry<K, V> next()
                {
                    return element = iterator.next();
                }

                @Override
                public void remove()
                {
                    try
                    {
                        iterator.remove();
                        invalidate();
                        change(element, false);
                    }
                    catch (Exception ignored)
                    {
                    }

                }
            };
        }

        @Override
        public void forEach(Consumer<? super Entry<K, V>> action)
        {
            map.entrySet().forEach(action);
        }

        @Override
        public <T> T[] toArray(IntFunction<T[]> generator)
        {
            //noinspection SuspiciousToArrayCall
            return map.entrySet().toArray(generator);
        }

        @Override
        public boolean removeIf(Predicate<? super Entry<K, V>> filter)
        {
            ChangeCollector<Entry<K, V>> collector = new ChangeCollector<>();
            Entry<K, V> element;
            boolean isModified = false;
            for (Iterator<Entry<K, V>> iterator = map.entrySet().iterator(); iterator.hasNext(); )
            {
                if (!filter.test(element = iterator.next())) continue;
                K key = element.getKey();
                V value = element.getValue();
                try
                {
                    iterator.remove();
                    collector.removeChange(new SimpleEntry(key, value));
                    isModified = true;
                }
                catch (Exception ignored)
                {
                }

            }
            change(collector);
            return isModified;
        }

        @Override
        public Spliterator<Entry<K, V>> spliterator()
        {
            return map.entrySet().spliterator();
        }

        @Override
        public Stream<Entry<K, V>> stream()
        {
            return map.entrySet().stream();
        }

        @Override
        public Stream<Entry<K, V>> parallelStream()
        {
            return map.entrySet().parallelStream();
        }

        @Override
        public int size()
        {
            return map.entrySet().size();
        }

        @Override
        public boolean add(Entry<K, V> kvEntry)
        {
            return !Objects.equals(put(kvEntry.getKey(), kvEntry.getValue()), kvEntry.getValue());
        }

        @Override
        public boolean addAll(Collection<? extends Entry<K, V>> c)
        {
            return SimpleObservableMap.this.addAll(c.toArray((IntFunction<Entry<K, V>[]>) Entry[]::new));
        }

        @Override
        public boolean contains(Object o)
        {
            return o instanceof Entry && containsKey(((Entry<?, ?>) o).getKey());
        }

        @Override
        public boolean containsAll(Collection<?> c)
        {
            return super.containsAll(c);
        }

    }

    @Override
    public Object clone() throws CloneNotSupportedException
    {
        @SuppressWarnings("unchecked") SimpleObservableMap<K,V> clone = (SimpleObservableMap<K, V>) super.clone();
        clone.entrySetRef = null;
        clone.isEmptyExpressionRef = null;
        clone.sizeExpressionRef = null;
        return clone;
    }
}
