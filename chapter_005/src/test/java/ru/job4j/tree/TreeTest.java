package ru.job4j.tree;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created on 11.08.17.
 * Testing Tree class.
 * @author Wamdue
 * @version 1.0
 */
public class TreeTest {
    /**
     * Add element to existent parent.
     * expect: true.
     */
    @Test
    public void whenWantToAddElementToExistentParentThenReturnTrue() {
        Tree<Integer> tree = new Tree<>();
        tree.add(null, 1);
        tree.add(null, 2);
        boolean expect = true;
        assertThat(tree.add(2, 3), is(expect));
    }

    /**
     * Add element to not existent parent.
     * expect: false;
     */
    @Test
    public void whenWantToAddElementToNotExistentParentThenReturnFalse() {
        Tree<Integer> tree = new Tree<>();
        boolean expect = false;
        assertThat(tree.add(3, 5), is(expect));
    }

    /**
     * Iterate through the tree.
     * expect: 5.
     */
    @Test
    public void whenWhantToIterateOnTreeThenIterate() {
        Tree<Integer> tree = new Tree<>();
        tree.add(null, 1);
        tree.add(null, 2);
        tree.add(1, 5);
        tree.add(1, 6);
        Iterator<Integer> iterator = tree.iterator();
        Integer expect = 5;
        iterator.next();
        iterator.next();
        assertThat(iterator.next(), is(expect));
    }

    /**
     * Have tree with more then 3 children in node.
     * expect: false.
     */
    @Test
    public void whenHaveMoreThenTwoChildrenThenReturnFalse() {
        Tree<Integer> tree = new Tree<>();
        tree.add(null, 1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        boolean expect = false;
        assertThat(tree.isBinary(), is(expect));
    }

    /**
     * Have tree with less or equal then 2 children in node.
     * expect: true.
     */
    @Test
    public void whenHaveLessOrEqualThenTwoChildrenThenReturnTrue() {
        Tree<Integer> tree = new Tree<>();
        tree.add(null, 1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(2, 4);
        tree.add(2, 5);
        tree.add(3, 6);
        boolean expect = true;
        assertThat(tree.isBinary(), is(expect));
    }
}
