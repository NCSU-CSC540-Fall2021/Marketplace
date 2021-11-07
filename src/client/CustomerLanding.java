package client;

import server.constants.CustomerLandingOptions;
import server.entity.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomerLanding extends JFrame implements ActionListener {
    private JLabel customerTitleBar;
    private JPanel customerLanding;
    private JComboBox customerLandingMenuComboBox;
    private JFrame jFrame;


    public void showInput(User user) {
        jFrame = new JFrame("Customer Landing Page");
        jFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        jFrame.setPreferredSize(new Dimension(500, 500));
        jFrame.setResizable(false);

        CustomerLandingOptions[] values = CustomerLandingOptions.values();
        customerLandingMenuComboBox = new JComboBox(values);
        customerTitleBar = new JLabel("Welcome to marketplace " + user.getUserName());
        customerLandingMenuComboBox.addActionListener(this);

        customerLanding = new JPanel();
        customerLanding.setLayout((new BoxLayout(customerLanding, BoxLayout.Y_AXIS)));
        customerLanding.add(customerTitleBar);
        customerLanding.add(customerLandingMenuComboBox);
        jFrame.add(customerLanding);
        jFrame.pack();
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == customerLandingMenuComboBox) {

            CustomerLandingOptions selectedItem = (CustomerLandingOptions) customerLandingMenuComboBox.getSelectedItem();
            assert selectedItem != null;
            performMenuOperation(selectedItem);
        }
    }

    private void performMenuOperation(CustomerLandingOptions customerLandingOption) {
        int optionId = customerLandingOption.getOptionId();
        switch (optionId)
        {
            case 1 :
                // enroll
                break;
            case 2 :
                // reward activity
                break;
            case 3 :
                // view wallet
                break;
            case 4 :
                // redeem points
                break;
            case 5 :
                // logout
                Homepage homepage = new Homepage();
                homepage.showHomePage();
                break;
        }
    }
}
