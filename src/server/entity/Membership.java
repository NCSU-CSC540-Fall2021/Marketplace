package server.entity;

public class Membership {
    public Integer membership_id;
    public Integer customer_id;
    public Integer loyalty_program_id;

    public Integer getMembership_id() {
        return membership_id;
    }

    public Integer getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(Integer customer_id) {
        this.customer_id = customer_id;
    }

    public Integer getLoyalty_program_id() {
        return loyalty_program_id;
    }

    public void setLoyalty_program_id(Integer loyalty_program_id) {
        this.loyalty_program_id = loyalty_program_id;
    }
}
