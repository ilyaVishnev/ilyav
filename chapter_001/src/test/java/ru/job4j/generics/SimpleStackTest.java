package ru.job4j.generics;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import static org.hamcrest.collection.IsArrayContainingInAnyOrder.arrayContainingInAnyOrder;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SimpleStackTest {
    @Test
    public void whenPushFiveThenGetResult() {
        SimpleStack<Integer> simpleStack = new SimpleStack<Integer>();
        simpleStack.push(23);
        simpleStack.push(45);
        simpleStack.push(9);
        simpleStack.push(78);
        simpleStack.push(18);
        Iterator<Integer> iterator = simpleStack.dynamicLinkedList.iterator();
        ArrayList<Integer> list = new ArrayList<>();
        while (iterator.hasNext()) {
            list.add(iterator.next());
        }
        ArrayList<Integer> example = new ArrayList<>();
        example.addAll(Arrays.asList(23, 45, 9, 78, 18));
        assertThat(list.toArray(), arrayContainingInAnyOrder(example.toArray()));
        assertThat(simpleStack.poll(), is(23));
        ArrayList<Integer> example2 = new ArrayList<>();
        example.addAll(Arrays.asList(45, 9, 78, 18));
    }
}
