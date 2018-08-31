package ru.job4j.threads.Bomberman.Bomberman2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.threads.Bomberman.Player2;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class Player3 extends Figure {

    Cell target;
    private boolean goal = false;
    private boolean kick = false;
    private static final Logger logger = LoggerFactory.getLogger(Player3.class);

    public Player3(Cell cell, Cell TargetCell, ReentrantLock[][] board) {
        super("Bomberman");
        this.setCell(cell);
        this.target = TargetCell;
        this.setBoard(board);
        this.lookAtTarget();
    }

    @Override
    public void run() {
        this.getCell().getReentrantLock().lock();
        this.makeStap();
        while (this.getCell().getX() != this.target.getX() || this.getCell().getY() != this.target.getY()) {
            try {
                if (this.getNewCell().getReentrantLock().tryLock(500, TimeUnit.MILLISECONDS)) {
                    this.getCell().getReentrantLock().unlock();
                    super.setCell(this.getNewCell());
                    this.getCell().getReentrantLock().lock();
                    System.out.println(Thread.currentThread().getName() + "- X: " + this.getCell().getX() + " Y: " + this.getCell().getY());
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
        this.getCell().getReentrantLock().unlock();
        this.goal = true;
    }

    public boolean isGoal() {
        return goal;
    }

    public void lookAtTarget() {
        if (this.target.getY() > this.getCell().getY()) {
            direction = Direction.right;
        } else if (this.target.getX() > this.getCell().getX()) {
            direction = Direction.down;
        } else if (this.target.getY() < this.getCell().getY()) {
            direction = Direction.left;
        } else if (this.target.getX() < this.getCell().getX()) {
            direction = Direction.up;
        } else {
            direction = Direction.target;
        }
    }
}

