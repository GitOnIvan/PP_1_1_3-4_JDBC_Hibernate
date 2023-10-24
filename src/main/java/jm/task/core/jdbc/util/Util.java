package jm.task.core.jdbc.util;

import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import jm.task.core.jdbc.model.User;




public class Util {
    private static String URL1;
    private static String URL2;
    private static String LOGIN;
    private static String PASS;
    private static String DRIVER;
    private static String DIALECT;
    private static String SHOW_SQL;
    private static String SESSION_CLASS;
    private static String HBM2DDL_AUTO;
    private static SessionFactory sessionFactory;


    // jdbc connection
    public static Connection getConnection() {

        Properties properties = new Properties();

        try(InputStream inputStream = new FileInputStream("src/main/resources/config.properties")) {
            properties.load(inputStream);
            URL1 = properties.getProperty("URL1");
            LOGIN = properties.getProperty("LOGIN");
            PASS = properties.getProperty("PASS");


        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Connection connection;
        try {
            connection = DriverManager.getConnection (URL1, LOGIN, PASS);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    return connection;

    }



    //Hibernate connection


    public static SessionFactory getSessionFactory() {

        Properties properties = new Properties();

        try(InputStream inputStream = new FileInputStream("src/main/resources/config.properties")) {
            properties.load(inputStream);

            URL2 = properties.getProperty("URL2");
            LOGIN = properties.getProperty("LOGIN");
            DRIVER = properties.getProperty("DRIVER");
            DIALECT = properties.getProperty("DIALECT");
            SHOW_SQL = properties.getProperty("SHOW_SQL");
            SESSION_CLASS = properties.getProperty("SESSION_CLASS");
            HBM2DDL_AUTO = properties.getProperty("HBM2DDL_AUTO");
            PASS = properties.getProperty("PASS");


        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();


                Properties settings = new Properties();
                settings.put(Environment.DRIVER, DRIVER);
                settings.put(Environment.URL, URL2);
                settings.put(Environment.USER, LOGIN);
                settings.put(Environment.PASS, PASS);
                settings.put(Environment.DIALECT, DIALECT);

                settings.put(Environment.SHOW_SQL, SHOW_SQL);

                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, SESSION_CLASS);

                settings.put(Environment.HBM2DDL_AUTO, HBM2DDL_AUTO);

                configuration.setProperties(settings);

                configuration.addAnnotatedClass(User.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }









}
