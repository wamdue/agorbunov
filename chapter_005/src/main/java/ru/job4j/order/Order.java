package ru.job4j.order;

/**
 * Created on 11.08.17
 *
 * @author Wamdue
 * @version 1.0
 */
public class Order implements Comparable<Order> {
    private String name;
    private Operation operation;
    /**
     * price.
     */
    private double value;
    private int volume;
    private int id;

    public Order() {
    }

    public Order(String name, Operation operation, double value, int volume, int id) {
        this.name = name;
        this.operation = operation;
        this.value = value;
        this.volume = volume;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        boolean result = false;
        Order order = (Order) o;
        if (order.id == 0) {
            result = name.equals(order.name);
            if (result) {
                result = value == order.value;
            }
        } else {
            result = this.id == order.id;
        }

        return result;
    }

    @Override
    public int hashCode() {
        int result = id;
        if (result == 0) {
            result = this.name.hashCode();
            result = (int) (31 * result + this.value);
        }
        return result;
    }

    @Override
    public String toString() {
        return "Order{" +
                "name='" + name + '\'' +
                ", operation=" + operation +
                ", value=" + value +
                ", volume=" + volume +
                ", id=" + id +
                '}';
    }

    @Override
    public int compareTo(Order o) {

        double result = this.getValue() - o.getValue();
        return  result > 0 ?  1 : result < 0 ? -1 : 0;
    }
}
