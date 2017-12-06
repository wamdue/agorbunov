package ru.job4j.fin;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 25.07.17.
 * Bank machine main class.
 * @author Wamdue
 * @version 1.0
 */
public class BankMachine {
    /**
     * Array of available banknotes.
     */
    private int[] banknotes = new int[] {10, 5, 1};

    /**
     *
     * @param cash - value to change.
     * @return available possibility.
     */
    public List<Integer> onePossibility(int cash) {
        List<Integer> list = new ArrayList<>();
        int result = cash;
        for (int i = 0; i < banknotes.length;) {
            if (result == 0) {
                break;
            }
            int temp = banknotes[i];
            if (temp <= result) {
                result -= temp;
                list.add(temp);
            } else {
                i++;
            }
        }
        return list;
    }

    /**
     *
     * @param cash - value to change.
     * @return list of possibilities.
     */
    public List<List<Integer>> allPossibilities(int cash) {
        List<List<Integer>> allLists = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        int result = cash;
        int len = banknotes.length;

        for (int i = 0; i < len; i++) {
            for (int j = i; j < len;) {
                int temp = banknotes[j];
                if (temp <= result) {
                    result -= temp;
                    list.add(temp);
                } else {
                    j++;
                }
                if (result == 0) {
                    allLists.add(list);
                    list = new ArrayList<>();
                    result = cash;
                    break;
                }
            }
        }
        return allLists;
    }
}
