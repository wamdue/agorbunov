package ru.job4j.order;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created on 11.08.17
 *
 * @author Wamdue
 * @version 1.0
 */
public class OrderBook {
    private Set<Order> list = new HashSet<>();
    private Set<Order> ask = new TreeSet<>();
    private Set<Order> bid = new TreeSet<>();
    private String name = "/home/alexey/Java/orders.xml";

    public static void main(String[] args) {
        OrderBook book = new OrderBook();
        long begin = System.currentTimeMillis();
        book.readFile();
        long end = System.currentTimeMillis();
        System.out.println("List size: " + book.list.size());
        System.out.println("Bid size: " + book.bid.size());
        System.out.println("Ask size: " + book.ask.size());
        System.out.println((end - begin) / 1000);
        System.out.println(book.ask.toArray()[0]);
        System.out.println(book.ask.toArray()[1]);
        System.out.println(book.bid.toArray()[0]);
        System.out.println(book.bid.toArray()[1]);
    }

    public void readFile() {
        try {
            XMLStreamReader xmlr = XMLInputFactory.newInstance().createXMLStreamReader(name, new FileInputStream(name));
            while (xmlr.hasNext()) {
                xmlr.next();
                if (xmlr.hasName()) {
                    if (xmlr.getName().getLocalPart().equals("AddOrder")) {

                        if (xmlr.isStartElement()) {
                            Order order = new Order();
                            order.setName(xmlr.getAttributeValue(0));
                            order.setOperation(Operation.valueOf(xmlr.getAttributeValue(1)));
                            order.setValue(Double.valueOf(xmlr.getAttributeValue(2)));
                            order.setVolume(Integer.valueOf(xmlr.getAttributeValue(3)));
                            order.setId(Integer.valueOf(xmlr.getAttributeValue(4)));
                            if (order.getOperation() == Operation.BUY) {
                                bid.add(order);
                            } else {
                                ask.add(order);
                            }
                        }
                    } else if (xmlr.getName().getLocalPart().equals("DeleteOrder")) {
                        if (xmlr.isStartElement()) {
                            Order order = new Order();
                            order.setName(xmlr.getAttributeValue(0));
                            order.setId(Integer.valueOf(xmlr.getAttributeValue(1)));
                            ask.remove(order);
                            bid.remove(order);
                        }
                    }
                }
            }
        } catch (IOException | XMLStreamException ex) {
            ex.printStackTrace();
        }
    }

    private Comparator<Order> asc() {
        return new Comparator<Order>() {
            @Override
            public int compare(Order o1, Order o2) {
                return (int) (o2.getValue() - o1.getValue());
            }
        };
    }

    private Comparator<Order> desc() {
        return new Comparator<Order>() {
            @Override
            public int compare(Order o1, Order o2) {
                return (int) (o1.getValue() - o2.getValue());
            };
        };
    }
}
