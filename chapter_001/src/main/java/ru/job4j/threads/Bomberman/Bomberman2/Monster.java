package ru.job4j.threads.Bomberman.Bomberman2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class Monster extends Thread {
    private Cell cell;
    private Cell newCell;
    private Player3 player;
    String direction;
    private ReentrantLock[][] board;
    private static final Logger logger = LoggerFactory.getLogger(Monster.class);

    public Monster(String name, ReentrantLock[][] board, Player3 player) {
        super(name);
        this.board = board;
        this.player = player;
    }

    public void setNewCell(Cell newCell) {
        this.newCell = newCell;
        this.lookAtDirection();
    }

    public void setCell(Cell cell) {
        this.cell = cell;
    }

    @Override
    public void run() {
        this.cell.getReentrantLock().lock();
        this.makeStap();
        while (!this.player.isGoal()) {
            try {
                if (this.newCell.getReentrantLock().tryLock(5000, TimeUnit.MILLISECONDS)) {
                    this.cell.getReentrantLock().unlock();
                    this.cell = this.newCell;
                    this.cell.getReentrantLock().lock();
                    System.out.println(Thread.currentThread().getName() + "- X: " + this.cell.getX() + " Y: " + this.cell.getY());
                } else {
                    this.reverse("right");
                }
                this.makeStap();
            } catch (InterruptedException ex) {
                logger.error(ex.getMessage());
            }
        }
    }

    public void makeStap() {
        try {
            Thread.sleep(2000);
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

    public void lookAtDirection() {
        if (this.newCell.getY() > this.cell.getY()) {
            direction = "+y";
        } else if (this.newCell.getX() > this.cell.getX()) {
            direction = "+x";
        } else if (this.newCell.getY() < this.cell.getY()) {
            direction = "-y";
        } else if (this.newCell.getX() < this.cell.getX()) {
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