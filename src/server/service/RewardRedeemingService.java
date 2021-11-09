package server.service;

import server.dao.RewardRedeemingRulesDao;
import server.entity.Brand;
import server.entity.RewardRedeemingRules;
import server.entity.User;

import java.sql.SQLException;
import java.util.Date;

public class RewardRedeemingService {
    RewardRedeemingRulesDao rewardRedeemingRulesDao ;

    public String addRewardRedeeming(String rrRuleCodeText, String rrPointsText, String rewardCode,
                                     String rewardInstances, User user) throws SQLException {
        RewardRedeemingRules rewardRedeemingRules = new RewardRedeemingRules();
        Brand brand = getBrandInfo(user);

        rewardRedeemingRules.setRewardRedeemingRules(rrRuleCodeText);
        rewardRedeemingRules.setRrPoints(Integer.valueOf(rrPointsText));
        rewardRedeemingRules.setRewardCode(rewardCode);
        rewardRedeemingRules.setVersionNumber(1);
        rewardRedeemingRules.setLoyaltyProgram(brand.getBrand_id());
        rewardRedeemingRules.setNumberOfInstances(Integer.valueOf(rewardInstances));
        rewardRedeemingRules.setCreatedBy(user.getUserName());
        rewardRedeemingRules.setCreatedAt(new Date());
        rewardRedeemingRules.setUpdatedBy(user.getUserName());

        rewardRedeemingRulesDao = new RewardRedeemingRulesDao();
        String response = rewardRedeemingRulesDao.createRewardRedeemingRules(rewardRedeemingRules);
        return response;
    }

    private Brand getBrandInfo(User user) throws SQLException {
        BrandService brandService = new BrandService();
        System.out.println("user name -->" + user.getUserName());
        Brand brand = brandService.getBrandInfoByUserName(user.getUserName());
        return brand;
    }
}
