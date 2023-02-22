package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private static final String TABLE_NAME = "users";

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

        executeUpdateForSQL(createTableSQL);
    }

    @Override
    public void dropUsersTable() {
        String dropTableSQL = "DROP TABLE IF EXISTS " + TABLE_NAME;

        executeUpdateForSQL(dropTableSQL);
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        if (age < 0) {
            System.out.println("User's age cannot be negative number!");
            return;
        }

        String saveSQL = String.format(
                "INSERT INTO " + TABLE_NAME + " (name, lastName, age) values ('%s', '%s', %s) ",
                name, lastName, age
        );

        executeUpdateForSQL(saveSQL);
        System.out.println("User с именем " + name + " добавлен в базу данных.");
    }

    @Override
    public void removeUserById(long id) {
        String deleteUserByIdSQL = "DELETE FROM " + TABLE_NAME + " WHERE id=" + id;

        executeUpdateForSQL(deleteUserByIdSQL);
    }

    @Override
    public List<User> getAllUsers() {
        final String query = "SELECT id, name, lastName, age FROM " + TABLE_NAME;
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
            throw new SQLExceptionUnchecked();
        }
        return userList;
    }

    @Override
    public void cleanUsersTable() {
        String cleanTableSQL = "TRUNCATE TABLE " + TABLE_NAME;

        executeUpdateForSQL(cleanTableSQL);
    }

    /**
     * Метод, устанавливающий соединение с БД и выполняющий SQL комманду.
     * @param sqlStatement SQL команда.
     */
    private void executeUpdateForSQL(String sqlStatement) {
        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()
        ) {
            statement.executeUpdate(sqlStatement);
        } catch (SQLException se) {
            String message = String.format("Error Code = %s; SQL state = %s; Message = %s;",
                    se.getErrorCode(), se.getSQLState(), se.getMessage());

            throw new SQLExceptionUnchecked(message);
        }
    }


    static private class SQLExceptionUnchecked extends RuntimeException {
        public SQLExceptionUnchecked() {

        }

        public SQLExceptionUnchecked(String message) {
            super(message);
        }
    }
}
