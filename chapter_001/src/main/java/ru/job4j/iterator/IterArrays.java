package ru.job4j.iterator;

import java.util.*;

public class IterArrays implements Iterator<Integer> {

    private final int[][] array;
    private int row = 0;
    private int colum = 0;

    public IterArrays(final int[][] array) {
        this.array = array;
    }

    @Override
    public boolean hasNext() {
        return colum * (row + 1) < array.length * array[row].length;
    }

    @Override
    public Integer next() {
        if (this.hasNext()) {
            int next;
            if (colum < array[row].length) {
                next = array[row][colum++];
            } else {
                next = array[++row][colum = 0];
                colum++;
            }
            return next;
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public void remove() {
    }

    public static void main(String[] args) {
        int column = 3;

        System.out.println(column = column + 1);
    }

}
