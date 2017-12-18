package ru.job4j.fin;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created on 18.12.17.
 * Testing search file util.
 * @author Wamdue
 * @version 1.0
 */
public class SearchFilesTest {
    /**
     * Creating temp file, to search.
     */
    private final File file = File.createTempFile("test", "end");
    /**
     * Link to result file.
     */
    private final File result = new File("test.txt");

    /**
     * Add exeption to constructor.
     * @throws IOException - exception.
     */
    public SearchFilesTest() throws IOException {
    }

    /**
     * When searching by full name, with key -f.
     * Then expect: true.
     */
    @Test
    public void whenSearchingByFullNameThenFindIt() {
        this.testCase(this.file.getName(), "-f", true);
        this.result.delete();
    }

    /**
     * When searching by part of name with *, with key -m.
     * Then expect true.
     */
    @Test
    public void whenSearchingByPartOfNameThenFindIt() {
        this.testCase("test*", "-m", true);
        this.result.delete();
    }

    /**
     * When searching by regular expression, with key -r.
     * Then expect true.
     */
    @Test
    public void whenSearchingByRegExpThenFindIt() {
        this.testCase("test.*end$", "-r", true);
        this.result.delete();
    }

    /**
     * When searching unknown file, with key -f.
     * Then expect false.
     */
    @Test
    public void whenNoFilesFoundThenFalse() {
        this.testCase("black_mamba.dll", "-f", false);
        this.result.delete();
    }

    /**
     * Main construct method.
     * @param search - search file.
     * @param filter - filter type.
     * @param expect - expectation of result.
     */
    private void testCase(String search, String filter, boolean expect) {
        String dir = System.getProperty("java.io.tmpdir");
        SearchFiles files = new SearchFiles(dir, search, filter, "test.txt");
        files.start();
        assertThat(this.result.length() > 0, is(expect));
    }

}