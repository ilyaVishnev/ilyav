package ru.job4j.generics;

public abstract class Base {
    String id;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        return this.id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Base other = (Base) obj;
        if (this.id.equals((other.id)))
            return false;
        return true;
    }
}
