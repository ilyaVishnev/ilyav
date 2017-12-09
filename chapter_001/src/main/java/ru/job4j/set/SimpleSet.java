package ru.job4j.set;

import ru.job4j.generics.DynamicLinkedList;

import java.util.*;

public class SimpleSet<E> implements Iterable<E> {
    Object[] objects = new Object[10];
    int index = 0;

    void add(E e) {
        if (!this.searchForDublicate(e)) {
            if (objects[objects.length - 1] != null) {
                objects = Arrays.copyOf(objects, objects.length * 2);
            }
            objects[index++] = e;
        }
    }

    public boolean searchForDublicate(E e) {
        for (Object element : objects) {
            if (element != null && element.equals(e)) {
                return true;
            }
        }
        return false;
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
                if (objects[indexIter++] != null) {
                    return (E) objects[indexIter++];
                }
                throw new NoSuchElementException();
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }
}
