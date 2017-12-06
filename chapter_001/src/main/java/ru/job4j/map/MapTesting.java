package ru.job4j.map;

import java.util.*;

public class MapTesting {
    public static void main(String[] args) {
        User first = new User("ilya", 2, new GregorianCalendar(2000, 10, 12));
        User second = new User("ilya", 2, new GregorianCalendar(2000, 10, 12));
        Map<User, Object> map = new HashMap<User, Object>();
        map.put(first, 1);
        map.put(second, 2);
        System.out.println(map);
    }
}
