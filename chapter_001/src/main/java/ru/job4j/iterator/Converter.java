package ru.job4j.iterator;


import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Converter {
    Iterator<Integer> convert(Iterator<Iterator<Integer>> it) {
        final Iterator<Iterator<Integer>> its = it;
        final Iterator<Integer> finiterator;
        if (its.hasNext()) {
            finiterator = its.next();
        } else {
            throw new NoSuchElementException();
        }
        return new Iterator<Integer>() {
            Iterator<Integer> iterator = finiterator;

            @Override
            public boolean hasNext() {
                return iterator.hasNext() || its.hasNext();
            }

            @Override
            public Integer next() {
                if (this.hasNext()) {
                    if (!iterator.hasNext()) {
                        iterator = its.next();
                    }
                    return iterator.next();
                }
                throw new NoSuchElementException();
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }
}
