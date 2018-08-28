package ru.job4j.threads.Bomberman.Bomberman2;

import java.util.concurrent.locks.ReentrantLock;

public class Cell {

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

    public void setReentrantLock(ReentrantLock reentrantLock) {
        this.reentrantLock = reentrantLock;
    }
}
