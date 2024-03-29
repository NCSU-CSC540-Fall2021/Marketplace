package client;

import server.constants.Roles;
import server.entity.User;
import server.service.BrandService;
import server.service.UserService;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.text.ParseException;


public class BrandCreation extends JFrame{
    private JPanel brandCreate;
    private JTextField brandName;
    private JTextField brandAddress;
    private JTextField brandJoinDate;
    private JButton createButton;
    private JButton goBackButton;
    private JTextField username;
    private JTextField password;
    private JFrame jFrame;
    private User user;

    public BrandCreation()
    {
        createButton.addActionListener(e -> {
            try {
                submit();
            } catch (ParseException | SQLException ex) {
                ex.printStackTrace();
            }
        });

        goBackButton.addActionListener(e -> {
            if(user == null) {
                Homepage homepage = new Homepage();
                homepage.showHomePage();
            }
            else {
                AdminLandingPage adminLandingPage = new AdminLandingPage();
                adminLandingPage.selectMenuOption(user);
            }
        });
    }

    public void showFormForInput(User user) {
        this.user = user;
        jFrame = new JFrame("Create Brand");
        jFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        jFrame.setPreferredSize(new Dimension(500, 500));
        jFrame.setResizable(false);

        jFrame.add(brandCreate);
        jFrame.pack();
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);
    }

    public void submit() throws ParseException, SQLException {
        String brandNameText = brandName.getText();
        String brandAddressText = brandAddress.getText();
        String brandJoinDateText = brandJoinDate.getText();
        String usernameText = username.getText();
        String passwordText = password.getText();

        String brandCreatorIdText = user != null ? user.getUserName() : usernameText;

        System.out.println("Submitted successfully " + brandNameText + " "
                + brandAddressText + " " + brandJoinDateText + " " + brandCreatorIdText);

        System.out.println("Creating user for brand with username " + usernameText + " and password " + passwordText);
        UserService userService = new UserService();
        userService.createUser(usernameText, passwordText, Roles.BRAND.getDesc());
        System.out.println("User " + usernameText + " created successfully!");

        System.out.println("Creating brand with username " + usernameText);
        BrandService brandService = new BrandService();
        String response = brandService.createBrand(brandNameText, brandAddressText, brandJoinDateText, brandCreatorIdText, usernameText);
        System.out.println("Brand created successfully!");

        JOptionPane.showMessageDialog(this, response);
        jFrame.setVisible(false);
        if(user == null) {
            Homepage homepage = new Homepage();
            homepage.showHomePage();
        }
        else {
            AdminLandingPage adminLandingPage = new AdminLandingPage();
            adminLandingPage.selectMenuOption(user);
        }
    }
}
