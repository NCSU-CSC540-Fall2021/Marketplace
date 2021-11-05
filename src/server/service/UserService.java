package server.service;

import server.dao.UserDao;
import server.entity.User;

import java.sql.SQLException;
import java.util.Scanner;

public class UserService {

    public static void main(String[] args) {
        UserService user = new UserService();
        String password = "123Thuan#";
        System.out.println(user.isValidPassword(password));
    }

    public String createUser(String usernameText, String passwordText, String role) throws SQLException {

        UserDao userDao = new UserDao();
        User user = new User();

        // todo: validate password
        if (!isValidPassword(passwordText))
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
        // Use Scanner to validate input password.
        Scanner sc = new Scanner(passwordText);
        //^                 # start-of-string
        //(?=.*[0-9])       # a digit must occur at least once
        //(?=.*[a-z])       # a lower case letter must occur at least once
        //(?=.*[A-Z])       # an upper case letter must occur at least once
        //(?=.*[@#$%^&+=])  # a special character must occur at least once
        //(?=\S+$)          # no whitespace allowed in the entire string
        //.{8,}             # anything, at least eight places though
        //$                 # end-of-string
        String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
        // Return boolean value.
        return sc.hasNext(regex);
    }
}
