package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;

public class Util {
    // реализуйте настройку соеденения с БД

    public static Connection getConnection() throws SQLException{
        Connection connection = null;

        String dbURL = "jdbc:mysql://localhost:3306/users?useSSL=false";
        String userName = "root";
        String password = "Ch3ck4dGR4Y!";

        connection = DriverManager.getConnection(dbURL, userName, password);

        return Objects.requireNonNull(connection, "There is no connection with Database");
    }
}
