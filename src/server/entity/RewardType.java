package server.entity;

import java.sql.Timestamp;
import java.util.Date;

public class RewardType {
    public String reward_name;
    public String reward_code;

    public Byte deleted;
    public String createdBy;
    public Date createdAt;
    public String updatedBy;
    public Timestamp updatedAt;

    public String getReward_name(){ return reward_name;}

    public void setReward_name(String reward_name) {
        this.reward_name = reward_name;
    }

    public void setReward_code(String reward_code) {
        this.reward_code = reward_code;
    }

    public String getReward_code(){ return reward_code;}

    public Byte getDeleted() {
        return deleted;
    }

    public void setDeleted(Byte deleted) {
        this.deleted = deleted;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }


}
