package ru.job4j.iterator;


import java.util.Iterator;
import java.util.NoSuchElementException;

public class Converter {
    Iterator<Integer> convert(Iterator<Iterator<Integer>> it) {
        final Iterator<Integer> iterator1 = it.next();
        final Iterator<Integer> iterator2 = it.next();
        final Iterator<Integer> iterator3 = it.next();
        return new Iterator<Integer>() {
            Iterator<Integer> iterator;

            @Override
            public boolean hasNext() {
                if (iterator1.hasNext()) {
                    iterator = iterator1;
                } else if (iterator2.hasNext()) {
                    iterator = iterator2;
                } else {
                    iterator = iterator3;
                }
                return iterator.hasNext();
            }

            @Override
            public Integer next() {
                if (iterator1.hasNext()) {
                    iterator = iterator1;
                } else if (iterator2.hasNext()) {
                    iterator = iterator2;
                } else {
                    iterator = iterator3;
                }
                return iterator.next();
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }
}
