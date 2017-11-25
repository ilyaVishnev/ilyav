package ru.job4j.polymorphism;

import ru.job4j.incapsulation.*;
import org.junit.Test;

import java.util.*;

import static org.hamcrest.collection.IsArrayContainingInAnyOrder.arrayContainingInAnyOrder;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class StubInputTest {
    @Test
    public void whenUserAddItemThenTrackerHasNewItemWithSameName() {
        List<String> list = new ArrayList<String>();
        list.addAll(Arrays.asList(new String[]{"0", "test name", "desc", "y"}));
        Input input = new StubInput(list);   //создаём StubInput с последовательностью действий
        StartUI startUI = new StartUI(input);
        startUI.init();    //   создаём StartUI и вызываем метод init()
        assertThat(startUI.tracker.findAll().get(0).getName(), is("test name")); // проверяем, что нулевой элемент массива в трекере содержит имя, введённое при эмуляции.
    }

    @Test
    public void whenUpdateThenTrackerHasUpdatedValue() {
        List<String> list = new ArrayList<String>();
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("test name", "desc"));
        list = Arrays.asList(new String[]{"2", item.getId(), "new test name", "desc", "y"});
        Input input = new StubInput(list);   //создаём StubInput с последовательностью действий
        StartUI startUI = new StartUI(input, tracker);
        startUI.init();
        // проверяем, что нулевой элемент массива в трекере содержит имя, введённое при эмуляции.
        assertThat(startUI.tracker.findById(item.getId()).getName(), is("new test name"));
    }

    @Test
    public void whenDeleteItemsThenBeenDeleted() {
        Tracker tracker = new Tracker();
        Item item1 = new Item("test name1", "desc1");
        Item item2 = new Item("test name2", "desc2");
        tracker.add(item1);
        tracker.add(item2);
        Input input = new StubInput(Arrays.asList(new String[]{"3", tracker.findByName("test name1").get(0).getId(), "y"}));
        StartUI startUI = new StartUI(input, tracker);
        startUI.init();
        List<Item> example = new ArrayList<Item>();
        example.addAll(Arrays.asList(new Item[]{item2, null}));
        assertThat(startUI.tracker.findAll().toArray(), arrayContainingInAnyOrder(example.toArray()));

    }
}
