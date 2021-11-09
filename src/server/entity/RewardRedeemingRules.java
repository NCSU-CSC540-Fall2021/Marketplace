package server.entity;

import java.sql.Timestamp;
import java.util.Date;

public class RewardRedeemingRules {

    public Integer reward_redeeming_rules_id;
    public String rewardRedeemingRules; // This is RR_Code (alpha numeric form)
    public Integer loyaltyProgram;
    public String rewardCode;
    public Integer rrPoints;
    public Integer versionNumber;
    public Integer numberOfInstances;

    public Byte deleted;
    public Date createdAt;
    public String createdBy;
    public Timestamp updatedAt;
    public String updatedBy;

    public String getRewardRedeemingRules() {
        return rewardRedeemingRules;
    }

    public void setRewardRedeemingRules(String rewardRedeemingRules) {
        this.rewardRedeemingRules = rewardRedeemingRules;
    }

    public Integer getReward_redeeming_rules_id() {
        return reward_redeeming_rules_id;
    }

    public void setReward_redeeming_rules_id(Integer reward_redeeming_rules_id) {
        this.reward_redeeming_rules_id = reward_redeeming_rules_id;
    }

    public Integer getLoyaltyProgram() {
        return loyaltyProgram;
    }

    public void setLoyaltyProgram(Integer loyaltyProgram) {
        this.loyaltyProgram = loyaltyProgram;
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
