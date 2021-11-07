package client;

import server.constants.Roles;
import server.entity.User;
import server.service.LoginService;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class Loginpage extends JFrame{
    private JTextField username;
    private JTextField password;
    private JButton login;
    private JPanel loginPage;
    private JButton goBackButton;
    private JFrame jFrame;

    LoginService loginService = new LoginService();
    CustomerLanding customerLanding = new CustomerLanding();
    BrandLanding brandLanding = new BrandLanding();
    AdminLandingPage adminLandingPage = new AdminLandingPage();

    public Loginpage() {
        login.addActionListener(e -> {
            try {
                login();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
        goBackButton.addActionListener(e -> {
            Homepage homepage = new Homepage();
            homepage.showHomePage();
        });
    }

    public void showInput() {
        jFrame = new JFrame("Login Page");
        jFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        jFrame.setPreferredSize(new Dimension(500, 500));
        jFrame.setResizable(false);

        jFrame.add(loginPage);
        jFrame.pack();
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);
    }

    public void login() throws SQLException {
        String usernameText = username.getText();
        String passwordText = password.getText();

        System.out.println("Performing login for " + usernameText + " " + passwordText);

        User user = loginService.checkLogin(usernameText, passwordText);
        System.out.println(user.getUserName() + " " + user.getPassword() + " " + user.getRole());

        if(user.getRole().equals(Roles.CUSTOMER.getDesc())) {
            // go to customer landing page
            customerLanding.showInput(user);
        }
        else if(user.getRole().equals(Roles.BRAND.getDesc())) {
            // go to brand landing page
            brandLanding.showInput(user);
        }
        else if(user.getRole().equals(Roles.ADMIN.getDesc())) {
            // go to admin landing page
            adminLandingPage.selectMenuOption(user);
        }
    }
}
