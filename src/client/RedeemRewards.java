package client;

import server.entity.*;
import server.service.BrandService;
import server.service.CustomerService;
import server.service.RewardRedeemingService;

import javax.swing.*;
import java.awt.*;
import java.util.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class RedeemRewards extends JFrame{
    private JButton goBackButton;
    private JButton redeemRewardsButton;
    private JTextField brandName;
    private JTextField giftCardCode;
    private JTextField dateField;
    private JPanel redeemRewardsPanel;
    private JFrame jFrame;
    private User user;

    public RedeemRewards() {
        goBackButton.addActionListener(e -> jFrame.setVisible(false));
        redeemRewardsButton.addActionListener(e->{
            try {
                submit();
            } catch (SQLException|ParseException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Some Error occurred.");
                jFrame.setVisible(false);
            }
        });
    }

    public void redeemRewards(User user) {
        this.user = user;
        jFrame = new JFrame("Customer: Redeem Points");
        jFrame.setPreferredSize(new Dimension(500, 500));
        jFrame.setResizable(false);
        jFrame.add(redeemRewardsPanel);
        jFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        jFrame.pack();
        jFrame.setVisible(true);
        jFrame.setLocationRelativeTo(null);
    }

    public void submit() throws SQLException, ParseException {
        String rewardCode = giftCardCode.getText();
        Date date = new SimpleDateFormat("MM/dd/yyyy").parse(dateField.getText());

        Customer customer = new CustomerService().getCustomerInfoByUserName(user.getUserName());
        Brand brand = new BrandService().getBrandInfoByUserName(brandName.getText());

        LoyaltyProgram loyaltyProgram = new LoyaltyProgram();
        loyaltyProgram.setLoyaltyProgramId(brand.getLoyaltyProgramId());

        System.out.println("loyalty program: " + loyaltyProgram.getLoyaltyProgramId());
        RewardRedeemingRules rewardRedeemingRules = new RewardRedeemingService().getRrRulesByLoyaltyProgramRewardCode(loyaltyProgram, rewardCode);
        System.out.println(rewardRedeemingRules);

        RewardActivityLog rewardActivityLog = new RewardActivityLog();
        rewardActivityLog.setCustomer_id(customer.getId());
        rewardActivityLog.setLoyalty_program_id(loyaltyProgram.getLoyaltyProgramId());
        rewardActivityLog.setReward_instance_code(rewardCode);
        rewardActivityLog.setCreatedAt(date);
        rewardActivityLog.setRedeemed(1);
    }
}
