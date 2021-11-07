package server.service;

import server.dao.ActivityTypeDao;
import server.entity.ActivityType;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class ActivityTypeService {
    ActivityTypeDao activityTypeDao = new ActivityTypeDao();

    public String createActivityType(String activityNameText, String userName) throws SQLException {
        String activityCode = generateActivityCode();

        ActivityType activityType = new ActivityType();
        activityType.setActivity_name(activityNameText);
        activityType.setActivity_code(activityCode);
        activityType.setCreatedBy(userName);
        activityType.setCreatedAt(new Date());
        activityType.setUpdatedBy(userName);

        String response = activityTypeDao.createActivity(activityType);
        return response;
    }

    /**
     * Should return an alphanumeric activity code
     * @return
     */
    private String generateActivityCode() throws SQLException {
        int totalActivitiesCount = activityTypeDao.getTotalActivitiesCount();
        String activityCode = "A" + (totalActivitiesCount + 1);
        System.out.println("activity code is " + activityCode);
        return activityCode;
    }

    public List<ActivityType> getAllActivities() throws SQLException {
        List<ActivityType> allActivities = activityTypeDao.getAllActivities();
//        Object[] activityTypes =  allActivities.toArray();
        return allActivities;
    }
}
