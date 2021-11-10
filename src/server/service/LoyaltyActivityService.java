package server.service;

import server.dao.LoyaltyActivityLogDao;
import server.entity.LoyaltyActivityLog;

import java.sql.SQLException;

public class LoyaltyActivityService {
    public LoyaltyActivityService() {

    }

    public String createPurchase(LoyaltyActivityLog loyaltyActivityLog) throws SQLException {
        LoyaltyActivityLogDao loyaltyActivityLogDao = new LoyaltyActivityLogDao();
        String resp = loyaltyActivityLogDao.createLoyaltyActivityLog(loyaltyActivityLog);
        return resp;
    }

    public String createReview(LoyaltyActivityLog loyaltyActivityLog) throws SQLException {
        LoyaltyActivityLogDao loyaltyActivityLogDao = new LoyaltyActivityLogDao();
        String resp = loyaltyActivityLogDao.createLoyaltyActivityLog(loyaltyActivityLog);
        return resp;
    }

    public String referAFriend(LoyaltyActivityLog loyaltyActivityLog) throws SQLException {
        LoyaltyActivityLogDao loyaltyActivityLogDao = new LoyaltyActivityLogDao();
        String resp = loyaltyActivityLogDao.createLoyaltyActivityLog(loyaltyActivityLog);
        return resp;
    }
}
