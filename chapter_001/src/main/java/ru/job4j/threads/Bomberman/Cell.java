package ru.job4j.threads.Bomberman;

import java.util.concurrent.locks.ReentrantLock;

class Cell {

    private int x;
    private int y;
    private ReentrantLock reentrantLock = new ReentrantLock();

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public ReentrantLock getReentrantLock() {
        return reentrantLock;
    }
}
