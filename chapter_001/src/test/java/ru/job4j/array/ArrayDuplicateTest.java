package ru.job4j.array;


import static org.hamcrest.collection.IsArrayContainingInAnyOrder.arrayContainingInAnyOrder;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
public class ArrayDuplicateTest {
    @Test
    public void whenRemoveDuplicatesThenArrayWithoutDuplicate() {
        ArrayDuplicate arrayDuplicate=new ArrayDuplicate();
        String[]example={"Привет", "Мир","Супер"};
        assertThat(arrayDuplicate.remove(new String[]{"Привет", "Мир", "Привет", "Супер", "Мир"}),arrayContainingInAnyOrder(example));
    }
}
