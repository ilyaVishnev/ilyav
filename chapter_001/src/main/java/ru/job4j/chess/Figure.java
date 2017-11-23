package ru.job4j.chess;

public abstract class Figure implements Cloneable {
    final Cell position;

    public Figure(Cell position) {
        this.position = position;
    }

    abstract Cell[] way(Cell dist) throws ImposibleMoveException;

    public abstract Figure clone(Cell dist);
}
