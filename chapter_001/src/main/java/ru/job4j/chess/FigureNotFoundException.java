package ru.job4j.chess;

public class FigureNotFoundException extends Exception {
    public FigureNotFoundException() {
        super("figure was not faund");
    }
}
