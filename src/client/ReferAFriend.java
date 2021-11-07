package client;

import server.service.LoyaltyActivityService;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.text.ParseException;

public class ReferAFriend extends JFrame {
    private JButton goBackButton;
    private JButton referAFriendButton;
    private JTextField username;
    private JTextField brandName;
    private JTextField referDetails;
    private JPanel referAFriendPanel;
    private JFrame jFrame;

    public ReferAFriend() {
        goBackButton.addActionListener(e -> {
            System.out.println("Going Back.");
            jFrame.setVisible(false);
        });
        referAFriendButton.addActionListener(e -> {
            try {
                submit();
            } catch (ParseException | SQLException ex) {
                ex.printStackTrace();
            }
        });
    }

    public void showReferAFriend() {
        jFrame = new JFrame("Customer: Refer a friend");
        jFrame.setPreferredSize(new Dimension(500, 500));
        jFrame.setResizable(false);
        jFrame.add(referAFriendPanel);
        jFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        jFrame.pack();
        jFrame.setVisible(true);
        jFrame.setLocationRelativeTo(null);
    }

    private void submit() throws ParseException, SQLException {
        String usernameText = username.getText();
        String brandNameText = brandName.getText();
        String referDetailsText = referDetails.getText();

        System.out.println("Submitted successfully " + brandNameText + " "+ usernameText + " " + referDetailsText + " purchased.");
        LoyaltyActivityService loyaltyActivityService = new LoyaltyActivityService();
        String resp = loyaltyActivityService.referAFriend(usernameText, brandNameText, referDetailsText);

        JOptionPane.showMessageDialog(this, resp);
    }
}
