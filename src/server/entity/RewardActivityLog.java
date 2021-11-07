package server.entity;

import java.sql.Timestamp;
import java.util.Date;

public class RewardActivityLog {
    public Integer reward_activity_log_id;
    public String reward_instance_code;
    public Integer customer_id;
    public Integer brand_id;
    public Date start_date;
    public Date end_date;
    public String rr_code;
    public Integer expired;
    public Integer redeemed;
    public Integer deleted;
    public Timestamp updatedAt;

    public Integer getReward_activity_log_id() {
        return reward_activity_log_id;
    }

    public void setReward_activity_log_id(Integer reward_activity_log_id) {
        this.reward_activity_log_id = reward_activity_log_id;
    }

    public String getReward_instance_code() {
        return reward_instance_code;
    }

    public void setReward_instance_code(String reward_instance_code) {
        this.reward_instance_code = reward_instance_code;
    }

    public Integer getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(Integer customer_id) {
        this.customer_id = customer_id;
    }

    public Integer getBrand_id() {
        return brand_id;
    }

    public void setBrand_id(Integer brand_id) {
        this.brand_id = brand_id;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public String getRr_code() {
        return rr_code;
    }

    public void setRr_code(String rr_code) {
        this.rr_code = rr_code;
    }

    public Integer getExpired() {
        return expired;
    }

    public void setExpired(Integer expired) {
        this.expired = expired;
    }

    public Integer getRedeemed() {
        return redeemed;
    }

    public void setRedeemed(Integer redeemed) {
        this.redeemed = redeemed;
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
