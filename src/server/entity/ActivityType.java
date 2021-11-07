package server.entity;

import java.sql.Timestamp;
import java.util.Date;

public class ActivityType {
    public String activity_code;
    public String activity_name;

    public Byte deleted;
    public String createdBy;
    public Date createdAt;
    public String updatedBy;
    public Timestamp updatedAt;

    public String getActivity_code() {
        return activity_code;
    }
    public void setActivity_code(String activity_code) {
        this.activity_code = activity_code;
    }

    public String getActivity_name() {
        return activity_name;
    }

    public void setActivity_name(String activity_name) {
        this.activity_name = activity_name;
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


    @Override
    public String toString() {
        return "activity_type{" +
                "activity_code='" + activity_code + '\'' +
                ", activity_name='" + activity_name + '\'' +
                ", deleted=" + deleted +
                ", createdBy=" + createdBy +
                ", createdAt=" + createdAt +
                ", updatedBy=" + updatedBy +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
