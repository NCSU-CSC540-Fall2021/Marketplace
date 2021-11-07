package server.service;

import server.dao.LoyaltyActivityLogDao;
import server.entity.LoyaltyActivityLog;

import java.sql.SQLException;

public class LoyaltyActivityService {
    public LoyaltyActivityService() {

    }

    public String createPurchase(String username, String brandName, String giftCardCode, String item_Purchased) throws SQLException {
        LoyaltyActivityLogDao loyaltyActivityLogDao = new LoyaltyActivityLogDao();
        LoyaltyActivityLog loyaltyActivityLog = new LoyaltyActivityLog();

        String activityType = "Purchase";
        // todo: get customerId from username -> loyaltyActivityLog.setCustomer_id(username);
        // todo: get activity_code and brandId based on brandName and activity.
        // todo: if(giftCardCode) get reward_earning_code.

        String resp = loyaltyActivityLogDao.createLoyaltyActivityLog(loyaltyActivityLog);
        return resp;
    }

    public String createReview(String username, String brandName, String reviewText) {
        LoyaltyActivityLog loyaltyActivityLog = new LoyaltyActivityLog();
        // todo: get customerId from username -> loyaltyActivityLog.setCustomer_id(username);
        // todo: get brandId from brandName->
        // insert
        return "1";
    }

    public String referAFriend(String username, String brandName, String referDetailsText) {
        LoyaltyActivityLog loyaltyActivityLog = new LoyaltyActivityLog();
        // todo: get customerId from username -> loyaltyActivityLog.setCustomer_id(username);
        // todo: get brandId from brandName->
        // insert
        return "1";
    }
}
