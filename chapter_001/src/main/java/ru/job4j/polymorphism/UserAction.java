package ru.job4j.polymorphism;

import ru.job4j.incapsulation.*;

public interface UserAction {
   /* int key();*/

    void execute(Input input, Tracker tracker);

    String info();
}
