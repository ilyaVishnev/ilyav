package ru.job4j.incapsulation;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.collection.IsArrayContainingInAnyOrder.arrayContainingInAnyOrder;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TrackerTest {
    @Test
    public void whenUpdateNameThenReturnNewName() {
        Tracker tracker = new Tracker();
        Item previous = new Task("test1", "testDescription", 123L);
        // Добавляем заявку в трекер. Теперь в объект проинициализирован id.
        tracker.add(previous);
        // Создаем новую заявку.
        Item next = new Task("test2", "testDescription2", 1234L);
        // Проставляем старый id из previous, который был сгенерирован выше.
        next.setId(previous.getId());
        // Обновляем заявку в трекере.
        tracker.update(next);
        // Проверяем, что заявка с таким id имеет новые имя test2.
        assertThat(tracker.findById(previous.getId()).getName(), is("test2"));
    }

    @Test
    public void whenFindbyNameThenReturnArray() {
        Tracker tracker = new Tracker();
        tracker.add(new Task("test4", "testDescription4", 123456L));
        tracker.add(new Task("test4", "testDescription5", 1234567L));
        List<Item> result=new ArrayList<Item>();
        result = tracker.findAll();
        tracker.add(new Task("test5", "testDescription6", 12345678L));
        //Находим все заявки с одним именем.
        assertThat(tracker.findByName("test4").toArray(), is(result.toArray()));

    }

    //Проверка возможности удаления
    @Test
    public void whenDeleteThenReturnWhithout() {
        Tracker tracker = new Tracker();
        tracker.add(new Task("test4", "testDescription4", 123456L));
        tracker.add(new Task("test4", "testDescription5", 1234567L));
        //Item[]begin = new Item[tracker.findAll().size() + 1];
        List<Item> begin = new ArrayList<Item>();
        for (int i = 0; i < tracker.findAll().size(); i++) {
            begin.add(tracker.findAll().get(i));
        }
        begin.add(null);
        Item itemDel = new Task("test5", "testDescription6", 12345678L);
        tracker.add(itemDel);
        tracker.delete(itemDel);
        List<Item> end = tracker.findAll();
        assertThat(begin.toArray(), arrayContainingInAnyOrder(end.toArray()));
    }
}
