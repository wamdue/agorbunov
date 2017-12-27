package ru.job4j.isp;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 27.12.17.
 * Main menu
 * @author Wamdue
 * @version 1.0
 */
public class Menu {
    /**
     * Menu name.
     */
    private String name;
    /**
     * Menu elements.
     */
    private List<Element> elementList = new ArrayList<>();

    /**
     * Main constructor.
     * @param name - menu name.
     */
    public Menu(String name) {
        this.name = name;
    }

    /**
     * Get list of menu elements.
     * @return - menu elements.
     */
    public List<Element> getList() {
        return this.elementList;
    }

    /**
     * Get menu name.
     * @return - menu name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Draw tree of menu elements.
     * @param elements - list of elements.
     * @param i - level of elements.
     */
    private void drawTree(List<Element> elements, int i) {
        for (Element e : elements) {
                for (int x = 0; x < i; x++) {
                    System.out.print("--");
                }
            System.out.println(e.getName());
            if (e.getElements().size() > 0) {
                drawTree(e.getElements(), i + 1);
            }
        }
    }

    /**
     * View all menu elements.
     */
    public void drawMenu() {
        System.out.println(this.getName());
        drawTree(this.elementList, 1);
    }


    /**
     * Main method.
     * @param args - not in use.
     */
    public static void main(String[] args) {
        Menu menu = new Menu("Задача 1.");
        Element e1 = new ElementImpl("Задача 1.1");
        Element e2 = new ElementImpl("Задача 1.1.1");
        Element e3 = new ElementImpl("Задача 1.1.2");
        Element e4 = new ElementImpl("Задача 1.2");
        Element e5 = new ElementImpl("Задача 1.2.1");
        Element e6 = new ElementImpl("Задача 1.2.2");
        Element e7 = new ElementImpl("Задача 1.1.1.1");
        menu.elementList.add(e1);
        e1.getElements().add(e2);
        e1.getElements().add(e3);
        menu.elementList.add(e4);
        e4.getElements().add(e5);
        e4.getElements().add(e6);
        e2.getElements().add(e7);
        menu.drawMenu();
    }
}
