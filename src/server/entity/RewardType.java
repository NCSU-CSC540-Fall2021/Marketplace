package server.entity;

import java.sql.Timestamp;
import java.util.Date;

public class RewardType {
    public String reward_type;
    public String reward_code;

    public Byte deleted;
    public Integer createdBy;
    public Date createdAt;
    public Integer updatedBy;
    public Timestamp updatedAt;

    public String getReward_type(){ return reward_type;}

    public void setReward_type(String reward_type) {
        this.reward_type = reward_type;
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

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public Integer getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Integer updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }


}
