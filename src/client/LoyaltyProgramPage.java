package client;

import server.entity.TierSetup;
import server.entity.User;
import server.service.BrandService;
import server.service.LoyaltyProgramService;
import server.service.TierSetupService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LoyaltyProgramPage extends JFrame implements ActionListener {
    private User user;

    private JComboBox tierStatusBox;
    private JPanel loyaltyProgramPanel;
    private JLabel tierStatusLabel;
    private JLabel numberOfLevelsLabel;

    private JTextField numberOfLevels;

    private JLabel tierCodeLabel;
    private JLabel tierNameLabel;
    private JLabel eligibilityPointsLabel;
    private JLabel multiplierLabel;

    private JTextField tierCode;
    private JTextField tierName;
    private JTextField eligibilityPoints;
    private JTextField multiplier;

    private JButton createLoyaltyActivity;

    private JFrame jFrame;

    List<List<JTextField>> textFieldList = new ArrayList<>();

    private boolean tiered;
    private JButton goBackButton;

    public LoyaltyProgramPage() {
        createLoyaltyActivity.addActionListener(e -> {
            try {
                submit();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
        goBackButton.addActionListener(e -> {
            this.setVisible(false);
            BrandLanding brandLanding = new BrandLanding();
            brandLanding.showInput(user);
        });
    }

    public void showInput(User user) {
        this.user = user;
        this.setTitle("Create Loyalty Program");
        this.setPreferredSize(new Dimension(500, 500));
        this.setResizable(true);

        List<String> tierStatus = new ArrayList<>();
        tierStatus.add("TIERED");
        tierStatus.add("REGULAR");

        loyaltyProgramPanel = new JPanel();
        tierStatusBox = new JComboBox(tierStatus.toArray());
        tierStatusBox.addActionListener(this);

        loyaltyProgramPanel = new JPanel();
        tierStatusLabel = new JLabel("Select Tier status");
        numberOfLevelsLabel = new JLabel("Enter Number of Tiers");
        numberOfLevels = new JTextField();

        loyaltyProgramPanel.setLayout(new BoxLayout(loyaltyProgramPanel, BoxLayout.Y_AXIS));
        loyaltyProgramPanel.add(tierStatusLabel);
        loyaltyProgramPanel.add(tierStatusBox);
        loyaltyProgramPanel.add(numberOfLevelsLabel);
        loyaltyProgramPanel.add(numberOfLevels);
        loyaltyProgramPanel.add(createLoyaltyActivity);
        loyaltyProgramPanel.add(goBackButton);
        this.add(loyaltyProgramPanel);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == tierStatusBox) {
            String selectedItem = (String) tierStatusBox.getSelectedItem();
            System.out.println(selectedItem);
            assert selectedItem != null;
            performMenuOperation(selectedItem);
        }
    }

    private void performMenuOperation(String selectedItem) {
        if(selectedItem.equals("TIERED"))
        {
            tiered = true;
            Integer levels = Integer.valueOf(numberOfLevels.getText());
            for(int i=0; i<levels;i++)
            {
                List<JTextField> textFields = new ArrayList<>();
                tierCode = new JTextField();
                tierName = new JTextField();
                eligibilityPoints = new JTextField();
                multiplier = new JTextField();

                int level = i+1;
                tierCodeLabel = new JLabel("Enter Tier level code ");
                tierNameLabel = new JLabel("Enter Tier level " + level + " name ");
                eligibilityPointsLabel = new JLabel("Enter Eligibility Points for Tier " + level);
                multiplierLabel = new JLabel("Enter Multiplier for Tier " + level);

                loyaltyProgramPanel.add(tierCodeLabel);
                loyaltyProgramPanel.add(tierCode);
                loyaltyProgramPanel.add(tierNameLabel);
                loyaltyProgramPanel.add(tierName);
                loyaltyProgramPanel.add(eligibilityPointsLabel);
                loyaltyProgramPanel.add(eligibilityPoints);
                loyaltyProgramPanel.add(multiplierLabel);
                loyaltyProgramPanel.add(multiplier);

                textFields.add(tierCode);
                textFields.add(tierName);
                textFields.add(eligibilityPoints);
                textFields.add(multiplier);

                textFieldList.add(textFields);

                this.add(loyaltyProgramPanel);
                this.setDefaultCloseOperation(EXIT_ON_CLOSE);
                this.pack();
                this.setVisible(true);
                this.setLocationRelativeTo(null);
            }
        }
        else {
            tiered = false;
        }
    }

    public void submit() throws SQLException {
        String numberOfLevelsText = numberOfLevels.getText();
        List<TierSetup> tierSetupList = new ArrayList<>();
        for (List<JTextField> jTextFields : textFieldList) {
            TierSetup tierSetup = new TierSetup();

            JTextField jTextField = jTextFields.get(0);
            Integer tierLevelCode = Integer.valueOf(jTextField.getText());

            jTextField = jTextFields.get(1);
            String tierLevelName = jTextField.getText();

            jTextField = jTextFields.get(2);
            Integer eligibilityPoints = Integer.valueOf(jTextField.getText());

            jTextField = jTextFields.get(3);
            Integer multiplier = Integer.valueOf(jTextField.getText());


            tierSetup.setTierLevelCode(tierLevelCode);
            tierSetup.setTierLevelName(tierLevelName);
            tierSetup.setEligibilityPoints(eligibilityPoints);
            tierSetup.setMultiplier(multiplier);

            tierSetupList.add(tierSetup);
        }

        LoyaltyProgramService loyaltyProgramService = new LoyaltyProgramService();
        String response = loyaltyProgramService.createLoyaltyProgram(user, numberOfLevelsText, tiered);

        System.out.println(response);

        if(tiered) {
            // update tier status
            TierSetupService tierSetupService = new TierSetupService();
            tierSetupService.createTierSetup(user, tierSetupList);
        }

        // update loyalty id in brand
        BrandService brandService = new BrandService();
        brandService.updateLoyaltyProgramIdForBrand(user);

        JOptionPane.showMessageDialog(this, response);
        this.setVisible(false);
        BrandLanding brandLanding = new BrandLanding();
        brandLanding.showInput(user);
    }
}
