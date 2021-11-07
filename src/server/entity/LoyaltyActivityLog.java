package server.entity;

import java.sql.Timestamp;
import java.util.Date;

public class LoyaltyActivityLog {
    public Integer loyalty_activity_log_id;
    public String activity_code;
    public String reward_earning_code;
    public Integer customer_id;
    public Integer points_gained;
    public Integer deleted;
    public Timestamp updatedAt;

    public Integer getLoyalty_activity_log_id() {
        return loyalty_activity_log_id;
    }

    public void setLoyalty_activity_log_id(Integer loyalty_activity_log_id) {
        this.loyalty_activity_log_id = loyalty_activity_log_id;
    }

    public String getActivity_code() {
        return activity_code;
    }

    public void setActivity_code(String activity_code) {
        this.activity_code = activity_code;
    }

    public String getReward_earning_code() {
        return reward_earning_code;
    }

    public void setReward_earning_code(String reward_earning_code) {
        this.reward_earning_code = reward_earning_code;
    }

    public Integer getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(Integer customer_id) {
        this.customer_id = customer_id;
    }

    public Integer getPoints_gained() {
        return points_gained;
    }

    public void setPoints_gained(Integer points_gained) {
        this.points_gained = points_gained;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

}
