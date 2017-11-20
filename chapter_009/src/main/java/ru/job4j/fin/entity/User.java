package ru.job4j.fin.entity;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Created on 20.11.17.
 * User object.
 * @author Wamdue
 * @version 1.0
 */
public class User {
    /**
     * User name.
     */
    private String name;
    /**
     * User id;
     */
    private int id;
    /**
     * User roles.
     */
    private Set<Role> roles = new HashSet<>();
    /**
     * User address.
     */
    private Address address;
    /**
     * User`s music types.
     */
    private Set<MusicType> musicTypes = new HashSet<>();

    /**
     * Get current user name.
     * @return - user name.
     */
    public String getName() {
        return name;
    }

    /**
     * Set new user name.
     * @param name - new user name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get user Id.
     * @return - user id.
     */
    public int getId() {
        return id;
    }

    /**
     * Set new user id.
     * @param id - new user id.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Get all user roles.
     * @return - all roles.
     */
    public Set<Role> getRoles() {
        return Collections.unmodifiableSet(roles);
    }

    /**
     * Add new role to user.
     * @param role - user role.
     */
    public void addRole(Role role) {
        roles.add(role);
    }

    /**
     * Delete user roles.
     * @param role - role to delete from user.
     */
    public void removeRole(Role role) {
        roles.remove(role);
    }

    /**
     * Get current user address.
     * @return - user address.
     */
    public Address getAddress() {
        return this.address;
    }

    /**
     * Set new user address.
     * @param address - new address.
     */
    public void setAddress(Address address) {
        this.address = address;
    }

    /**
     * Get all users music types..
     * @return music types.
     */
    public Set<MusicType> getMusicTypes() {
        return Collections.unmodifiableSet(this.musicTypes);
    }

    /**
     * Add new music type to user.
     * @param type - music type to add.
     */
    public void addMusicType(MusicType type) {
        this.musicTypes.add(type);
    }

    /**
     * Remove music type from user.
     * @param type - music type to remove from user.
     */
    public void removeMusicType(MusicType type) {
        this.musicTypes.remove(type);
    }
}
