package ru.job4j.Thread;


import org.junit.Test;

import static org.hamcrest.Matchers.arrayContainingInAnyOrder;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.*;

import ru.job4j.threads.*;

import java.util.LinkedList;

public class UserStorageTest {

    UserStorage userStorage = new UserStorage();
    List<UserStorage.User> example = new LinkedList<UserStorage.User>();

    @Test
    public void whenAddSomeWithHelpByThreads() throws InterruptedException {
        for (int i = 0; i < 4; i++) {
            AddThread myThread = new AddThread(i);
            myThread.start();
            myThread.join();
        }
        example.addAll(Arrays.asList(new UserStorage.User(0, 53), new UserStorage.User(1, 56), new UserStorage.User(2, 59), new UserStorage.User(3, 62)));
        assertThat(userStorage.getList().toArray(), arrayContainingInAnyOrder(example.toArray()));
    }

    @Test
    public void whenUpdateSomeWithHelpByThreads() throws InterruptedException {
        for (int i = 0; i < 4; i++) {
            AddThread myThread = new AddThread(i);
            myThread.start();
            myThread.join();
        }
        for (int i = 0; i < 3; i++) {
            UpdateThread myThread = new UpdateThread(i);
            myThread.start();
            myThread.join();
        }
        example.addAll(Arrays.asList(new UserStorage.User(0, 64), new UserStorage.User(1, 68), new UserStorage.User(2, 72), new UserStorage.User(3, 62)));
        assertThat(userStorage.getList().toArray(), arrayContainingInAnyOrder(example.toArray()));
    }

    @Test
    public void whenTrunsferSomeWithHelpByThreads() throws InterruptedException {
        for (int i = 0; i < 4; i++) {
            AddThread myThread = new AddThread(i);
            myThread.start();
            myThread.join();
        }
        for (int i = 0; i < 2; i++) {
            TransferThread myThread = new TransferThread(i);
            myThread.start();
            myThread.join();
        }
        example.addAll(Arrays.asList(new UserStorage.User(0, 13), new UserStorage.User(1, 16), new UserStorage.User(2, 99), new UserStorage.User(3, 102)));
        assertThat(userStorage.getList().toArray(), arrayContainingInAnyOrder(example.toArray()));
    }

    public class AddThread extends Thread {
        Integer k;

        AddThread(Integer k) {
            this.k = k;
        }

        @Override
        public void run() {
            userStorage.add(new UserStorage.User(k++, k * 3 + 50));
        }
    }

    public class UpdateThread extends Thread {
        Integer k;

        UpdateThread(Integer k) {
            this.k = k;
        }

        @Override
        public void run() {
            userStorage.update(new UserStorage.User(k++, k * 4 + 60));
        }
    }

    public class TransferThread extends Thread {
        Integer k;

        TransferThread(Integer k) {
            this.k = k;
        }

        @Override
        public void run() {
            userStorage.transfer(k, k + 2, 40);
        }
    }

}
