package ru.job4j.polymorphism;

import ru.job4j.incapsulation.*;

public class StartUI {

    private Input input;

    public StartUI(Input input) {
        this.input = input;
    }

    public void init() {
        Tracker tracker = new Tracker();
        MenuTracker menu = new MenuTracker(this.input, tracker);
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
