package server.service;

import server.dao.CustomerDao;
import server.entity.Customer;
import server.entity.User;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;

import static server.utils.MarketplaceHelper.getDefaultAdminId;

public class CustomerService {

    CustomerDao customerDao = new CustomerDao();
    public CustomerService() {

    }

    public String createCustomer(String customerName, String address,
                                 String phoneNumber, String customerCreatorId, String usernameText) throws ParseException, SQLException {


        Customer customer = new Customer();

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

    public Customer fetchCustomerInformation(User user) throws SQLException {
        String userName = user.getUserName();
        Customer customer = customerDao.fetchCustomerByUserName(userName);
        return customer;
    }
}
