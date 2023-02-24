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

    static final String DB_URL = "jdbc:mysql://localhost:3306/users_db?useSSL=false";
    static final String USER_NAME = "root";
    static final String PASSWORD = "Ch3ck4dGR4Y!";

    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
    }

    public Session getSession() throws HibernateException {
        return getSessionFactory().openSession();
    }

    private SessionFactory getSessionFactory() throws HibernateException {
        SessionFactory sessionFactory;
        ServiceRegistry serviceRegistry;

        Configuration configuration = new Configuration();
        configuration.configure("src/main/java/hibernate.cfg.xml");

        serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties())
                .build();

        sessionFactory = configuration.buildSessionFactory(serviceRegistry);

        return sessionFactory;
    }
}
