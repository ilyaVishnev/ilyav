package ru.job4j.threads;

public class Interaption {

    public static void main(String[] args) {
        CountElements countElements = new CountElements("efwfwrvwrvrvwrvwrwrwrvwrwrwrvwrvwrvwrvwrevwrvwrvrevrevwreververvrevrevrvrvererv");
        Waiting waiting = new Waiting(countElements);
        countElements.start();
        waiting.start();

    }

    public static class CountElements extends Thread {
        String example;
        int countGap = 0;

        public CountElements(String massiv) {
            this.example = massiv;
        }

        @Override
        public void run() {
            char[] elements = example.toCharArray();
            for (int i = 0; i < elements.length; i++) {
                System.out.println(elements[i]);
                try {
                    Thread.sleep(1);
                } catch (InterruptedException ex) {
                    System.out.println("Прерывание");
                    return;
                }
            }
        }
    }

    public static class Waiting extends Thread {
        public Thread countElement;

        public Waiting(Thread countElement) {
            this.countElement = countElement;
        }

        @Override
        public void run() {
            long startTime = System.currentTimeMillis();
            long difference;
            while (true) {
                if ((difference = System.currentTimeMillis() - startTime) > 3) {
                    countElement.interrupt();
                    return;
                }
            }
        }
    }
}
