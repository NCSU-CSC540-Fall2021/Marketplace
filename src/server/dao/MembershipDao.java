package server.dao;

import server.utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MembershipDao {
    public static String TABLENAME = "membership";
    public Connection connection;

    public String enrollCustomerLoyalty(Integer customer_id, Integer loyalty_program_id) throws SQLException {
        String response = "";
        try {
            connection = DatabaseConnection.createDatabaseConnection();
            String sqlQuery = "Insert into " + TABLENAME + "(customer_id, loyalty_program_id) values (?,?)";

            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1, customer_id);
            preparedStatement.setInt(2, loyalty_program_id);

            try {
                if(preparedStatement.executeUpdate() > 0) {
                    response = "Enrolled Successfully";
                }
            } catch (SQLException e) {
                response = e.getMessage();
                e.printStackTrace();
            }
            DatabaseConnection.closeDatabaseConnection(connection);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return response;
    }
}
