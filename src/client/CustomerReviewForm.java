package client;

import server.dao.ActivityTypeDao;
import server.entity.*;
import server.service.*;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class CustomerReviewForm extends JFrame {
    private JTextField username;
    private JTextField brandName;
    private JButton goBackButton;
    private JButton leaveAReviewButton;
    private JPanel leaveAReviewPanel;
    private JTextField reviewContent;
    private JTextField dateField;
    private JFrame jFrame;
    User user;

    public CustomerReviewForm() {
        leaveAReviewButton.addActionListener(e -> {
            try {
                submit();
            } catch (ParseException|SQLException|NullPointerException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "This activity is not supported by this brand's loyalty program.");
                jFrame.setVisible(false);
            }
        });
        goBackButton.addActionListener(e-> {
            CustomerRewardActivitiesCreation customerRewardActivitiesCreation = new CustomerRewardActivitiesCreation();
            customerRewardActivitiesCreation.selectRewardActivity(user);
            jFrame.setVisible(false);
        });
    }

    public void leaveAReviewForm(User user) {
        this.user = user;
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
        try {
            String activity_code = new ActivityTypeDao().findActivityCodeByName("Write a review");
            Brand brand = new BrandService().getBrandInfoByUserName(brandName.getText());
            LoyaltyProgram loyaltyProgram = new LoyaltyProgramService().fetchLoyaltyProgramByBrand(brand.getBrand_id());
            RewardEarningRules rewardEarningRules = new RewardEarningService().getReRulesByLoyaltyProgramActivityCode(loyaltyProgram, activity_code);
            Customer customer = new CustomerService().getCustomerInfoByUserName(user.getUserName());

            LoyaltyActivityLog loyaltyActivityLog = new LoyaltyActivityLog();
            loyaltyActivityLog.setActivity_code(activity_code);
            loyaltyActivityLog.setCustomer_id(customer.getId());
            loyaltyActivityLog.setReward_earning_code(rewardEarningRules.getRewardEarningCode());
            loyaltyActivityLog.setPoints_gained(rewardEarningRules.getRePoints());
            loyaltyActivityLog.setSummary(reviewContent.getText());
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
            JOptionPane.showMessageDialog(this, "This activity is not supported by this brand's loyalty program.");
            jFrame.setVisible(false);
        }
    }
}