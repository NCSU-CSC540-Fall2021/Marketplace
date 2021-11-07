package server.entity;

import java.sql.Timestamp;
import java.util.Date;

public class LoyaltyProgram {
    public Integer loyaltyProgramId;
    public Integer brandId;
    public Boolean tiered;
    public Integer numberOfTiers;
    public Byte deleted;
    public Date createdAt;
    public String createdBy;
    public Timestamp updatedAt;
    public String updatedBy;

    public Integer getLoyaltyProgramId() {
        return loyaltyProgramId;
    }

    public void setLoyaltyProgramId(Integer loyaltyProgramId) {
        this.loyaltyProgramId = loyaltyProgramId;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public Boolean getTiered() {
        return tiered;
    }

    public void setTiered(Boolean tiered) {
        this.tiered = tiered;
    }

    public Integer getNumberOfTiers() {
        return numberOfTiers;
    }

    public void setNumberOfTiers(Integer numberOfTiers) {
        this.numberOfTiers = numberOfTiers;
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
