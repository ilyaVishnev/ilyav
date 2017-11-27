package ru.job4j.collection;

import java.util.*;

public class UserConvert {

    public HashMap<Integer, User> process(List<User> list) {
        HashMap<Integer, User> map = new HashMap<Integer, User>();
        for (User user : list) {
            map.put(user.id, user);
        }
        return map;
    }

    public static void main(String[] args) {
        HashMap<Integer, User> map = new HashMap<Integer, User>();
        UserConvert userConvert = new UserConvert();
        List<User> list = new ArrayList<>();
        list.addAll(Arrays.asList(new User(1, "Ilya", "Spb"), new User(4, "Philip", "Leningrad"), new User(7, "goblin", "Moskwa")));
        map = userConvert.process(list);
        for (Map.Entry<Integer, User> mapp : map.entrySet()) {
            System.out.println("ключ: " + mapp.getKey() + " значение: " + mapp.getValue());
        }
    }
}
