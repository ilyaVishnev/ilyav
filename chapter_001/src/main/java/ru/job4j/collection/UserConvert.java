package ru.job4j.collection;

import java.util.*;

public class UserConvert {
    public static HashMap<Integer, User> process(List<User> list) {
        HashMap<Integer, User> map = new HashMap<Integer, User>();
        for (int index = 0; index < list.size(); index++) {
            map.put(list.get(index).id, list.get(index));
        }
        return map;
    }

    public static void main(String[] args) {
        HashMap<Integer, User> map = new HashMap<Integer, User>();
        List<User> list = new ArrayList<>();
        list.addAll(Arrays.asList(new User(1, "Ilya", "Spb"), new User(4, "Philip", "Leningrad"), new User(7, "goblin", "Moskwa")));
        map = process(list);
        for (Map.Entry<Integer, User> entry : map.entrySet()) {
            System.out.println(entry.getKey() + "  " + entry.getValue());
        }
    }
}
