package ru.job4j.loop;
import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
/**
* Test paintings.
*/
public class PaintTest {
    /**
    * n = 2.
    *  ^
    * ^^^.
    */
    @Test
    public void whenHeightIsTwoThenPaint() {
	StringBuilder sb = new StringBuilder();
	Paint p = new Paint();
	sb.append(" ^ \n");
	sb.append("^^^\n");
	assertThat(p.piramid(2), is(sb.toString()));
    }
    /**
    * n = 3.
    *  ^
    * ^^^
    *^^^^^
    */
    @Test
    public void whenHeightIsThreeThenPaint() {
	StringBuilder sb = new StringBuilder();
	Paint p = new Paint();
	sb.append("  ^  \n");
	sb.append(" ^^^ \n");
	sb.append("^^^^^\n");
	assertThat(p.piramid(3), is(sb.toString()));
    }
}