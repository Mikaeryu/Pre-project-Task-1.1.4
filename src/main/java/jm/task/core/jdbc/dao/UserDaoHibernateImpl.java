package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    //SessionFactory создаётся единственный раз
    private static final SessionFactory SESSION_FACTORY = Util.getSessionFactory();

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
        Session session = SESSION_FACTORY.openSession();
        session.createSQLQuery(sqlQuery).executeUpdate();
        session.close();
    }
}
