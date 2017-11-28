package ru.job4j.banking;

import org.junit.Test;

import java.util.*;

import static org.hamcrest.collection.IsArrayContainingInAnyOrder.arrayContainingInAnyOrder;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class BankTest {
    @Test
    public void whenDeleteAccountThenDeleted() {
        Bank bank = new Bank();
        User me = new User("ilya", "pass1");
        bank.addUser(me);
        Account account = new Account(12, "acc1");
        Account account2 = new Account(15, "acc2");
        bank.addAccountToUser(me, account);
        bank.addAccountToUser(me, account2);
        List<Account> list = new ArrayList<Account>();
        list = Arrays.asList(new Account(12, "acc1"), new Account(15, "acc2"));
        assertThat(bank.getUserAccounts(me).toArray(), arrayContainingInAnyOrder(list.toArray()));
        bank.deleteAccountFromUser(me, account);
        list = Arrays.asList(new Account(15, "acc2"));
        assertThat(bank.getUserAccounts(me).toArray(), arrayContainingInAnyOrder(list.toArray()));
    }

    @Test
    public void whenTransferThenGet() {
        Bank bank = new Bank();
        User me = new User("ilya", "pass1");
        bank.addUser(me);
        Account account = new Account(12, "acc1");
        Account account2 = new Account(15, "acc2");
        bank.addAccountToUser(me, account);
        bank.addAccountToUser(me, account2);
        User another = new User("vasja", "pass2");
        Account anotherAccount = new Account(100, "acc3");
        bank.addUser(another);
        bank.addAccountToUser(another, anotherAccount);
        bank.transferMoney(me, account2, another, anotherAccount, 9);
        assertThat(bank.getUserAccounts(me).toArray()[1].toString(), is("реквизиты: acc2 кол-во денег: 6.0"));
    }
}
