package ru.job4j.map;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import static org.hamcrest.collection.IsArrayContainingInAnyOrder.arrayContainingInAnyOrder;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class MyHashmapTest {
    @Test
    public void whenPutKeyThenGetValue() {
        MyHashmap<String, Integer> myHashmap = new MyHashmap<>();
        myHashmap.insert("first key", 1);
        myHashmap.insert("second key", 2);
        myHashmap.insert("third key", 3);
        Iterator<MyHashmap.MyEntry<String, Integer>> iterator = myHashmap.iterator();
        ArrayList<MyHashmap.MyEntry> arrayEntry = new ArrayList<>();
        while (iterator.hasNext()) {
            arrayEntry.add(iterator.next());
        }
        ArrayList<MyHashmap.MyEntry<String, Integer>> example = new ArrayList<>();
        MyHashmap.MyEntry<String, Integer> myEntry1 = new MyHashmap.MyEntry<>();
        myEntry1.setKey("first key");
        myEntry1.setValue(1);
        MyHashmap.MyEntry<String, Integer> myEntry2 = new MyHashmap.MyEntry<>();
        myEntry2.setKey("second key");
        myEntry2.setValue(2);
        MyHashmap.MyEntry<String, Integer> myEntry3 = new MyHashmap.MyEntry<>();
        myEntry3.setKey("third key");
        myEntry3.setValue(3);
        example.addAll(Arrays.asList(myEntry1, myEntry2, myEntry3));
        assertThat(arrayEntry.toArray(), arrayContainingInAnyOrder(example.toArray()));
        assertThat(myHashmap.get("second key"), is(2));
        myHashmap.delete("third key");
        example = new ArrayList<>();
        example.addAll(Arrays.asList(myEntry1, myEntry2));
        Iterator<MyHashmap.MyEntry<String, Integer>> newIterator = myHashmap.iterator();
        ArrayList<MyHashmap.MyEntry> newArrayEntry = new ArrayList<>();
        while (newIterator.hasNext()) {
            newArrayEntry.add(newIterator.next());
        }
        assertThat(newArrayEntry.toArray(), arrayContainingInAnyOrder(example.toArray()));
    }

}
