package jm.task.core.jdbc.util;

import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import jm.task.core.jdbc.model.User;




public class Util {
    private final static String URL1 = "jdbc:mysql://localhost:3306/pp_db";
    private final static String URL2 = "jdbc:mysql://localhost:3306/pp_db?autoReconnect=true&useSSL=false";
    private final static String LOGIN = "root";
    private final static String PASS = "root";
    private static String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static String DIALECT = "org.hibernate.dialect.MySQL5Dialect";
    private static String SHOW_SQL = "true";
    private static String SESSION_CLASS = "thread";
    private static String HBM2DDL_AUTO = "create";
    private static SessionFactory sessionFactory = null;


    // jdbc connection
    public static Connection getConnection() {

        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL1, LOGIN,PASS);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;

    }



    //Hibernate connection


    public static SessionFactory getSessionFactory() {



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
