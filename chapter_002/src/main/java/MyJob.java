import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.spi.JobStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.Date;

public class MyJob implements Job {

    private static final Logger Log = LoggerFactory.getLogger(MyJob.class);

    public MyJob() {
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        Parser parser = new Parser();
        Date date;
        if (jobExecutionContext.getPreviousFireTime() != null) {
            date = jobExecutionContext.getPreviousFireTime();
        } else {
            date = new Date(2000 + new Date().getYear() - 100, 0, 1);
        }
        parser.readPage(date);
    }
}
