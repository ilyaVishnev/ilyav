
import org.jdom.Document;
import org.jdom.input.SAXBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.xml.bind.Element;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;


public class SqlDatabase {


    public static void main(String[] args) {
   /*     MyConnection myConnection = new MyConnection();
        try {
            myConnection.creatingOfTable();
            myConnection.createXml();
            myConnection.converting();
            System.out.print(myConnection.getSum());

        } catch (Exception ex) {
            ex.getMessage();
        }*/
   Date date=new Date();
   System.out.print(date.getMinutes());
    }

}


class MyConnection {
    private static final Logger Log = LoggerFactory.getLogger(SqlDatabase.class);
    private Connection connection;
    private String url = "jdbc:postgresql://localhost:5432/from_one_to_n";
    private String username = "postgres";
    private String password = "pobeda";
    private int n = 12;
    File first = new File("C:\\online_shop\\1.xml");
    File second = new File("C:\\online_shop\\2.xml");
    File style = new File("C:\\online_shop\\style.xml");
    private int sum = 0;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void creatingOfTable() throws SQLException {
        connection = DriverManager.getConnection(this.getUrl(), this.getUsername(), this.getPassword());
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            resultSet = connection.getMetaData().getTables(null, null, "TEST", null);
            if (resultSet.next()) {
                preparedStatement = connection.prepareStatement("TRUNCATE TABLE TEST");
                preparedStatement.executeUpdate();
            } else {
                preparedStatement = connection.prepareStatement("CREATE TABLE TEST(field integer)");
                preparedStatement.execute();
            }

            this.fillingOfTable();
        } catch (Exception ex) {
            ex.getMessage();
        }


    }

    public void fillingOfTable() throws SQLException {
        connection.setAutoCommit(false);
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("insert into TEST(FIELD) values(?)");
            for (int i = 1; i <= n; i++) {
                preparedStatement.setInt(1, i);
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException ex) {
            Log.error(ex.getMessage(), ex);
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
    }

    public void createXml() throws FileNotFoundException {
        ResultSet resultSet = null;

        try {
            connection.setAutoCommit(false);
            resultSet = connection.createStatement().executeQuery("select * from TEST");
            StringBuilder xml = new StringBuilder();
            xml.append("<entries>").append(System.getProperty("line.separator"));
            while (resultSet.next()) {
                xml.append(" <entry>").append(System.getProperty("line.separator"));
                xml.append("  <field>").append(resultSet.getInt(1)).append("</field>").append(System.getProperty("line.separator"));
                xml.append("</entry>").append(System.getProperty("line.separator"));
            }
            xml.append("</entries>");
            PrintWriter printWriter = new PrintWriter(first);
            printWriter.print(xml.toString());
            printWriter.close();
        } catch (SQLException ex) {
            Log.error(ex.getMessage(), ex);
            try {
                connection.rollback();
            } catch (SQLException e) {
                Log.error(e.getMessage(), e);
            }

        } finally {
            try {
                resultSet.close();
            } catch (SQLException ex) {
                Log.error(ex.getMessage(), ex);
            }
        }
    }

    public void converting() {
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Source xslt = new StreamSource(style);
            Transformer transformer = transformerFactory.newTransformer(xslt);
            Source origin = new StreamSource(this.first);
            transformer.transform(origin, new StreamResult(this.second));
        } catch (TransformerException ex) {
            Log.error(ex.getMessage(), ex);
        }
    }

    public int getSum() {
        SAXBuilder saxBuilder = new SAXBuilder();
        try {
            Document document = saxBuilder.build(new FileInputStream(second));
            org.jdom.Element rootElement = document.getRootElement();
            List<org.jdom.Element> elements = rootElement.getChildren("entry");
            for (org.jdom.Element element : elements) {
                sum += Integer.parseInt(element.getAttributeValue("field"));
            }

        } catch (Exception ex) {
            Log.error(ex.getMessage(), ex);
        }
        return sum;
    }
}