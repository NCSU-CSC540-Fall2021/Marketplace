package server.dao;

import server.entity.Customer;
import server.utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CustomerDao {
    public static String TABLENAME = "Customer";
    public Connection connection;

    public String createCustomer(Customer customer) throws SQLException {
        connection = DatabaseConnection.createDatabaseConnection();

        String sqlQuery = "Insert into " + TABLENAME + "(cname, address, phone_no, username, createdBy, createdAt, updatedBy) values (?,?,?,?,?,?,?)";

        PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
        preparedStatement.setString(1, customer.getCname());
        preparedStatement.setString(2, customer.getAddress());
        preparedStatement.setString(3, customer.getPhone_no());
        preparedStatement.setString(4, customer.getUserName());
        preparedStatement.setInt(5, customer.getCreatedBy());
        preparedStatement.setDate(6, new Date (customer.getCreatedAt().getTime()));
        preparedStatement.setInt(7, customer.getUpdatedBy());

        String response = "";
        try {
            int i = preparedStatement.executeUpdate();
            if (i > 0) {
                response = "Customer created successfully";
            }
        } catch (SQLException exception) {
            response = exception.getMessage();
            exception.printStackTrace();
        }

        DatabaseConnection.closeDatabaseConnection(connection);
        return response;

    }
}
