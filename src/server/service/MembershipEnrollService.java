package server.service;

import server.dao.MembershipDao;
import server.entity.Membership;

import java.sql.SQLException;

public class MembershipEnrollService {
    private MembershipDao membershipDao;

    public String enrollCustomer(Integer customer_id, Integer loyalty_program_id) throws SQLException {
        membershipDao = new MembershipDao();
        return membershipDao.enrollCustomerLoyalty(customer_id, loyalty_program_id);
    }
}
