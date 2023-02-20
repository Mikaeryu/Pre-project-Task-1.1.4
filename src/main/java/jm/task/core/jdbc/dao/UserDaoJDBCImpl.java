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
                + "name VARCHAR(50), "
                + "lastName VARCHAR(50), "
                + "age TINYINT"
                + ")";

        try (Connection connection = Util.getConnection(); Statement statement = connection.createStatement()) {
            if (userTableExists(connection, tableName)) {
                System.out.println("\"" + tableName + "\" table already exists.");
                return;
            }

            statement.executeUpdate(createTableSQL);
            System.out.println("Created \"" + tableName + "\" table in given database.");
        } catch (SQLException s) {
            s.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {

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

    private boolean userTableExists(Connection connection, String tableName) {
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
