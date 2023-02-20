package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД

    static Connection connection;

    public static Connection getConnection() {
        String dbURL = "jdbc:mysql://127.0.0.1:3306/users?useSSL=false";
        String userName = "root";
        String password = "Ch3ck4dGR4Y!";

        try {
            connection = DriverManager.getConnection(dbURL, userName, password);
            if (connection == null) {
                System.out.println("There is no connection with Database");
                System.exit(0);
            }
            System.out.println("Connected");
            return connection;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        return null;
    }

    public static void closeConnection() {
        try {
            connection.close();
        } catch (SQLException s) {
            s.printStackTrace();
        }
    }
}
