package ru.job4j.banking;

public class User {
    public String name;
    public String passport;

    public User(String name, String passport) {
        this.name = name;
        this.passport = passport;
    }

    @Override
    public int hashCode() {
        return this.name.hashCode() + this.passport.hashCode();
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
        if (passport != other.passport)
            return false;
        return true;
    }

    public String toString() {
        return "Имя: " + this.name + " password: " + this.passport;
    }
}
