package ru.job4j.threads.Bomberman;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;

public class Bomberman {

    private Player onePlayer = new Player("onePlayer");
    private Player secondPlayer = new Player("secondPlayer");
    private static final Logger logger = LoggerFactory.getLogger(Bomberman.class);
    private final Cell[] board = this.setBoard(4, 5);

    public Bomberman() {
        onePlayer.setCell(new Cell(0, 0));
        secondPlayer.setCell(new Cell(3, 4));
    }

    public static void main(String[] args) {
        Bomberman bomberman = new Bomberman();
        final Cell[] board = bomberman.setBoard(4, 5);
        bomberman.onePlayer.setBoard(board);
        bomberman.onePlayer.setNewCell(board[1]);
        bomberman.onePlayer.start();
        bomberman.secondPlayer.setBoard(board);
        bomberman.secondPlayer.setNewCell(board[19]);
        bomberman.secondPlayer.start();
    }

    public Cell[] setBoard(int sizeX, int sizeY) {
        int index = 0;
        Cell[] board = new Cell[sizeX * sizeY];
        for (int i = 0; i < sizeX; i++) {
            for (int j = 0; j < sizeY; j++) {
                board[index++] = new Cell(i, j);
            }
        }
        return board;
    }
}

