package server.entity;

import java.sql.Timestamp;
import java.util.Date;

public class RewardRedeemingRules {

    public Integer rewardRedeemingRules;
    public Integer brandId;
    public String rewardCode;
    public Integer rrPoints;
    public Integer versionNumber;
    public Integer numberOfInstances;
    public Byte deleted;
    public Date createdAt;
    public String createdBy;
    public Timestamp updatedAt;
    public String updatedBy;

    public Integer getRewardRedeemingRules() {
        return rewardRedeemingRules;
    }

    public void setRewardRedeemingRules(Integer rewardRedeemingRules) {
        this.rewardRedeemingRules = rewardRedeemingRules;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public String getRewardCode() {
        return rewardCode;
    }

    public void setRewardCode(String rewardCode) {
        this.rewardCode = rewardCode;
    }

    public Integer getRrPoints() {
        return rrPoints;
    }

    public void setRrPoints(Integer rrPoints) {
        this.rrPoints = rrPoints;
    }

    public Integer getVersionNumber() {
        return versionNumber;
    }

    public void setVersionNumber(Integer versionNumber) {
        this.versionNumber = versionNumber;
    }

    public Integer getNumberOfInstances() {
        return numberOfInstances;
    }

    public void setNumberOfInstances(Integer numberOfInstances) {
        this.numberOfInstances = numberOfInstances;
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
