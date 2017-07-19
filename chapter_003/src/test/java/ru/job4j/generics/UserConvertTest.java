package ru.job4j.generics;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created on 17.07.17
 *
 * @author Wamdue
 * @version 1.0
 */
public class UserConvertTest {
    /**
     * Have 2 users: 1) 1 Vasya Murmansk.
     *               2) 5 Vova Vologda.
     * Expect map: 1 : Vasya
     *             5 : Vova.
     */
    @Test
    public void whenHaveListOfUsersThenMakeMapOfUsers() {
        HashMap<Integer, User> expect = new HashMap<>();
        User firstUser = new User(1, "Vasya", "Murmansk");
        User secondUser = new User(5, "Vova", "Vologda");
        expect.put(1, firstUser);
        expect.put(5, secondUser);
        List<User> list = new ArrayList<>();
        list.add(firstUser);
        list.add(secondUser);
        UserConvert userConvert = new UserConvert();
        assertThat(userConvert.process(list), is(expect));
    }
}
