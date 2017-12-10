package ru.job4j.map;

import ru.job4j.generics.DynamicLinkedList;
import ru.job4j.generics.DynamicList;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyHashmap<K, V> implements Iterable<MyHashmap.MyEntry<K, V>> {
    int length = (int) Math.pow(2, 14);
    int size = 0;
    Object[] container = new Object[length];

    boolean insert(K key, V value) {
        MyEntry<K, V> myEntry = new MyEntry<>();
        myEntry.setKey(key);
        myEntry.setValue(value);
        int index = myEntry.hashCode() & (length - 1);
        if (container[index] == null) {
            if ((size > 0.75 * length)) {
                Object[] exchange = container;
                container = new Object[length = 2 * length];
                for (Object object : exchange) {
                    if (object != null) {
                        container[object.hashCode() & (length - 1)] = object;
                    }
                }
            }
            container[index] = myEntry;
            size++;
            return true;
        }
        return false;
    }

    public V get(K key) {
        return (V) ((MyEntry) container[key.hashCode() & (length - 1)]).getValue();
    }

    public boolean delete(K key) {
        int index = key.hashCode() & (length - 1);
        if (container[index] != null) {
            container[index] = null;
            size--;
            return true;
        }
        return false;
    }

    @Override
    public Iterator<MyHashmap.MyEntry<K, V>> iterator() {
        return new Iterator<MyEntry<K, V>>() {
            int indexIter = 0;
            int sizeIter = 0;

            @Override
            public boolean hasNext() {
                return sizeIter < size;
            }

            @Override
            public MyEntry<K, V> next() {
                if (this.hasNext()) {
                    for (int i = indexIter; i < container.length; i++) {
                        if (container[i] != null) {
                            indexIter = i;
                            break;
                        }
                    }
                    sizeIter++;
                    return (MyEntry<K, V>) container[indexIter++];
                }
                throw new NoSuchElementException();
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    public static class MyEntry<K, V> {
        K key;
        V value;

        public void setValue(V value) {
            this.value = value;
        }

        public V getValue() {
            return this.value;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public K getKey() {
            return this.key;
        }

        @Override
        public int hashCode() {
            return this.key.hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            MyEntry<K, V> other = (MyEntry<K, V>) obj;
            if (!key.equals(other.key))
                return false;
            if (!value.equals(other.value))
                return false;
            return true;
        }
    }
}
