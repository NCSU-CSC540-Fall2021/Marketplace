package server.dao;

import server.entity.LoyaltyProgram;
import server.utils.DatabaseConnection;

import java.sql.*;

public class LoyaltyProgramDao {

    public static String TABLENAME = "loyalty_program";
    public Connection connection;

    public String createLoyaltyProgram(LoyaltyProgram loyaltyProgram){
        String response = "";
        try {
            connection = DatabaseConnection.createDatabaseConnection();
            String sqlQuery = "Insert into " + TABLENAME + "(brand_id, tiered, number_of_tiers, createdBy, createdAt, updatedBy) values (?,?,?,?,?,?,?)";

            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1, loyaltyProgram.getBrandId());
            preparedStatement.setBoolean(2,loyaltyProgram.getTiered());
            preparedStatement.setInt(3,loyaltyProgram.getNumberOfTiers());
            preparedStatement.setString(4, loyaltyProgram.getCreatedBy());
            preparedStatement.setDate(5, new Date(loyaltyProgram.getCreatedAt().getTime()));
            preparedStatement.setString(6, loyaltyProgram.getUpdatedBy());

            try {
                int i = preparedStatement.executeUpdate();
                if (i > 0) {
                    response = "Loyalty Program created successfully";
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

    public String updateLoyaltyProgram(LoyaltyProgram loyaltyProgram){
        String response = "";
        try {
            connection = DatabaseConnection.createDatabaseConnection();
            String sqlQuery = "Update " + TABLENAME + " set brand_id=?, tiered =?, number_of_tiers=?, createdBy=?, createdAt=?, updatedBy=?"
                                    +" where loyalty_program_id =?";

            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1, loyaltyProgram.getBrandId());
            preparedStatement.setBoolean(2,loyaltyProgram.getTiered());
            preparedStatement.setInt(3,loyaltyProgram.getNumberOfTiers());
            preparedStatement.setString(4, loyaltyProgram.getCreatedBy());
            preparedStatement.setDate(5, new Date(loyaltyProgram.getCreatedAt().getTime()));
            preparedStatement.setString(6, loyaltyProgram.getUpdatedBy());
            preparedStatement.setInt(7, loyaltyProgram.getLoyaltyProgramId());

            try {
                int i = preparedStatement.executeUpdate();
                if (i > 0) {
                    response = "Loyalty Program updated successfully";
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

    public LoyaltyProgram getLoyaltyProgramByBrandName(String brandName){

        LoyaltyProgram loyaltyProgram = new LoyaltyProgram();
        try {
            connection = DatabaseConnection.createDatabaseConnection();
            String sqlQuery = "Select * from "+ TABLENAME+" where brand_id = (select brand_id from brand where brand_name =\'"+brandName+"\')";

            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);

            try {
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()){
                    loyaltyProgram.setLoyaltyProgramId(resultSet.getInt("loyalty_program_id"));
                    loyaltyProgram.setBrandId(resultSet.getInt("brand_id"));
                    loyaltyProgram.setTiered(resultSet.getBoolean("tiered"));
                    loyaltyProgram.setNumberOfTiers(resultSet.getInt("number_of_tiers"));
                    loyaltyProgram.setCreatedAt(resultSet.getDate("createdAt"));
                    loyaltyProgram.setCreatedBy(resultSet.getString("createdBy"));
                    loyaltyProgram.setUpdatedBy(resultSet.getString("updatedBy"));

                }

            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        DatabaseConnection.closeDatabaseConnection(connection);
        return loyaltyProgram;
    }

}
