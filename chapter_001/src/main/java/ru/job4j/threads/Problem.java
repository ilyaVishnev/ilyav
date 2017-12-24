package ru.job4j.threads;

public class Problem {
    static int value = 2;

    public static void main(String[] args) {

        Problem problem = new Problem();
        Problem.Increment increment = problem.new Increment(value);
        Problem.Another another = problem.new Another();
        increment.start();
        another.start();
    }

    public class Increment extends Thread {
        int value;

        Increment(int value) {
            this.value = value;
        }

        @Override
        public void run() {
            value = 100;
            System.out.println("Значение value: " + value);
        }
    }

    public class Another extends Thread {
        @Override
        public void run() {
            System.out.println("Значение value из другого потока: " + value);
        }
    }
}
