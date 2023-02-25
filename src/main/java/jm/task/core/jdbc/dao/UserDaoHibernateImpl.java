package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
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

        String saveUserSQL = SQLQueries.saveUser(name, lastName, age);

        Query saveUserQuery = session.createSQLQuery(saveUserSQL);
        saveUserQuery.executeUpdate();
        System.out.println("User с именем " + name + " добавлен в базу данных.");

        session.close();
    }

    @Override
    public void removeUserById(long id) {
        String removeUserByIdSQL = SQLQueries.removeUserById(id);

        executeUpdateViaSQL(removeUserByIdSQL);
    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }

    @Override
    public void cleanUsersTable() {
        executeUpdateViaSQL(SQLQueries.cleanUsersTable());
    }

    private void executeUpdateViaSQL(String sqlQuery) {
        Session session = SESSION_FACTORY.openSession();
        session.createSQLQuery(sqlQuery).executeUpdate();
        session.close();
    }
}
