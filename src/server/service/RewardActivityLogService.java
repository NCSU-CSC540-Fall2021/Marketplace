package server.service;

import server.dao.RewardActivityLogDao;
import server.entity.RewardActivityLog;

import java.sql.SQLException;

public class RewardActivityLogService {
    RewardActivityLogDao rewardActivityLogDao;

    public RewardActivityLogService () {
        rewardActivityLogDao = new RewardActivityLogDao();
    }

    public String createRewardActivityLog(RewardActivityLog rewardActivityLog) throws SQLException {
        String response = rewardActivityLogDao.rewardActivityLog(rewardActivityLog);
        return response;
    }
}
