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

    static String tableName = "users";

    public UserDaoJDBCImpl() {

    }

    @Override
    public void createUsersTable() {
        String createTableSQL =
                "CREATE TABLE " + tableName
                + "("
                + "id BIGINT,"
                + "name VARCHAR(100), "
                + "lastName VARCHAR(100), "
                + "age TINYINT"
                + ")";

        try (Connection connection = Util.getConnection(); Statement statement = connection.createStatement()) {
            System.out.println("Trying to create \"" + tableName + "\" table.");
            if (tableExists(connection, tableName)) {
                System.out.println("\"" + tableName + "\" table already exists.");
                return; //если таблица существует, метод прерывается, попытка создания таблицы не происходит
            }

            statement.executeUpdate(createTableSQL);
            System.out.println("Created \"" + tableName + "\" table in given database.");
        } catch (SQLException s) {
            s.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        String dropTableSQL = "DROP TABLE " + tableName;

        try (Connection connection = Util.getConnection(); Statement statement = connection.createStatement()) {
            System.out.println("Trying to drop \"" + tableName + "\" table.");
            if (!tableExists(connection, tableName)) {
                System.out.println("\"" + tableName + "\" table doesn't exists.");
                return;
            }

            statement.executeUpdate(dropTableSQL);
            System.out.println("Dropped \"" + tableName + "\" table from database.");
        } catch (SQLException s) {
            s.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {

    }

    @Override
    public void removeUserById(long id) {

    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }

    @Override
    public void cleanUsersTable() {

    }

    /**
     * Метод для проверки существования таблицы в Базе Данных.
     * @param connection текущее соединение
     * @param tableName название таблицы
     * @return true, если таблица существует, или false, если таблица не существует
     */
    private boolean tableExists(Connection connection, String tableName) {
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
