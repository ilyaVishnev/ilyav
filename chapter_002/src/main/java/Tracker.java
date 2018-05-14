import java.io.*;
import java.sql.*;


public class Tracker implements AutoCloseable {
    private int[] values = new int[15];
    Connection connection = null;
    File file;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    public Tracker(File file) {
        this.file = file;
    }

    @Override
    public void close() throws Exception {

    }

    public static void main(String[] args) {

        CreateFile createFile = new CreateFile();
        try (Tracker tracker = new Tracker(createFile.creating())) {
            tracker.fullDB();

        } catch (Exception ex) {
            ex.getMessage();
        }
    }

    public void fullDB() {
        for (int index = 0; index < 15; index++) {
            values[index] = index + 1;
        }
        this.getConnection();
        try {
            resultSet = connection.getMetaData().getTables(null, null, "test", null);
            if (!resultSet.next()) {
                preparedStatement = connection.prepareStatement("CREATE TABLE TEST(field integer)");
                preparedStatement.execute();
            }
            this.insertValue();

        } catch (SQLException ex) {
            ex.getMessage();
        } finally {
            try {
                resultSet.close();
                connection.close();
            } catch (Exception ex) {
                ex.getMessage();
            }
        }

    }

    public void insertValue() {
        try {
            preparedStatement = connection.prepareStatement("insert into TEST(FIELD) values(?)");
            for (int value : values) {
                preparedStatement.setInt(1, value);
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
        } catch (SQLException ex) {
            ex.getMessage();
        }
    }

    public void getConnection() {
        int index = 0;
        String[] connect = {"", "", ""};
        int c = 0;
        try {
            FileReader reader = new FileReader(file);
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
}
