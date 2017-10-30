package ru.job4j.tree;


import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created on 10.08.17.
 * Simple tree realization.
 * @author Wamdue
 * @version 1.0
 * @param <E> - class to work with.
 */
public class Tree<E extends Comparable<E>> implements SimpleTree<E> {
    /**
     * root item.
     */
    private Node<E> root;

    /**
     * Class to store elements.
     * @param <E> - class to store.
     */
    class Node<E> {
        /**
         * Left child.
         */
        private Node<E> left;
        /**
         * Right child.
         */
        private Node<E> right;
        /**
         * Children of element.
         */
        private List<Node<E>> children;
        /**
         * Value of element.
         */
        private E value;
    }

    /**
     * Main constructor, adds null element ass root.
     */
    public Tree() {
        Node<E> newNode = new Node<>();
        newNode.value = null;
        newNode.children = new ArrayList<>();
        this.root = newNode;
    }

    /**
     * Add item to tree.
     * @param parent parent - parent element.
     * @param child child - element to add.
     * @return - true if item has been added, otherwise false.
     */
    @Override
    public boolean add(E parent, E child) {
        Node<E> newNode = new Node<>();
        newNode.children = new ArrayList<>();
        newNode.value = child;
        Node<E> temp = getParent(parent);
        boolean result = temp != null;
        if (result) {
            temp.children.add(newNode);
        }
        return result;
    }

    /**
     * Using binary tree.
     * @param e - item to add.
     */
    public void add(E e) {
        if (root.value == null) {
            root.value = e;
        } else {
            Node<E> current = root;
            Node<E> newNode = new Node<>();
            newNode.value = e;
            while (true) {
                if (current.value.compareTo(e) > 0) {
                    if (current.left == null) {
                        current.left = newNode;
                        break;
                    } else {
                        current = current.left;
                    }
                } else if (current.value.compareTo(e) < 0) {
                    if (current.right == null) {
                        current.right = newNode;
                        break;
                    } else {
                        current = current.right;
                    }
                } else {
                    break;
                }
            }
        }
    }

    /**
     * @param parent value of parent element.
     * @return return node of the parent element, or null if not found.
     */
    private Node<E> getParent(E parent) {
        if (parent == null) {
            return root;
        }
        Node<E> result = null;
        Queue<Node<E>> queue = new LinkedList<>();
        queue.addAll(root.children);
        Node<E> temp = null;
        while (!queue.isEmpty()) {
           temp = queue.poll();
           if (temp.value.compareTo(parent) == 0) {
               result = temp;
               break;
           }
           queue.addAll(temp.children);
        }
        return result;
    }

    /**
     *
     * @return tree iterator.
     */
     @Override
     public Iterator<E> iterator() {
         return new Iterator<E>() {
             private Queue<Node<E>> queue = new LinkedList<>();

             {
                 init();
             }

             @Override
             public boolean hasNext() {
                 return !queue.isEmpty();
             }

             @Override
             public E next() {
                 return queue.poll().value;
             }

             /**
              * fills internal queue to iterate on it
              * but removes first element(which is null).
              */
             private void init() {
                 Queue<Node<E>> temp = new ArrayDeque<>();
                 queue.add(root);
                 temp.addAll(root.children);
                 Node<E> node = null;
                 while (!temp.isEmpty()) {
                    node = temp.poll();
                    queue.add(node);
                    temp.addAll(node.children);
                 }
                 queue.poll();
             }
         };
     }

    /**
     *
     * @return true if tree node contains less or equal 2 elements, false if more.
     */
    public boolean isBinary() {
         Queue<Node<E>> temp = new LinkedList<>();
         boolean result = true;
         temp.add(root);
         Node<E> node;
         while (!temp.isEmpty()) {
             node = temp.poll();
             if (node.children.size() > 2) {
                 result = false;
                 break;
             }
             temp.addAll(node.children);
         }
         return result;
    }
}
