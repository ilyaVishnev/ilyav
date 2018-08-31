package ru.job4j.threads.Bomberman.Bomberman2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class Monster extends Figure {

    private Player3 player;
    String direction;
    private ReentrantLock[][] board;
    private static final Logger logger = LoggerFactory.getLogger(Monster.class);

    public Monster(String name, ReentrantLock[][] board, Player3 player) {
        super(name);
        super.setBoard(board);
        this.player = player;
    }

    public void setNewCell(Cell newCell) {
        super.setNewCell(newCell);
        super.lookAtDirection();
    }

    @Override
    public void run() {
        this.getCell().getReentrantLock().lock();
        this.makeStap();
        while (!this.player.isGoal()) {
            try {
                if (super.getNewCell().getReentrantLock().tryLock(5000, TimeUnit.MILLISECONDS)) {
                    super.getCell().getReentrantLock().unlock();
                    super.setCell(super.getNewCell());
                    super.getCell().getReentrantLock().lock();
                    System.out.println(Thread.currentThread().getName() + "- X: " + this.getCell().getX() + " Y: " + this.getCell().getY());
                } else {
                    super.reverse("right");
                }
                super.makeStap();
            } catch (InterruptedException ex) {
                logger.error(ex.getMessage());
            }
        }
    }
}