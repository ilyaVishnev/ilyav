package ru.job4j.tree;

import org.junit.Test;
import ru.job4j.tree.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.*;

import static org.hamcrest.collection.IsArrayContainingInAnyOrder.arrayContainingInAnyOrder;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TreeTest2 {
    @Test
    public void whenPutElementsThenTheyllhere() {
        BST<Integer> bst = new BST<>();
        bst.add(8);
        bst.add(6);
        bst.add(10);
        bst.add(2);
        bst.add(12);
        bst.add(4);
        bst.add(11);
        List<Integer> list = new ArrayList<>();
        Iterator<Integer> iterator = bst.iterator();
        while (iterator.hasNext()) {
            list.add(iterator.next());
        }
        List<Integer> example = new ArrayList<>();
        example.addAll(Arrays.asList(8, 6, 10, 2, 12, 4, 11));
        assertThat(list.toArray(), arrayContainingInAnyOrder(example.toArray()));
    }
}
