package ru.job4j.profession;
import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;
/**
* Doctor information test.
*/
public class DoctorTest {
    /**
    * Expect = Doctor Pirogov heals Ivan\n.
    */
    @Test
    public void whenHasPacientThenHeal() {
	Pacient pacient = new Pacient("Ivan");
	Doctor doc = new Doctor("Pirogov", 40, "Main doctor", "Surgery");
	String expect = "Doctor Pirogov heals Ivan\n";
	ByteArrayOutputStream out = new ByteArrayOutputStream();
	System.setOut(new PrintStream(out));
	doc.heal(pacient);
	assertThat(out.toString(), is(expect));

    }
}
