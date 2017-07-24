package ru.job4j.list;

/**
 * Created on 24.07.17
 * Simple queue realization.
 * @author Wamdue
 * @version 1.0
 */
public class SimpleQueue<E> extends SimpleLinkedList<E>{
    /**
     *  Link to the first element.
     */
    private Node<E> first = null;
    /**
     *  Link to the last element.
     */
    private Node<E> last = null;
    /**
     * Size.
     */
    private int size = 0;

    /**
     * Removes first element from list, and returns it.
     * @return - first element, or null if size == 0.
     */
    public E poll () {
        E temp = this. first.item;
        if (this.size > 0) {
            this.first = this.first.next;
            this.size--;
        }
        return temp;
    }

    /**
     * Removes first element from list, and returns it.
     * @return - first element, or null if size == 0.
     */
    public E remove() {
        return this.poll();
    }

    /**
     * Returns first element from the list, without deleting.
     * @return first element from the list.
     */
    public E peek() {
        return first.item;
    }

    /**
     * adding element to the end of the list.
     * @param e - element to add.
     */
    public void offer(E e) {
        Node<E> l = last;
        Node<E> newNode = new Node<>(e, l, null);
        if (l == null) {
            first = newNode;
            last = newNode;
        } else {
            l.next = newNode;
            last = l.next;
        }
        size++;
    }
    /**
     * Private class to store elements in list.
     * @param <E> - class to store.
     */
    private class Node<E> {
        /**
         * main element.
         */
        E item;
        /**
         * Link to previous item.
         */
        Node<E> previous;
        /**
         * link ot next item.
         */
        Node<E> next;

        /**
         *
         * @param item - main item.
         * @param previous - link to previous item.
         * @param next - link to next item.
         */
        public Node (E item, Node<E> previous, Node<E> next) {
            this.item = item;
            this.previous = previous;
            this.next = next;
        }
    }
}
