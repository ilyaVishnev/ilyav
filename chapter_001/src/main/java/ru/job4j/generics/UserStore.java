package ru.job4j.generics;

public class UserStore extends AbstractStore<User> {
    // SimpleArray<User> simpleArray;

    public UserStore(int size) {

        super(size);
    }

   /* @Override
    public User add(User model) {
        return simpleArray.add(model);
    }*/

/*    @Override
    public User update(User model) {
        for (int index = 0; index < simpleArray.getObjects().length; index++) {
            if (((User) simpleArray.getObjects()[index]).id.equals(model.id)) {
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
            if (((User) simpleArray.getObjects()[index]).id.equals(id)) {
                simpleArray.getObjects()[index] = null;
                del = index;
                break;
            }
        }
        return simpleArray.getObjects()[del] == null;
    }*/
}
