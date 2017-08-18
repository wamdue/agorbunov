package ru.job4j.order;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

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
    private Map<Order, Order> result = new TreeMap<>();
    private String name = "/home/alexey/Java/orders.xml";

    public static void main(String[] args) {
        OrderBook book = new OrderBook();
        long begin = System.currentTimeMillis();
        book.readFile();
        System.out.println("List size: " + book.list.size());
        System.out.println("Bid size: " + book.bid.size());
        System.out.println("Ask size: " + book.ask.size());
        LinkedList<Order> bidList = new LinkedList<>(book.bid);
        LinkedList<Order> askList = new LinkedList<>(book.ask);
        while (!bidList.isEmpty() && !askList.isEmpty()) {
            Order as = askList.peek();
            Order bd = bidList.peek();
            if (as == null) {
                askList.remove();
                continue;
            }
            if (bd == null) {
                bidList.remove();
                continue;
            }
            if (bd.getValue() - as.getValue() >= 0) {
                int volume = as.getVolume() - bd.getVolume() > 0 ? bd.getVolume() : as.getVolume();
                Order orderBid = new Order();
                orderBid.setVolume(volume);
                orderBid.setValue(bd.getValue());
                Order orderAsk = new Order();
                orderAsk.setValue(as.getValue());
                orderAsk.setVolume(volume);
                bd.setVolume(bd.getVolume() - volume);
                as.setVolume(as.getVolume() - volume);
                book.result.put(orderBid, orderAsk);
                if (bd.getVolume() == 0) {
                    bidList.remove();
                }
                if(as.getVolume() == 0) {
                    askList.remove();
                }
            } else {
                askList.remove();
            }
        }
        System.out.println("BID - ASK");
        for (Map.Entry<Order, Order> map : book.result.entrySet()) {
            System.out.printf("%d@%f - %d@%f\n", map.getKey().getVolume(), map.getKey().getValue()
                    , map.getValue().getVolume(), map.getValue().getValue());
        }
        System.out.println(book.result.size());
        //        for (Order a : book.ask) {
//            for (Order b : book.bid) {
//                if (a.getValue() <= b.getValue()) {
//                    book.result.put(a, b);
//                    book.bid.remove(b);
//                    break;
//                }
//            }
//        }
        long end = System.currentTimeMillis();
        System.out.println((end - begin) / 1000);
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
