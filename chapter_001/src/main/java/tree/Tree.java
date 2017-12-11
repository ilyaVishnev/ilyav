package tree;

import java.util.*;

public class Tree<E extends Comparable<E>> implements SimpleTree<E> {
    Node<E> root = new Node<>();
    boolean rootFound = false;

    class Node<E> {
        List<Node<E>> children = new ArrayList<>();
        private E value;

        public void setValue(E value) {
            this.value = value;
        }

        public E getValue() {
            return this.value;
        }

        public List<Node<E>> getChilden() {
            return this.children;
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
            if (this.getValue().equals(other.getValue()))
                return false;
            return true;
        }
    }

    @Override
    public boolean add(E parent, E child) {
        boolean foundChild = false;
        boolean foundParent = false;
        Node<E> childrenNode = new Node<>();
        if (!rootFound) {
            root.setValue(parent);
            root.getChilden().add(root);
            childrenNode.setValue(child);
            root.getChilden().add(childrenNode);
            rootFound = true;
            return true;
        }
        Node<E> parentNode = new Node<>();
        Iterator<Node<E>> iterator = root.getChilden().iterator();
        while (iterator.hasNext()) {
            if (parent.compareTo((parentNode = iterator.next()).value) == 0) {
                foundParent = true;
            }
            if (foundParent && iterator.hasNext() && child.compareTo(iterator.next().value) == 0) {
                foundChild = true;
                break;
            }
        }
        if (!foundChild) {
            if (parentNode.value.compareTo(root.value) < 0) {
                root = parentNode;
                root.getChilden().add(root);
            }
            Node<E> newNode = new Node<>();
            childrenNode.setValue(child);
            List<Node<E>> list = root.getChilden();
            for (int i = 0; i < list.size(); i++) {
                if ((newNode = list.get(i)).value.compareTo(childrenNode.value) < 0) {
                    newNode.getChilden().add(childrenNode);
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            Iterator<Node<E>> iterator = root.getChilden().iterator();

            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public E next() {
                return iterator.next().value;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }
}
