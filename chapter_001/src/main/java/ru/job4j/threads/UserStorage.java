package ru.job4j.threads;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.List;

@ThreadSafe
public class UserStorage {
    List<User> userList = new LinkedList<>();

    public synchronized boolean add(User user) {
        return userList.add(user);
    }

    public synchronized boolean update(User user) {
        for (User user1 : userList) {
            if (user1.id == user.id) {
                user1 = user;
                return true;
            }
        }
        return false;
    }

    public synchronized boolean transfer(int fromId, int toId, int amount) {
        User userFrom = new User();
        User userTo = new User();
        for (User user1 : userList) {
            if (user1.id == fromId) {
                userFrom = user1;
            } else if (user1.id == toId) {
                userTo = user1;
            }
        }
        if (userFrom.id == null || userTo.id == null || userFrom.amount < amount)
            return false;
        userFrom.amount -= amount;
        userTo.amount += amount;
        return true;
    }

    synchronized boolean delete(User user) {
        return userList.remove(user);
    }

    public List<User> getList() {
        return userList;
    }

    @ThreadSafe
    public static class User {
        Integer id;
        Integer amount;

        public User(Integer id, Integer amount) {
            this.id = id;
            this.amount = amount;
        }

        public User() {
        }

        @Override
        public int hashCode() {
            return this.id.hashCode();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (o == null)
                return false;
            if (getClass() != o.getClass())
                return false;
            User another = (User) o;
            if (this.id != another.id)
                return false;
            return true;
        }
    }
}
