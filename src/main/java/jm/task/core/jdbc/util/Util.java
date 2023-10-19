package jm.task.core.jdbc.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {


    public static Connection getCon() {
        String URL = null;
        String USERNAME = null;
        String PASSWORD = null;

        Properties properties = new Properties();
        try {
            InputStream inputStream = new FileInputStream("src/main/resources/config.properties");
            properties.load(inputStream);
            URL = properties.getProperty("db.host");
            USERNAME = properties.getProperty("db.login");
            PASSWORD = properties.getProperty("db.password");

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        Connection connection = null;
        try {
            connection = DriverManager.getConnection (URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    return connection;

    }





}
