package ru.job4j.polymorphism;

import java.util.Scanner;

public class ConsoleInput implements Input {
    private Scanner scanner = new Scanner(System.in);

    @Override
    public String ask(String question) {
        System.out.print(question);
        return scanner.nextLine();
    }

    public int ask(String question, int[] range) {
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
