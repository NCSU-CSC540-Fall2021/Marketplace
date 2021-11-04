package server.service;

import server.dao.UserDao;
import server.entity.User;

import java.sql.SQLException;

public class UserService {

    public String createUser(String usernameText, String passwordText, String role) throws SQLException {

        UserDao userDao = new UserDao();
        User user = new User();

        // todo: validate password
        if(!isValidPassword(passwordText))
            return "Invalid password! Please try again";

        user.setUserName(usernameText);
        user.setPassword(passwordText);
        user.setRole(role);

        String response = userDao.createUser(user);
        System.out.println(response + " for user name " + usernameText + " password " + passwordText);
        return response;
    }

    // Todo: complete this
    private boolean isValidPassword(String passwordText) {
        return true;
    }
}
