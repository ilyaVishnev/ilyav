package ru.job4j.generics;

import java.util.Iterator;

public class ListNode<T> {
    boolean hasCycle(Node<T> first) {
        Node<T> node;
        DynamicLinkedList<Node> dynamicLinkedList = new DynamicLinkedList<>();
        while (first != null) {
            node = first.next;
            dynamicLinkedList.add(first);
            Iterator<Node> iterator = dynamicLinkedList.iterator();
            while (iterator.hasNext()) {
                if (iterator.next() == node) {
                    return true;
                }
            }
            first = node;
        }
        return false;
    }

    public class Node<T> {
        T value;
        Node<T> next;

        public Node(T value) {
            this.value = value;
        }
    }
}
