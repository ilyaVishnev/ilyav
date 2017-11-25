package ru.job4j.collection;

public class User {
    public User(Integer id, String name, String city) {
        this.id = id;
        this.name = name;
        this.city = city;
    }

    Integer id;
    String name;
    String city;

    @Override
    public String toString() {
        return "id elementa: " + this.id + " name elementa: " + this.name + " city elementa: " + this.city;
    }
}
