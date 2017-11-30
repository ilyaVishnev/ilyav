package ru.job4j.banking;

import java.util.*;

public class Bank {
    public Map<User, List<Account>> map = new HashMap<User, List<Account>>();

    public void addUser(User user) {
        List<Account> list = new ArrayList<Account>();
        map.put(user, list);
    }

    public void deleteUser(User user) {
        map.remove(user);
    }

    public void addAccountToUser(User user, Account account) {
        List<Account> list = new ArrayList<Account>();
        list = map.get(user);
        list.add(account);
        // map.put(user, list);
    }

    public void deleteAccountFromUser(User user, Account account) {
        List<Account> list = new LinkedList<Account>();
        list = map.get(user);
        list.remove(account);
        //  map.put(user, list);
    }

    public List<Account> getUserAccounts(User user) {
        return map.get(user);
    }

    public boolean transferMoney(User srcUser, Account srcAccount, User dstUser, Account dstAccount, double amount) {
        int indexSrc = map.get(srcUser).indexOf(srcAccount);
        int indexDst = map.get(dstUser).indexOf(dstAccount);
        if (indexSrc != -1 && indexDst != -1 && srcAccount.value >= amount) {
            srcAccount.value -= amount;
            dstAccount.value += amount;
            map.get(srcUser).set(indexSrc, srcAccount);
            map.get(dstUser).set(indexDst, dstAccount);
            return true;
        }
        return false;
    }
}
