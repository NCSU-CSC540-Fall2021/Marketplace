package server.dao;

import server.entity.LoyaltyProgram;
import server.entity.RewardEarningRules;
import server.utils.DatabaseConnection;

import java.sql.*;

public class RewardEarningRulesDao {

    public static String TABLENAME = "reward_earning_rules";
    public Connection connection;

    public String createRewardEarningRules(RewardEarningRules rewardEarningRules, Integer type){
        String response = "";
        try {
            connection = DatabaseConnection.createDatabaseConnection();
            String sqlQuery = "Insert into " + TABLENAME + "(reward_earning_code, loyalty_program_id, version_number, activity_code, re_points ,createdBy, createdAt, updatedBy) values (?,?,?,?,?,?,?,?)";

            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, rewardEarningRules.getRewardEarningCode());
            preparedStatement.setInt(2, rewardEarningRules.getLoyaltyProgramId());
            preparedStatement.setInt(3, rewardEarningRules.getVersionNumber());
            preparedStatement.setString(4, rewardEarningRules.getActivityCode());
            preparedStatement.setInt(5, rewardEarningRules.getRePoints());
            preparedStatement.setString(6, rewardEarningRules.getCreatedBy());
            preparedStatement.setDate(7, new Date(rewardEarningRules.getCreatedAt().getTime()));
            preparedStatement.setString(8, rewardEarningRules.getUpdatedBy());

            try {
                int i = preparedStatement.executeUpdate();
                if (i > 0) {
                    if(type == 1)
                        response = "Reward earning rules created successfully";
                    else if(type == 2)
                        response = "Reward earning rules updated successfully";
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

    public RewardEarningRules fetchByLoyaltyProgramActivityCode(LoyaltyProgram loyaltyProgram, String activityCode) {
        RewardEarningRules rewardEarningRules = null;
        try {
            connection = DatabaseConnection.createDatabaseConnection();
            String sqlQuery = "Select * from " + TABLENAME + " where loyalty_program_id = ? " +
                    "and activity_code = ? order by version_number desc limit 1";

            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1, loyaltyProgram.getLoyaltyProgramId());
            preparedStatement.setString(2, activityCode);
            try {
                ResultSet resultSet = preparedStatement.executeQuery();
                if(!resultSet.isBeforeFirst()) {
                    throw new SQLException("No data found");
                }

                while(resultSet.next()) {
                    rewardEarningRules = new RewardEarningRules();
                    rewardEarningRules.setRewardEarningRulesId(resultSet.getInt(1));
                    rewardEarningRules.setRewardEarningCode(resultSet.getString(2));
                    rewardEarningRules.setLoyaltyProgramId(resultSet.getInt(3));
                    rewardEarningRules.setVersionNumber(resultSet.getInt(4));
                    rewardEarningRules.setActivityCode(resultSet.getString(5));
                    rewardEarningRules.setRePoints(resultSet.getInt(6));
                }
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        DatabaseConnection.closeDatabaseConnection(connection);
        return rewardEarningRules;
    }
}
