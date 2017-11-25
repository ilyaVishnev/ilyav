package ru.job4j.polymorphism;

import ru.job4j.incapsulation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StartUI {

    private Input input;
    public Tracker tracker = new Tracker();

    public StartUI(Input input) {
        this.input = input;
    }

    public StartUI(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    public void init() {
        ;
        MenuTracker menu = new MenuTracker(this.input, this.tracker);
        menu.fillAction();
        do {
            menu.show();
            menu.select(input);
        } while (!"y".equals(this.input.ask("Exit ? y")));

    }

    /**
     * Запускт программы.
     *
     * @param args
     */
    public static void main(String[] args) {
       Input input = new ValidateInput();
       new StartUI(input).init();
    }
}
