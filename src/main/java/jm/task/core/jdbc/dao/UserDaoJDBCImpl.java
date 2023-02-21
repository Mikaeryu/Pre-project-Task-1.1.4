package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private static final String tableName = "users";

    public UserDaoJDBCImpl() {

    }

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
        String dropTableSQL = "DROP TABLE " + tableName;

        try (Connection connection = Util.getConnection(); Statement statement = connection.createStatement()) {
            if (!tableExists(connection)) {
                System.out.println("\"" + tableName + "\" table doesn't exists.");
                return; //если таблица не существует, метод прерывается, попытка создания таблицы не происходит
            }

            statement.executeUpdate(dropTableSQL);
        } catch (SQLException s) {
            s.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        if (age < 0) {
            System.out.println("User's age cannot be negative number!");
            return;
        }

        String saveSQL = String.format(
                "INSERT INTO " + tableName + " (name, lastName, age) values ('%s', '%s', %s) ", name, lastName, age
        );

        executeUpdateForSQL(saveSQL);
    }

    @Override
    public void removeUserById(long id) {
        String deleteUserByIdSQL = "DELETE FROM " + tableName + " WHERE id=" + id;

        executeUpdateForSQL(deleteUserByIdSQL);
    }

    @Override
    public List<User> getAllUsers() {
        return null;
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
        try (Connection connection = Util.getConnection(); Statement statement = connection.createStatement()) {
            statement.executeUpdate(sqlStatement);
        } catch (SQLException s) {
            s.printStackTrace();
        }
    }

    /**
     * Метод для проверки существования таблицы в Базе Данных.
     * @param connection текущее соединение
     * @return true, если таблица существует, или false, если таблица не существует
     */
    private boolean tableExists(Connection connection) {
        try {
            DatabaseMetaData meta = connection.getMetaData();
            ResultSet resultSet = meta.getTables(null, null, tableName, new String[] {"TABLE"});
            return resultSet.next();
        } catch (SQLException s) {
            s.printStackTrace();
        }

        return false;
    }
}
