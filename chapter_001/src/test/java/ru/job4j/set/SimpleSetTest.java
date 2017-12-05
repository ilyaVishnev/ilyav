package ru.job4j.set;

import org.junit.Test;
import ru.job4j.condition.Point;

import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import java.io.PrintStream;

import static org.hamcrest.collection.IsArrayContainingInAnyOrder.arrayContainingInAnyOrder;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SimpleSetTest {
    @Test
    public void whenPutThesameThenDontPut() {
        SimpleSet<Integer> simpleSet = new SimpleSet<Integer>();
        simpleSet.add(1);
        simpleSet.add(2);
        simpleSet.add(3);
        simpleSet.add(4);
        simpleSet.add(5);
        simpleSet.add(6);
        simpleSet.add(7);
        assertThat(simpleSet.toString(), is("1 2 3 4 5 6 7 "));
        simpleSet.add(5);
        simpleSet.add(6);
        simpleSet.add(7);
        assertThat(simpleSet.toString(), is("1 2 3 4 5 6 7 "));
    }
}
