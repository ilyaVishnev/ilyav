package ru.job4j.set;

import org.junit.Test;
import ru.job4j.condition.Point;

import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import java.io.PrintStream;

import static org.hamcrest.collection.IsArrayContainingInAnyOrder.arrayContainingInAnyOrder;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SetHashtableTest {
    @Test
    public void whenPutThesameThenGetOnlydifferent() {
        SetHashtable<Integer> setHashtable = new SetHashtable<Integer>();
        setHashtable.add(1);
        setHashtable.add(2);
        setHashtable.add(3);
        setHashtable.add(4);
        setHashtable.add(5);
        setHashtable.add(6);
        setHashtable.add(7);
        setHashtable.add(1);
        setHashtable.add(2);
        setHashtable.add(3);
        setHashtable.add(4);
        setHashtable.add(5);
        setHashtable.add(9);
        assertThat(setHashtable.toString(), is("1 2 3 4 5 6 7 9 "));
        setHashtable.remove(2);
        setHashtable.remove(3);
        setHashtable.remove(4);
        assertThat(setHashtable.toString(), is("1 5 6 7 9 "));
    }
}
