package jm.task.core.jdbc.util;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД
    private Util() {}

    public static Connection getConnection() throws SQLException{
        final String DB_URL = "jdbc:mysql://localhost:3306/users_db?useSSL=false";
        final String USER_NAME = "root";
        final String PASSWORD = "Ch3ck4dGR4Y!";

        return DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
    }

    public static Session getSession() throws HibernateException {
        return getSessionFactory().openSession();
    }

    private static SessionFactory getSessionFactory() throws HibernateException {
        SessionFactory sessionFactory;
        ServiceRegistry serviceRegistry;

        Configuration configuration = new Configuration();
        configuration.configure();

        serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties())
                .build();

        sessionFactory = configuration.buildSessionFactory(serviceRegistry);

        return sessionFactory;
    }
}
