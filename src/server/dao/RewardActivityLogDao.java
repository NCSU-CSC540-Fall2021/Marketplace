package server.dao;

import server.entity.RewardActivityLog;
import server.utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RewardActivityLogDao {
    public static String TABLENAME = "reward_activity_log";
    public Connection connection;

    public String rewardActivityLog(RewardActivityLog rewardActivityLog) throws SQLException {
        connection = DatabaseConnection.createDatabaseConnection();

        String sqlQuery = "Insert into " + TABLENAME + "(customer_id, loyalty_program_id, start_date, end_date, rr_code, expired, points_redeemed, redeemed, deleted, createdAt) values (?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
        preparedStatement.setInt(1, rewardActivityLog.getCustomer_id());
        preparedStatement.setInt(2, rewardActivityLog.getLoyalty_program_id());
        preparedStatement.setDate(3, new Date(rewardActivityLog.getStart_date().getTime()));
        preparedStatement.setDate(4, new Date(rewardActivityLog.getEnd_date().getTime()));
        preparedStatement.setString(5, rewardActivityLog.getRr_code());
        preparedStatement.setInt(6, rewardActivityLog.getExpired());
        preparedStatement.setInt(7, rewardActivityLog.getPoints_redeemed());
        preparedStatement.setInt(8, rewardActivityLog.getRedeemed());
        preparedStatement.setInt(9, rewardActivityLog.getDeleted());
        preparedStatement.setDate(10, new Date(rewardActivityLog.getCreatedAt().getTime()));

        String resp = "";
        try {
            if(preparedStatement.executeUpdate() > 0) {
                resp = "Rewards claimed successfully.";
            }
        } catch (SQLException exception) {
            resp = exception.getMessage();
            exception.printStackTrace();
        }
        DatabaseConnection.closeDatabaseConnection(connection);
        return resp;
    }
}
