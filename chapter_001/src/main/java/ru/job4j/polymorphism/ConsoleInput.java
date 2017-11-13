package ru.job4j.polymorphism;

import java.util.Scanner;

public class ConsoleInput implements Input {
    private Scanner scanner = new Scanner(System.in);
    @Override
    public String ask(String question) {
        System.out.print(question);
        return scanner.nextLine();
    }
}
