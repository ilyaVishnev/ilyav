package ru.job4j.generics;

public class SimpleStack<T> {
    DynamicLinkedList<T> dynamicLinkedList = new DynamicLinkedList<T>();

    public T poll() {
        DynamicLinkedList<T> dynamicLinkedPollList = new DynamicLinkedList<T>();
        T item = dynamicLinkedList.get(0);
        for (int index = 1; index < dynamicLinkedList.index; index++) {
            dynamicLinkedPollList.add(dynamicLinkedList.get(index));
        }
        dynamicLinkedList = dynamicLinkedPollList;
        return item;
    }

    public void push(T value) {
        dynamicLinkedList.add(value);
    }
}
