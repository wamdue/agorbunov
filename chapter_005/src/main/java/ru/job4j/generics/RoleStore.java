package ru.job4j.generics;

/**
 * Created on 20.07.17
 *
 * @author Wamdue
 * @version 1.0
 */
public class RoleStore implements Store<Role> {
    /**
     * Storage of roles.
     */
    private SimpleArray<Role> roles;

    /**
     * Main constructor.
     * @param size - maximum storage size.
     */
    public RoleStore(int size) {
        roles = new SimpleArray<>(size);
    }

    /**
     * Adding new item in storage.
     * @param role - role to add.
     */
    @Override
    public void add(Role role) {
        roles.add(role);
    }

    /**
     * Update item in storage.
     * @param old - old item.
     * @param updated - new item.
     */
    @Override
    public void update(Role old, Role updated) {
        roles.update(roles.getId(old), updated);
    }

    /**
     * Delete role from storage.
     * @param role - role to delete.
     */
    @Override
    public void delete(Role role) {
        roles.delete(role);
    }
}