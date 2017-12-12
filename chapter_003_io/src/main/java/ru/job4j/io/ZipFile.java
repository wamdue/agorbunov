package ru.job4j.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Created on 12.12.17.
 * Simple archivator.
 * @author Wamdue
 * @version 1.0
 */
public class ZipFile {
    /**
     * List of extension, to add in archive.
     */
    private final List<String> ext;
    /**
     * List of files to pack.
     */
    private final List<File> files = new ArrayList<>();

    /**
     * Archive name.
     */
    private final File archive;

    /**
     * Main constructor.
     * @param path - directory to pack.
     * @param ext - extensions of files to add.
     * @param archive - name of archive.
     */
    public ZipFile(String path, List<String> ext, File archive) {
        this.archive = archive;
        this.ext = ext;
        this.getListOvFiles(path);
    }

    /**
     * Write file to OutputStream.
     * @param in - source stream.
     * @param out - target stream
     * @throws IOException - exception.
     */
    private void write(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        int len;
        while ((len = in.read(buffer)) >= 0) {
            out.write(buffer, 0, len);
        }
    }

    /**
     * Fill tree of files.
     * @param dir - start directory.
     */
    private void getListOvFiles(String dir) {
        File f = new File(dir);
        if (f.listFiles() != null) {
            for (File file : f.listFiles()) {
                if (file.isDirectory()) {
                    this.getListOvFiles(file.getAbsolutePath());
                } else {
                    String name = f.getName();
                    if (name.contains(".")) {
                        String e = name.substring(name.lastIndexOf(".") + 1, name.length());
                        if (this.ext.contains(e)) {
                            this.files.add(file);
                        }
                    }
                }
            }
        }
    }
    /**
     * Packing method.
     */
    public void pack() {
        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(archive))) {
            for (File file : files) {
                ZipEntry entry = new ZipEntry(file.getAbsolutePath());
                zout.putNextEntry(entry);
                this.write(new FileInputStream(file), zout);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Main method, to start archivation.
     * @param args - in user 6 params:
     * 0 - -d
     * 1 - directory to archivate.
     * 2 - -e
     * 3 - list of extensions, split by ','
     * 4 - -o
     * 5 - archive name.
     */
    public static void main(String[] args) {
        if (args.length == 6) {
            String directory = null;
            String[] ext = null;
            String archive = null;
            if (args[0].equals("-d")) {
                directory = args[1];
            }

            if (args[2].equals("-e")) {
                ext = args[3].split(",");
            }

            if (args[4].equals("-o")) {
                archive = args[5];
            }

            if (directory != null && ext != null && archive != null) {
                ZipFile zip = new ZipFile(directory, Arrays.asList(ext), new File(archive));
                zip.pack();
            }
        } else {
            System.out.println("Need to fill 6 params.");
            System.out.println("Example:");
            System.out.println("-d <directory to archivate> -e <list of extensions, split by ','> -o <archive name>");
        }
    }

}
