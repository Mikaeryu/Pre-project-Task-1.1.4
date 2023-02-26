package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.*;
import java.math.BigInteger;
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
        String saveUserSQL = SQLQueries.saveUser(name, lastName, age);
        executeUpdateViaSQL(saveUserSQL);
        System.out.println("User с именем " + name + " добавлен в базу данных.");

        //        User user = new User(name, lastName, age);
        //        session.save(user);
    }

    @Override
    public void removeUserById(long id) {
        String removeUserByIdSQL = SQLQueries.removeUserById(id);

        executeUpdateViaSQL(removeUserByIdSQL);
    }

    @Override
    public List<User> getAllUsers() {
        Session session = SESSION_FACTORY.openSession();
        Transaction transaction = session.beginTransaction();

        Query tableResults = session.createSQLQuery(SQLQueries.selectUserQuery());

        List<Object[]> results = tableResults.list();
        final List<User> userList= new ArrayList<>();
        results.forEach(result -> {
            long id = ((BigInteger) result[0]).longValue();
            String name = (String) result[1];
            String lastName = (String) result[2];
            byte age = (byte) result[3];

            User user = new User(name, lastName, age);
            user.setId(id);
            userList.add(user);
        });

        transaction.commit();
        session.close();

        return userList;
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
