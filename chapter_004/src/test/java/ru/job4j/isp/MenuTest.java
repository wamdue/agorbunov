package ru.job4j.isp;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Menu output testing.
 * @author Wamdue
 * @version 1.0
 * @since 03.01.2018
 */
public class MenuTest {
    /**
     * Add one element to menu.
     * Expect: list contains element - true.
     */
    @Test
    public void whenAddItemToListThenListNotEmpty() {
        Menu menu = new Menu("TestMenu");
        Element element = new ElementImpl("Test1");
        menu.getList().add(element);
        boolean expect = true;

        assertThat(menu.getList().contains(element), is(expect));
    }

    /**
     * Set menu name TestMenu.
     * Expect menu name equals TestMenu.
     */
    @Test
    public void whenSetMenuNameThenDefineIt() {
        Menu menu = new Menu("TestMenu");
        String expect = "TestMenu";

        assertThat(menu.getName(), is(expect));
    }

    /**
     * Draw menu output.
     * Have menu адача 1.
     * Have children: Задача 1.1, Задача 1.2 .
     * Задача 1.1 have children: Задача 1.1.1 , Задача 1.1.2 .
     * Задача 1.2 have children: адача 1.2.1 , адача 1.2.2 .
     * Задача 1.1.2 have child: Задача 1.1.1.1 .
     * Expect output:
     * Задача 1.
     * --Задача 1.1
     * ----Задача 1.1.1
     * ------Задача 1.1.1.1
     * ----Задача 1.1.2
     * --Задача 1.2
     * ----Задача 1.2.1
     * ----Задача 1.2.2
     */
    @Test
    public void whenAddMenuItemsThatHaveItemsTooThenDrawAllItemsLikeTree() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Menu menu = new Menu("Задача 1.");
        Element e1 = new ElementImpl("Задача 1.1");
        Element e2 = new ElementImpl("Задача 1.1.1");
        Element e3 = new ElementImpl("Задача 1.1.2");
        Element e4 = new ElementImpl("Задача 1.2");
        Element e5 = new ElementImpl("Задача 1.2.1");
        Element e6 = new ElementImpl("Задача 1.2.2");
        Element e7 = new ElementImpl("Задача 1.1.1.1");
        menu.getList().add(e1);
        e1.getElements().add(e2);
        e1.getElements().add(e3);
        menu.getList().add(e4);
        e4.getElements().add(e5);
        e4.getElements().add(e6);
        e2.getElements().add(e7);
        menu.drawMenu();

        String expect = "Задача 1.\r\n--Задача 1.1\r\n----Задача 1.1.1\r\n------Задача 1.1.1.1\r\n"
                + "----Задача 1.1.2\r\n--Задача 1.2\r\n----Задача 1.2.1\r\n----Задача 1.2.2\r\n";

        assertThat(out.toString(), is(expect));
    }
}