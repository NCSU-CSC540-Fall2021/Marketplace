package server.dao;

import server.entity.RewardEarningRules;
import server.entity.TierSetup;
import server.utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RewardEarningRulesDao {

    public static String TABLENAME = "reward_earning_rules";
    public Connection connection;

    public String createRewardEarningRules(RewardEarningRules rewardEarningRules){
        String response = "";
        try {
            connection = DatabaseConnection.createDatabaseConnection();
            String sqlQuery = "Insert into " + TABLENAME + "(reward_earning_code, brand_id, version_number, activity_code, re_points ,createdBy, createdAt, updatedBy) values (?,?,?,?,?,?,?,?)";

            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, rewardEarningRules.getRewardEarningCode());
            preparedStatement.setInt(2, rewardEarningRules.getBrandId());
            preparedStatement.setInt(3, rewardEarningRules.getVersionNumber());
            preparedStatement.setString(4, rewardEarningRules.getActivityCode());
            preparedStatement.setInt(5, rewardEarningRules.getRePoints());
            preparedStatement.setString(6, rewardEarningRules.getCreatedBy());
            preparedStatement.setDate(7, new Date(rewardEarningRules.getCreatedAt().getTime()));
            preparedStatement.setString(8, rewardEarningRules.getUpdatedBy());

            try {
                int i = preparedStatement.executeUpdate();
                if (i > 0) {
                    response = "Reward earning rules created successfully";
                }
            } catch (SQLException exception) {
                response = exception.getMessage();
                exception.printStackTrace();
            }
        }catch (Exception e){
            response = e.getMessage();
            e.printStackTrace();
        }
        DatabaseConnection.closeDatabaseConnection(connection);
        return response;
    }

}
