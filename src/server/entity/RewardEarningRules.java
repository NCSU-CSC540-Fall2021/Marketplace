package server.entity;

import java.sql.Timestamp;
import java.util.Date;

public class RewardEarningRules {

    public Integer rewardEarningRulesId;
    public String rewardEarningCode;
    public Integer brandId;
    public Integer versionNumber;
    public String activityCode;
    public Integer rePoints;
    public Byte deleted;
    public Date createdAt;
    public String createdBy;
    public Timestamp updatedAt;
    public String updatedBy;

    public Integer getRewardEarningRulesId() {
        return rewardEarningRulesId;
    }

    public void setRewardEarningRulesId(Integer rewardEarningRulesId) {
        this.rewardEarningRulesId = rewardEarningRulesId;
    }

    public String getRewardEarningCode() {
        return rewardEarningCode;
    }

    public void setRewardEarningCode(String rewardEarningCode) {
        this.rewardEarningCode = rewardEarningCode;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public Integer getVersionNumber() {
        return versionNumber;
    }

    public void setVersionNumber(Integer versionNumber) {
        this.versionNumber = versionNumber;
    }

    public String getActivityCode() {
        return activityCode;
    }

    public void setActivityCode(String activityCode) {
        this.activityCode = activityCode;
    }

    public Integer getRePoints() {
        return rePoints;
    }

    public void setRePoints(Integer rePoints) {
        this.rePoints = rePoints;
    }

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

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }
}
