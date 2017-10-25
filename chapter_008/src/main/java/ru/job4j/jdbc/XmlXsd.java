package ru.job4j.jdbc;

import javax.xml.stream.*;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;
import java.sql.*;


/**
 * Created on 04.10.17
 * Xml xslt
 * @author Wamdue
 * @version 1.0
 */
public class XmlXsd {
    /**
     * count of rows.
     */
    private final int entries;
    /**
     * name of the first file.
     */
    private final String first = "1.xml";
    /**
     * name of the second file, xslt.
     */
    private final String second = "2.xml";
    /**
     * Style, to convert from xml to xslt.
     */
    private final String xsltStyle = "style.xsl";
    /**
     * db file name.
     */
    private final String dbName = "test.db";

    /**
     * main constructor
     * @param entries - count of rows to create.
     */
    public XmlXsd(int entries) {
        this.entries = entries;
        this.init();
        this.convertXmlToXslt();
        System.out.println(this.calculateSum());
    }

    /**
     * Init method, connection to db and working with data.
     */
    private void init() {
        boolean isExist = true;
        File file = new File(dbName);

        if (!file.exists()) {
            this.createFile(file);
            isExist = false;
        }
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        ResultSet set = null;

        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:" + file.getAbsolutePath());
             Statement st = conn.createStatement()) {
            this.createTable(st);
            st.executeUpdate("DELETE FROM test");
            conn.setAutoCommit(false);
            this.insertVaules(st, this.entries);
            conn.commit();
            set = st.executeQuery("SELECT field FROM test");
            this.writetoXmlFile(set);
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (set != null) {
                    set.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Create file if not exist.
     * @param file - file name.
     */
    private void createFile(File file) {
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Create table if not created.
     * @param st - statement of the connection.
     * @throws SQLException
     */
    private void createTable(Statement st) throws SQLException {
        st.execute("CREATE TABLE IF NOT EXISTS TEST (field integer);");
    }

    /**
     * Fills table with default data.
     * @param st - statement of the connection.
     * @param n - count of row.
     * @throws SQLException
     */
    private void insertVaules(Statement st, int n) throws SQLException {
        for (int i = 0; i < n; i++) {
            st.addBatch("INSERT INTO TEST (field) VALUES (1);");
        }
        st.executeBatch();
    }

    /**
     * Wfite data to xml.
     * @param set - result of the select.
     */
    private void writetoXmlFile(ResultSet set) {
        XMLOutputFactory factory = XMLOutputFactory.newInstance();
        XMLStreamWriter writer = null;
        try {
            writer = factory.createXMLStreamWriter(new FileWriter(new File(first)));
            writer.writeStartDocument();
            writer.writeStartElement("entries");
            while (set.next()) {
                writer.writeStartElement("entry");
                writer.writeStartElement("field");
                writer.writeCharacters(String.valueOf(set.getInt("field")));
                writer.writeEndElement();
                writer.writeEndElement();
            }
            writer.writeEndElement();
            writer.writeEndDocument();
            writer.flush();
            writer.close();
        } catch (XMLStreamException | IOException | SQLException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (XMLStreamException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Convertion from xml to xml xslt
     */
    private void convertXmlToXslt() {
        try {
            Transformer transformer = TransformerFactory.newInstance().newTransformer(new StreamSource(xsltStyle));
            transformer.transform(new StreamSource(first), new StreamResult(second));
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    /**
     * Calculating sum from xslt file.
     * @return - sum.
     */
    private int calculateSum() {
        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLStreamReader reader = null;
        int event;
        int result = 0;
        String value;

        try {
            reader = factory.createXMLStreamReader(new FileReader(new File(second)));
            while (reader.hasNext()) {
                event = reader.next();
                if (event == XMLStreamConstants.START_ELEMENT) {
                    value = reader.getAttributeValue(0);
                    if (value != null) {
                        result += Integer.valueOf(value);
                    }
                }
            }
        } catch (XMLStreamException | FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (XMLStreamException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        new XmlXsd(1_000_000);
    }
}
