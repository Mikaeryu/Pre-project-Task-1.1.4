package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserDaoJDBCImpl userDaoJDBC = new UserDaoJDBCImpl();

        userDaoJDBC.dropUsersTable();
        userDaoJDBC.createUsersTable();
        userDaoJDBC.saveUser("Joshua", "Graham", (byte) 33);
        userDaoJDBC.saveUser("Andrei", "Tarkovsky", (byte) 45);
        userDaoJDBC.saveUser("Hideo", "Kojima", (byte) 59);
        userDaoJDBC.saveUser("Vasya", "Pupkin", (byte) 0);

        List<User> userList = userDaoJDBC.getAllUsers();
        System.out.println(userList);
        userDaoJDBC.cleanUsersTable();
        userDaoJDBC.dropUsersTable();


    }
}
