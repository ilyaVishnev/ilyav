package ru.job4j.Thread;

import org.junit.Test;
import ru.job4j.threads.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.Matchers.arrayContainingInAnyOrder;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.theInstance;
import static org.junit.Assert.assertThat;

public class SaveDynamicListTest {

    SaveDynamicList<Integer> saveDynamicList = new SaveDynamicList<>();
    List<Integer> examle = new ArrayList<>();
    List<Integer> list = new ArrayList<>();

    @Test
    public void whenAddToListIsSaveThread() throws InterruptedException {
        for (int i = 0; i < 4; i++) {
            AddThread addThread = new AddThread(i);
            addThread.start();
            addThread.join();
        }
        Iterator<Integer> iterator = saveDynamicList.iterator();
        while (iterator.hasNext()) {
            list.add(iterator.next());
        }

        examle.addAll(Arrays.asList(2, 5, 8, 11));
        assertThat(list.toArray(), arrayContainingInAnyOrder(examle.toArray()));
    }

    @Test
    public void whenGetToListIsSaveThread() throws InterruptedException {
        for (int i = 0; i < 4; i++) {
            AddThread addThread = new AddThread(i);
            addThread.start();
            addThread.join();
        }
        for (int i = 0; i < 2; i++) {
            GetThread getThread = new GetThread(i);
            getThread.start();
            getThread.join();
        }
        examle.addAll(Arrays.asList(2, 5));
        assertThat(list.toArray(), arrayContainingInAnyOrder(examle.toArray()));
    }

    public class AddThread extends Thread {
        int i;

        AddThread(int i) {
            this.i = i;
        }

        @Override
        public void run() {
            saveDynamicList.add(i * 3 + 2);
        }
    }

    public class GetThread extends Thread {
        int i;

        GetThread(int i) {
            this.i = i;
        }

        @Override
        public void run() {
            list.add(saveDynamicList.get(i));
        }
    }
}
