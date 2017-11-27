package ru.job4j.fin.entity;

/**
 * Created on 20.11.17.
 * Address object.
 * @author Wamdue
 * @version 1.0
 */
public class Address {
    /**
     * User address.
     */
    private String address;
    /**
     * Address id.
     */
    private int id = -1;

    /**
     * get current user Address.
     * @return - address.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Set new user address.
     * @param address - new address.
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Get address id.
     * @return - address id.
     */
    public int getId() {
        return id;
    }

    /**
     * Set new address id.
     * @param id - address id.
     */
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Address address1 = (Address) o;

        if (id != address1.id) {
            return false;
        }
        return address != null ? address.equals(address1.address) : address1.address == null;
    }

    @Override
    public int hashCode() {
        int result = address != null ? address.hashCode() : 0;
        result = 31 * result + id;
        return result;
    }
}
