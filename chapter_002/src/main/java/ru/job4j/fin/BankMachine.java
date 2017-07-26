package ru.job4j.fin;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 25.07.17
 *
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
    public String onePossibility(int cash) {
        StringBuilder sb = new StringBuilder();
        int result = cash;
        for (int i = 0; i < banknotes.length;) {
            if (result == 0) {
                break;
            }
            int temp = banknotes[i];
            if (temp <= result) {
                result -= temp;
                sb.append(temp);
            } else {
                i++;
            }
        }
        return sb.toString();
    }

    /**
     *
     * @param cash - value to change.
     * @return list of possibilities.
     */
    public List<String> allPossibilities(int cash) {
        List<String> list = new ArrayList<>();
        int result = cash;
        StringBuilder sb = new StringBuilder();
        int len = banknotes.length;

        for (int i = 0; i < len; i++) {
            for (int j = i; j < len;) {
                int temp = banknotes[j];
                if (temp <= result) {
                    result -= temp;
                    sb.append(temp);
                } else {
                    j++;
                }
                if (result == 0) {
                    if (!list.contains(sb.toString())) {
                        list.add(sb.toString());
                    }
                    sb = new StringBuilder();
                    result = cash;
                    break;
                }
            }
        }
        return list;
    }
}
