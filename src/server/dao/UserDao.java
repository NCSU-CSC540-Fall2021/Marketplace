package server.dao;

import server.entity.Customer;
import server.entity.User;
import server.utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDao {
    public static String TABLENAME = "User";
    public Connection connection;

    public String createUser(User user) throws SQLException {
        connection = DatabaseConnection.createDatabaseConnection();

        String sqlQuery = "Insert into " + TABLENAME + "(username, password, role) values (?,?,?)";

        PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
        preparedStatement.setString(1, user.getUserName());
        preparedStatement.setString(2, user.getPassword());
        preparedStatement.setString(3, user.getRole());

        String response = "";
        try {
            int i = preparedStatement.executeUpdate();
            if (i > 0) {
                response = "User created successfully";
            }
        } catch (SQLException exception) {
            response = exception.getMessage();
            exception.printStackTrace();
        }

        DatabaseConnection.closeDatabaseConnection(connection);
        return response;

    }
}
