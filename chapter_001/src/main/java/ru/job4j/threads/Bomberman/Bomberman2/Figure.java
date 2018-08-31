package ru.job4j.threads.Bomberman.Bomberman2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.threads.Bomberman.Player2;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public abstract class Figure extends Thread {
    private Cell cell;
    private Cell newCell;
    Direction direction;
    private ReentrantLock[][] board;
    private static final Logger logger = LoggerFactory.getLogger(Player3.class);

    public void setCell(Cell cell) {
        this.cell = cell;
    }

    public Cell getCell() {
        return cell;
    }

    public Cell getNewCell() {
        return newCell;
    }

    public void setNewCell(Cell newCell) {
        this.newCell = newCell;
    }

    public Figure(String name) {
        super(name);
    }

    public void setBoard(ReentrantLock[][] board) {
        this.board = board;
    }

    enum Direction {left, right, up, down, target}

    public void makeStap() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            logger.error(ex.getMessage());
        }
        switch (direction) {
            case right:
                this.newCell = new Cell(this.cell.getX(), this.cell.getY() + 1);
                if (this.checkBorder()) {
                    this.newCell.setReentrantLock(board[this.cell.getX()][this.cell.getY() + 1]);
                }
                break;
            case left:
                this.newCell = new Cell(this.cell.getX(), this.cell.getY() - 1);
                if (this.checkBorder()) {
                    this.newCell.setReentrantLock(board[this.cell.getX()][this.cell.getY() - 1]);
                }
                break;
            case down:
                this.newCell = new Cell(this.cell.getX() + 1, this.cell.getY());
                if (this.checkBorder()) {
                    this.newCell.setReentrantLock(board[this.cell.getX() + 1][this.cell.getY()]);
                }
                break;
            case up:
                this.newCell = new Cell(this.cell.getX() - 1, this.cell.getY());
                if (this.checkBorder()) {
                    this.newCell.setReentrantLock(board[this.cell.getX() - 1][this.cell.getY()]);
                }
                break;
        }
    }

    public void reverse(String reverse) {
        switch (direction) {
            case right:
                if (reverse.equals("right")) {
                    direction = Direction.down;
                } else {
                    direction = Direction.up;
                }
                break;
            case left:
                if (reverse.equals("right")) {
                    direction = Direction.up;
                } else {
                    direction = Direction.down;
                }
                break;
            case down:
                if (reverse.equals("right")) {
                    direction = Direction.left;
                } else {
                    direction = Direction.right;
                }
                break;
            case up:
                if (reverse.equals("right")) {
                    direction = Direction.right;
                } else {
                    direction = Direction.left;
                }
                break;
        }
    }

    public void lookAtDirection() {
        if (this.newCell.getY() > this.cell.getY()) {
            direction = Direction.right;
        } else if (this.newCell.getX() > this.cell.getX()) {
            direction = Direction.down;
        } else if (this.newCell.getY() < this.cell.getY()) {
            direction = Direction.left;
        } else if (this.newCell.getX() < this.cell.getX()) {
            direction = Direction.up;
        } else {
            direction = Direction.target;
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

