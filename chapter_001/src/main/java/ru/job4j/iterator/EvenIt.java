package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenIt implements Iterator {
    private final int[] array;
    private int index = 0;

    public EvenIt(final int[] array) {
        this.array = array;
    }

    @Override
    public boolean hasNext() {
        for (int i = index + 1; i < array.length; i++) {
            if (array[i] % 2 == 0)
                return true;
        }
        return false;
    }

    @Override
    public Integer next() {
        for (int i = index + 1; i < array.length; i++) {
            if (array[i] % 2 == 0) {
                index = i;
                return array[index++];
            }
        }
        throw new NoSuchElementException();
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }
}
