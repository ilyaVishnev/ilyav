import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

class ThrowinDB {
    private static final Logger Log = LoggerFactory.getLogger(ThrowinDB.class);
    private Connection connection;
    private String url;
    private String username;
    private String password;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    public void execute(String text) {

        try {
            connection = DriverManager.getConnection(url, username, password);
            resultSet = connection.getMetaData().getTables(null, null, "job", null);
            if (!resultSet.next()) {
                preparedStatement = connection.prepareStatement("CREATE TABLE Job(id SERIAL,field text)");
                preparedStatement.execute();
            }
            this.fillingOfTable(text);

        } catch (SQLException ex) {
            Log.error(ex.getMessage(), ex);
        }
    }

    public void fillingOfTable(String text) {
        try {
            preparedStatement = connection.prepareStatement("insert into Job(FIELD) values(?)");
            preparedStatement.setString(1, text);
            preparedStatement.execute();
            preparedStatement.close();
            connection.close();
        } catch (SQLException ex) {
            Log.error(ex.getMessage(), ex);
        }

    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public String getUrl() {
        return url;
    }

    public String getUsername() {
        return username;
    }
}
