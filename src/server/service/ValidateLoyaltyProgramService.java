package server.service;

import server.entity.Brand;
import server.entity.LoyaltyProgram;
import server.entity.User;

import java.sql.SQLException;

public class ValidateLoyaltyProgramService {
    BrandService brandService;
    LoyaltyProgramService loyaltyProgramService;
    RewardEarningService rewardEarningService;
    RewardRedeemingService rewardRedeemingService;

    public ValidateLoyaltyProgramService () {
        brandService = new BrandService();
        loyaltyProgramService = new LoyaltyProgramService();
        rewardEarningService = new RewardEarningService();
        rewardRedeemingService = new RewardRedeemingService();
    }

    public String validateLoyaltyProgram(User user) throws SQLException {
        String response = "";

        Brand brand = brandService.getBrandInfoByUserName(user.getUserName());
        LoyaltyProgram loyaltyProgram = loyaltyProgramService.fetchLoyaltyProgramByBrand(brand.getBrand_id());

        // get the loyalty program for the brand
            // if there does not exist any loyalty program, throw a message saying
            // "no loyalty program associated with the brand"

        if(loyaltyProgram == null) {
            response = "No Loyalty Program associated with Brand " + brand.getBrand_name();
            return response;
        }

        // if its tiered
            // if no of tiers is 0, throw error saying - "number of tiers not specified for tiered setup"
        // if tier setup is not mentioned, throw error saying
        // "no tier setup is done properly"
        if(loyaltyProgram.getTiered() && loyaltyProgram.getNumberOfTiers() == 0) {
            response = "Number of tiers not specified for tiered setup";
            return response;
        }

        // check if re rules exist
            // if no re rules exist, then throw error saying " no re rules specified"
        Integer reRulesCountByLoyaltyProgram = rewardEarningService.getReRulesCountByLoyaltyProgram(loyaltyProgram);
        if(reRulesCountByLoyaltyProgram == 0) {
            response = "No RE Rules present for the brand " + brand.getBrand_name();
            return response;
        }

        // check if rr rules exist
            // if no rr rules exist, then throw error saying " no rr rules specified"
        Integer rrRulesCountByLoyaltyProgram = rewardRedeemingService.getRrRulesCountByLoyaltyProgram(loyaltyProgram);
        if(rrRulesCountByLoyaltyProgram == 0) {
            response = "No RR Rules for the brand " + brand.getBrand_name();
            return response;
        }

        // finally return saying valid loyalty program
        response = "Loyalty Program validated successfully!";
        return response;
    }
}
