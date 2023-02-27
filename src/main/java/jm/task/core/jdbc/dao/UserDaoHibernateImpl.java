package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.*;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    //SessionFactory создаётся единственный раз
    private static final SessionFactory SESSION_FACTORY = Util.getSessionFactory();

    @Override
    public void createUsersTable() {
        executeUpdateViaSQL(SQLQueries.createUsersTable());
    }

    @Override
    public void dropUsersTable() {
        executeUpdateViaSQL(SQLQueries.dropUsersTable());
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = SESSION_FACTORY.openSession();
        User user = new User(name, lastName, age);
        session.save(user);
        session.close();
    }

    @Override
    public void removeUserById(long id) {
        String removeUserByIdSQL = SQLQueries.removeUserById(id);

        executeUpdateViaSQL(removeUserByIdSQL);
    }

    @Override
    public List<User> getAllUsers() {
        Session session = SESSION_FACTORY.openSession();

        Query query = session.createQuery("from jm.task.core.jdbc.model.User");
        List<User> users = query.list();
        session.close();

        return users;
    }

    @Override
    public void cleanUsersTable() {
        executeUpdateViaSQL(SQLQueries.cleanUsersTable());
    }

    private void executeUpdateViaSQL(String sqlQuery) {
        Session session = SESSION_FACTORY.openSession();
        Transaction transaction = session.beginTransaction();
        session.createSQLQuery(sqlQuery).executeUpdate();
        transaction.commit();
        session.close();
    }
}
