package server.dao;

import server.entity.RewardEarningRules;
import server.entity.RewardRedeemingRules;
import server.utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RewardRedeemingRulesDao {

    public static String TABLENAME = "reward_redeeming_rules";
    public Connection connection;

    public String createRewardEarningRules(RewardRedeemingRules rewardredeemingRules){
        String response = "";
        try {
            connection = DatabaseConnection.createDatabaseConnection();
            String sqlQuery = "Insert into " + TABLENAME + "(reward_code, brand_id, rr_points,version_number, number_of_instances ,createdBy, createdAt, updatedBy) values (?,?,?,?,?,?,?,?,?)";

            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, rewardredeemingRules.getRewardCode());
            preparedStatement.setInt(2, rewardredeemingRules.getBrandId());
            preparedStatement.setInt(3, rewardredeemingRules.getRrPoints());
            preparedStatement.setInt(4, rewardredeemingRules.getVersionNumber());
            preparedStatement.setInt(5, rewardredeemingRules.getNumberOfInstances());
            preparedStatement.setString(6, rewardredeemingRules.getCreatedBy());
            preparedStatement.setDate(7, new Date(rewardredeemingRules.getCreatedAt().getTime()));
            preparedStatement.setString(8, rewardredeemingRules.getUpdatedBy());

            try {
                int i = preparedStatement.executeUpdate();
                if (i > 0) {
                    response = "Reward redeeming rules created successfully";
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
