package ru.job4j.set;

import ru.job4j.generics.DynamicLinkedList;

import java.util.*;

public class SimpleLinkedSet<E> implements Iterable<E> {
    DynamicLinkedList<E> dynamicLinkedList = new DynamicLinkedList<E>();
    int index = 0;

    void add(E e) {
        boolean same = false;
        for (int i = 0; i < index; i++) {
            if (e.equals(dynamicLinkedList.get(i))) {
                same = true;
            }
        }
        if (!same || index == 0) {
            dynamicLinkedList.add(e);
            index++;
        }
    }

    @Override
    public String toString() {
        Iterator<E> iterator = dynamicLinkedList.iterator();
        StringBuilder stringBuilder = new StringBuilder();
        while (iterator.hasNext()) {
            stringBuilder.append(iterator.next());
            stringBuilder.append(" ");
        }
        return stringBuilder.toString();
    }

    @Override
    public Iterator<E> iterator() {
        return dynamicLinkedList.iterator();
    }
}
