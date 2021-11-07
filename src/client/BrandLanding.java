package client;

import server.constants.BrandLandingOptions;
import server.entity.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BrandLanding extends JFrame implements ActionListener {
    private JComboBox brandLandingComboBox;
    private JLabel brandTitleBar;
    private JPanel brandLandingPanel;
    private JFrame jFrame;

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == brandLandingComboBox) {

            String selectedItem = (String) brandLandingComboBox.getSelectedItem();
            System.out.println(selectedItem);
            BrandLandingOptions brandLandingOptions = BrandLandingOptions.getBrandOptionByDescription(selectedItem);
            performMenuOperation(brandLandingOptions);
        }
    }

    public void showInput(User user) {
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

    private void performMenuOperation(BrandLandingOptions brandLandingOption) {
        int optionId = brandLandingOption.getOptionId();
        switch (optionId) {
            case 1:
                // ADD_LOYALTY_PROGRAM
                break;
            case 2:
                // ADD_RE_RULES
                break;
            case 3:
                // UPDATE_RE_RULES
                break;
            case 4:
                // UPDATE_RR_RULES
                break;
            case 5:
                // ADD_RR_RULES
                break;
            case 6 :
                // VALIDATE_LOYALTY_PROGRAM
                break;
            case 7:
                // logout
                Homepage homepage = new Homepage();
                homepage.showHomePage();
                break;
        }
    }
}
