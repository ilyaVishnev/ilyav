package ru.job4j.sorting;

import java.util.*;

public class SortUser {
    public static Set<User> sort(List<User> list) {
        Set<User> set = new TreeSet<User>();
        for (int i = 0; i < list.size(); i++) {
            set.add(list.get(i));
        }
        return set;
    }
}
