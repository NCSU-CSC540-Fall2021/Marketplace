package client;

import server.constants.BrandLandingOptions;
import server.entity.ActivityType;
import server.entity.RewardType;
import server.entity.User;
import server.service.ActivityTypeService;
import server.service.RewardTypeService;
import server.service.ValidateLoyaltyProgramService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class BrandLanding extends JFrame implements ActionListener {
    private JComboBox brandLandingComboBox;
    private JLabel brandTitleBar;
    private JPanel brandLandingPanel;
    private JFrame jFrame;
    private User user;

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == brandLandingComboBox) {

            String selectedItem = (String) brandLandingComboBox.getSelectedItem();
            System.out.println(selectedItem);
            BrandLandingOptions brandLandingOptions = BrandLandingOptions.getBrandOptionByDescription(selectedItem);
            try {
                performMenuOperation(brandLandingOptions);
            } catch (SQLException ex) {
                ex.printStackTrace();
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }
    }

    public void showInput(User user) {
        this.user = user;
        jFrame = new JFrame("Brand Landing Page");
        jFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        jFrame.setPreferredSize(new Dimension(500, 500));
        jFrame.setResizable(false);

        Object[] brandLandingOptions = BrandLandingOptions.getOptionDescriptionList().toArray();
        brandLandingComboBox = new JComboBox(brandLandingOptions);
        brandTitleBar = new JLabel("Welcome to marketplace " + user.getUserName());
        brandLandingComboBox.addActionListener(this);

        brandLandingPanel = new JPanel();
        brandLandingPanel.setLayout(new BoxLayout(brandLandingPanel, BoxLayout.Y_AXIS));
        brandLandingPanel.add(brandTitleBar);
        brandLandingPanel.add(brandLandingComboBox);
        jFrame.add(brandLandingPanel);
        jFrame.pack();
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);
    }

    private void performMenuOperation(BrandLandingOptions brandLandingOption) throws SQLException, Throwable {
        int optionId = brandLandingOption.getOptionId();

        ActivityTypeService activityTypeService = new ActivityTypeService();
        List<ActivityType> allActivities = activityTypeService.getAllActivities();

        RewardTypeService rewardTypeService = new RewardTypeService();
        List<RewardType> allRewards = rewardTypeService.getAllRewards();

        switch (optionId) {
            case 1:
                // ADD_LOYALTY_PROGRAM
                LoyaltyProgramPage loyaltyProgramPage = new LoyaltyProgramPage();
                loyaltyProgramPage.showInput(user);
                jFrame.setVisible(false);
                break;
            case 2:
                // ADD_RE_RULES
                AddUpdateRERules addRERules = new AddUpdateRERules();
                addRERules.showInput(user, allActivities, 1);
                jFrame.setVisible(false);
                break;
            case 3:
                // UPDATE_RE_RULES
                AddUpdateRERules updateRERules = new AddUpdateRERules();
                updateRERules.showInput(user, allActivities, 2);
                jFrame.setVisible(false);
                break;
            case 4:
                // ADD_RR_RULES
                AddUpdateRRRules addRRRules = new AddUpdateRRRules();
                addRRRules.showInput(user, allRewards, 1);
                jFrame.setVisible(false);
                break;
            case 5:
                // UPDATE_RR_RULES
                AddUpdateRRRules updateRRRules = new AddUpdateRRRules();
                updateRRRules.showInput(user, allRewards, 2);
                jFrame.setVisible(false);
                break;
            case 6 :
                // VALIDATE_LOYALTY_PROGRAM
                ValidateLoyaltyProgramService validateLoyaltyProgramService = new ValidateLoyaltyProgramService();
                String response = validateLoyaltyProgramService.validateLoyaltyProgram(user);
                JOptionPane.showMessageDialog(this, response);
                break;
            case 7:
                // logout
                Homepage homepage = new Homepage();
                homepage.showHomePage();
                jFrame.setVisible(false);
                break;
        }
    }
}
