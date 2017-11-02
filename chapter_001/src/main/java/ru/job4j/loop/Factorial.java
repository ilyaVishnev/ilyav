package ru.job4j.loop;

public class Factorial {
    public int calc(int n) {
        int Factorial=1;
        for(int i=1;i<=n;i++){
            Factorial*=i;
        }
        return Factorial;
    }
}
