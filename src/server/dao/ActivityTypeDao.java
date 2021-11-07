package server.dao;

import server.entity.ActivityType;
import server.utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ActivityTypeDao {

    public static String TABLENAME = "activity_type";
    public Connection connection;

    public int getTotalActivitiesCount() throws SQLException {
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

    public String createActivity(ActivityType activityType) throws SQLException {
        connection = DatabaseConnection.createDatabaseConnection();
        String sqlQuery = "Insert into " + TABLENAME + " (activity_code, activity_name, createdBy, createdAt, updatedBy) values "
                + " (?,?,?,?,?) ";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
        preparedStatement.setString(1, activityType.getActivity_code());
        preparedStatement.setString(2, activityType.getActivity_name());
        preparedStatement.setString(3, activityType.getCreatedBy());
        preparedStatement.setDate(4, new Date(activityType.getCreatedAt().getTime()));
        preparedStatement.setString(5, activityType.getUpdatedBy());
        String response = "";
        try {
            int i = preparedStatement.executeUpdate();
            if (i > 0) {
                response = "Activity type created successfully";
            }
        } catch (SQLException exception) {
            response = exception.getMessage();
            exception.printStackTrace();
        }
        DatabaseConnection.closeDatabaseConnection(connection);
        return response;
    }

    public List<ActivityType> getAllActivities() throws SQLException {
        connection = DatabaseConnection.createDatabaseConnection();
        String sqlQuery = "Select * from " + TABLENAME ;
        PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);

        List<ActivityType> activityTypeList = new ArrayList<>();
        try {
            ResultSet resultSet = preparedStatement.executeQuery();
            if(!resultSet.isBeforeFirst())
                throw new SQLException("No data found");

            while(resultSet.next()) {
                ActivityType activityType = new ActivityType();
                activityType.setActivity_code(resultSet.getString(1));
                activityType.setActivity_name(resultSet.getString(2));
                activityTypeList.add(activityType);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        DatabaseConnection.closeDatabaseConnection(connection);
        return activityTypeList;
    }
}
