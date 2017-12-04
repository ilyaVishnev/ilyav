package ru.job4j.generics;

public class SimpleQueue<T> {
    DynamicLinkedList<T> dynamicLinkedList = new DynamicLinkedList<T>();

    public T poll() {
        DynamicLinkedList<T> dynamicLinkedPollList = new DynamicLinkedList<T>();
        T item = dynamicLinkedList.get(dynamicLinkedList.index - 1);
        for (int index = 0; index < dynamicLinkedList.index - 1; index++) {
            dynamicLinkedPollList.add(dynamicLinkedList.get(index));
        }
        dynamicLinkedList = dynamicLinkedPollList;
        return item;
    }

    public void push(T value) {
        dynamicLinkedList.add(value);
    }
}
