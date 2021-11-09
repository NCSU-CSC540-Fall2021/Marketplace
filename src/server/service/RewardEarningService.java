package server.service;

import server.dao.RewardEarningRulesDao;
import server.entity.Brand;
import server.entity.LoyaltyProgram;
import server.entity.RewardEarningRules;
import server.entity.User;

import java.sql.SQLException;
import java.util.Date;

public class RewardEarningService {
    RewardEarningRulesDao rewardEarningRulesDao;

    public String addRewardEarning(String reRuleCodeText, String rePointsText, String activityCode, User user) throws SQLException {
        RewardEarningRules rewardEarningRules = new RewardEarningRules();
        LoyaltyProgram loyaltyProgram = getLoyaltyProgramInfo(user);

        rewardEarningRules.setRewardEarningCode(reRuleCodeText);
        rewardEarningRules.setLoyaltyProgramId(loyaltyProgram.getLoyaltyProgramId());
        rewardEarningRules.setVersionNumber(1);
        rewardEarningRules.setActivityCode(activityCode);
        rewardEarningRules.setRePoints(Integer.valueOf(rePointsText));

        rewardEarningRules.setCreatedBy(user.getUserName());
        rewardEarningRules.setCreatedAt(new Date());
        rewardEarningRules.setUpdatedBy(user.getUserName());

        rewardEarningRulesDao = new RewardEarningRulesDao();
        String response = rewardEarningRulesDao.createRewardEarningRules(rewardEarningRules);
        return response;
    }

    private LoyaltyProgram getLoyaltyProgramInfo(User user) throws SQLException {
        BrandService brandService = new BrandService();
        Brand brand = brandService.getBrandInfoByUserName(user.getUserName());

        LoyaltyProgramService loyaltyProgramService = new LoyaltyProgramService();
        LoyaltyProgram loyaltyProgram = loyaltyProgramService.fetchLoyaltyProgramByBrand(brand.getBrand_id());

        return loyaltyProgram;
    }
}
