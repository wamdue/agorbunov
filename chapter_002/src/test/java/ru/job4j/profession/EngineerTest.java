package ru.job4j.profession;
import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;
/**
* Engineer information test.
*/
public class EngineerTest {
    /**
    * Expect = Kulibin constructing House\n.
    */
    @Test
    public void whenHasBuildingThenBuild() {
	Building building = new Building("House");
	Engineer engineer = new Engineer("Kulibin", 5, "Crazy inventor", "Can do anything");
	String expect = "Kulibin constructing House\n";
	ByteArrayOutputStream out = new ByteArrayOutputStream();
	System.setOut(new PrintStream(out));
	engineer.build(building);
	assertThat(out.toString(), is(expect));
    }
    /**
    * Expect = Kulibin repairing iron\n.
    */
    @Test
    public void whenHasRapairingThenRepair() {
	Item item = new Item("iron");
	Engineer engineer = new Engineer("Kulibin", 5, "Crazy inventor", "Can do anything");
	String expect = "Kulibin repairing iron\n";
	ByteArrayOutputStream out = new ByteArrayOutputStream();
	System.setOut(new PrintStream(out));
	engineer.repair(item);
	assertThat(out.toString(), is(expect));
    }
    /**
    * Expect = Kulibin making chair\n.
    */
    @Test
    public void whenHasToConstructThenMake() {
	Item item = new Item("chair");
	Engineer engineer = new Engineer("Kulibin", 5, "Crazy inventor", "Can do anything");
	String expect = "Kulibin making chair\n";
	ByteArrayOutputStream out = new ByteArrayOutputStream();
	System.setOut(new PrintStream(out));
	engineer.construct(item);
	assertThat(out.toString(), is(expect));
    }
}
