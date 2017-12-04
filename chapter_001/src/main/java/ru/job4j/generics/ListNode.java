package ru.job4j.generics;

public class ListNode<T> {
    boolean hasCycle(Node<T> first) {
        Node<T> node;
        while (first != null) {
            first = first.next;
            node = first;
            while (node != null) {
                node = node.next;
                if (first == node) {
                    return true;
                }
            }
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
