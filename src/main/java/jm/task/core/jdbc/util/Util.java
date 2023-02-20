package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД

    public static void setConnection() {
        String dbURL = "jdbc:mysql://127.0.0.1:3306/users?useSSL=false";
        String userName = "root";
        String password = "Ch3ck4dGR4Y!";

        try (Connection conn = DriverManager.getConnection(dbURL, userName, password)) {
            if (conn == null) {
                System.out.println("There is no connection with Database");
                System.exit(0);
            }
            System.out.println("Connected");
//            Statement stmt = conn.createStatement();
//            ResultSet rs = stmt.executeQuery("SELECT * FROM users");
//
//            while (rs.next()) {
//                System.out.println(rs.getRow() + ". " + rs.getString("firstname")
//                        + "\t" + rs.getString("lastname"));
//            }
//
//            /**
//             * stmt.close();
//             * При закрытии Statement автоматически закрываются
//             * все связанные с ним открытые объекты ResultSet
//             */
//            stmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
