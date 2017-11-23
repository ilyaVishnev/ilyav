package ru.job4j.coffee;

public class Coffee {
    public static int[] changes(int value, int price) {
        int change = value - price;
        int then = change / 10;
        int five = (change - 10 * then) / 5;
        int two = (change - 10 * then - 5 * five) / 2;
        int one = change - 10 * then - 5 * five - 2 * two;
        int[] coins = new int[then + five + two + one];
        for (int i = 0; i < coins.length; i++) {
            if (then != 0) {
                coins[i] = 10;
                then--;
                continue;
            }
            if (five != 0) {
                coins[i] = 5;
                five--;
                continue;
            }
            if (two != 0) {
                coins[i] = 2;
                two--;
                continue;
            }
            if (one != 0) {
                coins[i] = 1;
                break;
            }

        }
        return coins;
    }
}
