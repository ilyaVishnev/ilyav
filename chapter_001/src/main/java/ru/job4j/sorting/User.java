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

    @Override
    public int hashCode() {
        return this.name.hashCode() + this.age.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        if (name != other.name)
            return false;
        if (age != other.age)
            return false;
        return true;
    }

    public String toString() {
        return " имя: " + this.name + " " + " возраст: " + this.age;
    }
}
