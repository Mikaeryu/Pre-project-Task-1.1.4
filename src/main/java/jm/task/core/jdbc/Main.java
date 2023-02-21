package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.util.Util;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserDaoJDBCImpl userDaoJDBC = new UserDaoJDBCImpl();
        userDaoJDBC.dropUsersTable();
        userDaoJDBC.createUsersTable();


        userDaoJDBC.saveUser("John", "Wick", (byte) 34);
        userDaoJDBC.saveUser("Cass", "Jog", (byte) 18);
        userDaoJDBC.saveUser("Fog", "Log", (byte) 11);
        userDaoJDBC.saveUser("Val", "Kick", (byte) 28);

        userDaoJDBC.removeUserById(2L);
        userDaoJDBC.cleanUsersTable();
    }
}
