package ru.job4j.tree;

import java.util.*;

public class Book {
    String name;
    Set<Order> bid = new TreeSet<>(new Comparator<Order>() {
        @Override
        public int compare(Order o1, Order o2) {
            return (int) (o1.price - o2.price);
        }
    });
    Set<Order> ask = new TreeSet<>(new Comparator<Order>() {
        @Override
        public int compare(Order o1, Order o2) {
            return (int) (o2.price - o1.price);
        }
    });

    public void delete(Order order) {
        bid.remove(order);
        ask.remove(order);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Book other = (Book) obj;
        if (!name.equals(other.name))
            return false;
        return true;
    }
}
