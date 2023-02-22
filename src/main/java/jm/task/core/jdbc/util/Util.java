package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД

    public static Connection getConnection() {
        Connection connection;

        String dbURL = "jdbc:mysql://127.0.0.1:3306/users?useSSL=false";
        String userName = "root";
        String password = "Ch3ck4dGR4Y!";

        try {
            connection = DriverManager.getConnection(dbURL, userName, password);

            if (connection == null) {
                System.out.println("There is no connection with Database");
            } //Shoud change this if???

            return connection; // maybe delete this return line in try block and use one return at the bottom?
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        return null; // change return here to requireNonNull return and delete requireNonNull options in Impl class
    }
}
