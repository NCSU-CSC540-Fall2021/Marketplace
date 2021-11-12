package client;

import server.dao.ActivityTypeDao;
import server.entity.*;
import server.service.*;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ReferAFriend extends JFrame {
    private JButton goBackButton;
    private JButton referAFriendButton;
    private JTextField brandName;
    private JTextField referDetails;
    private JPanel referAFriendPanel;
    private JTextField dateField;
    private JFrame jFrame;
    User user;

    public ReferAFriend() {
        goBackButton.addActionListener(e -> {
            CustomerRewardActivitiesCreation customerRewardActivitiesCreation = new CustomerRewardActivitiesCreation();
            customerRewardActivitiesCreation.selectRewardActivity(user);
            jFrame.setVisible(false);
        });
        referAFriendButton.addActionListener(e -> {
            try {
                submit();
            } catch (ParseException | SQLException|NullPointerException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "This activity is not supported by this brand's loyalty program.");
                jFrame.setVisible(false);
            }
        });
    }

    public void showReferAFriend(User user) {
        this.user = user;
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
        try {
            String activity_code = new ActivityTypeDao().findActivityCodeByName("Refer a friend");
            Brand brand = new BrandService().getBrandInfoByUserName(brandName.getText());
            LoyaltyProgram loyaltyProgram = new LoyaltyProgramService().fetchLoyaltyProgramByBrand(brand.getBrand_id());
            RewardEarningRules rewardEarningRules = new RewardEarningService().getReRulesByLoyaltyProgramActivityCode(loyaltyProgram, activity_code);
            Customer customer = new CustomerService().getCustomerInfoByUserName(user.getUserName());

            LoyaltyActivityLog loyaltyActivityLog = new LoyaltyActivityLog();
            loyaltyActivityLog.setActivity_code(activity_code);
            loyaltyActivityLog.setCustomer_id(customer.getId());
            loyaltyActivityLog.setReward_earning_code(rewardEarningRules.getRewardEarningCode());
            loyaltyActivityLog.setPoints_gained(rewardEarningRules.getRePoints());
            loyaltyActivityLog.setSummary(referDetails.getText());
            loyaltyActivityLog.setLoyalty_program_id(loyaltyProgram.getLoyaltyProgramId());
            loyaltyActivityLog.setCreated_at(new SimpleDateFormat("MM/dd/yyyy").parse(dateField.getText()));

            LoyaltyActivityService loyaltyActivityService = new LoyaltyActivityService();
            String resp = loyaltyActivityService.createReview(loyaltyActivityLog);

            JOptionPane.showMessageDialog(this, resp);
            CustomerRewardActivitiesCreation customerRewardActivitiesCreation = new CustomerRewardActivitiesCreation();
            customerRewardActivitiesCreation.selectRewardActivity(user);
            jFrame.setVisible(false);
        } catch (SQLException|NullPointerException exception) {
            System.out.println("exception raised.");
            JOptionPane.showMessageDialog(this, "This activity is not supported by this brand");
            jFrame.setVisible(false);
        }
    }
}
