package client;

import server.entity.RewardType;
import server.entity.User;
import server.service.RewardRedeemingService;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AddRRRules extends JFrame{

    private User user;
    private JTextField rrPoints;
    private JButton addRRRulesButton;
    private JButton goBackButton;
    private JPanel rrRulesPanel;
    private JTextField rrRuleCode;
    private JComboBox rrActivityBox;
    private JLabel rewardRuleCodeEntry;
    private JLabel rrPointsEntry;
    private JLabel rewardSelect;
    private JLabel rewardInstancesLabel;
    private JTextField rewardInstances;

    Map<String, String> rewardNameToCodeMap = new HashMap<>();
    List<RewardType> rewardTypes;

    public AddRRRules() {
        goBackButton.addActionListener(e -> {
            BrandLanding brandLanding = new BrandLanding();
            brandLanding.showInput(user);
            this.setVisible(false);
        });

        addRRRulesButton.addActionListener(e -> {
            try {
                submitRRRules();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
    }

    public void showInput(User user, List<RewardType> rewardTypes) {
        this.user = user;
        this.rewardTypes = rewardTypes;
        this.setTitle("Add RR Rules");
        this.setPreferredSize(new Dimension(500, 500));
        this.setResizable(false);

        List<String> rewardTypeNames = rewardTypes.stream().map(RewardType::getReward_type).collect(Collectors.toList());

        rrActivityBox = new JComboBox(rewardTypeNames.toArray());
        rrRulesPanel = new JPanel();
        rewardRuleCodeEntry = new JLabel("Enter Reward rule code");
        rrRuleCode = new JTextField();
        rrPointsEntry = new JLabel("Enter RR points");
        rrPoints = new JTextField();
        rewardSelect = new JLabel("Select Reward");
        rewardInstancesLabel = new JLabel("Enter number of instances");
        rewardInstances = new JTextField();

        rrRulesPanel.setLayout(new BoxLayout(rrRulesPanel, BoxLayout.Y_AXIS));

        rrRulesPanel.add(rewardRuleCodeEntry);
        rrRulesPanel.add(rrRuleCode);
        rrRulesPanel.add(rrPointsEntry);
        rrRulesPanel.add(rrPoints);
        rrRulesPanel.add(rewardSelect);
        rrRulesPanel.add(rrActivityBox);
        rrRulesPanel.add(rewardInstancesLabel);
        rrRulesPanel.add(rewardInstances);
        rrRulesPanel.add(addRRRulesButton);
        rrRulesPanel.add(goBackButton);


        this.add(rrRulesPanel);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

    private void submitRRRules() throws SQLException {
        String rrRuleCodeText = rrRuleCode.getText();
        String rrPointsText = rrPoints.getText();
        Object selectedItem = rrActivityBox.getSelectedItem();
        String rewardInstances = this.rewardInstances.getText();
        rewardNameToCodeMap = rewardTypes.stream().collect(Collectors.toMap(RewardType::getReward_type, RewardType::getReward_code));
        String rewardCode = rewardNameToCodeMap.get(selectedItem);
        System.out.println(rrRuleCodeText);
        System.out.println(rrPointsText);
        System.out.println(selectedItem);

        RewardRedeemingService rewardRedeemingService = new RewardRedeemingService();
        String response = rewardRedeemingService.addRewardRedeeming(rrRuleCodeText, rrPointsText, rewardCode, rewardInstances, user);

        JOptionPane.showMessageDialog(this, response);
        this.setVisible(false);

    }

}
