package jm.task.core.jdbc.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static String URL; // = "jdbc:mysql://localhost:3306/pp_db";
    private static String USERNAME; // = "root";
    private static String PASSWORD; // = "Kris07102023";

    public static Connection getCon() {





        Connection connection = null;
        try {
            connection = DriverManager.getConnection (URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    return connection;

    }





}
