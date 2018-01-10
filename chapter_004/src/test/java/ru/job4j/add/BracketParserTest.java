package ru.job4j.add;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created on 10.01.18.
 * Bracket parser test.
 * @author Wamdue
 * @version 1.0
 */
public class BracketParserTest {
    /**
     * BrackedParser object to test.
     */
    private final BracketParser bracketPair = new BracketParser();

    /**
     * Add new bracket pair to lib.
     * Trying to find it in map.
     * Expect: true.
     */
    @Test
    public void whenAddNewPairToMapThenCanFindItInMap() {
        this.bracketPair.addPair('\"', '\"');
        boolean result = bracketPair.getMap().containsKey('\"');
        boolean expect = true;

        assertThat(result, is(expect));
        this.bracketPair.removePair('\"');
    }
    /**
     * Removing just added new bracket pair to lib.
     * Trying to find it in map.
     * Expect: false.
     */
    @Test
    public void whenRemovePairFromMapThenCannotFindIt() {
        this.bracketPair.addPair('\'', '\'');
        this.bracketPair.removePair('\'');
        boolean result = bracketPair.getMap().containsKey('\'');
        boolean expect = false;

        assertThat(result, is(expect));

    }

    /**
     * Parsing string with correct brackets: a[(b{c}d)e].
     * Expect string:
     * open [ at 1, close ] at 10
     * open ( at 2, close ) at 8
     * open { at 4, close } at 6
     */
    @Test
    public void whenHaveStringWithCorrectBracketsThenReturnResultString() {
        String result = this.bracketPair.check("a[(b{c}d)e]");
        String expect = "open [ at 1, close ] at 10\nopen ( at 2, close ) at 8\nopen { at 4, close } at 6\n";

        assertThat(result, is(expect));
    }

    /**
     * Parsing string with not paired brackets: a[(b{c}d)e].
     * Expect string: No bracket in string, or have not paired brackets.
     */
    @Test
    public void whenHaveStringWithNotPaieredBracketsThenError() {
        String result = this.bracketPair.check("a[b{c}d)e]");
        String expect = "No bracket in string, or have not paired brackets.";

        assertThat(result, is(expect));

    }

}