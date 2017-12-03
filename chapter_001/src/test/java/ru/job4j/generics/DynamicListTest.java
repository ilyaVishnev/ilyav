package ru.job4j.generics;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import static org.hamcrest.collection.IsArrayContainingInAnyOrder.arrayContainingInAnyOrder;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class DynamicListTest {
    @Test
    public void whenAddnewElementsThenListgrows() {
        DynamicList<Integer> dynamicList = new DynamicList<Integer>();
        dynamicList.add(new Integer(23));
        dynamicList.add(new Integer(23));
        dynamicList.add(new Integer(23));
        dynamicList.add(new Integer(23));
        dynamicList.add(new Integer(23));
        dynamicList.add(new Integer(23));
        dynamicList.add(new Integer(23));

        Iterator<Integer> iterator = dynamicList.iterator();
        ArrayList<Integer> list = new ArrayList<>();
        while (iterator.hasNext()) {
            list.add(iterator.next());
        }
        ArrayList<Integer> example = new ArrayList<>();
        example.addAll(Arrays.asList(23, 23, 23, 23, 23, 23, 23));
        assertThat(list.toArray(), arrayContainingInAnyOrder(example.toArray()));
    }
}
