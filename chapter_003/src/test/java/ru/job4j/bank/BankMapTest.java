package ru.job4j.bank;

import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;
/**
 * Created on 18.07.17
 *
 * @author Wamdue
 * @version 1.0
 */
public class BankMapTest {
    /**
     * Adding new user to map.
     * expecting empty list of accounts.
     */
    @Test
    public void whenAddNewUserThenAdd() {
        BankMap map = new BankMap();
        User vova = new User("Vova", "123456");
        map.addUser(vova);
        int expected = 0;
        assertThat(map.getUserAccounts(vova).size(), is(expected));
    }

    /**
     *  Adding new account to user.
     *  expecting size = 1.
     */
    @Test
    public void whenHaveUserAddingNewAccountThenAddAccount() {
        BankMap map = new BankMap();
        User vova = new User("Vova", "123456");
        Account account = new Account(50000D, "123");
        map.addUser(vova);
        map.addAccountToUser(vova, account);
        int expected = 1;
        assertThat(map.getUserAccounts(vova).size(), is(expected));
    }

    /**
     * Deleting one account from user.
     * expecting size 1.
     */
    @Test
    public void whenDeletingAccountThenHaveLess() {
        BankMap map = new BankMap();
        User vova = new User("Vova", "123456");
        Account account = new Account(50000D, "123");
        Account account2 = new Account(400D, "111");
        map.addUser(vova);
        map.addAccountToUser(vova, account);
        map.addAccountToUser(vova, account2);
        int expected = 1;
        map.deleteAccountFromUser(vova, account);
        assertThat(map.getUserAccounts(vova).size(), is(expected));
    }
    /**
     * Transfer amount between accounts of user.
     * expecting new amount for account "222" = 2100D.
     */
    @Test
    public void whenDotransferthenMove() {
        BankMap map = new BankMap();
        User vova = new User("Vova", "123456");
        Account account = new Account(50000D, "123");
        Account account2 = new Account(400D, "111");
        Account account3 = new Account(100d, "222");
        map.addUser(vova);
        map.addAccountToUser(vova, account);
        map.addAccountToUser(vova, account2);
        map.addAccountToUser(vova, account3);
        double expected = 2100D;
        map.transferMoney(vova, account, vova, account3, 2000d);
        double value = 0D;
        for (Account a : map.getUserAccounts(vova)) {
            if (a.getRequisites().equals("222")) {
                value = a.getValue();
                break;
            }
        }
        assertThat(value, is(expected));
    }
}
