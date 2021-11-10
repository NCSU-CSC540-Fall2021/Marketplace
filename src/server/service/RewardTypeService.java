package server.service;

import server.dao.RewardTypeDao;
import server.entity.RewardType;
import server.entity.User;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import static server.utils.MarketplaceHelper.getDefaultAdminId;

public class RewardTypeService {
    RewardTypeDao rewardTypeDao = new RewardTypeDao();

    public String createRewardType(String rewardNameText, User user) throws SQLException {
        String rewardCode = generateRewardCode();

        RewardType rewardType = new RewardType();
        rewardType.setReward_name(rewardNameText);
        rewardType.setReward_code(rewardCode);
        rewardType.setCreatedBy(user.getUserName());
        rewardType.setCreatedAt(new Date());
        rewardType.setUpdatedBy(user.getUserName());

        String response = rewardTypeDao.createReward(rewardType);
        return response;
    }


    /**
     * Should return an alphanumeric activity code
     * @return
     */
    private String generateRewardCode() throws SQLException {
        int totalRewardsCount = rewardTypeDao.getTotalRewardsCount();
        String rewardCode = "R" + (totalRewardsCount + 1);
        System.out.println("reward code is " + rewardCode);
        return rewardCode;
    }

    public List<RewardType> getAllRewards() throws SQLException {
        List<RewardType> allRewards = rewardTypeDao.getAllRewards();
        return allRewards;
    }
}
