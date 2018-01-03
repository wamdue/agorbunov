package ru.job4j.lsp.storage;

import org.junit.Test;
import ru.job4j.lsp.food.Food;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Warehouse testing.
 * @author Wamdue
 * @version 1.0
 * @since 01.01.2018
 */
public class WarehouseTest {

    /**
     * Trying to add just created food.
     * Expect: true.
     */
    @Test
    public void whenAddVeryFreshFoodThenTrue() {
        this.testCase(0, true);
    }

    /**
     * Trying to add not very fresh food, expiration 50%
     * Expect: false.
     */
    @Test
    public void whenAddNotFreshFoodThenFalse() {
        this.testCase(2, false);
    }

    /**
     * Remove code dublicates.
     * @param shift - time shift, creation date from current date.
     * @param expect - expected result.
     */
    private void testCase(int shift, boolean expect) {
        Storage shop = new Warehouse();
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