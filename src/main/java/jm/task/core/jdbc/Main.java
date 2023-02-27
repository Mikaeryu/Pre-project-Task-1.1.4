package jm.task.core.jdbc;

import java.util.List;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;


public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserServiceImpl userService = new UserServiceImpl();

        userService.createUsersTable();

        userService.saveUser("Joshua", "Graham", (byte) 33);
        userService.saveUser("Andrei", "Tarkovsky", (byte) 45);
        userService.saveUser("Hideo", "Kojima", (byte) 59);
        userService.saveUser("Vasya", "Pupkin", (byte) 0);

        List<User> userList = userService.getAllUsers();
        userList.forEach(System.out::println);

        userService.cleanUsersTable();
        userService.dropUsersTable();

        Util.closeSessionFactory();
    }
}
