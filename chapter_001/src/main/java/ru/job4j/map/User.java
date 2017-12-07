package ru.job4j.map;

import java.util.*;

public class User {
    String name;
    int children;
    Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

  @Override
    public int hashCode() {
        return name.hashCode() + children + birthday.hashCode();
    }

    @Override
    public boolean equals(Object object) {
        if (this == object)
            return true;
        if (object == null)
            return false;
        if (this.getClass() != object.getClass())
            return false;
        User another = (User) object;
        if (!birthday.equals(another.birthday) || children != another.children || !name.equals(another.name))
            return false;
        return true;
    }
}
