package ru.job4j.tree;
//

import org.junit.Test;
import ru.job4j.tree.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.*;

import static org.hamcrest.collection.IsArrayContainingInAnyOrder.arrayContainingInAnyOrder;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TreeTest {
    @Test
    public void when6ElFindLastThen6() {
        Tree<Integer> tree = new Tree<>();
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);
        assertThat(
                tree.findBy(6).isPresent(),
                is(true)
        );
    }

    @Test
    public void when6ElFindNotExitThenOptionEmpty() {
        Tree<Integer> tree = new Tree<>();
        tree.add(1, 2);
        assertThat(
                tree.findBy(7).isPresent(),
                is(false)
        );
    }

    @Test
    public void whenTreeisBinaryThenTrue() {
        Tree<Integer> tree = new Tree<>();
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        assertThat(
                tree.isBinary(),
                is(false)
        );
        Tree<Integer> tree2 = new Tree<>();
        tree2.add(1, 2);
        tree2.add(1, 3);
        tree2.add(2, 5);
        tree2.add(2, 7);
        assertThat(
                tree2.isBinary(),
                is(true)
        );
    }

    @Test
    public void whenIteratorworksThengetNext() {
        Tree<Integer> tree2 = new Tree<>();
        tree2.add(1, 2);
        tree2.add(1, 3);
        tree2.add(2, 5);
        tree2.add(2, 7);
        List<Integer> list = new ArrayList<>();
        Iterator<Integer> iterator = tree2.iterator();
        while (iterator.hasNext()) {
            list.add(iterator.next());
        }
        List<Integer> example = new ArrayList<>();
        example.addAll(Arrays.asList(1, 2, 3, 5, 7));
        assertThat(list.toArray(), arrayContainingInAnyOrder(example.toArray()));
    }
}
