package ru.job4j.threads;

import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

public class Bomberman {
    public static void main(String[] args) {
        final ReentrantLock[][] board = new ReentrantLock[4][5];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                board[i][j] = new ReentrantLock();
            }
        }
    }
}

class OnePlayer extends Thread {
    ReentrantLock[][] board;
    long time = System.currentTimeMillis();
    ;
    int xstartWay = 0;
    int ystartWay = 0;

    OnePlayer(ReentrantLock[][] board) {
        this.board = board;
    }

    @Override
    public void run() {
        while (true) {
            if (board[xstartWay][ystartWay].tryLock()) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            } else if (System.currentTimeMillis() - time > 500) {
                board[xstartWay][ystartWay].unlock();
                xstartWay = xstartWay + (int) Math.random();
                ystartWay = ystartWay + (int) Math.random();
                xstartWay = xstartWay <= board.length - 1 ? xstartWay : xstartWay - 1;
                ystartWay = ystartWay <= board[0].length - 1 ? ystartWay : ystartWay - 1;
                time = System.currentTimeMillis();
            }
        }
    }
}

class SecondPlayer extends Thread {
    ReentrantLock[][] board;
    long time;
    int xstartWay = board.length - 1;
    int ystartWay = board[0].length - 1;

    SecondPlayer(ReentrantLock[][] board) {
        this.board = board;
    }

    @Override
    public void run() {
        while (true) {
            if (board[xstartWay][ystartWay].tryLock()) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            } else if (System.currentTimeMillis() - time > 500) {
                board[xstartWay][ystartWay].unlock();
                xstartWay = xstartWay - (int) Math.random();
                ystartWay = ystartWay - (int) Math.random();
                xstartWay = xstartWay <= board.length - 1 ? xstartWay : xstartWay - 1;
                ystartWay = ystartWay <= board[0].length - 1 ? ystartWay : ystartWay - 1;
                time = System.currentTimeMillis();
            }
        }
    }
}
