package ru.job4j.ioc;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.assertNotNull;

/**
 * Created on 02.02.18.
 * Testing IoC.
 * @author Wamdue
 * @version 1.0
 */
public class UserTest {
    /**
     * Spring context.
     */
    private final ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");

    /**
     * Trying to define variable via xml.
     * Expecting: not null.
     */
    @Test
    public void whenTryToGetBeanFromXmlThenNotNull() {
        User user = context.getBean(User.class);
        assertNotNull(user);
    }
    /**
     * Trying to define variable via annotations.
     * Expecting: not null.
     */
    @Test
    public void whenTryToGetBeanFromAnnotationsThenNotNull() {
        Model model = this.context.getBean(Model.class);
        assertNotNull(model);
    }
    /**
     * Trying to define variable via annotation autowire.
     * Expecting: not null.
     */
    @Test
    public void whenAutoWireThenNotNull() {
        Model model = this.context.getBean(Model.class);
        assertNotNull(model.getUser());
    }
}