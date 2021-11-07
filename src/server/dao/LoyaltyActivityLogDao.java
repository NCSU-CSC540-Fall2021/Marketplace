package server.dao;

import server.entity.LoyaltyActivityLog;
import server.utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LoyaltyActivityLogDao {
    public static  String TABLENAME = "loyalty_activity_log";
    public Connection connection;

    public String createLoyaltyActivityLog(LoyaltyActivityLog loyaltyActivityLog) throws SQLException {
        connection = DatabaseConnection.createDatabaseConnection();

        String sqlQuery = "Insert into " + TABLENAME + "(activity_code, reward_earning_code, customer_id, points_gained) values (?,?,?,?)";

        PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
        preparedStatement.setString(1, loyaltyActivityLog.getActivity_code());
        preparedStatement.setString(2, loyaltyActivityLog.getReward_earning_code());
        preparedStatement.setInt(3, loyaltyActivityLog.getCustomer_id());
        preparedStatement.setInt(4, loyaltyActivityLog.getPoints_gained());

        String resp = "";
        try {
            if (preparedStatement.executeUpdate() > 0) {
                resp = "Activity performed successfully";
            }
        } catch (SQLException exception) {
            resp = exception.getMessage();
            exception.printStackTrace();
        }

        DatabaseConnection.closeDatabaseConnection(connection);
        return resp;
    }
}
