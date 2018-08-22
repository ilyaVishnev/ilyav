import java.io.*;
import java.sql.*;


public class Tracker implements AutoCloseable {
    Connection connection;
    int[] values = new int[15];

    public Tracker(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void close() throws Exception {
        connection.close();
    }

    public static void main(String[] args) {

        CreateFile createFile = new CreateFile();
        try (Tracker tracker = new Tracker(createFile.getConnection())) {
            tracker.fullDB();

        } catch (Exception ex) {
            ex.getMessage();
        }
    }

    public void fullDB() {
        for (int index = 0; index < 15; index++) {
            values[index] = index + 1;
        }
        try {
            ResultSet resultSet = connection.getMetaData().getTables(null, null, "test", null);
            if (!resultSet.next()) {
                PreparedStatement preparedStatement = connection.prepareStatement("CREATE TABLE TEST(field integer)");
                preparedStatement.execute();
            }
            this.insertValue();

        } catch (SQLException ex) {
            ex.getMessage();
        }

    }

    public void insertValue() {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into TEST(FIELD) values(?)");
            for (int value : values) {
                preparedStatement.setInt(1, value);
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
        } catch (SQLException ex) {
            ex.getMessage();
        }
    }


}

class CreateFile {

    File file = new File("C:\\online_shop\\dataBase.xml");
    String conDates = "jdbc:postgresql://localhost:5432/from_one_to_n postgres pobeda";

    public File creating() {
        try {
            PrintWriter printWriter = new PrintWriter(file);
            printWriter.print(conDates);
            printWriter.close();
        } catch (FileNotFoundException ex) {
            ex.getMessage();
        }
        return file;
    }

    public Connection getConnection() {
        Connection connection = null;
        int index = 0;
        String[] connect = {"", "", ""};
        int c = 0;
        try {
            FileReader reader = new FileReader(this.creating());
            while ((c = reader.read()) != -1) {
                if (((char) c) != ' ') {
                    connect[index] += (char) c;
                } else {
                    index++;
                }
            }
            connection = DriverManager.getConnection(connect[0], connect[1], connect[2]);
        } catch (Exception ex) {
            ex.getMessage();
        }
        return connection;
    }
}
