import javax.swing.text.Document;

import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.rmi.runtime.Log;

import javax.swing.text.Document;
import javax.swing.text.Element;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.text.DateFormat;
import java.util.*;
import java.util.Date;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

public class Parser {


    public static final String PATH_TO_PROPERTIES = "chapter_002/src/main/resources/app.properties";
    private static final Logger Log = LoggerFactory.getLogger(Parser.class);
    ThrowinDB throwinDB = new ThrowinDB();
    Properties properties = this.fullProperties();

    public static void main(String[] args) throws SchedulerException {
        Parser parser = new Parser();
        JobDetail job = newJob(MyJob.class)
                .withIdentity("myJob", "group1")
                .build();
        Trigger trigger = newTrigger()
                .withIdentity("myTrigger", "group1")
                .startNow()
                .withSchedule(cronSchedule(parser.properties.getProperty("cron.time")))
                .build();
        Scheduler scheduler = new StdSchedulerFactory().getScheduler();
        scheduler.start();
        scheduler.scheduleJob(job, trigger);
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
                    throwinDB.execute(col.text());
                }
            }

        } catch (IOException ex) {
            ex.getMessage();
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
        boolean first = day > currentDate.getDay() && month > currentDate.getMonth() && Integer.parseInt(year) == currentDate.getYear();
        if (first && hours > currentDate.getHours() && min > currentDate.getMinutes()) {
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

    public Properties fullProperties() {
        Properties properties = new Properties();
        FileInputStream fileInputStream;
        try {
            fileInputStream = new FileInputStream(PATH_TO_PROPERTIES);
            properties.load(fileInputStream);

            throwinDB.setUrl(properties.getProperty("jdbc.url"));
            throwinDB.setUsername(properties.getProperty("jdbc.username"));
            throwinDB.setPassword(properties.getProperty("jdbc.password"));
        } catch (IOException ex) {
            ex.getMessage();
        }
        return properties;
    }
}

