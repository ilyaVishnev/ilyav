package ru.job4j.set;

import org.junit.Test;
import ru.job4j.condition.Point;

import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import java.io.PrintStream;

import static org.hamcrest.collection.IsArrayContainingInAnyOrder.arrayContainingInAnyOrder;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.util.*;

public class SimpleLinkedSetTest {
    @Test
    public void whenPutSevenThenGetSeven() {
        SimpleLinkedSet<Integer> simpleLinkedSet = new SimpleLinkedSet<Integer>();
        simpleLinkedSet.add(1);
        simpleLinkedSet.add(2);
        simpleLinkedSet.add(3);
        simpleLinkedSet.add(4);
        simpleLinkedSet.add(5);
        simpleLinkedSet.add(6);
        simpleLinkedSet.add(7);
        Iterator<Integer> iterator = simpleLinkedSet.iterator();
        List<Integer> list = new ArrayList<Integer>();
        while (iterator.hasNext()) {
            list.add(iterator.next());
        }
        List<Integer> example = new ArrayList<Integer>();
        example.addAll(Arrays.asList(1, 2, 3, 4, 5, 6, 7));
        assertThat(list.toArray(), arrayContainingInAnyOrder(example.toArray()));
    }

    @Test
    public void whenPutThesameThenDontPut() {
        SimpleLinkedSet<Integer> simpleLinkedSet = new SimpleLinkedSet<Integer>();
        simpleLinkedSet.add(1);
        simpleLinkedSet.add(2);
        simpleLinkedSet.add(3);
        simpleLinkedSet.add(4);
        simpleLinkedSet.add(5);
        simpleLinkedSet.add(6);
        simpleLinkedSet.add(7);
        simpleLinkedSet.add(5);
        simpleLinkedSet.add(6);
        simpleLinkedSet.add(7);
        assertThat(simpleLinkedSet.toString(), is("1 2 3 4 5 6 7 "));
    }
}
