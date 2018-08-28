package ru.job4j.threads.Bomberman.Bomberman2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class Bomberman2 {

    private List<Monster> monsters = new ArrayList<>();
    private ReentrantLock[][] board;
    private Player3 player;
    private static final Logger logger = LoggerFactory.getLogger(Bomberman2.class);

    public static void main(String[] args) {
        Bomberman2 bomberman = new Bomberman2();
        bomberman.setBoard(7, 9);
        bomberman.setBlockedThread(1, 2);
        bomberman.setBlockedThread(2, 2);
        bomberman.setPlayer(new Cell(0, 8), new Cell(3, 1));
        bomberman.setMonster("monster1", new Cell(5, 0), new Cell(5, 1));
        bomberman.setMonster("monster2", new Cell(6, 7), new Cell(5, 7));

    }

    public void setBoard(int sizeX, int sizeY) {
        board = new ReentrantLock[sizeX][sizeY];
        for (int i = 0; i < sizeX; i++) {
            for (int j = 0; j < sizeY; j++) {
                board[i][j] = new ReentrantLock();
            }
        }
    }

    public void setBlockedThread(int x, int y) {
        board[x][y].lock();
    }

    public void setMonster(String name, Cell cell, Cell newCell) {
        Monster monster = new Monster(name, board, player);
        monster.setCell(cell);
        monster.setNewCell(newCell);
        this.monsters.add(monster);
        monster.start();
    }

    public void setPlayer(Cell cell, Cell Target) {
        Player3 player = new Player3(cell, Target, board);
        this.player = player;
        this.player.start();

    }
}
