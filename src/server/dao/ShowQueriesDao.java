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

    public Map<String, List<String[]>> getResultForQuery2() {
        Map<String, List<String []>> map = new HashMap<>();
        try {
            connection = DatabaseConnection.createDatabaseConnection();
            String sqlQuery = "SELECT M.customer_id, M.loyalty_program_id" +
                    " from membership M" +
                    " where M.customer_id NOT IN (" +
                    "    SELECT DISTINCT LAL.customer_id" +
                    "    from loyalty_activity_log LAL" +
                    "    INNER JOIN reward_earning_rules rer on LAL.reward_earning_code = rer.reward_earning_code" +
                    "    where rer.loyalty_program_id = M.loyalty_program_id" +
                    ")";

            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);

            try {
                List<String []> columnNames = new ArrayList<>();
                columnNames.add(new String[] {"customer_id", "loyalty_program_id"});
                map.put("column_names", columnNames);

                ResultSet resultSet = preparedStatement.executeQuery();
                if(!resultSet.isBeforeFirst()) {
                    throw new SQLException("No data found");
                }


                List<String []> values = new ArrayList<>();
                while (resultSet.next()) {
                    System.out.println("Result for query 1 -- " + resultSet.getInt(1) + " -- "
                            + resultSet.getString(2));

                    values.add(new String[]
                            {
                                    String.valueOf(resultSet.getInt(1)),
                                    String.valueOf(resultSet.getInt(2))
                            });
                }

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

    public Map<String, List<String[]>> getResultForQuery5() {
        Map<String, List<String []>> map = new HashMap<>();
        try {
            connection = DatabaseConnection.createDatabaseConnection();
            String sqlQuery = "SELECT lal.ACTIVITY_CODE, COUNT(*) " +
                    "from loyalty_activity_log lal " +
                    "INNER JOIN reward_earning_rules rer on lal.reward_earning_code = rer.reward_earning_code " +
                    "INNER JOIN brand b on rer.loyalty_program_id = b.loyalty_program_id " +
                    "where b.brand_name = 'BrandX' " +
                    "GROUP BY lal.ACTIVITY_CODE order by ACTIVITY_CODE";

            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);

            try {
                List<String []> columnNames = new ArrayList<>();
                columnNames.add(new String[] {"activity_code", "count"});
                map.put("column_names", columnNames);

                ResultSet resultSet = preparedStatement.executeQuery();
                if(!resultSet.isBeforeFirst()) {
                    throw new SQLException("No data found");
                }


                List<String []> values = new ArrayList<>();
                while (resultSet.next()) {
                    System.out.println("Result for query 1 -- " + resultSet.getString(1) + " -- "
                            + resultSet.getInt(2));

                    values.add(new String[]
                            {
                                   resultSet.getString(1),
                                    String.valueOf(resultSet.getInt(2))
                            });
                }

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

    public Map<String, List<String[]>> getResultForQuery7() {
        Map<String, List<String []>> map = new HashMap<>();
        try {
            connection = DatabaseConnection.createDatabaseConnection();
            String sqlQuery = "select lp.brand_id, SUM(points_redeemed) as points_redeemed " +
                    "from reward_activity_log ral " +
                    "JOIN LOYALTY_PROGRAM lp on lp.LOYALTY_PROGRAM_ID = ral.LOYALTY_PROGRAM_ID " +
                    "group by lp.brand_id " +
                    "having SUM(points_redeemed) < 500";

            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);

            try {
                List<String []> columnNames = new ArrayList<>();
                columnNames.add(new String[] {"brand_id", "points_redeemed"});
                map.put("column_names", columnNames);

                ResultSet resultSet = preparedStatement.executeQuery();
                if(!resultSet.isBeforeFirst()) {
                    throw new SQLException("No data found");
                }


                List<String []> values = new ArrayList<>();
                while (resultSet.next()) {
                    System.out.println("Result for query 1 -- "
                            + resultSet.getInt(1) + " -- "
                            + resultSet.getInt(2));

                    values.add(new String[]
                            {
                                    String.valueOf(resultSet.getInt(1)),
                                    String.valueOf(resultSet.getInt(2))
                            });
                }
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

    public Map<String, List<String[]>> getResultForQuery8() {
        Map<String, List<String []>> map = new HashMap<>();
        try {
            connection = DatabaseConnection.createDatabaseConnection();
            String sqlQuery = "SELECT lal.customer_id, lp.brand_id, COUNT(*) AS no_of_activities " +
                    "FROM loyalty_activity_log lal " +
                    "INNER JOIN reward_earning_rules rer on lal.reward_earning_code = rer.reward_earning_code " +
                    "INNER JOIN loyalty_program lp on rer.loyalty_program_id = lp.loyalty_program_id " +
                    "where lal.updatedAt >= to_date('2021-08-01', 'YYYY-MM-DD') AND lal.updatedAt <= to_date('2021-09-30', 'YYYY-MM-DD') " +
                    "GROUP BY lal.customer_id, lp.brand_id";

            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);

            try {
                List<String []> columnNames = new ArrayList<>();
                columnNames.add(new String[] {"customer_id", "brand_id", "no_of_activities"});
                map.put("column_names", columnNames);

                ResultSet resultSet = preparedStatement.executeQuery();
                if(!resultSet.isBeforeFirst()) {
                    throw new SQLException("No data found");
                }


                List<String []> values = new ArrayList<>();
                while (resultSet.next()) {
                    System.out.println("Result for query 1 -- "
                            + resultSet.getInt(1) + " -- "
                            + resultSet.getInt(2) + " -- " + resultSet.getInt(3)
                    );

                    values.add(new String[]
                            {
                                    String.valueOf(resultSet.getInt(1)),
                                    String.valueOf(resultSet.getInt(2)),
                                    String.valueOf(resultSet.getInt(3))
                            });
                }
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

    public Map<String, List<String[]>> getResultForQuery6() {
        Map<String, List<String []>> map = new HashMap<>();
        try {
            connection = DatabaseConnection.createDatabaseConnection();
            String sqlQuery = "SELECT ral.customer_id " +
                    "FROM reward_activity_log ral " +
                    "INNER JOIN LOYALTY_PROGRAM lp on ral.LOYALTY_PROGRAM_ID = lp.LOYALTY_PROGRAM_ID " +
                    "INNER JOIN brand b on lp.BRAND_ID = b.BRAND_ID " +
                    "where b.brand_name = 'BrandX' " +
                    "GROUP BY ral.customer_id " +
                    "HAVING COUNT(*)>1";

            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);

            try {
                List<String []> columnNames = new ArrayList<>();
                columnNames.add(new String[] {"customer_id"});
                map.put("column_names", columnNames);

                ResultSet resultSet = preparedStatement.executeQuery();
                if(!resultSet.isBeforeFirst()) {
                    throw new SQLException("No data found");
                }


                List<String []> values = new ArrayList<>();
                while (resultSet.next()) {
                    System.out.println("Result for query 1 -- "
                            + resultSet.getInt(1)
                    );

                    values.add(new String[]
                            {
                                    String.valueOf(resultSet.getInt(1))
                            });
                }
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
}
