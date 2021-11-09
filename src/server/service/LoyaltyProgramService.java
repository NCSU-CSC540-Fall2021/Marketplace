package server.service;

import server.dao.LoyaltyProgramDao;
import server.entity.Brand;
import server.entity.LoyaltyProgram;
import server.entity.User;

import java.sql.SQLException;
import java.util.Date;

public class LoyaltyProgramService {
    LoyaltyProgramDao loyaltyProgramDao;

    public LoyaltyProgramService () {
        loyaltyProgramDao = new LoyaltyProgramDao();
    }

    public String createLoyaltyProgram(User user, String numberOfLevelsText, boolean tiered) throws SQLException {
        LoyaltyProgram loyaltyProgram = new LoyaltyProgram();
        Brand brand = getBrandByUser(user);

        loyaltyProgram.setBrandId(brand.getBrand_id());
        loyaltyProgram.setTiered(tiered);
        loyaltyProgram.setNumberOfTiers(Integer.valueOf(numberOfLevelsText));
        loyaltyProgram.setCreatedBy(user.getUserName());
        loyaltyProgram.setCreatedAt(new Date());
        loyaltyProgram.setUpdatedBy(user.getUserName());

        String response = loyaltyProgramDao.createLoyaltyProgram(loyaltyProgram);
        return response;
    }

    private Brand getBrandByUser(User user) throws SQLException {
        BrandService brandService = new BrandService();
        Brand brand = brandService.getBrandInfoByUserName(user.getUserName());
        return brand;
    }

    public LoyaltyProgram fetchLoyaltyProgramByBrand(Integer brand_id) {
        LoyaltyProgram loyaltyProgram = loyaltyProgramDao.fetchLoyaltyProgramByBrand(brand_id);
        return loyaltyProgram;
    }
}
