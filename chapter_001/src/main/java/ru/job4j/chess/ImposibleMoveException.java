package ru.job4j.chess;

public class ImposibleMoveException extends Exception {
    public ImposibleMoveException() {
        super("can't move");
    }
}
