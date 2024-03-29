package server.dao;

import server.entity.Wallet;
import server.utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class WalletDao {
    public static String TABLENAME = "wallet";
    public Connection connection;

    public Wallet[] viewWallet(Integer customerId) throws SQLException {
        connection = DatabaseConnection.createDatabaseConnection();

        String sqlQuery = "Select loyalty_id, net_points, tier_status, deleted, updatedAt  from " + TABLENAME + " where customer_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        preparedStatement.setInt(1, customerId);

        Wallet[] wallets = new Wallet[0];
        int rowcount = 0;
        try {
            ResultSet resultSet = preparedStatement.executeQuery();
            if(!resultSet.next()) {
                throw new SQLException("No wallet data found!");
            } else {
                resultSet.last();
                rowcount = resultSet.getRow();
                resultSet.beforeFirst(); // not rs.first() because the rs.next() below will move on, missing the first element
            }

            wallets = new Wallet[rowcount];
            int i =0;
            while (resultSet.next()) {
                wallets[i] = new Wallet();
                wallets[i].setLoyalty_id(resultSet.getInt(1));
                wallets[i].setNet_points(resultSet.getInt(2));
                wallets[i].setTier_status(resultSet.getInt(3));
                wallets[i].setDeleted(resultSet.getInt(4));
                wallets[i].setUpdatedAt(resultSet.getTimestamp(5));
                i++;
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        DatabaseConnection.closeDatabaseConnection(connection);
        return wallets;
    }
}