package server.dao;

import server.entity.User;
import server.utils.DatabaseConnection;

import java.sql.*;

public class UserDao {
    public static String TABLENAME = "Users";
    public Connection connection;

    public String createUser(User user) throws SQLException {
        String response = "";
        try {

            connection = DatabaseConnection.createDatabaseConnection();

            String sqlQuery = "Insert into " + TABLENAME + "(username, password, role) values (?,?,?)";

            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, user.getUserName());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getRole());

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
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return response;
    }

    public User fetchUser(User user) throws SQLException {
        connection = DatabaseConnection.createDatabaseConnection();

        String sqlQuery = "Select *  from " + TABLENAME + " where username = ? and password = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);

        preparedStatement.setString(1, user.getUserName());
        preparedStatement.setString(2, user.getPassword());

        try {
            ResultSet resultSet = preparedStatement.executeQuery();
            if(!resultSet.isBeforeFirst())
                throw new SQLException("No data found! Please check your credentials");

            while(resultSet.next()) {
                user.setRole(resultSet.getString(3)); // todo : change according to table structure
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        DatabaseConnection.closeDatabaseConnection(connection);
        return user;
    }
}
