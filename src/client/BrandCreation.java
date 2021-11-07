package client;

import server.constants.Roles;
import server.entity.User;
import server.service.BrandService;
import server.service.UserService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;

import static server.utils.MarketplaceHelper.getDefaultAdminId;

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

        UserService userService = new UserService();
        userService.createUser(usernameText, passwordText, Roles.BRAND.getDesc());

        BrandService brandService = new BrandService();
        String response = brandService.createBrand(brandNameText, brandAddressText, brandJoinDateText, brandCreatorIdText, usernameText);

        JOptionPane.showMessageDialog(this, response);
        jFrame.setVisible(false);
    }


}
