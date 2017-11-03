package ru.job4j.crud.controller;

import org.junit.Test;
import ru.job4j.crud.model.DBConnection;
import ru.job4j.crud.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created on 03.11.17.
 * Testing DB connection.
 * @author Wamdue
 * @version 1.0
 */
public class DBConnectionTest {
    /**
     * Connection to db.
     */
    private final DBConnection connection = DBConnection.getInstance();

    /**
     * Trying to add user to database.
     * Adding user with name dbconn.
     * Expect:  method getUserByName return user with name "dbconn"
     * @throws ServletException - servlet exception
     * @throws IOException - io exception.
     */
    @Test
    public void whenAddNewUserThenInsertIntoDB() throws ServletException, IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        AddUser addUser = new AddUser();
        when(request.getParameter("name")).thenReturn("dbconn");
        when(request.getParameter("login")).thenReturn("dbconn");
        when(request.getParameter("email")).thenReturn("dbconn");
        when(request.getParameter("password")).thenReturn("dbconn");
        when(request.getParameter("role")).thenReturn("USER");

        addUser.doPost(request, response);

        User user = this.connection.getUserByName("dbconn");
        assertThat(user.getName(), is("dbconn"));
    }

    /**
     * Trying to update user in db.
     * Have user with name dbconn
     * trying to change it to newdbconn.
     * expect user with same id, but with new name - newdbconn.
     * @throws ServletException - servlet exception.
     * @throws IOException - io exception.
     */
    @Test
    public void whenUpdatingUserThenHeMustChange() throws ServletException, IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        UpdateUser updateUser = new UpdateUser();

        User user = this.connection.getUserByName("dbconn");
        when(request.getParameter("id")).thenReturn(String.valueOf(user.getId()));

        when(request.getParameter("newname")).thenReturn("newdbconn");
        when(request.getParameter("newlogin")).thenReturn("newdbconn");
        when(request.getParameter("newpassword")).thenReturn("newdbconn");
        when(request.getParameter("newemail")).thenReturn("newdbconn");

        updateUser.doPost(request, response);

        user = this.connection.getUserById(user.getId());

        assertThat(user.getName(), is("newdbconn"));

    }

    /**
     * Trying to delete user from db.
     * Delete user from db with name newdbconn.
     * expect: when user was deleted, list by the method listOfUsers
     * do not contain that user.
     * @throws ServletException - servlet exception.
     * @throws IOException - io exception.
     */
    @Test
    public void whenDeleteUserThenUpdateDatabase() throws ServletException, IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        DeleteUser deleteUser = new DeleteUser();
        User user = this.connection.getUserByName("newdbconn");
        when(request.getParameter("id")).thenReturn(String.valueOf(user.getId()));

        deleteUser.doPost(request, response);

        List<User> users = this.connection.listOfUsers();
        boolean result = users.contains(user);
        assertThat(result, is(false));
    }
}