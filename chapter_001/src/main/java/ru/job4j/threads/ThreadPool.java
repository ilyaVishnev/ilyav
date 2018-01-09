package ru.job4j.threads;

import java.util.Queue;
import java.util.concurrent.*;
import javax.swing.text.Utilities.*;

public class ThreadPool extends Thread {
    int amount = Runtime.getRuntime().availableProcessors();
    Queue<Runnable> queue = new ConcurrentLinkedQueue<>();
    Thread[] treads;

    public void execute() {
        treads = new Thread[amount];
        for (int i = 0; i < amount; i++) {
            treads[i] = new Thread(this, "thread-" + i);
            treads[i].start();
        }
    }

    public static void main(String[] args) {
        ThreadPool threadPool = new ThreadPool();
        threadPool.execute();
        for (int i = 0; i < 35; i++) {
            final int j = i;
            threadPool.add(new Runnable() {
                @Override
                public void run() {
                    System.out.println("Решение задачи номер " + j + " выполнение потоком " + Thread.currentThread().getName());
                }
            });
        }
    }

    public void add(Runnable work) {
        queue.offer(work);
        synchronized (queue) {
            queue.notify();
        }
    }

  /*  public void shutDown() {
        for (int i = 0; i < amount; i++) {
            if (treads[i].isAlive()) {
                treads[i].interrupt();
            }
        }
        this.interrupt();
    }*/

    @Override
    public void run() {
        while (true) {
            Runnable work = queue.poll();
            if (work == null) {

                try {
                    while (queue.isEmpty()) {
                        synchronized (queue) {
                            queue.wait();
                        }
                    }
                } catch (InterruptedException ex) {
                }
            } else {
                work.run();
            }
        }
    }

}
