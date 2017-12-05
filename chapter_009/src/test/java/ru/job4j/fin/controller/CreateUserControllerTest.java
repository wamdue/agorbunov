package ru.job4j.fin.controller;

import org.junit.Test;
import ru.job4j.fin.dao.MusicTypeDao;
import ru.job4j.fin.dao.RoleDao;
import ru.job4j.fin.dao.UserDao;
import ru.job4j.fin.entity.Address;
import ru.job4j.fin.entity.User;
import ru.job4j.fin.enums.Connect;
import ru.job4j.fin.repository.UserRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


/**
 * Created on 27.11.17.
 * User creation test.
 * @author Wamdue
 * @version 1.0
 */
public class CreateUserControllerTest {
    /**
     * Current db connection.
     */
    private Connection connection = Connect.INSTANCE.getConnection();
    /**
     * Properties of connection.
     */
    private Properties props = new Properties();

    /**
     * Have user:
     * Name: test user;
     * address: Test address;
     * role id: 1, 2;
     * music type: 1, 2;
     * Inserting this user in db.
     * Then trying to get it from db and compare with similar created user.
     * Expect true.
     *
     * @throws ServletException - servlet exception.
     * @throws IOException - io exception.
     */
    @Test
    public void whenCreatingNewUserThenInsertIntoDb() throws ServletException, IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        CreateUserController create = new CreateUserController();
        String userName = "test user";
        String userAddress = "Test address";

        when(request.getParameter("name")).thenReturn(userName);
        when(request.getParameter("address")).thenReturn(userAddress);
        when(request.getParameterValues("musicTypes[]")).thenReturn(new String[] {"1", "2"});
        when(request.getParameterValues("roles[]")).thenReturn(new String[] {"1", "2"});

        create.doPost(request, response);

        UserRepository repository = new UserRepository(this.connection);
        UserDao userDao = new UserDao(this.connection);
        int userId = -1;
        for (User user : userDao.getAll()) {
            if (user.getName().equals(userName)) {
                userId = user.getId();
                break;
            }
        }


        User user = new User();
        RoleDao roleDao = new RoleDao(this.connection);
        MusicTypeDao musicTypeDao = new MusicTypeDao(this.connection);

        user.setId(userId);
        user.setName(userName);
        Address address = new  Address();
        address.setAddress(userAddress);
        user.setAddress(address);
        user.addRole(roleDao.findById(1));
        user.addRole(roleDao.findById(2));
        user.addMusicType(musicTypeDao.findById(1));
        user.addMusicType(musicTypeDao.findById(2));


        boolean expect = true;
        User dbUser = repository.getUserById(userId);

        try {
            ClassLoader cl = Thread.currentThread().getContextClassLoader();
            InputStream input = cl.getResourceAsStream("psinit.properties");
            props.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.deleteUserEntity(userId, props.getProperty("delete_user_roles"));
        this.deleteUserEntity(userId, props.getProperty("delete_user_music"));
        this.deleteUserEntity(userId, props.getProperty("delete_user_address"));
        this.deleteUserEntity(userId, props.getProperty("delete_user"));


        assertThat(expect, is(dbUser.equals(user)));

    }

    /**
     * Working with similar queries.
     * @param id - id of user.
     * @param sql - sql string to execute.
     */
    private void deleteUserEntity(int id, String sql) {
        try (PreparedStatement statement = this.connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}