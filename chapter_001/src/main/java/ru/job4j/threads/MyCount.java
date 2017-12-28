package ru.job4j.threads;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

@ThreadSafe
public class MyCount {
    @GuardedBy("this")
    Count count = new Count();

    public int incremantInThen() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            MyThread myThread = new MyThread(count);
            myThread.start();
            myThread.join();
        }
        return count.get();
    }

    public static class MyThread extends Thread {
        protected final Count count;

        MyThread(final Count count) {
            this.count = count;
        }

        @Override
        public void run() {
            count.increment();
        }
    }
}

