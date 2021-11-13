package server.dao;

import server.entity.LoyaltyProgram;
import server.entity.RewardRedeemingRules;
import server.utils.DatabaseConnection;

import java.sql.*;

public class RewardRedeemingRulesDao {

    public static String TABLENAME = "reward_redeeming_rules";
    public Connection connection;

    public String createRewardRedeemingRules(RewardRedeemingRules rewardredeemingRules, Integer type){
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
                    if(type == 1)
                        response = "Reward redeeming rules created successfully";
                    else if(type == 2)
                        response = "Reward redeeming rules updated successfully";
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

    public RewardRedeemingRules fetchRrRulesByLoyaltyProgramRewardCode(LoyaltyProgram loyaltyProgram, String rewardCode) {
        RewardRedeemingRules rewardRedeemingRules = null;
        try {
            connection = DatabaseConnection.createDatabaseConnection();
            String sqlQuery = "Select * from " + TABLENAME + " where loyalty_program = ? and reward_code = ? " +
                    "order by version_number desc FETCH FIRST 1 ROWS ONLY";

            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1, loyaltyProgram.getLoyaltyProgramId());
            preparedStatement.setString(2, rewardCode);

            System.out.println(preparedStatement.toString());
            try {
                ResultSet resultSet = preparedStatement.executeQuery();
                if(!resultSet.isBeforeFirst()) {
                    throw new SQLException("No data found");
                }

                while(resultSet.next()) {
                    rewardRedeemingRules = new RewardRedeemingRules();
                    rewardRedeemingRules.setReward_redeeming_rules_id(resultSet.getInt(1));
                    rewardRedeemingRules.setRewardCode(resultSet.getString(2));
                    rewardRedeemingRules.setRrPoints(resultSet.getInt(3));
                    rewardRedeemingRules.setVersionNumber(resultSet.getInt(4));
                    rewardRedeemingRules.setLoyaltyProgram(resultSet.getInt(5));
                    rewardRedeemingRules.setRewardRedeemingRules(resultSet.getString(6));
                    rewardRedeemingRules.setNumberOfInstances(resultSet.getInt(7));
                }
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        DatabaseConnection.closeDatabaseConnection(connection);
        return rewardRedeemingRules;
    }

    public Integer fetchCountByLoyaltyProgram(LoyaltyProgram loyaltyProgram) {
        Integer count = 0;
        try {
            connection = DatabaseConnection.createDatabaseConnection();
            String sqlQuery = "Select count(*) from " + TABLENAME + " where loyalty_program = ? ";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1, loyaltyProgram.getLoyaltyProgramId());
            try {
                ResultSet resultSet = preparedStatement.executeQuery();
                if(!resultSet.isBeforeFirst()) {
                    throw new SQLException("No data found");
                }

                while(resultSet.next()) {
                    count = resultSet.getInt(1);
//                    rewardEarningRules = new RewardEarningRules();
//                    rewardEarningRules.setRewardEarningRulesId(resultSet.getInt(1));
//                    rewardEarningRules.setRewardEarningCode(resultSet.getString(2));
//                    rewardEarningRules.setLoyaltyProgramId(resultSet.getInt(3));
//                    rewardEarningRules.setVersionNumber(resultSet.getInt(4));
//                    rewardEarningRules.setActivityCode(resultSet.getString(5));
//                    rewardEarningRules.setRePoints(resultSet.getInt(6));
                }
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        DatabaseConnection.closeDatabaseConnection(connection);
        return count;
    }
}
