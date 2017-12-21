package ru.job4j.tree;

import java.util.*;

public class Order {
    double price;
    int volume;
    int orderId;
    List<Order> match = new LinkedList<>();

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Order other = (Order) obj;
        if (orderId != other.orderId)
            return false;
        return true;
    }
}
