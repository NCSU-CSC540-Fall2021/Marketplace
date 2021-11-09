package client;

import server.entity.ActivityType;
import server.entity.User;
import server.service.RewardEarningService;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AddRERules extends JFrame {
    private JTextField rePoints;
    private JButton addRERulesButton;
    private JButton goBackButton;
    private JPanel reRulesPanel;
    private User user;
    private JTextField reRuleCode;
    private JComboBox reActivityBox;
    private JLabel rewardRuleCodeEntry;
    private JLabel rePointsEntry;
    private JLabel activitySelect;
    Map<String, String> activityNameToCodeMap = new HashMap<>();
    List<ActivityType> activityTypes;

    public AddRERules() {
        goBackButton.addActionListener(e -> {
            BrandLanding brandLanding = new BrandLanding();
            brandLanding.showInput(user);
            this.setVisible(false);
        });

        addRERulesButton.addActionListener(e -> {
            try {
                submitRERules();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
    }

    private void submitRERules() throws SQLException {
        String reRuleCodeText = reRuleCode.getText();
        String rePointsText = rePoints.getText();
        Object selectedItem = reActivityBox.getSelectedItem();
        activityNameToCodeMap = activityTypes.stream().collect(Collectors.toMap(ActivityType::getActivity_name, ActivityType::getActivity_code));
        String activityCode = activityNameToCodeMap.get(selectedItem);
        System.out.println(reRuleCodeText);
        System.out.println(rePointsText);
        System.out.println(selectedItem);
        RewardEarningService rewardEarningService = new RewardEarningService();
        String response = rewardEarningService.addRewardEarning(reRuleCodeText, rePointsText, activityCode, user);

        JOptionPane.showMessageDialog(this, response);
        this.setVisible(false);

    }

    public void showInput(User user, List<ActivityType> activityTypes) {
        this.user = user;
        this.activityTypes = activityTypes;
        this.setTitle("Add RE Rules");
        this.setPreferredSize(new Dimension(500, 500));
        this.setResizable(false);

        List<String> activityNames = activityTypes.stream().map(ActivityType::getActivity_name).collect(Collectors.toList());

        reActivityBox = new JComboBox(activityNames.toArray());
        reRulesPanel = new JPanel();
        reRuleCode = new JTextField();
        rePoints = new JTextField();
        rewardRuleCodeEntry = new JLabel("Enter RE rule code");
        rePointsEntry = new JLabel("Enter RE points");
        activitySelect = new JLabel("Select activity");

        reRulesPanel.setLayout(new BoxLayout(reRulesPanel, BoxLayout.Y_AXIS));

        reRulesPanel.add(rewardRuleCodeEntry);
        reRulesPanel.add(reRuleCode);
        reRulesPanel.add(rePointsEntry);
        reRulesPanel.add(rePoints);
        reRulesPanel.add(activitySelect);
        reRulesPanel.add(reActivityBox);
        reRulesPanel.add(addRERulesButton);
        reRulesPanel.add(goBackButton);


        this.add(reRulesPanel);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

}
