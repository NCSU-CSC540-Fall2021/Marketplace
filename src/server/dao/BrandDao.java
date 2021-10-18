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

        String sqlQuery = "Insert into " + TABLENAME + "(name, address, joindate, createdBy, createdAt, updatedBy) values (?,?,?,?,?,?)";

        PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
        preparedStatement.setString(1, brand.getName());
        preparedStatement.setString(2, brand.getAddress());
        preparedStatement.setDate(3, new Date(brand.getJoindate().getTime()));
        preparedStatement.setInt(4, brand.getCreatedBy());
        preparedStatement.setDate(5, new Date (brand.getCreatedAt().getTime()));
        preparedStatement.setInt(6, brand.getUpdatedBy());

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
