package ru.job4j.threads.Bomberman;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

class Player extends Thread {

    private Cell cell;
    private Cell newCell;
    private Cell[] board;
    private static final Logger logger = LoggerFactory.getLogger(Player.class);

    public Player(String name) {
        super(name);
    }

    public void setCell(Cell cell) {
        this.cell = cell;
    }

    public void setNewCell(Cell cell) {
        this.newCell = cell;
    }

    public Cell getCell() {
        return this.cell;
    }

    public void setBoard(Cell[] board) {
        this.board = board;
    }

    @Override
    public void run() {
        while (true) {
            try {
                this.cell.getReentrantLock().lock();
                if (newCell.getReentrantLock().tryLock(500, TimeUnit.MILLISECONDS)) {
                    this.cell.getReentrantLock().unlock();
                    this.cell = newCell;
                    System.out.println(Thread.currentThread().getName() + "- X: " + this.getCell().getX() + " Y: " + this.getCell().getY());
                    this.cell.getReentrantLock().lock();
                    Thread.sleep(1000);
                }
                newCell = board[(int) (Math.random() * 20)];

            } catch (InterruptedException ex) {
                logger.error(ex.getMessage());
            }
        }
    }
}
