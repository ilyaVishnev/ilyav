import javax.swing.text.Document;

import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.rmi.runtime.Log;

import javax.swing.text.Document;
import javax.swing.text.Element;

import java.io.IOException;
import java.sql.*;
import java.text.DateFormat;
import java.util.*;
import java.util.Date;

public class Parser {

    ThrowinDB throwinDB = new ThrowinDB();
    Set<String> jobs = new HashSet<>();

    public static void main(String[] args) {
        Timer timer = new Timer();
        Date myDate = new Date();
        MyTimerTask myTimerTask = new MyTimerTask();
        timer.schedule(myTimerTask, myDate, 1000 * 60 * 60 * 24);

    }

    public void readPage(Date currentdate) {
        try {
            org.jsoup.nodes.Document document = Jsoup.connect("http://www.sql.ru/forum/job").get();
            org.jsoup.nodes.Element table = document.select("table.forumTable").first();
            Elements rows = table.select("tr");

            for (int i = 1; i < rows.size(); i++) {
                org.jsoup.nodes.Element row = rows.get(i);
                Elements cols = row.select("td");
                org.jsoup.nodes.Element col = cols.get(1);
                org.jsoup.nodes.Element date = cols.get(5);
                if (this.compareDate(date.text().toString(), currentdate) && this.readCol(col.text().toString())) {
                    jobs.add(col.text());
                }
            }

        } catch (IOException ex) {
            ex.getMessage();
        }
    }


    public void insertDB() {
        for (String job : jobs) {
            throwinDB.execute(job);
        }
    }

    public boolean readCol(String text) {
        char[] textArray = text.toLowerCase().toCharArray();
        char[] java = {'j', 'a', 'v', 'a'};
        char[] script = {'s', 'c', 'r', 'i', 'p', 't'};
        int indexJava = 0;
        int indexScript = 0;
        boolean javaWord = false;
        boolean result = false;
        for (int index = 0; index < textArray.length; index++) {
            if (javaWord) {
                if (textArray[index] == ' ' && index != textArray.length - 1) {
                    continue;
                }
                if (textArray[index] == script[indexScript]) {
                    indexScript++;
                    if (indexScript == script.length) {
                        javaWord = false;
                        indexJava = 0;
                        indexScript = 0;
                    }
                } else {
                    return result = true;
                }
            }
            if (textArray[index] == java[indexJava]) {
                indexJava++;
                if (indexJava == java.length) {
                    javaWord = true;
                    indexJava = 0;
                }
            } else {
                indexJava = 0;
            }

        }
        return result;
    }

    public boolean compareDate(String date, Date currentDate) {
        String year = this.getYear(date);
        Integer hours = this.getHours(date);
        Integer min = this.getMin(date);
        if (year.equals("сегодня")) {
            return true;
        } else if (year.equals("вчера")) {
            if (hours > currentDate.getHours() && min > currentDate.getMinutes()) {
                return true;
            } else {
                return false;
            }
        }
        Integer month = this.getMonth(date);
        Integer day = this.getDay(date);
        if (day > currentDate.getDay() && month > currentDate.getMonth() && Integer.parseInt(year) == currentDate.getYear() && hours > currentDate.getHours() && min > currentDate.getMinutes()) {
            return true;
        } else {
            return false;
        }
    }

    public String getYear(String date) {
        char[] arrayDate = date.toCharArray();
        boolean getYear = false;
        String year = "";
        for (int index = arrayDate.length - 1; index >= 0; index--) {
            if (getYear) {
                if (arrayDate[index] == ' ') {
                    break;
                }
                year += arrayDate[index];
            }
            if (arrayDate[index] == ',') {
                getYear = true;
            }
        }
        try {
            Integer.parseInt(year);
        } catch (NumberFormatException e) {
            StringBuffer stringBuffer = new StringBuffer(year);
            return stringBuffer.reverse().toString();

        }
        StringBuffer stringBuffer = new StringBuffer(year);
        return "20" + stringBuffer.reverse().toString();
    }

    public Integer getMonth(String date) {
        char[] arrayDate = date.toCharArray();
        boolean getMonth = false;
        String month = "";
        int monthInt = -1;
        for (int index = 0; index < arrayDate.length - 1 && month.toCharArray().length < 3; index++) {
            if (getMonth) {
                month += arrayDate[index];
            }
            if (!getMonth && arrayDate[index] == ' ') {
                getMonth = true;
            }
        }
        switch (month) {
            case "янв":
                monthInt = 0;
                break;
            case "фев":
                monthInt = 1;
                break;
            case "мар":
                monthInt = 2;
                break;
            case "апр":
                monthInt = 3;
                break;
            case "май":
                monthInt = 4;
                break;
            case "июн":
                monthInt = 5;
                break;
            case "июл":
                monthInt = 6;
                break;
            case "авг":
                monthInt = 7;
                break;
            case "сен":
                monthInt = 8;
                break;
            case "окт":
                monthInt = 9;
                break;
            case "ноя":
                monthInt = 10;
                break;
            case "дек":
                monthInt = 11;
                break;

        }
        return monthInt;
    }

    public Integer getDay(String date) {
        char[] arrayDate = date.toCharArray();
        String dateS = "";
        for (int index = 0; index < arrayDate.length - 1; index++) {
            if (arrayDate[index] == ' ') {
                break;
            }
            dateS += arrayDate[index];
        }

        return Integer.parseInt(dateS);
    }

    public Integer getHours(String date) {
        char[] arrayHours = date.toCharArray();
        String hours = "";
        boolean getHours = false;
        for (int index = arrayHours.length - 1; index >= 0 && hours.toCharArray().length < 2; index--) {
            if (getHours) {
                hours += arrayHours[index];
            }
            if (arrayHours[index] == ':') {
                getHours = true;
            }
        }
        StringBuffer stringBuffer = new StringBuffer(hours);
        return Integer.parseInt(stringBuffer.reverse().toString());
    }

    public Integer getMin(String date) {
        char[] arrayMin = date.toCharArray();
        String min = "";
        for (int index = arrayMin.length - 1; index >= 0 && min.toCharArray().length < 2; index--) {
            min += arrayMin[index];
        }
        StringBuffer stringBuffer = new StringBuffer(min);
        return Integer.parseInt(stringBuffer.reverse().toString());
    }

}

class ThrowinDB {
    private static final Logger Log = LoggerFactory.getLogger(ThrowinDB.class);
    private Connection connection;
    private String url = "jdbc:postgresql://localhost:5432/from_one_to_n";
    private String username = "postgres";
    private String password = "pobeda";
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    public void execute(String text) {

        try {
            connection = DriverManager.getConnection(url, username, password);
            resultSet = connection.getMetaData().getTables(null, null, "job", null);
            if (!resultSet.next()) {
                preparedStatement = connection.prepareStatement("CREATE TABLE Job(field text)");
                preparedStatement.execute();
            }
            this.fillingOfTable(text);

        } catch (SQLException ex) {
            Log.error(ex.getMessage(), ex);
        }
    }

    public void fillingOfTable(String text) {
        try {
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement("insert into Job(FIELD) values(?)");
            preparedStatement.setString(1, text);
            preparedStatement.addBatch();
            preparedStatement.executeBatch();
            connection.commit();
            preparedStatement.close();
            connection.setAutoCommit(true);


        } catch (SQLException ex) {
            Log.error(ex.getMessage(), ex);
        }

    }
}

class MyTimerTask extends TimerTask {
    private Date date = new Date(2018, 0, 1);
    static Date newDate;
    private static final Logger Log = LoggerFactory.getLogger(MyTimerTask.class);


    @Override
    public void run() {
        Connection connection;
        ResultSet resultSet;
        boolean firstStep = false;
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/from_one_to_n", "postgres", "pobeda");
            resultSet = connection.getMetaData().getTables(null, null, "job", null);
            if (resultSet.next()) {
                firstStep = true;
            }
        } catch (Exception ex) {
            Log.error(ex.getMessage(), ex);
        }
        Parser parser = new Parser();
        if (firstStep) {
            date = newDate;
        }
        parser.readPage(date);
        parser.insertDB();
        newDate = new Date();
    }
}
