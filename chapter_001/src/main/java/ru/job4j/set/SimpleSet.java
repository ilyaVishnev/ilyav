package ru.job4j.set;

import java.util.Arrays;
import java.util.Iterator;

public class SimpleSet<E> implements Iterable<E> {
    Object[] objects = new Object[10];
    int index = 0;

    void add(E e) {
        boolean same = false;
        for (Object element : objects) {
            if (element != null && element.equals(e)) {
                same = true;
            }
        }
        if (!same) {
            if (objects[objects.length - 1] != null) {
                objects = Arrays.copyOf(objects, objects.length * 2);
            }
            objects[index++] = e;
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Object element : objects) {
            if (element != null) {
                stringBuilder.append(element);
                stringBuilder.append(" ");
            }
        }
        return stringBuilder.toString();
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int indexIter = 0;

            @Override
            public boolean hasNext() {
                return indexIter < index;
            }

            @Override
            public E next() {
                return (E) objects[indexIter++];
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }
}
