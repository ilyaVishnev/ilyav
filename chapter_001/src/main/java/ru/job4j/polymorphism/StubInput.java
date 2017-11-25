package ru.job4j.polymorphism;

import java.util.ArrayList;
import java.util.List;

public class StubInput implements Input {
    private List<String> answers=new ArrayList<String>();
    private int position = 0;

    public StubInput(List<String> answers) {
        this.answers = answers;
    }

    @Override
    public String ask(String question) {
        return answers.get(position++);
    }

    @Override
    public int ask(String question, List<Integer> range) {
        int key = Integer.valueOf(this.ask(question));
        boolean value = false;
        for (int number : range) {
            if (key == number) {
                value = true;
                break;
            }
        }
        if (value) {
            return key;
        } else {
            throw new MenuOutException();
        }
    }
}
