package ru.job4j.list;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 24.07.17.
 * Checkin cycle in the linked list.
 * @author Wamdue
 * @version 1.0
 */
public class Cycle {

    /**
     *
     * @param first - first element in the linked list.
     * @return true if has cycle, false if not.
     */
    public boolean hasCycle(Node first) {
        boolean result = false;
        List<Node> list = new ArrayList<>();
        Node current = first.getNext();
        list.add(first);
        while (current != null) {
            for (Node n : list) {
                if (n == current) {
                    result = true;
                    break;
                }
            }
            if (result) {
                break;
            }
            list.add(current);
            current = current.getNext();
        }
        return result;
    }
}