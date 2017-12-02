package ru.job4j.generics;

public class RoleStore implements Store<Role> {
    SimpleArray<Role> simpleArray;

    public RoleStore(int size) {
        simpleArray = new SimpleArray<Role>(size);
    }

    @Override
    public Role add(Role model) {
        return simpleArray.add(model);
    }

    @Override
    public Role update(Role model) {
        for (int index = 0; index < simpleArray.getObjects().length; index++) {
            if (((Role) simpleArray.getObjects()[index]).id.equals(model.id)) {
                simpleArray.getObjects()[index] = model;
                break;
            }
        }
        return model;
    }

    @Override
    public boolean delete(String id) {
        int del = 0;
        for (int index = 0; index < simpleArray.getObjects().length; index++) {
            if (((Role) simpleArray.getObjects()[index]).id.equals(id)) {
                simpleArray.getObjects()[index] = null;
                del = index;
                break;
            }
        }
        return simpleArray.getObjects()[del] == null;
    }
}
