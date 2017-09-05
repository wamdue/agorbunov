package ru.job4j.order;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

/**
 * Created on 11.08.17
 * Managing order books.
 * @author Wamdue
 * @version 1.0
 */
public class OrderBook {
    /**
     * ask orders.
     */
    private Set<Order> ask = new HashSet<>();
    /**
     * bid orders.
     */
    private Set<Order> bid = new HashSet<>();
    /**
     * result map.
     */
    private Map<Order, List<Order>> result = new TreeMap<>((o1, o2) -> (int) (o1.getValue() - o2.getValue()));
    /**
     * path to source file.
     */
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

        book.compute(bidList, askList);

        System.out.println("BID - ASK");
        for (Map.Entry<Order, List<Order>> map : book.result.entrySet()) {
            for (Order o : map.getValue()) {
                System.out.printf("%d@%f - %d@%f\n", map.getKey().getVolume(), map.getKey().getValue()
                        , o.getVolume(), o.getValue());
            }
        }

        System.out.println(book.result.size());
        long end = System.currentTimeMillis();
        System.out.println((end - begin) / 1000);
    }

    /**
     * reading source file and fill ask & bid sets.
     */
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

    /**
     * Method to aggregate orders with same value.
     * @param orders - sourse list.
     * @return aggregated list.
     */
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

    /**
     * Method fills result map with orders.
     * @param bid - bid list.
     * @param ask - ask list.
     */
    private void compute(LinkedList<Order> bid, LinkedList<Order> ask) {
        while (!bid.isEmpty() && !ask.isEmpty()) {
            Order as = ask.peekFirst();
            Order bd = bid.peekLast();

            if (as == null) {
                ask.removeFirst();
                continue;
            }
            if (bd == null) {
                bid.removeLast();
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
                if (result.containsKey(orderBid)) {
                    List<Order> list = result.get(orderBid);
                    list.add(orderAsk);
                    for (Order o : result.keySet()) {
                        if (o.equals(orderBid)) {
                            orderBid.setVolume(orderBid.getVolume() + o.getVolume());
                            break;
                        }
                    }

                    result.put(orderBid, list);

                } else {
                    List<Order> list = new ArrayList<>();
                    list.add(orderAsk);
                    result.put(orderBid, list);
                }
                if (bd.getVolume() == 0) {
                    bid.removeLast();
                }
                if(as.getVolume() == 0) {
                    ask.removeFirst();
                }
            } else {
                ask.removeFirst();
            }
        }

    }
}