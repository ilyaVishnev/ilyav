package ru.job4j.generics;

public abstract class AbstractStore<T extends Base> {
    SimpleArray<T> simpleArray;

    public AbstractStore(int size) {
        simpleArray = new SimpleArray<T>(size);
    }

    public T add(T model) {
        return simpleArray.add(model);
    }


    public Base update(T model) {
        for (int index = 0; index < simpleArray.getObjects().length; index++) {
            if (((T) simpleArray.getObjects()[index]).id.equals(model.id)) {
                simpleArray.getObjects()[index] = model;
                break;
            }
        }
        return model;
    }


    public boolean delete(String id) {
        int del = 0;
        for (int index = 0; index < simpleArray.getObjects().length; index++) {
            if (((T) simpleArray.getObjects()[index]).id.equals(id)) {
                simpleArray.getObjects()[index] = null;
                del = index;
                break;
            }
        }
        return simpleArray.getObjects()[del] == null;
    }
}
