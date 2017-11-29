package ru.job4j.sorting;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.*;

import static org.hamcrest.collection.IsArrayContainingInAnyOrder.arrayContainingInAnyOrder;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SortUserTest {
    @Test
    public void whenListToSet() {
        SortUser sortUser = new SortUser();
        Set<User> treeSet = new TreeSet<User>();
        List<User> list = new ArrayList<User>();
        list.addAll(Arrays.asList(new User("Ivan", 19), new User("Volfgang", 24), new User("Sergei", 19)));
        treeSet = sortUser.sort(list);
        List<User> newlist = new ArrayList<User>();
        newlist.addAll(Arrays.asList(new User("Ivan", 19), new User("Sergei", 19), new User("Volfgang", 24)));
        assertThat(newlist.toArray(), is(treeSet.toArray()));
    }
}
