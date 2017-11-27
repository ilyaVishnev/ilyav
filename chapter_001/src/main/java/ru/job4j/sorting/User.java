package ru.job4j.sorting;

public class User implements Comparable<User> {
    String name;
    Integer age;

    public User(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public int compareTo(User o) {
        int result = age.compareTo(o.age);
        return result != 0 ? result : this.name.compareTo(o.name);
    }

    public String toString() {
        return " имя: " + this.name + " " + " возраст: " + this.age;
    }
}
