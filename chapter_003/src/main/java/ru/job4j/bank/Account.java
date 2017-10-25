package ru.job4j.bank;

/**
 * Created on 18.07.17
 * Account information.
 * @author Wamdue
 * @version 1.0
 */
public class Account {
    /**
     * @param value - amount of money on account.
     */
    private double value;
    /**
     * @param requisites - number of account.
     */
    private final String requisites;

    /**
     *
     * @param value - primary amount of money.
     * @param requisites - account number.
     */
    public Account(final double value, final String requisites) {
        this.value = value;
        this.requisites = requisites;
    }

    /**
     *
     * @return - current amount of money.
     */
    public double getValue() {
        return value;
    }

    /**
     *
     * @param value - set new amount of money.
     */
    public void setValue(double value) {
        this.value = value;
    }

    /**
     *
     * @return - account number.
     */
    public String getRequisites() {
        return requisites;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Account account = (Account) o;

        if (Double.compare(account.value, value) != 0) {
            return false;
        }
        return requisites.equals(account.requisites);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(value);
        result = (int) (temp ^ (temp >>> 32));
        result = 31 * result + requisites.hashCode();
        return result;
    }
}
