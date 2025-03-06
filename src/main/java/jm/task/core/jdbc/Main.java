package jm.task.core.jdbc;



import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args)  {
        UserService UserService = new UserServiceImpl();

        try {
            UserService.createUsersTable();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        UserService.saveUser("Name1", "LastName1", (byte) 11);
        UserService.saveUser("Name2", "LastName2", (byte) 22);
        UserService.saveUser("Name3", "LastName3", (byte) 33);
        UserService.saveUser("Name4", "LastName4", (byte) 44);

        UserService.removeUserById(1);
        UserService.getAllUsers();
        UserService.cleanUsersTable();
        UserService.dropUsersTable();
    }
}