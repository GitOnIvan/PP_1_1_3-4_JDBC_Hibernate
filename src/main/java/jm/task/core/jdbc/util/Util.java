package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Util {
    private final static String URL1 = "jdbc:mysql://localhost:3306/pp_db";
    private final static String LOGIN = "root";
    private final static String PASS = "root";


    public static Connection getConnection() {

        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL1, LOGIN,PASS);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;

    }







}
