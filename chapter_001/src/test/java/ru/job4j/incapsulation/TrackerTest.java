package ru.job4j.incapsulation;

import org.junit.Test;

import static org.hamcrest.collection.IsArrayContainingInAnyOrder.arrayContainingInAnyOrder;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TrackerTest {
    @Test
    public void whenUpdateNameThenReturnNewName() {
        Tracker tracker = new Tracker();
        Item previous = new Task("test1","testDescription",123L);
        // Добавляем заявку в трекер. Теперь в объект проинициализирован id.
        tracker.add(previous);
        // Создаем новую заявку.
        Item next = new Task("test2","testDescription2",1234L);
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
        Item item2=new Task("test4","testDescription4",123456L);
        Item item3=new Task("test4","testDescription5",1234567L);
        Item item4=new Task("test5","testDescription6",12345678L);
        tracker.add(item2);
        tracker.add(item3);
        Item[]result=tracker.findAll();
        tracker.add(item4);
        //Находим все заявки с одним именем.
        assertThat(tracker.findByName("test4"), is(result));

    }
    //Проверка возможности удаления
    @Test
    public void whenDeleteThenReturnWhithout() {
        Tracker tracker = new Tracker();
        Item item2=new Task("test4","testDescription4",123456L);
        Item item3=new Task("test4","testDescription5",1234567L);
        Item item4=new Task("test5","testDescription6",12345678L);
        tracker.add(item2);
        tracker.add(item3);
        Item[]resultBeg=new Item[tracker.findAll().length+1];
        for(int i=0;i<tracker.findAll().length;i++){
            resultBeg[i]=tracker.findAll()[i];
        }
        tracker.add(item4);
        tracker.delete(item4);
        Item[]resultEnd=tracker.findAll();
        assertThat(resultBeg, arrayContainingInAnyOrder(resultEnd));
    }
}