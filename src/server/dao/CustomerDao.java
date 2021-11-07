package server.dao;

import server.entity.Customer;
import server.utils.DatabaseConnection;

import java.sql.*;

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
        preparedStatement.setString(5, customer.getCreatedBy());
        preparedStatement.setDate(6, new Date (customer.getCreatedAt().getTime()));
        preparedStatement.setString(7, customer.getUpdatedBy());

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

    public Customer fetchCustomerByUserName(String userName) throws SQLException {
        connection = DatabaseConnection.createDatabaseConnection();
        String sqlQuery = "Select * from " + TABLENAME + " where username = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
        preparedStatement.setString(1, userName);
        Customer customer = new Customer();
        try {
            ResultSet resultSet = preparedStatement.executeQuery(sqlQuery);
            if(!resultSet.isBeforeFirst())
                throw new SQLException("No data found for the username " + userName);


            while (resultSet.next()) {
                customer.setId(resultSet.getInt(1));
                customer.setCname(resultSet.getString(2));
                customer.setPhone_no(resultSet.getString(3));
                customer.setUserName(resultSet.getString(4));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        DatabaseConnection.closeDatabaseConnection(connection);
        return customer;
    }
}
