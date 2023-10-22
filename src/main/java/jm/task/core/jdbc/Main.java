package jm.task.core.jdbc;


import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();

        userService.saveUser("Garry","Kasparov",(byte)60 );
        userService.saveUser("Magnus","Carlsen",(byte)32 );
        userService.saveUser("Vladimir","Kramnik",(byte)48 );
        userService.saveUser("Anatoly","Karpov",(byte)72 );


        userService.getAllUsers();

        userService.cleanUsersTable();

        userService.dropUsersTable();


    }
}
