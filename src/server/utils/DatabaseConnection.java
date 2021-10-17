package server.utils;

import server.constants.DatabaseCredentials;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {

    public static Connection createDatabaseConnection() {
        String jdbcURL = DatabaseCredentials.getJdbcURL();
        String userName = DatabaseCredentials.getUser();
        String password = DatabaseCredentials.getPassword();
        String driverString = DatabaseCredentials.getDriverString();
        Connection connection = null;
        try {
            Class.forName(driverString);
            connection = DriverManager.getConnection(jdbcURL, userName, password);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void closeDatabaseConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (Throwable whatever) {
            }
        }
    }
}
