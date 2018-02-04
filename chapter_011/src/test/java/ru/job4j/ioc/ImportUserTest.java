package ru.job4j.ioc;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Testing Import user.
 * @author Wamdue
 * @version 1.0
 * @since 04.02.2018
 */
public class ImportUserTest {
    /**
     * Application context for test purposes.
     */
    private final ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");

    /**
     * Testing add and getUsers methods.
     * Creating new user, import user.
     * Expect: getUsers().size() == 1.
     */
    @Test
    public void getUsers() {
        ImportUser importUser = this.context.getBean(ImportUser.class);
        User user = new User();
        user.setId(1);
        user.setName("Test");
        importUser.add(user);
        assertThat(1, is(importUser.getUsers().size()));
    }
}