package ru.job4j.add;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created on 12.02.18.
 * Testing cmd.
 * @author Wamdue
 * @version 1.0
 */
public class ShellTest {
    /**
     * When creating object.
     * Expect root linux path.
     */
    private Entry entry;

    /**
     * Fill temp entry.
     */
    {
        Entry e = new Entry();
        e.setName("/");
        e.addEntry(new Entry("var"));
        Entry usr = new Entry("usr");
        Entry lib = new Entry("lib");
        Entry local = new Entry("/local");
        usr.addEntry(local);
        e.addEntry(usr);
        e.addEntry(lib);
        this.entry = e;
    }

    /**
     * Testing root entry.
     */
    @Test
    public void whenCreatingObjectThenReturnRootDirectory() {
        Shell shell = new Shell(this.entry);
        String expect = "/";
        assertThat(shell.path(), is(expect));
    }

    /**
     * When changing directories.
     * Changing to: /var.
     * Expect: path changed.
     */
    @Test
    public void whenEnteringToDirectoryThenChangePath() {
        Shell shell = new Shell(this.entry);
        shell.cd("var");
        String expect = "/var";
        assertThat(shell.path(), is(expect));
    }

    /**
     * When going deeper to directories.
     * Changing to: /usr/local
     * Expect: path changed.
     */
    @Test
    public void whenGoingDeeperThenChangePathToIt() {
        Shell shell = new Shell(this.entry);
        shell.cd("usr").cd("local");
        shell.cd("../local").cd("./");
        String expect = "/usr/local";
        assertThat(shell.path(), is(expect));
    }

    /**
     * When chang directory, and then move to the parent directory.
     * Changing to: /usr/local then move to parent.
     * Expect: path will change to /usr.
     */
    @Test
    public void whenGoingToParenDirectoryThenChangePathToIt() {
        Shell shell = new Shell(this.entry);
        shell.cd("usr").cd("local");
        shell.cd("..");
        String expect = "/usr";
        assertThat(shell.path(), is(expect));
    }

    /**
     * When enter a look like path.
     * Changing to: //lib///.
     * Expect: change path.
     */
    @Test
    public void whenHaveAnotherMaskThenChangePath() {
        Shell shell = new Shell(this.entry);
        shell.cd("//lib///");
        String expect = "/lib";
        assertThat(shell.path(), is(expect));
    }

    /**
     * When enter incorrect path.
     * Changing to: usr/..
     * Expect: path not changed.
     */
    @Test
    public void whenHaveIncorrectMaskThenDontChangePath() {
        Shell shell = new Shell(this.entry);
        shell.cd("usr/..");
        assertThat(shell.path(), is("/"));
    }

}