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

    public static List<User> sortNameLength(List<User> list) {
        Collections.sort(list, new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return o1.name.length() - o2.name.length();
            }
        });
        return list;
    }

    public static List<User> sortByAllFields(List<User> list) {
        Collections.sort(list, new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                int result = o1.name.compareTo(o2.name);
                return result != 0 ? result : o1.age.compareTo(o2.age);
            }
        });
        return list;
    }

}
