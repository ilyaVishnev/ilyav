package SQLLite;

import org.sqlite.JDBC;

import java.io.File;
import java.sql.*;
import java.util.*;
import java.util.logging.Logger;

public class StoreSQL {
    private static Logger log = Logger.getLogger(StoreSQL.class.getName());
    private final String driverName = "org.sqlite.JDBC";
    private final String connectionString = "jdbc:sqlite:sample.db";
    private static File second = new File("./2.xml");
    private static File style = new File("./style.xml");


    public Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(driverName);
            connection = DriverManager.getConnection(connectionString);
        } catch (Exception ex) {
            log.info(ex.getMessage());
        }
        return connection;
    }

    public void generate(int n) {
        PreparedStatement preparedStatement = null;
        try (Connection connection = this.getConnection();) {
            ResultSet resultSet = connection.getMetaData().getTables(null, null, "TEST", null);
            if (resultSet.next()) {
                preparedStatement = connection.prepareStatement("TRUNCATE TABLE TEST");
                preparedStatement.executeUpdate();
            } else {
                preparedStatement = connection.prepareStatement("CREATE TABLE TEST(field integer)");
                preparedStatement.execute();
            }

            this.fillingOfTable(n, connection);
        } catch (Exception ex) {
            ex.getMessage();
        }
    }

    public void fillingOfTable(int n, Connection connection) throws SQLException {
        connection.setAutoCommit(false);
        try (PreparedStatement preparedStatement = connection.prepareStatement("insert into TEST(FIELD) values(?)");) {
            for (int i = 1; i <= n; i++) {
                preparedStatement.setInt(1, i);
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException ex) {
            log.info(ex.getMessage());
        }
    }

    public List<Field> getListFields() {
        List<Field> fields = new ArrayList<>();
        try (Connection connection = this.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from TEST");
             ResultSet resultSet = preparedStatement.executeQuery();
        ) {
            while (resultSet.next()) {
                fields.add(new Field(resultSet.getInt(1)));
            }
        } catch (Exception ex) {
            log.info(ex.getMessage());
        }
        return fields;
    }

    public static void main(String[] args) {
        StoreSQL storeSQL = new StoreSQL();
        storeSQL.generate(10);
        File file = new File("./1.xml");
        StoreXML storeXML = new StoreXML();
        storeXML.setTarget(file);
        Entries entries = new Entries();
        storeXML.save(entries.getEntry());
        ConvertXSQT convertXSQT = new ConvertXSQT();
        convertXSQT.convert(file, second, style);
        System.out.print(convertXSQT.getSum(second));
    }
}
