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
        String url;
        String login;
        String pass;

        Properties properties = new Properties();

        try(InputStream inputStream = new FileInputStream("src/main/resources/config.properties")) {

            properties.load(inputStream);
            url = properties.getProperty("host");
            login = properties.getProperty("login");
            pass = properties.getProperty("pass");

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Connection connection;
        try {
            connection = DriverManager.getConnection (url, login, pass);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    return connection;

    }





}
