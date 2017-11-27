package ru.job4j.collection;

import ru.job4j.collection.ConvertList.*;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.collection.IsArrayContainingInAnyOrder.arrayContainingInAnyOrder;
import static org.hamcrest.number.IsCloseTo.closeTo;
import static org.junit.Assert.*;

public class ConvertListTest {
    ConvertList convertList = new ConvertList();

    @Test
    public void whenArrayThenList() {
        List<Integer> list = new ArrayList<Integer>();
        int[][] array = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}, {10, 11, 12}};
        list = convertList.toList(array);
        LinkedList<Integer> example = new LinkedList<Integer>();
        example.add(1);
        example.add(2);
        example.add(3);
        example.add(4);
        example.add(5);
        example.add(6);
        example.add(7);
        example.add(8);
        example.add(9);
        example.add(10);
        example.add(11);
        example.add(12);
        assertThat(list.toArray(), arrayContainingInAnyOrder(example.toArray()));
    }

    @Test
    public void whenListThenArray() {
        List<Integer> list = new ArrayList<Integer>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(7);
        assertThat(convertList.toArray(list, 3), arrayContainingInAnyOrder(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 0, 0}}));
    }

    @Test
    public void whenListArraysThenList() {
        List<int[]> list = new LinkedList<int[]>();
        list.add(new int[]{1, 2});
        list.add(new int[]{3, 4, 5, 6});
        List<Integer> result = convertList.convert(list);
        List<Integer> example = new LinkedList<Integer>();
        example.addAll(Arrays.asList(1, 2, 3, 4, 5, 6));
        assertThat(result.toArray(), arrayContainingInAnyOrder(example.toArray()));
    }
}
