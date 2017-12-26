package ru.job4j.threads;

public class MyInteraption {
    public static void main(String[] args) {
        MyInteraption.CountElements countElements = new MyInteraption.CountElements("необходимое выражение условия свободы и независимости от внешних факторов и остальной фигни");
        MyInteraption.Waiting waiting = new MyInteraption.Waiting(countElements);
        countElements.start();
        waiting.start();

    }

    public static class CountElements extends Thread {
        String example;
        boolean stop = false;

        public CountElements(String massiv) {
            this.example = massiv;
        }

        @Override
        public void run() {
            char[] elements = example.toCharArray();
            for (int i = 0; i < elements.length && !stop; i++) {
                System.out.print(elements[i]);
            }
        }
    }

    public static class Waiting extends Thread {
        public CountElements countElement;

        public Waiting(CountElements countElement) {
            this.countElement = countElement;
        }

        @Override
        public void run() {
            long startTime = System.currentTimeMillis();
            long difference;
            while (true) {
                if ((difference = System.currentTimeMillis() - startTime) > 1) {
                    countElement.stop = true;
                    return;
                }
            }
        }
    }
}
