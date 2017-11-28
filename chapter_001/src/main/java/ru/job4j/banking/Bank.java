package ru.job4j.banking;

import java.util.*;

public class Bank {
    public static Map<User, List<Account>> map = new HashMap<User, List<Account>>();

    public void addUser(User user) {
        map.put(user, null);
    }

    public void deleteUser(User user) {
        map.remove(user);
    }

    public void addAccountToUser(User user, Account account) {
        List<Account> list = new ArrayList<Account>();
        if (map.get(user) != null) {
            list = map.get(user);
        }
        list.add(account);
        map.put(user, list);
    }

    public void deleteAccountFromUser(User user, Account account) {
        List<Account> list = new LinkedList<Account>();
        list = map.get(user);
        list.remove(account);
        map.put(user, list);
    }

    public List<Account> getUserAccounts(User user) {
        return map.get(user);
    }

    public boolean transferMoney(User srcUser, Account srcAccount, User dstUser, Account dstAccount, double amount) {
        if (map.get(srcUser).indexOf(srcAccount) != -1 && map.get(dstUser).indexOf(dstAccount) != -1 && srcAccount.value >= amount) {
            map.get(srcUser).remove(map.get(srcUser).indexOf(srcAccount));
            map.get(dstUser).remove(map.get(dstUser).indexOf(dstAccount));
            srcAccount.value -= amount;
            dstAccount.value += amount;
            map.get(srcUser).add(srcAccount);
            map.get(dstUser).add(dstAccount);
            return true;
        }
        return false;
    }
}
