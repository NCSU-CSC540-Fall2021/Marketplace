package client;

import server.constants.Roles;
import server.entity.User;
import server.service.BrandService;
import server.service.CustomerService;
import server.service.UserService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class CustomerCreation extends JFrame{
    private JTextField customerName;
    private JTextField address;
    private JTextField phoneNumber;
    private JTextField username;
    private JTextField password;
    private JButton registerButton;
    private JFrame jFrame;
    private JPanel customerCreate;
    private JButton goBack;
    private User user;

    public CustomerCreation() {
        registerButton.addActionListener(e -> {
            try {
                submit();
            } catch (ParseException | SQLException ex) {
                ex.printStackTrace();
            }
        });
        goBack.addActionListener(e -> {
            if(user == null) {
                Homepage homepage = new Homepage();
                homepage.showHomePage();
                jFrame.setVisible(false);
            }
            else {
                AdminLandingPage adminLandingPage = new AdminLandingPage();
                adminLandingPage.selectMenuOption(user);
                jFrame.setVisible(false);
            }
        });
    }

    public void showFormForInput(User user) {
        this.user = user;
        jFrame = new JFrame("Create Customer");
        jFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        jFrame.setPreferredSize(new Dimension(500, 500));
        jFrame.setResizable(false);

        jFrame.add(customerCreate);
        jFrame.pack();
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);
    }

    public void submit() throws ParseException, SQLException {
        String customerNameText = customerName.getText();
        String addressText = address.getText();
        String phoneNumberText = phoneNumber.getText();
        String usernameText = username.getText();
        String passwordText = password.getText();
        String adminIdText = user != null ? user.getUserName() : usernameText;

        System.out.println("Submitted successfully " + customerNameText + " " + addressText + " " + phoneNumberText + " " + usernameText + " " + passwordText + " " + adminIdText);

        UserService userService = new UserService();
        userService.createUser(usernameText, passwordText, Roles.CUSTOMER.getDesc());

        CustomerService customerService = new CustomerService();
        String response = customerService.createCustomer(customerNameText, phoneNumberText, addressText, adminIdText, usernameText);

        JOptionPane.showMessageDialog(this, response);
        jFrame.setVisible(false);
    }
}
