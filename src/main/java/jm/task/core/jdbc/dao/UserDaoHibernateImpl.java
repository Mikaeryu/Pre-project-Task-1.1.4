package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

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
        Transaction transaction = session.beginTransaction();

        User user = new User(name, lastName, age);
        session.save(user);

        transaction.commit();
        session.close();

        System.out.println("User с именем " + name + " добавлен в базу данных.");
    }

    @Override
    public void removeUserById(long id) {
        String removeUserByIdSQL = SQLQueries.removeUserById(id);
        executeUpdateViaSQL(removeUserByIdSQL);
    }

    @SuppressWarnings("unchecked assignment: 'java.util.List' to 'java.util.List<jm.task.core.jdbc.model.User>")
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
