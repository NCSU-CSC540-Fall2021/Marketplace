package server.service;

import server.dao.BrandDao;
import server.dao.CustomerDao;
import server.entity.Brand;
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

    public String createCustomer(String customerName, String phoneNumber, String address,
                                 String customerCreatorId, String usernameText) throws SQLException {


        Customer customer = new Customer();

        customer.setCname(customerName);
        customer.setAddress(address);
        customer.setPhone_no(phoneNumber);
        customer.setUserName(usernameText);
        customer.setCreatedBy(customerCreatorId);
        customer.setCreatedAt(new Date());
        customer.setUpdatedBy(customerCreatorId);

        String response = customerDao.createCustomer(customer);
        return response;
    }

    public Customer fetchCustomerInformation(User user) throws SQLException {
        String userName = user.getUserName();
        Customer customer = customerDao.fetchCustomerByUserName(userName);
        return customer;
    }

    public Customer getCustomerInfoByUserName(String customerUserNameText) throws SQLException {
        Customer customer = new Customer();
        customerDao = new CustomerDao();
        customer.setUserName(customerUserNameText);

        customer = customerDao.findCustomerInfoByUserName(customer);
        return customer;
    }
}
