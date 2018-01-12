package ru.job4j.threads;

public class Example {
    static Integer value = 0;

    public static void main(String[] args) {
        MyLock myLock = new MyLock();
        Innen innen = new Innen();
        System.out.println("Изначальное значение : " + innen.x);
        for (int i = 0; i < 3; i++) {
            Thread thread = new MyThread(innen, myLock);
            thread.start();
        }
    }

    public static class MyThread extends Thread {
        Innen innen;
        MyLock myLock;

        MyThread(Innen innen, MyLock myLock) {
            this.innen = innen;
            this.myLock = myLock;
        }

        @Override
        public void run() {
            myLock.lock();
            System.out.println(Thread.currentThread().getName() + " получил значение: " + innen.x);
            innen.x++;
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ex) {
            }
            System.out.println("Значение value в " + Thread.currentThread().getName() + " после: " + innen.x);
            myLock.unlock();
        }
    }
}

class Innen {
    int x = 0;
}