package ru.job4j.profession;
import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;
/**
* Teacher information test.
*/
public class TeacherTest {
    /**
    * Expect = Makarenko teaching Petrov\n.
    */
    @Test
    public void whenHasStudentThenTeach() {
	Student student = new Student("Petrov");
	Teacher teacher = new Teacher("Makarenko", 40, "Main teacher", "initial class");
	String expect = "Makarenko teaching Petrov\n";
	ByteArrayOutputStream out = new ByteArrayOutputStream();
	System.setOut(new PrintStream(out));
	teacher.teach(student);
	assertThat(out.toString(), is(expect));

    }
}
