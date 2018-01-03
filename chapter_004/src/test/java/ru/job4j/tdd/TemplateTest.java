package ru.job4j.tdd;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created on 28.12.17.
 * Testing simple generator.
 * @author Wamdue
 * @version 1.0
 */
public class TemplateTest {
    /**
     * Test class.
     */
    private Template generator = new SimpleGenerator();

    /**
     * When have string: I am a ${name}, Who are ${subject}?
     * Map have keys : name -> Alex
     *                 subject -> you.
     * after calling generate method expecting string:
     * I am a Alex, Who are you?
     */
    @Test
    public void whenHaveStringAndMapKeyThenCombineIt() {
        Map<String, String> map = new HashMap<>();
        map.put("name", "Alex");
        map.put("subject", "you");
        String temp = "I am a ${name}, Who are ${subject}?";
        String expect = "I am a Alex, Who are you?";
        String result = generator.generate(temp, map);

        assertThat(result, is(expect));
    }
    /**
     * When have string: Help, ${sos}, ${sos}, ${sos}.
     * Map have keys : sos -> Aaa
     * after calling generate method expecting string:
     * Help, Aaa, Aaa, Aaa.
     */
    @Test
    public void whenHaveSeveralSameKeysThenReplace() {
        Map<String, String> map = new HashMap<>();
        map.put("sos", "Aaa");

        String temp = "Help, ${sos}, ${sos}, ${sos}.";
        String expect = "Help, Aaa, Aaa, Aaa.";
        String result = this.generator.generate(temp, map);

        assertThat(result, is(expect));
    }

    /**
     * Temp string contain key, but map don`t.
     * Expecting UnsupportedOperationException.
     */
    @Test (expected = UnsupportedOperationException.class)
    public void whenDoNotHaveParameterInListThenException() {
        Map<String, String> map = new HashMap<>();

        String temp = "Hello ${name}";
        String result = this.generator.generate(temp, map);
    }

    /**
     * Temp string do no contain key, but map do.
     * Expecting UnsupportedOperationException.
     */
    @Test (expected = UnsupportedOperationException.class)
    public void whenStringWithoutKeyButMapWithThenException() {
        Map<String, String> map = new HashMap<>();
        map.put("name", "Vasya");

        String temp = "Hello";
        String result = this.generator.generate(temp, map);
    }

    /**
     * Temp string contain key, but map do.
     * Expecting UnsupportedOperationException.
     */
    @Test (expected = UnsupportedOperationException.class)
    public void whenStringHaveKeyButMapDontHasveThatkeyThenException() {
        Map<String, String> map = new HashMap<>();
        map.put("name", "Vasya");

        String temp = "Hello ${help}";
        String result = this.generator.generate(temp, map);
    }

    /**
     * Temp string contains less key then map do.
     * Expecting UnsupportedOperationException.
     */
    @Test (expected = UnsupportedOperationException.class)
    public void whenMapHaveMoreKeysThenStrngThenException() {
        Map<String, String> map = new HashMap<>();
        map.put("name", "Vasya");
        map.put("surname", "Vasiliev");

        String temp = "Hello, ${name}";
        String result = this.generator.generate(temp, map);
    }

}