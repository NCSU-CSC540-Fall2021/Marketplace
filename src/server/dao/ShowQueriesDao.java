package server.dao;

import server.utils.DatabaseConnection;

import java.sql.*;
import java.util.*;

public class ShowQueriesDao {
    public Connection connection;

    public Map<String, List<String[]>> getResultForQuery1() {
        Map<String, List<String []>> map = new HashMap<>();
        try {
            connection = DatabaseConnection.createDatabaseConnection();
            String sqlQuery = "SELECT C.customer_id, C.customer_name" +
                    " from customer C" +
                    " where C.customer_id NOT IN (" +
                    "    Select M.customer_id" +
                    "    from membership M" +
                    "    INNER JOIN loyalty_program lp on M.loyalty_program_id = lp.loyalty_program_id" +
                    "    INNER JOIN brand b on lp.brand_id = b.brand_id" +
                    "    where b.brand_name = 'BrandY'" +
                    ")";

            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);

            try {
                ResultSet resultSet = preparedStatement.executeQuery();
                if(!resultSet.isBeforeFirst())
                    throw new SQLException("No data found");

                List<String []> columnNames = new ArrayList<>();
                columnNames.add(new String[] {"customer_id", "customer_name"});

                List<String []> values = new ArrayList<>();
                while (resultSet.next()) {
                    System.out.println("Result for query 1 -- " + resultSet.getInt(1) + " -- "
                            + resultSet.getString(2));

                    values.add(new String[]{String.valueOf(resultSet.getInt(1)), resultSet.getString(2)});
                }


                map.put("column_names", columnNames);
                map.put("values", values);
            } catch (SQLException exception) {
                exception.printStackTrace();
            }

            DatabaseConnection.closeDatabaseConnection(connection);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return map;
    }

    public Map<String, List<String>> getResultForQuery3() {
        Map<String, List<String>> map = new HashMap<>();
        try {
            connection = DatabaseConnection.createDatabaseConnection();
            String sqlQuery = "select DISTINCT RT.reward_name from reward_type RT" +
                    "  INNER JOIN reward_redeeming_rules rrr on RT.reward_code = rrr.reward_code" +
                    "  INNER JOIN loyalty_program lp on rrr.loyalty_program = lp.loyalty_program_id" +
                    "  INNER JOIN brand b on lp.brand_id = b.brand_id" +
                    "  where b.brand_name = 'BrandX'";

            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);

            try {
                ResultSet resultSet = preparedStatement.executeQuery();
                if(!resultSet.isBeforeFirst())
                    throw new SQLException("No data found");

                List<String> reward_names = new ArrayList<>();
                while (resultSet.next()) {
                    System.out.println("Result for query 3 " + resultSet.getString(1));
                    reward_names.add(resultSet.getString(1));
                }
                map.put("reward_name", reward_names);
            } catch (SQLException exception) {
                map.put("reward_name", Collections.singletonList(exception.getMessage()));
                exception.printStackTrace();
            }

            DatabaseConnection.closeDatabaseConnection(connection);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return map;
    }

    public Map<String, List<String>> getResultForQuery4() {
        Map<String, List<String>> map = new HashMap<>();
        try {
            connection = DatabaseConnection.createDatabaseConnection();
            String sqlQuery = "select re.loyalty_program_id from reward_earning_rules re" +
                    "  INNER JOIN activity_type a on re.activity_code = a.activity_code" +
                    "  where a.activity_name = 'Refer a friend'";

            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);

            try {
                ResultSet resultSet = preparedStatement.executeQuery();
                if(!resultSet.isBeforeFirst())
                    throw new SQLException("No data found");

                List<String> loyaltyProgramIds = new ArrayList<>();
                while (resultSet.next()) {
                    System.out.println("Result for query 4 " + resultSet.getInt(1));
                    loyaltyProgramIds.add(String.valueOf(resultSet.getInt(1)));
                }
                map.put("loyalty_program_id", loyaltyProgramIds);
            } catch (SQLException exception) {
                map.put("loyalty_program_id", Collections.singletonList(exception.getMessage()));
                exception.printStackTrace();
            }

            DatabaseConnection.closeDatabaseConnection(connection);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return map;
    }

}
