package server.dao;

import server.entity.RewardRedeemingRules;
import server.utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RewardRedeemingRulesDao {

    public static String TABLENAME = "reward_redeeming_rules";
    public Connection connection;

    public String createRewardRedeemingRules(RewardRedeemingRules rewardredeemingRules){
        String response = "";
        try {
            connection = DatabaseConnection.createDatabaseConnection();
            String sqlQuery = "Insert into " + TABLENAME + "(reward_code, loyalty_program, rr_points, version_number, reward_redeeming_rules, number_of_instances ,createdBy, createdAt, updatedBy) " +
                    "values (?,?,?,?,?,?,?,?,?)";

            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, rewardredeemingRules.getRewardCode());
            preparedStatement.setInt(2, rewardredeemingRules.getLoyaltyProgram());
            preparedStatement.setInt(3, rewardredeemingRules.getRrPoints());
            preparedStatement.setInt(4, rewardredeemingRules.getVersionNumber());
            preparedStatement.setString(5, rewardredeemingRules.getRewardRedeemingRules());
            preparedStatement.setInt(6, rewardredeemingRules.getNumberOfInstances());

            preparedStatement.setString(7, rewardredeemingRules.getCreatedBy());
            preparedStatement.setDate(8, new Date(rewardredeemingRules.getCreatedAt().getTime()));
            preparedStatement.setString(9, rewardredeemingRules.getUpdatedBy());

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
