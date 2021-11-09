package server.entity;

import java.sql.Timestamp;
import java.util.Date;

public class TierSetup {
    public Integer tierSetupId;
    public Integer loyaltyId;
    public Integer tierLevelCode;
    public String tierLevelName;
    public Integer eligibilityPoints;
    public Integer multiplier;
    public Byte deleted;
    public Date createdAt;
    public String createdBy;
    public Timestamp updatedAt;
    public String updatedBy;

    public Integer getTierSetupId() {
        return tierSetupId;
    }

    public void setTierSetupId(Integer tierSetupId) {
        tierSetupId = tierSetupId;
    }

    public Integer getLoyaltyId() {
        return loyaltyId;
    }

    public void setLoyaltyId(Integer loyaltyId) {
        this.loyaltyId = loyaltyId;
    }

    public Integer getTierLevelCode() {
        return tierLevelCode;
    }

    public void setTierLevelCode(Integer tierLevelCode) {
        this.tierLevelCode = tierLevelCode;
    }

    public String getTierLevelName() {
        return tierLevelName;
    }

    public void setTierLevelName(String tierLevelName) {
        this.tierLevelName = tierLevelName;
    }

    public Integer getEligibilityPoints() {
        return eligibilityPoints;
    }

    public void setEligibilityPoints(Integer eligibilityPoints) {
        this.eligibilityPoints = eligibilityPoints;
    }

    public Integer getMultiplier() {
        return multiplier;
    }

    public void setMultiplier(Integer multiplier) {
        this.multiplier = multiplier;
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

    @Override
    public String toString() {
        return "TierSetup{" +
                "tierSetupId=" + tierSetupId +
                ", loyaltyId=" + loyaltyId +
                ", tierLevelCode=" + tierLevelCode +
                ", tierLevelName='" + tierLevelName + '\'' +
                ", eligibilityPoints=" + eligibilityPoints +
                ", multiplier=" + multiplier +
                ", deleted=" + deleted +
                ", createdAt=" + createdAt +
                ", createdBy='" + createdBy + '\'' +
                ", updatedAt=" + updatedAt +
                ", updatedBy='" + updatedBy + '\'' +
                '}';
    }
}
