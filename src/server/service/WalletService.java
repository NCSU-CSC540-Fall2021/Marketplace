package server.service;

import server.dao.WalletDao;
import server.entity.Wallet;

import java.sql.SQLException;

public class WalletService {
    public Wallet[] viewWallet(Integer customerId) throws SQLException {
        Wallet[] wallets = new WalletDao().viewWallet(customerId);
        return wallets;
    }
}
