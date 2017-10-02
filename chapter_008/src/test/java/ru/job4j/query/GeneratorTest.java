package ru.job4j.query;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created on 02.10.17
 * Query generator test class.
 * @author Wamdue
 * @version 1.0
 */
public class GeneratorTest {
    /**
     * begin of query : select * from items where
     * Have lines:
     * first : behave = Behave.NULL
     *         field = name
     *         action = Action.CONTAIN
     *         value = tree
     * second : behave = Behave.AND
     *          field = count
     *          action = Action.MORE_OR_EQUAL
     *          value = 20
     * expect : select * from items where name LIKE '%tree%'\n AND count >= 20\n
     */
    @Test
    public void whenHaveLinesInFilterThenGenerate() {
        Generator generator = new Generator();
        String begin;
        begin = "select * from items where";
        Line first = new Line();

        first.setField("name");
        first.setAction(Action.CONTAIN);
        first.setValue("tree");

        Line second = new Line();

        second.setBehave(Behave.AND);
        second.setField("count");
        second.setAction(Action.MORE_OR_EQUAL);
        second.setValue("20");

        List<Line> lines = new ArrayList<>();
        lines.add(first);
        lines.add(second);
        String expect = "select * from items where name LIKE '%tree%'\n AND count >= 20\n";

        assertThat(generator.generateSelect(begin, lines), is(expect));
    }

}