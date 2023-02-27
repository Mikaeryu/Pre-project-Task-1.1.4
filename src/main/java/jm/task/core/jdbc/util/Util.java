package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;


public class Util {
    // реализуйте настройку соеденения с БД
    private Util() {
    }

    private static final String DB_URL = "jdbc:mysql://localhost:3306/users_db?useSSL=false";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "Ch3ck4dGR4Y!";

    private static SessionFactory sessionFactory;

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
    }

    public static SessionFactory getSessionFactory() throws HibernateException {
        Configuration configuration = new Configuration();
        configuration.setProperties(getPropertiesForConfiguration())
                .addResource("User.hbm.xml");

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties())
                .build();

        sessionFactory = configuration.buildSessionFactory(serviceRegistry);

        return sessionFactory;
    }

    public static void closeSessionFactory() {
        sessionFactory.close();
    }

    private static Properties getPropertiesForConfiguration() {
        Properties p = new Properties();

        p.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        p.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
        p.setProperty("hibernate.connection.url", DB_URL);
        p.setProperty("hibernate.connection.username", USER_NAME);
        p.setProperty("hibernate.connection.password", PASSWORD);

        return p;
    }
}
