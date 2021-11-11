package client;


import server.entity.User;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class CustomerLanding extends JFrame {
    private JPanel customerLanding;
    private JButton enrollInLoyaltyProgramButton;
    private JButton rewardActivitiesButton;
    private JButton viewWalletButton;
    private JButton redeemPointsButton;
    private JButton logOutButton;
    private JFrame jFrame;
    User user;

    public CustomerLanding() {
        enrollInLoyaltyProgramButton.addActionListener(e -> {
            CustomerLoyaltySignUp customerLoyaltySignUp = new CustomerLoyaltySignUp();
            customerLoyaltySignUp.customerLoyaltyForm(user);
            jFrame.setVisible(false);
        });
        rewardActivitiesButton.addActionListener(e -> {
            CustomerRewardActivitiesCreation customerRewardActivitiesCreation = new CustomerRewardActivitiesCreation();
            customerRewardActivitiesCreation.selectRewardActivity(user);
            jFrame.setVisible(false);
        });
        viewWalletButton.addActionListener(e -> {
            ViewWallet viewWallet = new ViewWallet();
            try {
                viewWallet.showCustomerWallet(user);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
        redeemPointsButton.addActionListener(e -> {});
        logOutButton.addActionListener(e -> {
            Homepage homepage = new Homepage();
            homepage.showHomePage();
            jFrame.setVisible(false);
        });
    }

    public void showInput(User user) {
        this.user = user;
        jFrame = new JFrame("Customer: Landing Page");
        jFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        jFrame.setPreferredSize(new Dimension(500, 500));
        jFrame.setResizable(false);
        jFrame.add(customerLanding);
        jFrame.pack();
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);
    }
}
