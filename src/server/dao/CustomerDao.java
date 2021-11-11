package server.dao;

import server.entity.Customer;
import server.utils.DatabaseConnection;

import java.sql.*;

public class CustomerDao {
    public static String TABLENAME = "customer";
    public Connection connection;

    public String createCustomer(Customer customer) throws SQLException {
        String response = "";
        try {
            connection = DatabaseConnection.createDatabaseConnection();

            String sqlQuery = "Insert into " + TABLENAME + "(customer_name, address, phone_no, username, createdBy, createdAt, updatedBy) values (?,?,?,?,?,?,?)";

            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, customer.getCustomerName());
            preparedStatement.setString(2, customer.getAddress());
            preparedStatement.setString(3, customer.getPhone_no());
            preparedStatement.setString(4, customer.getUserName());
            preparedStatement.setString(5, customer.getCreatedBy());
            preparedStatement.setDate(6, new Date(customer.getCreatedAt().getTime()));
            preparedStatement.setString(7, customer.getUpdatedBy());

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
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return response;
    }

    public Customer findCustomerInfoByUserName(Customer customer) throws SQLException {
        try {
            connection = DatabaseConnection.createDatabaseConnection();
            String sqlQuery = "Select * from " + TABLENAME + " where username = ? ";

            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, customer.getUserName());

            try {
                ResultSet resultSet = preparedStatement.executeQuery();
                if (!resultSet.isBeforeFirst())
                    throw new SQLException("No data found");

                while (resultSet.next()) {
                    customer.setId(resultSet.getInt(1));
                    customer.setCustomerName(resultSet.getString(2));
                    customer.setAddress(resultSet.getString(3));
                    customer.setPhone_no(resultSet.getString(4));
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            DatabaseConnection.closeDatabaseConnection(connection);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return customer;
    }
}
