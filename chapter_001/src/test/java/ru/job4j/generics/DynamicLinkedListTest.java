package ru.job4j.generics;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import static org.hamcrest.collection.IsArrayContainingInAnyOrder.arrayContainingInAnyOrder;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class DynamicLinkedListTest {
    @Test
    public void whenAddElementThenItAdded() {
        DynamicLinkedList<Integer> dynamicLinkedList = new DynamicLinkedList<Integer>();
        dynamicLinkedList.add(45);
        dynamicLinkedList.add(34);
        dynamicLinkedList.add(4);
        dynamicLinkedList.add(67);
        dynamicLinkedList.add(78);
        dynamicLinkedList.add(35);
        dynamicLinkedList.add(12);
        dynamicLinkedList.add(47);
        dynamicLinkedList.add(40);
        dynamicLinkedList.add(1);
        Iterator<Integer> iterator = dynamicLinkedList.iterator();
        ArrayList<Integer> list = new ArrayList<>();
        while (iterator.hasNext()) {
            list.add(iterator.next());
        }
        ArrayList<Integer> example = new ArrayList<>();
        example.addAll(Arrays.asList(45, 34, 4, 67, 78, 35, 12, 47, 40, 1));
        assertThat(list.toArray(), arrayContainingInAnyOrder(example.toArray()));
    }
    @Test
    public void whenGetByIndexThenCatch() {
        DynamicLinkedList<Integer> dynamicLinkedList = new DynamicLinkedList<Integer>();
        dynamicLinkedList.add(45);
        dynamicLinkedList.add(34);
        dynamicLinkedList.add(4);
        dynamicLinkedList.add(67);
        assertThat(dynamicLinkedList.get(2),is(4));
    }
}
