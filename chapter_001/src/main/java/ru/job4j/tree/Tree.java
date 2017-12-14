package ru.job4j.tree;

import java.util.*;

public class Tree<E extends Comparable<E>> implements SimpleTree<E>{
    Node<E> root;
    boolean rootFound = false;

    @Override
    public boolean add(E parent, E child) {
        if (!rootFound) {
            root = new Node<>(parent);
            rootFound = true;
        }
        if (this.findBy(parent) != null) {
            Optional<Node<E>> parentNode = this.findBy(parent);
            Node<E> childNode = new Node<>(child);
            parentNode.get().add(childNode);
        }
        return false;
    }

    // @Override
    public Optional<Node<E>> findBy(E value) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (el.eqValue(value)) {
                rsl = Optional.of(el);
                break;
            }
            for (Node<E> child : el.leaves()) {
                data.offer(child);
            }
        }
        return rsl;
    }

    @Override
    public Iterator iterator() {
        return null;
    }

    public class Node<E extends Comparable<E>> {
        private final List<Node<E>> children = new ArrayList<>();
        private final E value;

        public Node(final E value) {
            this.value = value;
        }

        public void add(Node<E> child) {
            this.children.add(child);
        }

        public List<Node<E>> leaves() {
            return this.children;
        }

        public boolean eqValue(E that) {
            return this.value.compareTo(that) == 0;
        }
    }

}
