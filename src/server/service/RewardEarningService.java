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

    public RewardEarningService() {
        rewardEarningRulesDao = new RewardEarningRulesDao();
    }

    public String addOrUpdateRewardEarningRules(String reRuleCodeText, String rePointsText, String activityCode,
                                                User user, Integer type) throws SQLException {

        String response = "";
        LoyaltyProgram loyaltyProgram = getLoyaltyProgramInfo(user);
        RewardEarningRules rewardEarningRules = null;

        if(type == 1) {
            // add RE rules
            rewardEarningRules = addRERules(user, loyaltyProgram, reRuleCodeText, activityCode, rePointsText);
        }
        else if(type == 2) {
            // update RE rules

            // get re rule by loyalty program  + activity
            rewardEarningRules = getReRulesByLoyaltyProgramActivityCode(loyaltyProgram, activityCode);

            // if it doesn't exist throw error
            if(rewardEarningRules == null) {
                response = "No RERules exist for loyaltyProgramId " + loyaltyProgram.getLoyaltyProgramId() + " reRuleCode " + reRuleCodeText
                        + " activityCode " + activityCode;
                return response;
            }
            // else update values and increment version number
            else {
                updateRERules(rewardEarningRules, user, rePointsText, reRuleCodeText);
            }
        }

        response = rewardEarningRulesDao.createRewardEarningRules(rewardEarningRules, type);
        return response;
    }

    private void updateRERules(RewardEarningRules rewardEarningRules, User user, String rePointsText, String reRuleCodeText) {
        rewardEarningRules.setRePoints(Integer.valueOf(rePointsText));
        rewardEarningRules.setVersionNumber(rewardEarningRules.getVersionNumber() + 1);
        rewardEarningRules.setRewardEarningCode(reRuleCodeText);

        rewardEarningRules.setCreatedBy(user.getUserName());
        rewardEarningRules.setCreatedAt(new Date());
        rewardEarningRules.setUpdatedBy(user.getUserName());
    }

    private RewardEarningRules getReRulesByLoyaltyProgramActivityCode(LoyaltyProgram loyaltyProgram, String activityCode) {
        RewardEarningRules rewardEarningRules = rewardEarningRulesDao.fetchByLoyaltyProgramActivityCode(loyaltyProgram, activityCode);
        return rewardEarningRules;

    }

    private RewardEarningRules addRERules(User user, LoyaltyProgram loyaltyProgram, String reRuleCodeText,
                                          String activityCode, String rePointsText) throws SQLException {
        RewardEarningRules rewardEarningRules = new RewardEarningRules();

        rewardEarningRules.setRewardEarningCode(reRuleCodeText);
        rewardEarningRules.setLoyaltyProgramId(loyaltyProgram.getLoyaltyProgramId());
        rewardEarningRules.setVersionNumber(1);
        rewardEarningRules.setActivityCode(activityCode);
        rewardEarningRules.setRePoints(Integer.valueOf(rePointsText));

        rewardEarningRules.setCreatedBy(user.getUserName());
        rewardEarningRules.setCreatedAt(new Date());
        rewardEarningRules.setUpdatedBy(user.getUserName());

        return rewardEarningRules;
    }

    private LoyaltyProgram getLoyaltyProgramInfo(User user) throws SQLException {
        BrandService brandService = new BrandService();
        Brand brand = brandService.getBrandInfoByUserName(user.getUserName());

        LoyaltyProgramService loyaltyProgramService = new LoyaltyProgramService();
        LoyaltyProgram loyaltyProgram = loyaltyProgramService.fetchLoyaltyProgramByBrand(brand.getBrand_id());

        return loyaltyProgram;
    }

    public Integer getReRulesCountByLoyaltyProgram(LoyaltyProgram loyaltyProgram) {
        Integer countByLoyaltyProgram = rewardEarningRulesDao.fetchCountByLoyaltyProgram(loyaltyProgram);
        return countByLoyaltyProgram;
    }
}
