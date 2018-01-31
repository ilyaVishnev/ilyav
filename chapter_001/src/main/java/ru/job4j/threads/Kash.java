package ru.job4j.threads;

//import org.omg.CORBA.Object;

import java.lang.*;

import java.util.concurrent.ConcurrentHashMap;

public class Kash {
    ConcurrentHashMap<String, Model> concurrentHashMap = new ConcurrentHashMap();
    static int key = 0;
    int oldVersion;

    public void add(Model model) {
        concurrentHashMap.put(model.id, model);
    }

    public void update(Model model) {
        oldVersion = model.version;
        concurrentHashMap.put(model.id, model);
        if (concurrentHashMap.get(model.id).version != oldVersion)
            throw new OplimisticException();
    }

    public void delete(Model model) {
        concurrentHashMap.remove(model.id);
    }
}

class Model {
    String name;
    int version = 0;
    String id;

    Model(String name, String id) {
        this.name = name;
        this.id = id;
        version++;
    }

    @Override
    public int hashCode() {
        return this.name.hashCode() + version;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object)
            return true;
        if (object == null)
            return false;
        if (this.getClass() != object.getClass())
            return false;
        Model another = (Model) object;
        if (!this.name.equals(another.name))
            return false;
        if (this.version != another.version)
            return false;
        return true;
    }
}

class OplimisticException extends RuntimeException {
    OplimisticException() {
        super("OptimisticException");
    }
}