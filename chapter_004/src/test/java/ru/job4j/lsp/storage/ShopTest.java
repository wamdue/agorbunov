package ru.job4j.lsp.storage;

import org.junit.Test;
import ru.job4j.lsp.food.Food;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Testing Shop storage.
 *
 * @author Wamdue
 * @version 1.0
 * @since 01.01.2018
 */
public class ShopTest {
    /**
     * Trying to add food with expiration period 50%.
     * Expected: true.
     */
    @Test
    public void whenAddNoSoFreshFoodThenTrue() {
        this.testCase(2, true);
    }

    /**
     * Trying to add food with expiration period less then 25%.
     * Expected: false.
     */
    @Test
    public void whenAddVeryFreshFoodThenFalse() {
        this.testCase(0, false);
    }
    /**
     * Trying to add food with expiration period more then 75%.
     * Expected: false.
     */
    @Test
    public void whenAddNotFreshFoodThenTrue() {
        this.testCase(6, true);
    }

    /**
     * Remove code dublicates.
     * @param shift - time shift, creation date from current date.
     * @param expect - expected result.
     */
    private void testCase(int shift, boolean expect) {
        Storage shop = new Shop();
        Food food = new Food();
        LocalDateTime local = LocalDateTime.now();
        local = local.minusMonths(shift);
        Date create = Date.from(local.atZone(ZoneId.systemDefault()).toInstant());
        local = local.plusMonths(shift + 2);
        Date expire = Date.from(local.atZone(ZoneId.systemDefault()).toInstant());
        food.setCreateDate(create);
        food.setExpireDate(expire);
        food.setName("Test");

        boolean result = shop.addFood(food);

        assertThat(result, is(expect));
    }


}