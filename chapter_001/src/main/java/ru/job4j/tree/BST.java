package ru.job4j.tree;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Optional;
import java.util.*;

public class BST<E extends Comparable<E>> implements Iterable<E> {
    Optional<Node<E>> next = Optional.empty();
    Optional<Node<E>> root = Optional.empty();
    boolean found = false;
    ;

    public void add(E e) {
        if (!root.isPresent()) {
            root = Optional.ofNullable(new Node<E>(e));
            root.get().left = new Node<>();
            root.get().right = new Node<>();
            next = root;
            return;
        }
        if (found) {
            next = root;
            found = false;
        }
        if (e.compareTo(next.get().value) == 0 || e.compareTo(next.get().value) < 0) {
            next = Optional.ofNullable(next.get().left);
            if (next.get().value != null) {
                this.add(e);
            }
            next.get().value = e;
            next.get().left = new Node<>();
            next.get().right = new Node<>();
            found = true;
        } else {
            next = Optional.ofNullable(next.get().right);
            if (next.get().value != null) {
                this.add(e);
            }
            next.get().value = e;
            next.get().left = new Node<>();
            next.get().right = new Node<>();
            found = true;
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator() {
            LinkedList<Optional<Node<E>>> dataIterator = new LinkedList<>();
            boolean rootIs = false;

            @Override
            public boolean hasNext() {
                return !rootIs || !dataIterator.isEmpty();
            }

            @Override
            public Object next() {
                if (!rootIs) {
                    dataIterator.addLast(root);
                    rootIs = true;
                }
                Optional<BST<E>.Node<E>> next = dataIterator.pollLast();
                Optional<BST<E>.Node<E>> left = Optional.ofNullable(next.get().left);
                Optional<BST<E>.Node<E>> right = Optional.ofNullable(next.get().right);
                if (right.get().value != null)
                    dataIterator.addLast(right);
                if (left.get().value != null)
                    dataIterator.addLast(left);
                return next.get().value;
            }
        };
    }

    class Node<E> {
        Node<E> left;
        Node<E> right;
        E value;

        public Node() {
        }

        public Node(E value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            Node<E> other = (Node<E>) obj;
            if (value != other.value)
                return false;
            return true;
        }
    }
}
