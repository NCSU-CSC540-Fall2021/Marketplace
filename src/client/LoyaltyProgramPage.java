package client;

import server.entity.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class LoyaltyProgramPage extends JFrame implements ActionListener {
    private User user;

    private JComboBox tierStatusBox;
    private JPanel loyaltyProgramPanel;
    private JLabel tierStatusLabel;
    private JLabel numberOfLevels;

    private JLabel tierCodeLabel;
    private JLabel tierNameLabel;
    private JLabel eligibilityPointsLabel;
    private JLabel multiplierLabel;

    private JTextField tierCode;
    private JTextField tierName;
    private JTextField eligibilityPoints;
    private JTextField multiplier;

    public void showInput()
    {
        this.user = user;
        this.setTitle("Create Loyalty Program");
        this.setPreferredSize(new Dimension(500, 500));
        this.setResizable(false);

        List<String> tierStatus = new ArrayList<>();
        tierStatus.add("TIERED");
        tierStatus.add("REGULAR");

        tierStatusBox = new JComboBox(tierStatus.toArray());
        loyaltyProgramPanel = new JPanel();
        tierStatusLabel = new JLabel("Check tier status");
        numberOfLevels = new JLabel("Enter number of levels");
//        rrRuleCode = new JTextField();
//        rrPoints = new JTextField();
//        rewardInstances = new JTextField();
//
//        rrRulesPanel.setLayout(new BoxLayout(rrRulesPanel, BoxLayout.Y_AXIS));
//
//        rrRulesPanel.add(rewardRuleCodeEntry);
//        rrRulesPanel.add(rrRuleCode);
//        rrRulesPanel.add(rrPointsEntry);
//        rrRulesPanel.add(rrPoints);
//        rrRulesPanel.add(rewardSelect);
//        rrRulesPanel.add(tierStatusBox);
//        rrRulesPanel.add(rewardInstancesLabel);
//        rrRulesPanel.add(rewardInstances);
//        rrRulesPanel.add(addRRRulesButton);
//        rrRulesPanel.add(goBackButton);
//
//
//        this.add(rrRulesPanel);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == tierStatusBox) {
            Object selectedItem = tierStatusBox.getSelectedItem();
            assert selectedItem != null;
            performMenuOperation(selectedItem);
        }
    }

    private void performMenuOperation(Object selectedItem) {
        if(selectedItem.equals("TIERED"))
        {
//            Integer levels = numberOfLevels.getText();
        }
    }
}
