package ru.job4j.set;

import ru.job4j.generics.DynamicList;

import java.util.Arrays;

public class SetHashtable<E> {
    int length = 10000;
    int size = 0;
    Object[] container = new Object[length];

    boolean add(E e) {
        if (!this.contains(e)) {
            if ((size > 0.75 * length)) {
                container = Arrays.copyOf(container, container.length * 2);
            }
            container[e.hashCode() & (length - 1)] = e;
            size++;
            return true;
        }
        return false;
    }

    boolean contains(E e) {
        return container[e.hashCode() & (length - 1)] != null;
    }

    boolean remove(E e) {
        if (this.contains(e)) {
            container[e.hashCode() & (length - 1)] = null;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Object element : container) {
            if (element != null) {
                stringBuilder.append(element);
                stringBuilder.append(" ");
            }
        }
        return stringBuilder.toString();
    }
}
