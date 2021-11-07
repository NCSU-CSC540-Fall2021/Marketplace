package server.service;

import server.dao.RewardEarningRulesDao;
import server.entity.Brand;
import server.entity.RewardEarningRules;
import server.entity.User;

import java.sql.SQLException;
import java.util.Date;

public class RewardEarningService {
    RewardEarningRulesDao rewardEarningRulesDao;

    public String addRewardEarning(String reRuleCodeText, String rePointsText, String activityCode, User user) throws SQLException {
        RewardEarningRules rewardEarningRules = new RewardEarningRules();
        Brand brand = getBrandInfo(user);
        rewardEarningRules.setRewardEarningCode(reRuleCodeText);
        rewardEarningRules.setRePoints(Integer.valueOf(rePointsText));
        rewardEarningRules.setActivityCode(activityCode);
        rewardEarningRules.setVersionNumber(1);
        rewardEarningRules.setBrandId(brand.getBrand_id());
        rewardEarningRules.setCreatedBy(user.getUserName());
        rewardEarningRules.setCreatedAt(new Date());
        rewardEarningRules.setUpdatedBy(user.getUserName());

        rewardEarningRulesDao = new RewardEarningRulesDao();
        String response = rewardEarningRulesDao.createRewardEarningRules(rewardEarningRules);
        return response;
    }

    private Brand getBrandInfo(User user) throws SQLException {
        BrandService brandService = new BrandService();
        System.out.println("user name -->" + user.getUserName());
        Brand brand = brandService.getBrandInfoByUserName(user.getUserName());
        return brand;
    }
}
