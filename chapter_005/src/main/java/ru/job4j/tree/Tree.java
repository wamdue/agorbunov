package ru.job4j.tree;

import java.util.*;

/**
 * Created on 10.08.17
 * Simple tree realization.
 * @author Wamdue
 * @version 1.0
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
        List<Node<E>> childen;
        E value;
    }

    /**
     * Main constructor, adds null element ass root.
     */
    public Tree() {
        Node<E> newNode = new Node<>();
        newNode.value = null;
        newNode.childen = new ArrayList<>();
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
        newNode.childen = new ArrayList<>();
        newNode.value = child;
        Node<E> temp = getParent(parent);
        boolean result = temp != null;
        if (result) {
            temp.childen.add(newNode);
        }
        return result;
    }

    /**
     * @param parent value of parent element.
     * @return return node of the parent element, or null if not found.
     */
    private Node<E> getParent(E parent) {
        if(parent == null){
            return root;
        }
        Node<E> result = null;
        Queue<Node<E>> queue = new LinkedList<>();
        queue.addAll(root.childen);
        Node<E> temp = null;
        while (!queue.isEmpty()) {
           temp = queue.poll();
           if (temp.value.compareTo(parent) == 0) {
               result = temp;
               break;
           }
           queue.addAll(temp.childen);
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
                 temp.addAll(root.childen);
                 Node<E> node = null;
                 while (!temp.isEmpty()) {
                    node = temp.poll();
                    queue.add(node);
                    temp.addAll(node.childen);
                 }
                 queue.poll();
             }
         };
     }
}
