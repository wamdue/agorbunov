package ru.job4j.fin;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created on 26.07.17
 * Bankomat testing.
 * @author Wamdue
 * @version 1.0
 */
public class BankMachineTest {
    /**
     * Have value 7.
     * Expect 5 1 1.
     */
    @Test
    public void whenHaveBanknoteThenReturnOneSolution() {
        BankMachine bank = new BankMachine();
        String expect = "511";
        assertThat(bank.onePossibility(7), is(expect));
    }

    /**
     * have value 12.
     * expect 10 1 1, 5 5 1 1, 1 1 1 1 1 1 1 1 1 1 1 1.
     */
    @Test
    public void whenHaveBanknoteThenReturnAllSolutions() {
        BankMachine bank = new BankMachine();
        List<String> expect = new ArrayList<>(Arrays.asList("1011", "5511","111111111111"));
        assertThat(bank.allPossibilities(12), is(expect));
    }

}