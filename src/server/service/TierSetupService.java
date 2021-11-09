package server.service;

import server.dao.TierSetupDao;
import server.entity.Brand;
import server.entity.LoyaltyProgram;
import server.entity.TierSetup;
import server.entity.User;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class TierSetupService {
    TierSetupDao tierSetupDao = new TierSetupDao();

    public void createTierSetup(User user, List<TierSetup> tierSetupList) throws SQLException {
        // get brand information
        Brand brand = getBrandByUser(user);

        // get loyalty_id created per brand
        LoyaltyProgram loyaltyProgram = getLoyaltyByBrandId(brand);
        for (TierSetup tierSetup : tierSetupList) {
            tierSetup.setLoyaltyId(loyaltyProgram.getLoyaltyProgramId());
            tierSetup.setCreatedBy(user.getUserName());
            tierSetup.setCreatedAt(new Date());
            tierSetup.setUpdatedBy(user.getUserName());
            System.out.println(tierSetup);
            String response = tierSetupDao.createTierSetup(tierSetup);
            System.out.println(response);
        }
    }

    private LoyaltyProgram getLoyaltyByBrandId(Brand brand) {
        LoyaltyProgramService loyaltyProgramService = new LoyaltyProgramService();
        Integer brand_id = brand.getBrand_id();
        LoyaltyProgram loyaltyProgram = loyaltyProgramService.fetchLoyaltyProgramByBrand(brand_id);
        return loyaltyProgram;
    }

    private Brand getBrandByUser(User user) throws SQLException {
        BrandService brandService = new BrandService();
        Brand brand = brandService.getBrandInfoByUserName(user.getUserName());
        return brand;
    }
}
