package ru.job4j.threads;

import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyLock {
    Thread LockedThread = null;
    int count = 0;
    boolean locked = false;

    public synchronized void lock() {
        Thread threadName = Thread.currentThread();
        while (locked && threadName != LockedThread) {
            try {
                wait();
            } catch (InterruptedException ex) {
            }
        }
        locked = true;
        count++;
        LockedThread = threadName;
    }

    public synchronized void unlock() {
        if (Thread.currentThread() == LockedThread) {
            count--;
        }
        if (count == 0) {
            locked = false;
            notifyAll();
        }
    }
}
