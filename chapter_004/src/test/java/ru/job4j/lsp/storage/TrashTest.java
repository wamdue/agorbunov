package ru.job4j.lsp.storage;

import org.junit.Test;
import ru.job4j.lsp.food.Food;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Testing trash.
 * @author Wamdue
 * @version 1.0
 * @since 01.01.2018
 */
public class TrashTest {

    /**
     * Trying to add food with expire date less the current date.
     * Expect: true.
     */
    @Test
    public void whenAddExpiredFoodThenTrue() {
        this.testCase(2, true);
    }

    /**
     * Trying to add food with expire date more the current date.
     * Expect: false.
     */
    @Test
    public void whenAddNotExpiredFoodThenFalse() {
        this.testCase(-2, false);
    }

    /**
     * Removing code dublication.
     * @param shift - month shift creation.
     * @param expect - expecting result.
     */
    private void testCase(int shift, boolean expect) {
        Storage shop = new Trash();
        Food food = new Food();
        LocalDateTime local = LocalDateTime.now();
        local = local.minusMonths((int) Math.pow(shift, 2));
        Date create = Date.from(local.atZone(ZoneId.systemDefault()).toInstant());
        LocalDateTime current = LocalDateTime.now();
        current = current.minusMonths(shift);
        Date expire = Date.from(current.atZone(ZoneId.systemDefault()).toInstant());
        food.setCreateDate(create);
        food.setExpireDate(expire);
        food.setName("Test");

        boolean result = shop.addFood(food);

        assertThat(result, is(expect));
    }

}