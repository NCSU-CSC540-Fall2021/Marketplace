package server.service;

import server.dao.CustomerDao;
import server.entity.Customer;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;

import static server.utils.MarketplaceHelper.getDefaultAdminId;

public class CustomerService {

    public CustomerService() {

    }

    public String createCustomer(String customerName, String address,
                                 String phoneNumber, String customerCreatorId, String usernameText) throws ParseException, SQLException {

        CustomerDao customerDao = new CustomerDao();
        Customer customer = new Customer();

        // todo: do all validations here
        customer.setCname(customerName);
        customer.setAddress(address);
        customer.setPhone_no(phoneNumber);
        customer.setUserName(usernameText);
        customer.setCreatedBy(customerCreatorId != null ? Integer.valueOf(customerCreatorId) : getDefaultAdminId());
        customer.setCreatedAt(new Date());
        customer.setUpdatedBy(customerCreatorId != null ? Integer.valueOf(customerCreatorId) : getDefaultAdminId());

        String response = customerDao.createCustomer(customer);
        return response;
    }

}
