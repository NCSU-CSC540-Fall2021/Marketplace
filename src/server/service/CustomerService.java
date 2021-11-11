package server.service;

import server.dao.CustomerDao;
import server.entity.Customer;

import java.sql.SQLException;
import java.util.Date;

public class CustomerService {

    CustomerDao customerDao;
    public CustomerService() {
        customerDao = new CustomerDao();
    }

    public String createCustomer(String customerName, String phoneNumber, String address,
                                 String customerCreatorId, String usernameText) throws SQLException {


        Customer customer = new Customer();

        customer.setCustomerName(customerName);
        customer.setAddress(address);
        customer.setPhone_no(phoneNumber);
        customer.setUserName(usernameText);
        customer.setCreatedBy(customerCreatorId);
        customer.setCreatedAt(new Date());
        customer.setUpdatedBy(customerCreatorId);

        String response = customerDao.createCustomer(customer);
        return response;
    }

    public Customer getCustomerInfoByUserName(String customerUserNameText) throws SQLException {
        Customer customer = new Customer();
        customer.setUserName(customerUserNameText);

        customer = customerDao.findCustomerInfoByUserName(customer);
        return customer;
    }
}
