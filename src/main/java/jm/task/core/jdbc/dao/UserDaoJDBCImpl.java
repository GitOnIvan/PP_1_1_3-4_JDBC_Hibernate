package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import static jm.task.core.jdbc.util.Util.getConnection;


public class UserDaoJDBCImpl implements UserDao  {
    public final static String CREATE = "CREATE TABLE IF NOT EXISTS user " +
            "(id BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL, name VARCHAR(45), lastname VARCHAR(45), age TINYINT);";

    public final static String DROP = "DROP TABLE IF EXISTS user;";
    public final static String CLEAR = "DELETE FROM user;";
    public final static String ADD = "INSERT INTO user (name, lastname, age) VALUES (?, ?, ?);";
    public final static String REMOVE = "DELETE FROM user WHERE id=?;";
    public final static String GETALL = "SELECT * FROM user;";
    Connection connection = getConnection();


    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (PreparedStatement statement = connection.prepareStatement(CREATE)) {
            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void dropUsersTable() {

        try (PreparedStatement statement = connection.prepareStatement(DROP)) {
            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void saveUser(String name, String lastName, byte age) {

        try (PreparedStatement preparedStatement = connection.prepareStatement(ADD)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2,lastName);
            preparedStatement.setByte(3,age);
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("User с именем – " + name +" добавлен в базу данных");




    }

    public void removeUserById(long id) {


        try (PreparedStatement preparedStatement = connection.prepareStatement(REMOVE)) {
            preparedStatement.setLong(1, id);
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public List<User> getAllUsers() {
        List<User> usersList = new ArrayList<>();



        try (PreparedStatement statement = connection.prepareStatement(GETALL)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastname"));
                user.setAge(resultSet.getByte("age"));

                usersList.add(user);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(usersList);

        return usersList;
    }

    public void cleanUsersTable() {

        try (PreparedStatement statement = connection.prepareStatement(CLEAR)) {
            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
