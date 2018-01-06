package ru.job4j.threads;

import java.util.Queue;
import java.util.concurrent.*;
import javax.swing.text.Utilities.*;

public class ThreadPool extends Thread {
    int amount = Runtime.getRuntime().availableProcessors();
    Queue<Runnable> queue = new ConcurrentLinkedQueue<>();
    Thread[] treads;

    public ThreadPool() {
        treads = new Thread[amount];
        for (int i = 0; i < amount; i++) {
            treads[i] = new Thread(this, "thread-" + i);
            treads[i].start();
        }
    }

    public static void main(String[] args) {
        ThreadPool threadPool = new ThreadPool();
        for (int i = 0; i < 10; i++) {
            final int j = i;
            threadPool.add(new Runnable() {
                @Override
                public void run() {
                    System.out.println("Решение задачи номер " + j);
                }
            });
        }
        //threadPool.shutDown();
    }

    public void add(Runnable work) {
        queue.offer(work);
        synchronized (queue) {
            queue.notify();
        }
    }

    public void shutDown() {
        for (int i = 0; i < amount; i++) {
            if (treads[i].isAlive()) {
                treads[i].interrupt();
            }
        }
    }

    @Override
    public void run() {
        while (true) {
            synchronized (queue) {
                if (Thread.currentThread().isInterrupted()) {
                    break;
                }
                Runnable work = queue.poll();
                if (work == null) {

                    try {
                        while (queue.isEmpty()) queue.wait();
                    } catch (InterruptedException ex) {
                    }
                } else {
                    work.run();
                }
            }
        }
    }

}
