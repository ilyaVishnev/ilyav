package ru.job4j.generics;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import static org.hamcrest.collection.IsArrayContainingInAnyOrder.arrayContainingInAnyOrder;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SimpleQueueTest {
    @Test
    public void whenPushFiveThenGetResult() {
        SimpleQueue<Integer> simpleQueue = new SimpleQueue<Integer>();
        simpleQueue.push(23);
        simpleQueue.push(45);
        simpleQueue.push(9);
        simpleQueue.push(78);
        simpleQueue.push(18);
        Iterator<Integer> iterator = simpleQueue.dynamicLinkedList.iterator();
        ArrayList<Integer> list = new ArrayList<>();
        while (iterator.hasNext()) {
            list.add(iterator.next());
        }
        ArrayList<Integer> example = new ArrayList<>();
        example.addAll(Arrays.asList(23, 45, 9, 78, 18));
        assertThat(list.toArray(), arrayContainingInAnyOrder(example.toArray()));
        assertThat(simpleQueue.poll(), is(18));
        ArrayList<Integer> example2 = new ArrayList<>();
        example.addAll(Arrays.asList(23, 45, 9, 78));
    }
}
