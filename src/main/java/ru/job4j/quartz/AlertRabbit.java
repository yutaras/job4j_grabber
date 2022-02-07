package ru.job4j.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import static org.quartz.JobBuilder.*;
import static org.quartz.TriggerBuilder.*;
import static org.quartz.SimpleScheduleBuilder.*;

public class AlertRabbit {

    private static Properties properties = new Properties();
    private static Connection connection;

    private static Connection initConnection(Properties properties) throws ClassNotFoundException, SQLException {
        Class.forName(properties.getProperty("driver_class"));
        String url = properties.getProperty("url");
        String login = properties.getProperty("login");
        String password = properties.getProperty("password");
        connection = DriverManager.getConnection(url, login, password);
        return connection;
    }

    public static Properties readProperties(String string) {
        try (FileInputStream in = new FileInputStream(string)) {
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Properties pr = readProperties("src/main/resources/rabbit.properties");
        try {
            initConnection(pr);
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            scheduler.start();
            JobDataMap data = new JobDataMap();
            data.put("connection", connection);
            JobDetail job = newJob(Rabbit.class)
                    .usingJobData(data)
                    .build();
            SimpleScheduleBuilder times = simpleSchedule()
                    .withIntervalInSeconds(Integer.parseInt(pr.getProperty("time")))
                    .repeatForever();
            Trigger trigger = newTrigger()
                    .startNow()
                    .withSchedule(times)
                    .build();
            scheduler.scheduleJob(job, trigger);
            Thread.sleep(10000);
            scheduler.shutdown();
        } catch (SchedulerException | InterruptedException se) {
            se.printStackTrace();
        }
    }

    public static class Rabbit implements Job {
        @Override
        public void execute(JobExecutionContext context) throws JobExecutionException {
            System.out.println("Rabbit runs here ...");
            Connection cn = (Connection) (context.getJobDetail().getJobDataMap().get("connection"));
            try (PreparedStatement statement =
                         cn.prepareStatement("insert into rabbit(created_date) values (?)")) {
                statement.setString(1, String.valueOf(System.currentTimeMillis()));
                statement.execute();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
