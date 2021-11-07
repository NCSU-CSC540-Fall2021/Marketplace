package server.service;

import server.dao.UserDao;
import server.entity.User;

import java.sql.SQLException;

public class LoginService {

    UserDao userDao = new UserDao();


    public User checkLogin(String usernameText, String passwordText) throws SQLException {
        User user = new User();
        user.setUserName(usernameText);
        user.setPassword(passwordText);

        // perform select on the username password combo and retrieve the info
        user = userDao.fetchUser(user);
        return user;

        // based on that fetch user or brand or customer information
//        if(user.getRole().equals(Roles.CUSTOMER.getDesc())) {
//            Customer customer = customerService.fetchCustomerInformation(user);
//        }
//        else if(user.getRole().equals(Roles.BRAND.getDesc())) {
//            brandService.fetchBrandInformation(user);
//        }

        // return the object to move them to corresponding landing page
    }
}
