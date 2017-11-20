package ru.job4j.chess;

public class Cell {
    int x;
    int y;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Cell cell = (Cell) o;

        if (x != cell.x)
            return false;

        if (y != cell.y)
            return false;

        return true;
    }

    @Override
    public String toString() {
        return " x: " + this.x + " y: " + this.y;
    }
}
