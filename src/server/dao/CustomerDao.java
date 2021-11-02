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

        String sqlQuery = "Insert into " + TABLENAME + "(cname, address, phone_no, createdBy, createdAt, updatedBy) values (?,?,?,?,?,?)";

        PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
        preparedStatement.setString(1, customer.getCname());
        preparedStatement.setString(2, customer.getAddress());
        preparedStatement.setString(3, customer.getPhone_no());
        preparedStatement.setInt(4, customer.getCreatedBy());
        preparedStatement.setDate(5, new Date (customer.getCreatedAt().getTime()));
        preparedStatement.setInt(6, customer.getUpdatedBy());

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
