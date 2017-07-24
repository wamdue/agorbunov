package ru.job4j.list;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created on 24.07.17
 *
 * @author Wamdue
 * @version 1.0
 */
public class CycleTest {
    /**
     * Create 4 Nodes, node#4.next = first. Have cycle.
     * call hasCycle(node#1)
     * expect true.
     */
    @Test
    public void whenHaveCycleThenReturnTrue() {
        Node first = new Node(1);
        Node two = new Node(2);
        Node third = new Node(3);
        Node four = new Node(4);

        first.next = two;
        two.next = third;
        third.next = four;
        four.next = first;

        Cycle cycle = new Cycle();
        boolean expect = true;
        assertThat(cycle.hasCycle(first), is(expect));
    }

}