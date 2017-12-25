package ru.job4j.threads;

public class Problem {
    static int value = 2;

    public static void main(String[] args) {

        for (int i = 0; i < 3; i++) {
            new Thread() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + " получил значение: " + value);
                    value++;
                    System.out.println("Значение value в " + Thread.currentThread().getName() + " после: " + value);
                }
            }.start();
        }
    }
}
