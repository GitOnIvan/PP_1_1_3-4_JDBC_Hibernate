package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;


public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
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
