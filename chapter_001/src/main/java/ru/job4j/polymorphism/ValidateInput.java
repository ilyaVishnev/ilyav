package ru.job4j.polymorphism;

import java.util.List;

public class ValidateInput extends ConsoleInput {

    public int ask(String question, List<Integer> range) {
        int value = -1;
        boolean invalid = true;
        do {
            try {
                value = super.ask(question, range);
                invalid = false;
            } catch (MenuOutException mex) {
                System.out.println(mex.getMessage());
            } catch (Exception eex) {
                System.out.println("value isn't a number");
            }
        } while (invalid);
        return value;
    }

}
