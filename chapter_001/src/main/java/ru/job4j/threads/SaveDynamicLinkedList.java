package ru.job4j.threads;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

@ThreadSafe
public class SaveDynamicLinkedList<E> implements Iterable<E> {
    Node<E> prev = new Node<E>();
    Node<E> next = new Node<E>();
    Node firstNode = new Node();
    int index = 0;

    @Override
    public synchronized Iterator<E> iterator() {
        return new Iterator<E>() {
            int indexIter = 0;
            Node<E> node = firstNode;

            @Override
            public boolean hasNext() {
                return indexIter < index;
            }

            @Override
            public E next() {
                if (this.hasNext()) {
                    E item = node.item;
                    node = node.next;
                    indexIter++;
                    return item;
                }
                throw new NoSuchElementException();
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    public synchronized void add(E value) {
        Node<E> node = new Node<E>(prev, value, next);
        prev.next = node;
        prev = node;
        if (index == 0) {
            firstNode = node;
        }
        index++;
    }

    public synchronized E get(int index) {
        Node<E> node = firstNode;
        for (int i = 1; i <= index; i++) {
            node = node.next;
        }
        return node.item;
    }

    private static class Node<E> {
        E item;
        Node<E> prev;
        Node<E> next;

        public Node() {
        }

        public Node(Node<E> prev, E item, Node<E> next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }
    }
}
