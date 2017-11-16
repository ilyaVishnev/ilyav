package ru.job4j.polymorphism;

public class MenuOutException extends RuntimeException {
    public MenuOutException() {
        super("value out of range");
    }
}
