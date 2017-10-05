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
 *
 * @author Wamdue
 * @version 1.0
 */
public class XmlXsd {
    private final int entries;
    private final String first = "1.xml";
    private final String second = "2.xml";
    private final String xsltStyle = "style.xsl";
    private final String dbName = "test.db";

    public XmlXsd(int entries) {
        this.entries = entries;
        this.init();
        this.convertXmlToXslt();
        System.out.println(this.calculateSum());
    }

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

    private void createFile(File file) {
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void createTable(Statement st) throws SQLException {
        st.execute("CREATE TABLE IF NOT EXISTS TEST (field integer);");
    }

    private void insertVaules(Statement st, int n) throws SQLException {
        for (int i = 0; i < n; i++) {
            st.addBatch("INSERT INTO TEST (field) VALUES (1);");
        }
        st.executeBatch();
    }

    private void writetoXmlFile(ResultSet set) {
        XMLOutputFactory factory = XMLOutputFactory.newInstance();
        XMLStreamWriter writer = null;
        try {
            writer = factory.createXMLStreamWriter(new FileWriter(new File(first)));
            writer.writeStartDocument();
            writer.writeStartElement("entries");
            while(set.next()) {
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
        }finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (XMLStreamException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void convertXmlToXslt() {
        try {
            Transformer transformer = TransformerFactory.newInstance().newTransformer(new StreamSource(xsltStyle));
            transformer.transform(new StreamSource(first), new StreamResult(second));
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

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
