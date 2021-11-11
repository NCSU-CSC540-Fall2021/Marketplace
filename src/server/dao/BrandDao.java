package server.dao;

import server.entity.Brand;
import server.entity.User;
import server.utils.DatabaseConnection;

import java.sql.*;

public class BrandDao {

    public static String TABLENAME = "brand";
    public Connection connection;

    public BrandDao() {

    }

    public String createBrand(Brand brand) throws SQLException {
        String response = "";
        try {
            connection = DatabaseConnection.createDatabaseConnection();

            String sqlQuery = "Insert into " + TABLENAME + "(brand_name, address, joindate, username, createdBy, createdAt, updatedBy) values (?,?,?,?,?,?,?)";

            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, brand.getBrand_name());
            preparedStatement.setString(2, brand.getAddress());
            preparedStatement.setDate(3, new Date(brand.getJoindate().getTime()));
            preparedStatement.setString(4, brand.getUsername());
            preparedStatement.setString(5, brand.getCreatedBy());
            preparedStatement.setDate(6, new Date (brand.getCreatedAt().getTime()));
            preparedStatement.setString(7, brand.getUpdatedBy());

            try {
                int i = preparedStatement.executeUpdate();
                if (i > 0) {
                    response = "Brand created successfully";
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

    public Brand findBrandInfoByUserName(Brand brand) throws SQLException {
        connection = DatabaseConnection.createDatabaseConnection();
        String sqlQuery = "Select * from " + TABLENAME + " where username = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);

        preparedStatement.setString(1, brand.getUsername());
        try {
            ResultSet resultSet = preparedStatement.executeQuery();
            if(!resultSet.isBeforeFirst())
                throw new SQLException("No data found");

            while(resultSet.next()) {
                brand.setBrand_id(resultSet.getInt(1));
                brand.setBrand_name(resultSet.getString(2));
                brand.setAddress(resultSet.getString(3));
                brand.setJoindate(resultSet.getDate(4));
                brand.setLoyaltyProgramId(resultSet.getInt(10));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        DatabaseConnection.closeDatabaseConnection(connection);
        return brand;
    }

    public String updateBrandLoyaltyProgram(User user, Integer loyaltyProgramId) throws SQLException {
        connection = DatabaseConnection.createDatabaseConnection();
        String sqlQuery = "Update " + TABLENAME + " set loyalty_program_id = ? where username = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
        preparedStatement.setInt(1, loyaltyProgramId);
        preparedStatement.setString(2, user.getUserName());

        System.out.println(preparedStatement.toString());
        String response = "";
        try {
            int i = preparedStatement.executeUpdate();
            if (i > 0) {
                response = "Brand created successfully";
            }
        } catch (SQLException exception) {
            response = exception.getMessage();
            exception.printStackTrace();
        }
        DatabaseConnection.closeDatabaseConnection(connection);
        return response;
    }
}
