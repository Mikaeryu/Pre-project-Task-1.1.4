package jm.task.core.jdbc.dao;


/**
 * Класс, возращающий Строки в виде SQL-запросов
 */
public class SQLQueries {
    private static final String TABLE_NAME = "users_db";

    private static final String CREATE_USERS_TABLE =
            "CREATE TABLE IF NOT EXISTS " + TABLE_NAME
            + "("
            + "id BIGINT NOT NULL AUTO_INCREMENT,"
            + "name VARCHAR(100) NOT NULL,"
            + "lastName VARCHAR(100) NOT NULL, "
            + "age TINYINT NOT NULL,"
            + "PRIMARY KEY (id)"
            + ")";
    private static final String DROP_USERS_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    private static final String SAVE_USER = "INSERT INTO " + TABLE_NAME + " (name, lastName, age) values ('%s', '%s', %s) ";
    private static final String REMOVE_USER_BY_ID = "DELETE FROM " + TABLE_NAME + " WHERE id=";
    private static final String SELECT_USER_QUERY = "SELECT id, name, lastName, age FROM " + TABLE_NAME;
    private static final String CLEAN_TABLE = "TRUNCATE TABLE " + TABLE_NAME;

    public static String createUsersTable() {
        return CREATE_USERS_TABLE;
    }

    public static String dropUsersTable() {
        return DROP_USERS_TABLE;
    }

    public static String saveUser(String name, String lastName, byte age) {
        return String.format(SAVE_USER, name, lastName, age);
    }

    public static String removeUserById(long id) {
        return REMOVE_USER_BY_ID + id;
    }

    public static String selectUserQuery() {
        return SELECT_USER_QUERY;
    }

    public static String cleanUsersTable() {
        return CLEAN_TABLE;
    }
}
