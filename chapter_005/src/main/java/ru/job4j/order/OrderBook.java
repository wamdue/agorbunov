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
    private Set<Order> ask = new HashSet<>();
    private Set<Order> bid = new HashSet<>();
    private Map<Order, Order> result = new TreeMap<>((o1, o2) -> (int) (o1.getValue() - o2.getValue()));
    private String name = "/home/alexey/Java/orders.xml";

    public static void main(String[] args) {
        OrderBook book = new OrderBook();
        long begin = System.currentTimeMillis();

        book.readFile();

        System.out.println("Bid size: " + book.bid.size());
        System.out.println("Ask size: " + book.ask.size());

        LinkedList<Order> bidList = new LinkedList<>(book.bid);
        bidList.sort((o1, o2) -> {
            double result = o2.getValue() - o1.getValue();
            return  result > 0 ? 1 : result == 0 ? o2.getName().compareTo(o1.getName()) : -1;
        });

        LinkedList<Order> askList = new LinkedList<>(book.ask);
        askList.sort((o1, o2) -> {
            double result = o1.getValue() - o2.getValue();
            return  result > 0 ? 1 : result == 0 ? o1.getName().compareTo(o2.getName()) : -1;
        });

        askList = book.aggregate(askList);
        bidList = book.aggregate(bidList);

        while (!bidList.isEmpty() && !askList.isEmpty()) {
            Order as = askList.peekFirst();
            Order bd = bidList.peekLast();

            if (as == null) {
                askList.removeFirst();
                continue;
            }
            if (bd == null) {
                bidList.removeLast();
                continue;
            }
            if (bd.getValue() - as.getValue() >= 0 && bd.getName().equals(as.getName())) {
                int volume = as.getVolume() - bd.getVolume() > 0 ? bd.getVolume() : as.getVolume();
                Order orderBid = new Order();
                orderBid.setVolume(volume);
                orderBid.setValue(bd.getValue());
                orderBid.setName(bd.getName());
                Order orderAsk = new Order();
                orderAsk.setValue(as.getValue());
                orderAsk.setVolume(volume);
                orderAsk.setName(as.getName());
                bd.setVolume(bd.getVolume() - volume);
                as.setVolume(as.getVolume() - volume);
                if (book.result.containsKey(orderBid)) {

                }
                book.result.put(orderBid, orderAsk);
                if (bd.getVolume() == 0) {
                    bidList.removeLast();
                }
                if(as.getVolume() == 0) {
                    askList.removeFirst();
                }
            } else {
                askList.removeFirst();
            }
        }
        System.out.println("BID - ASK");
        for (Map.Entry<Order, Order> map : book.result.entrySet()) {
            System.out.printf("%d@%f - %d@%f\n", map.getKey().getVolume(), map.getKey().getValue()
                    , map.getValue().getVolume(), map.getValue().getValue());
        }

        System.out.println(book.result.size());
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

    private LinkedList<Order> aggregate(LinkedList<Order> orders) {
        Iterator<Order> iterator = orders.iterator();
        LinkedList<Order> list = new LinkedList<>();
        Order current = iterator.next();
        while (iterator.hasNext()) {
            Order temp = iterator.next();
            if (current.getValue() == temp.getValue() && current.getName().equals(temp.getName())) {
                current.setVolume(current.getVolume() + temp.getVolume());
            } else {
                list.add(current);
                current = temp;
            }
        }
        return list;
    }
}