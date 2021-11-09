package server.service;

import server.dao.RewardRedeemingRulesDao;
import server.entity.*;

import java.sql.SQLException;
import java.util.Date;

public class RewardRedeemingService {
    RewardRedeemingRulesDao rewardRedeemingRulesDao ;

    public RewardRedeemingService () {
        rewardRedeemingRulesDao = new RewardRedeemingRulesDao();
    }

    public String addOrUpdateRewardRedeemingRules(String rrRuleCodeText, String rrPointsText, String rewardCode,
                                                  String rewardInstances, User user, Integer type) throws SQLException {

        String response = "";
        RewardRedeemingRules rewardRedeemingRules = null;
        LoyaltyProgram loyaltyProgram = getLoyaltyProgramInfo(user);

        if(type == 1) {
            // add RR Rules
            rewardRedeemingRules = addRRRules(user, rrRuleCodeText, rrPointsText, rewardCode, loyaltyProgram, rewardInstances);
        }
        else {
            // update RR Rules

            // get re rule by loyalty program  + activity
            rewardRedeemingRules = getRrRulesByLoyaltyProgramRewardCode(loyaltyProgram, rewardCode);

            // if it doesn't exist throw error
            if(rewardRedeemingRules == null) {
                response = "No RRRules exist for loyaltyProgramId " + loyaltyProgram.getLoyaltyProgramId()
                        + " rewardCode " + rewardCode;
                return response;
            }
            // else update values and increment version number
            else {
                updateRRRules(rewardRedeemingRules, user, rrPointsText, rrRuleCodeText, rewardInstances);
            }
        }

//        rewardRedeemingRules.setRewardRedeemingRules(rrRuleCodeText);
//        rewardRedeemingRules.setRrPoints(Integer.valueOf(rrPointsText));
//        rewardRedeemingRules.setRewardCode(rewardCode);
//        rewardRedeemingRules.setVersionNumber(1);
//        rewardRedeemingRules.setLoyaltyProgram(loyaltyProgram.getLoyaltyProgramId());
//        rewardRedeemingRules.setNumberOfInstances(Integer.valueOf(rewardInstances));
//        rewardRedeemingRules.setCreatedBy(user.getUserName());
//        rewardRedeemingRules.setCreatedAt(new Date());
//        rewardRedeemingRules.setUpdatedBy(user.getUserName());


        response = rewardRedeemingRulesDao.createRewardRedeemingRules(rewardRedeemingRules, type);
        return response;
    }

    private void updateRRRules(RewardRedeemingRules rewardRedeemingRules, User user, String rrPointsText, String rrRuleCodeText,
                               String rewardInstances) {
        rewardRedeemingRules.setRrPoints(Integer.valueOf(rrPointsText));
        rewardRedeemingRules.setRewardRedeemingRules(rrRuleCodeText);
        rewardRedeemingRules.setNumberOfInstances(Integer.valueOf(rewardInstances));
        rewardRedeemingRules.setVersionNumber(rewardRedeemingRules.getVersionNumber() + 1);

        rewardRedeemingRules.setCreatedBy(user.getUserName());
        rewardRedeemingRules.setCreatedAt(new Date());
        rewardRedeemingRules.setUpdatedBy(user.getUserName());
    }

    private RewardRedeemingRules getRrRulesByLoyaltyProgramRewardCode(LoyaltyProgram loyaltyProgram, String rewardCode) {
        RewardRedeemingRules rewardRedeemingRules = rewardRedeemingRulesDao.fetchRrRulesByLoyaltyProgramRewardCode(loyaltyProgram, rewardCode);
        return rewardRedeemingRules;
    }

    private LoyaltyProgram getLoyaltyProgramInfo(User user) throws SQLException {
        BrandService brandService = new BrandService();
        Brand brand = brandService.getBrandInfoByUserName(user.getUserName());

        LoyaltyProgramService loyaltyProgramService = new LoyaltyProgramService();
        LoyaltyProgram loyaltyProgram = loyaltyProgramService.fetchLoyaltyProgramByBrand(brand.getBrand_id());

        return loyaltyProgram;
    }

    private RewardRedeemingRules addRRRules(User user, String rrRuleCodeText, String rrPointsText, String rewardCode, LoyaltyProgram loyaltyProgram, String rewardInstances) {
        RewardRedeemingRules rewardRedeemingRules = new RewardRedeemingRules();
        rewardRedeemingRules.setRewardRedeemingRules(rrRuleCodeText);
        rewardRedeemingRules.setRrPoints(Integer.valueOf(rrPointsText));
        rewardRedeemingRules.setRewardCode(rewardCode);
        rewardRedeemingRules.setVersionNumber(1);
        rewardRedeemingRules.setLoyaltyProgram(loyaltyProgram.getLoyaltyProgramId());
        rewardRedeemingRules.setNumberOfInstances(Integer.valueOf(rewardInstances));
        rewardRedeemingRules.setCreatedBy(user.getUserName());
        rewardRedeemingRules.setCreatedAt(new Date());
        rewardRedeemingRules.setUpdatedBy(user.getUserName());

        return rewardRedeemingRules;
    }

    private Brand getBrandInfo(User user) throws SQLException {
        BrandService brandService = new BrandService();
        System.out.println("user name -->" + user.getUserName());
        Brand brand = brandService.getBrandInfoByUserName(user.getUserName());
        return brand;
    }
}
