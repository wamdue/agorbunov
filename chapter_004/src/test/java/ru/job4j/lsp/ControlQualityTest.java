package ru.job4j.lsp;

import org.junit.Test;
import ru.job4j.lsp.food.Doshirak;
import ru.job4j.lsp.food.Food;
import ru.job4j.lsp.food.Macaroni;
import ru.job4j.lsp.storage.Shop;
import ru.job4j.lsp.storage.Storage;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Wamdue
 * @version 1.0
 * @since 01.01.2018
 */
public class ControlQualityTest {

    /**
     * Trying to add new storage.
     * Expect: true.
     */
    @Test
    public void whenAddNewStorageThenTrue() {
        Storage shop = new Shop();
        ControlQuality quality = new ControlQuality();
        quality.addStorage(shop);
        boolean result  = quality.getList().contains(shop);
        boolean expect = true;
        assertThat(result, is(expect));
    }

    /**
     * Trying add new food with expiration 50% via control quality.
     * Expect: true.
     */
    @Test
    public void whenAddNewFoodThenReturnTrue() {
        Food food = new Macaroni();

        LocalDateTime ldt = LocalDateTime.now();
        ldt = ldt.minusMonths(2);
        Date create = Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
        food.setCreateDate(create);
        ldt = ldt.plusMonths(4);
        Date expire = Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
        food.setExpireDate(expire);

        this.taskCase(food, true);
    }

    /**
     * Trying to add new storage.
     * Expect: true.
     */
    @Test
    public void whenAddNewFoodThatNotMatcherToAnyStorageThenFalse() {
        Food food = new Doshirak();

        LocalDateTime ldt = LocalDateTime.now();
        ldt = ldt.minusMonths(2);
        Date create = Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
        food.setCreateDate(create);
        ldt = ldt.plusMonths(1);
        Date expire = Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
        food.setExpireDate(expire);

        this.taskCase(food, false);

    }

    /**
     * Remove code duplicates.
     * @param food - food to add to the storage.
     * @param expect - expecting result.
     */
    private void taskCase(Food food, boolean expect) {
        Storage shop = new Shop();
        ControlQuality quality = new ControlQuality();
        quality.addStorage(shop);
        quality.add(food);

        boolean result = false;
        for (Storage store : quality.getList()) {
            if (store.getStorage().contains(food)) {
                result = true;
                break;
            }
        }

        assertThat(result, is(expect));
    }
}