package ru.job4j.threads;

public class Threads {
    public static void main(String[] args) {
        String massiv = " sdf fsdf fwefw ergrg wdwqd";
        new Thread(new Gap(massiv)).start();
        new Thread(new Words(massiv)).start();
    }

    public static class Gap implements Runnable {
        String example;
        int countGap = 0;

        public Gap(String example) {
            this.example = example;
        }

        @Override
        public void run() {
            char[] symbol = example.toCharArray();
            for (char ch : symbol) {
                if (ch == ' ')
                    countGap++;
            }
            System.out.println("количество пробелов: " + countGap);
        }
    }

    public static class Words implements Runnable {
        String example;
        int countWord=0;

        public Words(String example) {
            this.example = example;
        }

        @Override
        public void run() {
            String[] words = example.split("\\s");
            for(String word:words){
                if(!word.equals(""))
                   countWord++;
            }
            System.out.println("количество слов: " + countWord);
        }
    }
}
