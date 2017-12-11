package ru.job4j.tree;

import org.junit.Test;
import ru.job4j.tree.*;
import tree.Tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.*;

import static org.hamcrest.collection.IsArrayContainingInAnyOrder.arrayContainingInAnyOrder;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
public class TreeTest {
    @Test
    public void whenAddelementToTreeThenGetresult(){
        Tree<Integer>tree=new Tree<>();
        tree.add(1,3);
        tree.add(3,7);
        tree.add(3,5);
        tree.add(1,4);
        tree.add(4,8);
        Iterator<Integer>iterator=tree.iterator();
        List<Integer>list=new ArrayList<>();
        while (iterator.hasNext()){
            list.add(iterator.next());
        }
        List<Integer>example=new ArrayList<>();
        example.addAll(Arrays.asList(1,3,4,5,7,8));
    assertThat(list.toArray(),arrayContainingInAnyOrder(example.toArray()));
    }
}
