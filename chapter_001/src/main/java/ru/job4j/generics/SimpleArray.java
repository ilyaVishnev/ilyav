package ru.job4j.generics;

public class SimpleArray<T> {
    Object[] objects;
    int index = 0;

    public SimpleArray(int size) {
        this.objects = new Object[size];
    }

    public void add(T object) {
        this.objects[index++] = object;
    }

    public void update(int newindex, T object) {
        this.objects[newindex] = object;
    }

    public T delete(int newindex) {
        T result = (T) this.objects[newindex];
        this.objects[newindex] = null;
        return result;
    }

    public T get(int newindex) {
        return (T) this.objects[newindex];
    }
}
