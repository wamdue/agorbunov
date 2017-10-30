package ru.job4j.bank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created on 18.07.17.
 * Map for work with accounts and users.
 * @author Wamdue
 * @version 1.0
 */
public class BankMap {
    /**
     * Main map.
     */
    private Map<User, List<Account>> map = new HashMap<>();

    /**
     *
     * @param user - user that you want to add.
     */
    public void addUser(User user) {
        map.put(user, new ArrayList<Account>());
    }

    /**
     *
     * @param user - user that you want to delete.
     */
    public void deleteUser(User user) {
        map.remove(user);
    }

    /**
     *
     * @param user - source user.
     * @param account - account to add.
     */
    public void addAccountToUser(User user, Account account) {
        List<Account> list = map.get(user);
        list.add(account);
    }

    /**
     *
     * @param user - source user.
     * @param account - account to delete.
     */
    public void deleteAccountFromUser(User user, Account account) {
        List<Account> list = map.get(user);
        list.remove(account);
    }

    /**
     *
     * @param user - source user.
     * @return - list of all accounts
     */
    public List<Account> getUserAccounts(User user) {
        return map.get(user);
    }

    /**
     * Transfer money between users or accounts.
     * @param srcUser - user which pays.
     * @param srcAccount - account for withdraw.
     * @param dstUser - destination user.
     * @param dstAccount - destination account.
     * @param amount - amount to transfer.
     * @return - true if transaction was successful or false if not.
     */
    public boolean transferMoney(User srcUser, Account srcAccount, User dstUser, Account dstAccount, double amount) {
        boolean result = true;
        List<Account> srcList = this.getUserAccounts(srcUser);
        List<Account> dstList = this.getUserAccounts(dstUser);
        int srcAccountIndex = srcList.indexOf(srcAccount);
        int dstAccountIndex = dstList.indexOf(dstAccount);
        if (srcAccountIndex == -1 || dstAccountIndex == -1) {
            result = false;
        } else {

            if (srcList.get(srcAccountIndex).getValue() < amount) {
                result = false;
            } else {
                srcAccount.setValue(srcAccount.getValue() - amount);
                dstAccount.setValue(dstAccount.getValue() + amount);
                srcList.set(srcAccountIndex, srcAccount);
                dstList.set(dstAccountIndex, dstAccount);
            }
        }
        return result;
    }
}
