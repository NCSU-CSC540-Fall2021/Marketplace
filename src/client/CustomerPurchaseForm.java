package client;

import server.service.LoyaltyActivityService;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.text.ParseException;

public class CustomerPurchaseForm extends JFrame {
    private JPanel purchaseOptionsPanel;
    private JTextField username;
    private JTextField brandName;
    private JTextField giftCardCode;
    private JTextField itemPurchased;
    private JButton goBackButton;
    private JButton purchaseButton;
    private JFrame jFrame;

    public CustomerPurchaseForm() {
        purchaseButton.addActionListener(e -> {
            try {
                submit();
            } catch (ParseException | SQLException ex) {
                ex.printStackTrace();
            }
        });
        goBackButton.addActionListener(e -> {
            System.out.println("Going Back.");
            jFrame.setVisible(false);
        });
    }

    public void selectPurchase() {
        jFrame = new JFrame("Customer: Purchase");
        jFrame.setPreferredSize(new Dimension(500, 500));
        jFrame.setResizable(false);
        jFrame.add(purchaseOptionsPanel);
        jFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        jFrame.pack();
        jFrame.setVisible(true);
        jFrame.setLocationRelativeTo(null);
    }

    private void submit() throws ParseException, SQLException {
        String usernameText = username.getText();
        String brandNameText = brandName.getText();
        String giftCard = giftCardCode.getText();
        String item_Purch = itemPurchased.getText();

        System.out.println("Submitted successfully " + brandNameText + " "+ usernameText + " " + giftCard + " " + item_Purch + " purchased.");
        LoyaltyActivityService loyaltyActivityService = new LoyaltyActivityService();
        String resp = loyaltyActivityService.createPurchase(usernameText, brandNameText, giftCard, item_Purch);

        JOptionPane.showMessageDialog(this, resp);
    }
}
