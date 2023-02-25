package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    @Override
    public void createUsersTable() {
        executeUpdateForSQL(SQLQueries.createUsersTable());
    }

    @Override
    public void dropUsersTable() {
        executeUpdateForSQL(SQLQueries.dropUsersTable());
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        String saveUserSQL = SQLQueries.saveUser(name, lastName, age);

        executeUpdateForSQL(saveUserSQL);
    }

    @Override
    public void removeUserById(long id) {
        String removeUserByIdSQL = SQLQueries.removeUserById(id);

        executeUpdateForSQL(removeUserByIdSQL);
    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }

    @Override
    public void cleanUsersTable() {
        executeUpdateForSQL(SQLQueries.cleanUsersTable());
    }

    private void executeUpdateForSQL(String sqlQuery) {
        SessionFactory sessionFactory = Util.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.createSQLQuery(sqlQuery).executeUpdate();
        session.close();
    }
}
