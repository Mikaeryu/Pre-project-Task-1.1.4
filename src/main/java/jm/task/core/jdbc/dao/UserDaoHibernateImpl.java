package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    private static final String TABLE_NAME = "users_db";

    @Override
    public void createUsersTable() {
        String createTableSQL =
                "CREATE TABLE IF NOT EXISTS " + TABLE_NAME
                        + "("
                        + "id BIGINT NOT NULL AUTO_INCREMENT,"
                        + "name VARCHAR(100) NOT NULL,"
                        + "lastName VARCHAR(100) NOT NULL, "
                        + "age TINYINT NOT NULL,"
                        + "PRIMARY KEY (id)"
                        + ")";


        Session session = null;
        try {
            session = Util.getSession();
            session.createSQLQuery(createTableSQL).executeUpdate();
        } catch (HibernateException he) {
            throw he;
        } finally {
            if (session != null) session.close();
        }

    }

    @Override
    public void dropUsersTable() {

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {

    }

    @Override
    public void removeUserById(long id) {

    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }

    @Override
    public void cleanUsersTable() {

    }
}
