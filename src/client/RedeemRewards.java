package client;

import server.entity.*;
import server.service.BrandService;
import server.service.CustomerService;
import server.service.RewardActivityLogService;
import server.service.RewardRedeemingService;

import javax.swing.*;
import java.awt.*;
import java.text.DateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.UUID;

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
        goBackButton.addActionListener(e -> {
//            CustomerLanding customerLanding = new CustomerLanding();
//            customerLanding.showInput(user);
            jFrame.setVisible(false);
        });

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
        System.out.println(rewardRedeemingRules.toString());

        RewardActivityLog rewardActivityLog = new RewardActivityLog();
        rewardActivityLog.setCustomer_id(customer.getId());
        rewardActivityLog.setLoyalty_program_id(loyaltyProgram.getLoyaltyProgramId());
        rewardActivityLog.setReward_instance_code(getUniqueRewardInstanceCode());
        rewardActivityLog.setRr_code(rewardRedeemingRules.getRewardRedeemingRules());
        rewardActivityLog.setCreatedAt(date);
        rewardActivityLog.setRedeemed(1);
        rewardActivityLog.setExpired(0);
        rewardActivityLog.setPoints_redeemed(rewardRedeemingRules.getRrPoints());
        rewardActivityLog.setStart_date(date);
        rewardActivityLog.setEnd_date(getRewardEndDate(date));
        rewardActivityLog.setCreatedBy(user.getUserName());
        rewardActivityLog.setUpdatedBy(user.getUserName());

        RewardActivityLogService rewardActivityLogService = new RewardActivityLogService();
        String response = rewardActivityLogService.createRewardActivityLog(rewardActivityLog);

        JOptionPane.showMessageDialog(this, response);
//        CustomerLanding customerLanding = new CustomerLanding();
//        customerLanding.showInput(user);
        jFrame.setVisible(false);

    }

    private String getUniqueRewardInstanceCode() {
        String uuid = UUID.randomUUID().toString().replace("-", "");
        System.out.println("uuid = " + uuid);
        return uuid;
    }

    private Date getRewardEndDate(Date date) {
        String DATE_FORMAT = "yyyy/MM/dd HH:mm:ss";
        DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
        DateTimeFormatter dateFormat8 = DateTimeFormatter.ofPattern(DATE_FORMAT);

        // convert date to localdatetime
        LocalDateTime localDateTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        System.out.println("localDateTime : " + dateFormat8.format(localDateTime));

        localDateTime = localDateTime.plusDays(90);

        // convert LocalDateTime to date
        Date rewardEndDate = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        System.out.println("\nOutput : " + dateFormat.format(rewardEndDate));
        return rewardEndDate;

    }
}
