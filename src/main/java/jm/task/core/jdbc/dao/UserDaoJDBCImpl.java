package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import static jm.task.core.jdbc.util.Util.getConnection;

public class UserDaoJDBCImpl implements UserDao  {
    Connection connection = getConnection();


    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS user (id BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL, name VARCHAR(45), lastname VARCHAR(45), age TINYINT);";
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void dropUsersTable() {
        String sql = "DROP TABLE IF EXISTS user;";
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void saveUser(String name, String lastName, byte age) {
        String sql = "INSERT INTO user (name, lastname, age) VALUES (?, ?, ?);";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
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

        String sql = "DELETE FROM user WHERE id=?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public List<User> getAllUsers() {
       List<User> usersList = new ArrayList<>();

       String sql = "SELECT * FROM user;";

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
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
        String sql = "DELETE FROM user;";
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
