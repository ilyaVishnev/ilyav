package ru.job4j.threads.Bomberman.Bomberman2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.threads.Bomberman.Player2;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class Player3 extends Thread {
    private Cell cell;
    private Cell newCell;
    String direction;
    private ReentrantLock[][] board;
    Cell target;
    private boolean goal = false;
    private boolean kick = false;
    private static final Logger logger = LoggerFactory.getLogger(Player2.class);

    public Player3(Cell cell, Cell TargetCell, ReentrantLock[][] board) {
        super("Bomberman");
        this.cell = cell;
        this.target = TargetCell;
        this.board = board;
        this.lookAtTarget();
    }

    public void setNewCell(Cell newCell) {
        this.newCell = newCell;
    }

    public void setCell(Cell cell) {
        this.cell = cell;
    }

    @Override
    public void run() {
        this.cell.getReentrantLock().lock();
        this.makeStap();
        while (this.cell.getX() != this.target.getX() || this.cell.getY() != this.target.getY()) {
            try {
                if (this.newCell.getReentrantLock().tryLock(500, TimeUnit.MILLISECONDS)) {
                    this.cell.getReentrantLock().unlock();
                    this.cell = this.newCell;
                    this.cell.getReentrantLock().lock();
                    System.out.println(Thread.currentThread().getName() + "- X: " + this.cell.getX() + " Y: " + this.cell.getY());
                    if (kick) {
                        this.reverse("left");
                        this.makeStap();
                    } else {
                        this.lookAtTarget();
                        this.makeStap();
                    }
                    kick = false;
                } else {
                    kick = true;
                    this.reverse("right");
                    this.makeStap();
                }

            } catch (InterruptedException ex) {
                logger.error(ex.getMessage());
            }
        }
        System.out.println("Bingo");
        this.cell.getReentrantLock().unlock();
        this.goal = true;
    }

    public void makeStap() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            logger.error(ex.getMessage());
        }
        switch (direction) {
            case "+y":
                this.newCell = new Cell(this.cell.getX(), this.cell.getY() + 1);
                if (this.checkBorder()) {
                    this.newCell.setReentrantLock(board[this.cell.getX()][this.cell.getY() + 1]);
                }
                break;
            case "-y":
                this.newCell = new Cell(this.cell.getX(), this.cell.getY() - 1);
                if (this.checkBorder()) {
                    this.newCell.setReentrantLock(board[this.cell.getX()][this.cell.getY() - 1]);
                }
                break;
            case "+x":
                this.newCell = new Cell(this.cell.getX() + 1, this.cell.getY());
                if (this.checkBorder()) {
                    this.newCell.setReentrantLock(board[this.cell.getX() + 1][this.cell.getY()]);
                }
                break;
            case "-x":
                this.newCell = new Cell(this.cell.getX() - 1, this.cell.getY());
                if (this.checkBorder()) {
                    this.newCell.setReentrantLock(board[this.cell.getX() - 1][this.cell.getY()]);
                }
                break;
        }
    }

    public boolean isGoal() {
        return goal;
    }

    public void reverse(String reverse) {
        switch (direction) {
            case "+y":
                if (reverse.equals("right")) {
                    direction = "+x";
                } else {
                    direction = "-x";
                }
                break;
            case "-y":
                if (reverse.equals("right")) {
                    direction = "-x";
                } else {
                    direction = "+x";
                }
                break;
            case "+x":
                if (reverse.equals("right")) {
                    direction = "-y";
                } else {
                    direction = "+y";
                }
                break;
            case "-x":
                if (reverse.equals("right")) {
                    direction = "+y";
                } else {
                    direction = "-y";
                }
                break;
        }
    }

    public void lookAtTarget() {
        if (this.target.getY() > this.cell.getY()) {
            direction = "+y";
        } else if (this.target.getX() > this.cell.getX()) {
            direction = "+x";
        } else if (this.target.getY() < this.cell.getY()) {
            direction = "-y";
        } else if (this.target.getX() < this.cell.getX()) {
            direction = "-x";
        } else {
            direction = "target";
        }
    }

    public boolean checkBorder() {
        if (this.newCell.getX() < 0 || this.newCell.getX() > board.length - 1 || this.newCell.getY() < 0 || this.newCell.getY() > board[0].length - 1) {
            this.newCell = this.cell;
            this.reverse("right");
            return false;
        }
        return true;
    }
}

