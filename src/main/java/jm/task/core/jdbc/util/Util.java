package jm.task.core.jdbc.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД
    private Util() {}

    private static SessionFactory factory;
    private static ServiceRegistry serviceRegistry;

    static final String DB_URL = "jdbc:mysql://localhost:3306/users_db?useSSL=false";
    static final String USER_NAME = "root";
    static final String PASSWORD = "Ch3ck4dGR4Y!";

    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
    }

    public static Session getSession() {
        Configuration configuration = new Configuration();
        configuration.configure();

        return null;
    }
}
