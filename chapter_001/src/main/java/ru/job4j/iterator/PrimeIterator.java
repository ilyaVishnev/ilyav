package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class PrimeIterator implements Iterator {
    private final int[] array;
    private int index = 0;

    public PrimeIterator(final int[] array) {
        this.array = array;
    }

    @Override
    public boolean hasNext() {
        for (int i = index; i < array.length; i++) {
            if ((array[i] != 1 && array[i] % 2 != 0 && array[i] % 3 != 0) || (array[i] == 2 || array[i] == 3))
                return true;
        }
        return false;
    }

    @Override
    public Integer next() {
        for (int i = index; i < array.length; i++) {
            if ((array[i] != 1 && array[i] % 2 != 0 && array[i] % 3 != 0) || (array[i] == 2 || array[i] == 3)) {
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
