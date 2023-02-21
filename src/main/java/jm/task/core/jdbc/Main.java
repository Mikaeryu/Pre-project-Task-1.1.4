package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.util.Util;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserDaoJDBCImpl userDaoJDBC = new UserDaoJDBCImpl();
        userDaoJDBC.dropUsersTable();
        userDaoJDBC.createUsersTable();


        userDaoJDBC.saveUser("John", "Wick", (byte) 3);
        userDaoJDBC.saveUser("Cass", "Jog", (byte) 1);
        userDaoJDBC.saveUser("Fog", "Log", (byte) 1);
        userDaoJDBC.saveUser("Val", "Kick", (byte) 2);
    }
}
