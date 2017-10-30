package ru.job4j.order;

/**
 * Created on 11.08.17.
 * Order information.
 * @author Wamdue
 * @version 1.0
 */
public class Order implements Comparable<Order> {
    /**
     * Order name.
     */
    private String name;
    /**
     * Operation.
     */
    private Operation operation;
    /**
     * price.
     */
    private double value;
    /**
     * Volume of order.
     */
    private int volume;
    /**
     * Id of the order.
     */
    private int id;

    /**
     * Main constructor.
     */
    public Order() {
    }

    /**
     * Constructor with all parameters.
     * @param name - order name.
     * @param operation - operation.
     * @param value -price.
     * @param volume - volume.
     * @param id - order id.
     */
    public Order(String name, Operation operation, double value, int volume, int id) {
        this.name = name;
        this.operation = operation;
        this.value = value;
        this.volume = volume;
        this.id = id;
    }

    /**
     * Get order name.
     * @return name.
     */
    public String getName() {
        return name;
    }

    /**
     * Set order name.
     * @param name - new name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get order operation.
     * @return - operation.
     */
    public Operation getOperation() {
        return operation;
    }

    /**
     * Set new operation for order.
     * @param operation - new operation.
     */
    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    /**
     * Get order price.
     * @return - price.
     */
    public double getValue() {
        return value;
    }

    /**
     * Set new price.
     * @param value - new price.
     */
    public void setValue(double value) {
        this.value = value;
    }

    /**
     * Get order volume.
     * @return - get volume
     */
    public int getVolume() {
        return volume;
    }

    /**
     * Set new order volume.
     * @param volume - new volume.
     */
    public void setVolume(int volume) {
        this.volume = volume;
    }

    /**
     * Get order id.
     * @return id.
     */
    public int getId() {
        return id;
    }

    /**
     * Set new order id.
     * @param id - new id.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Custom equals realisation.
     * @param o - object to compare.
     * @return result of compare.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

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

    /**
     * Calc new hashcode.
     * @return - hashcode.
     */
    @Override
    public int hashCode() {
        int result = id;
        if (result == 0) {
            result = this.name.hashCode();
            result = (int) (31 * result + this.value);
        }
        return result;
    }

    /**
     * Custom output.
     * @return readable string.
     */
    @Override
    public String toString() {
        return "Order{"
                + "name='" + name + '\''
                + ", operation=" + operation
                + ", value=" + value
                + ", volume=" + volume
                + ", id=" + id
                + '}';
    }

    /**
     * Comparator realisation.
     * @param o - item to compare.
     * @return result of compare.
     */
    @Override
    public int compareTo(Order o) {

        double result = this.getValue() - o.getValue();
        return  result > 0 ?  1 : result < 0 ? -1 : 0;
    }
}
