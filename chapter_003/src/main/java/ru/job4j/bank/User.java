package ru.job4j.bank;

/**
 * Created on 18.07.17
 *
 * @author Wamdue
 * @version 1.0
 */
public class User {
    /**
     * @param name - user name;
     */
    private String name;
    /**
     * @param passport - user passport;
     */
    private String passport;

    /**
     *
     * @param name - Set name of user;
     * @param passport- set passport of user;
     */
    public User(String name, String passport) {
        this.name = name;
        this.passport = passport;
    }

    /**
     *
     * @return user name.
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name - new user name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return - user passport.
     */
    public String getPassport() {
        return passport;
    }

    /**
     *
     * @param passport - new user passport.
     */
    public void setPassport(String passport) {
        this.passport = passport;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        User user = (User) o;

        if (name != null ? !name.equals(user.name) : user.name != null) {
            return false;
        }
        return passport != null ? passport.equals(user.passport) : user.passport == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (passport != null ? passport.hashCode() : 0);
        return result;
    }
}
