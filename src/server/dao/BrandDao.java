package server.dao;

import server.entity.Brand;
import server.utils.DatabaseConnection;

import java.sql.*;

public class BrandDao {

    public static String TABLENAME = "Brand";
    public Connection connection;

    public BrandDao() {

    }

    public String createBrand(Brand brand) throws SQLException {
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

    public Brand findBrandInfoByUserName(Brand brand) throws SQLException {
        connection = DatabaseConnection.createDatabaseConnection();
        String sqlQuery = "Select * " + TABLENAME + " where username = ?";
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
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        DatabaseConnection.closeDatabaseConnection(connection);
        return brand;
    }
}
