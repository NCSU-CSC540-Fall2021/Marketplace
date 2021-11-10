package server.entity;

import java.sql.Timestamp;
import java.util.Date;

public class Wallet {
    public Integer wallet_id;
    public Integer customer_id;
    public Integer loyalty_id;
    public Integer net_points;
    public Integer tier_status;
    public Integer deleted;
    public Date createdAt;
    public String createdBy;
    public String updatedBy;
    public Timestamp updatedAt;

    public Integer getWallet_id() {
        return wallet_id;
    }

    public void setWallet_id(Integer wallet_id) {
        this.wallet_id = wallet_id;
    }

    public Integer getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(Integer customer_id) {
        this.customer_id = customer_id;
    }

    public Integer getLoyalty_id() {
        return loyalty_id;
    }

    public void setLoyalty_id(Integer loyalty_id) {
        this.loyalty_id = loyalty_id;
    }

    public Integer getNet_points() {
        return net_points;
    }

    public void setNet_points(Integer net_points) {
        this.net_points = net_points;
    }

    public Integer getTier_status() {
        return tier_status;
    }

    public void setTier_status(Integer tier_status) {
        this.tier_status = tier_status;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
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
