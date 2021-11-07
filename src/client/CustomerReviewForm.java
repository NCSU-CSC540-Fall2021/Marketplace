package client;

import server.service.LoyaltyActivityService;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.text.ParseException;

public class CustomerReviewForm extends JFrame {
    private JTextField username;
    private JTextField brandName;
    private JButton goBackButton;
    private JButton leaveAReviewButton;
    private JPanel leaveAReviewPanel;
    private JTextField reviewContent;
    private JFrame jFrame;

    public CustomerReviewForm() {
        leaveAReviewButton.addActionListener(e -> {
            try {
                submit();
            } catch (ParseException|SQLException ex) {
                ex.printStackTrace();
            }
        });
        goBackButton.addActionListener(e-> {
            System.out.println("Going Back.");
            jFrame.setVisible(false);
        });
    }

    public void leaveAReviewForm() {
        jFrame = new JFrame("Customer: Leave a review");
        jFrame.setPreferredSize(new Dimension(500, 500));
        jFrame.setResizable(false);
        jFrame.add(leaveAReviewPanel);
        jFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        jFrame.pack();
        jFrame.setVisible(true);
        jFrame.setLocationRelativeTo(null);
    }

    public void submit() throws ParseException, SQLException {
        String usernameText = username.getText();
        String brandNameText = brandName.getText();
        String reviewText = reviewContent.getText();

        System.out.println("Submitted successfully " + brandNameText + " "+ usernameText + " " + reviewText + " Review.");
        LoyaltyActivityService loyaltyActivityService = new LoyaltyActivityService();
        String resp = loyaltyActivityService.createReview(usernameText, brandNameText, reviewText);
    }
}