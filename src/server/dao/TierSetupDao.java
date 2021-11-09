package server.dao;

import server.entity.LoyaltyProgram;
import server.entity.TierSetup;
import server.utils.DatabaseConnection;

import java.sql.*;

public class TierSetupDao {

    public static String TABLENAME = "tier_setup";
    public Connection connection;

    public String createTierSetup(TierSetup tierSetup){
        String response = "";
        try {
            connection = DatabaseConnection.createDatabaseConnection();
            String sqlQuery = "Insert into " + TABLENAME + "(loyalty_id, tier_level_code, tier_level_name, eligibility_points, multiplier, createdBy, createdAt, updatedBy) values (?,?,?,?,?,?,?,?)";

            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1, tierSetup.getLoyaltyId());
            preparedStatement.setInt(2, tierSetup.getTierLevelCode());
            preparedStatement.setString(3, tierSetup.getTierLevelName());
            preparedStatement.setInt(4, tierSetup.getEligibilityPoints());
            preparedStatement.setInt(5, tierSetup.getMultiplier());
            preparedStatement.setString(6, tierSetup.getCreatedBy());
            preparedStatement.setDate(7, new Date(tierSetup.getCreatedAt().getTime()));
            preparedStatement.setString(8, tierSetup.getUpdatedBy());

            try {
                int i = preparedStatement.executeUpdate();
                if (i > 0) {
                    response = "Tier Setup created successfully";
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
