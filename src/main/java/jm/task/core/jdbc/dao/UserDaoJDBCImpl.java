package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.exceptions.ExecuteSQLException;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
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
        if (age < 0) {
            System.err.println("User's age cannot be negative number!");
            return;
        }

        executeUpdateViaSQL(SQLQueries.saveUser(name, lastName, age));
        System.out.println("User с именем " + name + " добавлен в базу данных.");
    }

    @Override
    public void removeUserById(long id) {
        executeUpdateViaSQL(SQLQueries.removeUserById(id));
    }

    @Override
    public List<User> getAllUsers() {
        final String query = SQLQueries.selectUserQuery();
        List<User> userList = new ArrayList<>();

        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)
        ) {
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String lastName = resultSet.getString("lastName");
                byte age = resultSet.getByte("age");
                long id = resultSet.getLong("id");

                User user = new User(name, lastName, age);
                user.setId(id);

                userList.add(user);
            }
        } catch (SQLException se) {
            throw new ExecuteSQLException();
        }
        return userList;
    }

    @Override
    public void cleanUsersTable() {
        executeUpdateViaSQL(SQLQueries.cleanUsersTable());
    }

    /**
     * Метод, устанавливающий соединение с БД и выполняющий SQL комманду.
     * @param sqlStatement SQL команда.
     */
    private void executeUpdateViaSQL(String sqlStatement) {
        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()
        ) {
            statement.executeUpdate(sqlStatement);
        } catch (SQLException se) {
            String message = String.format("Error Code = %s; SQL state = %s; Message = %s;",
                    se.getErrorCode(), se.getSQLState(), se.getMessage());

            throw new ExecuteSQLException(message);
        }
    }
}
