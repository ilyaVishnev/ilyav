package ru.job4j.chess;

public class OccupiedWayException extends Exception {
    public OccupiedWayException() {
        super("on the way is somebody");
    }
}
