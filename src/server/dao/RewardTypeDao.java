package server.dao;

import server.entity.ActivityType;
import server.entity.RewardType;
import server.utils.DatabaseConnection;

import java.sql.*;

public class RewardTypeDao {
    public static String TABLENAME = "reward_type";
    public Connection connection;

    public int getTotalRewardsCount() throws SQLException {
        connection = DatabaseConnection.createDatabaseConnection();
        String sqlQuery = "Select count(*) from " + TABLENAME;
        PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
        int totalRowCount = 0;
        try {
            ResultSet resultSet = preparedStatement.executeQuery(sqlQuery);
            while(resultSet.next())
            {
                totalRowCount = resultSet.getInt(1);
                System.out.println("totalRowCount" + totalRowCount);
            }
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
            exception.printStackTrace();
        }
        DatabaseConnection.closeDatabaseConnection(connection);
        return totalRowCount;
    }

    public String createReward(RewardType rewardType) throws SQLException {
        connection = DatabaseConnection.createDatabaseConnection();
        String sqlQuery = "Insert into " + TABLENAME + " (reward_type, reward_code, createdBy, createdAt, updatedBy) values "
                + " (?,?,?,?,?) ";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
        preparedStatement.setString(1, rewardType.getReward_type());
        preparedStatement.setString(2, rewardType.getReward_code());
        preparedStatement.setInt(3, rewardType.getCreatedBy());
        preparedStatement.setDate(4, new Date(rewardType.getCreatedAt().getTime()));
        preparedStatement.setInt(5, rewardType.getUpdatedBy());
        String response = "";
        try {
            int i = preparedStatement.executeUpdate();
            if (i > 0) {
                response = "Reward type created successfully";
            }
        } catch (SQLException exception) {
            response = exception.getMessage();
            exception.printStackTrace();
        }
        DatabaseConnection.closeDatabaseConnection(connection);
        return response;
    }
}
