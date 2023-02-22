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

    private static final String tableName = "users";

    @Override
    public void createUsersTable() {
        String createTableSQL =
                "CREATE TABLE IF NOT EXISTS " + tableName
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
        String dropTableSQL = "DROP TABLE IF EXISTS " + tableName;

        executeUpdateForSQL(dropTableSQL);
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        if (age < 0) {
            System.out.println("User's age cannot be negative number!");
            return;
        }

        String saveSQL = String.format(
                "INSERT INTO " + tableName + " (name, lastName, age) values ('%s', '%s', %s) ",
                name, lastName, age
        );

        executeUpdateForSQL(saveSQL);
        System.out.println("User с именем " + name + " добавлен в базу данных.");
    }

    @Override
    public void removeUserById(long id) {
        String deleteUserByIdSQL = "DELETE FROM " + tableName + " WHERE id=" + id;

        executeUpdateForSQL(deleteUserByIdSQL);
    }

    @Override
    public List<User> getAllUsers() {
        final String query = "SELECT id, name, lastName, age FROM " + tableName;
        List<User> userList = new ArrayList<>();

        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)
        ) {
            while (resultSet.next()) {
                User user = new User(
                        resultSet.getString("name"), resultSet.getString("lastName"), resultSet.getByte("age")
                );
                user.setId(resultSet.getLong("id"));
                userList.add(user);
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }
        return userList;
    }

    @Override
    public void cleanUsersTable() {
        String cleanTableSQL = "TRUNCATE TABLE " + tableName;

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
            System.out.println("Error Code = " + se.getErrorCode());
            System.out.println("SQL state = " + se.getSQLState());
            System.out.println("Message = " + se.getMessage());
            //se.printStackTrace();
            throw new RuntimeException(se.getMessage());
            //вот тут не особо уверен, нормально ли так обрабатывать исключение?надо же что-то прокинуть, но не checked исключение же
        }
    }
}
