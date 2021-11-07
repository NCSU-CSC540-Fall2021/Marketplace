package client;

import server.Marketplace;
import server.entity.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminLandingPage extends JFrame implements ActionListener {

    private JPanel mainMenuPanel;
    private JLabel adminLandingBar;
    private JComboBox menuCombo;
    private User user;

    public void selectMenuOption(User user) {

        this.user = user;
        this.setTitle("Welcome to marketplace");
        this.setPreferredSize(new Dimension(500, 500));
        this.setResizable(false);

        server.constants.MenuOptions[] menuOptions = server.constants.MenuOptions.values();
        menuCombo = new JComboBox(menuOptions);
        menuCombo.addActionListener(this);

        adminLandingBar = new JLabel("Welcome to marketplace " + user.getUserName());
        mainMenuPanel.setLayout(new BoxLayout(mainMenuPanel, BoxLayout.Y_AXIS));
        mainMenuPanel.add(adminLandingBar);
        mainMenuPanel.add(menuCombo);

        this.add(mainMenuPanel);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == menuCombo) {
            server.constants.MenuOptions selectedItem = (server.constants.MenuOptions) menuCombo.getSelectedItem();
            assert selectedItem != null;
            performMenuOperation(selectedItem);
        }
    }

    private void performMenuOperation(server.constants.MenuOptions menuOption) {
        int menuId = menuOption.getMenuId();
        switch(menuId)
        {
            case 1 :
                // add brand with admin user
                BrandCreation brandCreation = new BrandCreation();
                brandCreation.showFormForInput(user);
                break;
            case 2 :
                // add customer with admin user
                CustomerCreation customerCreation = new CustomerCreation();
                customerCreation.showFormForInput(user);
                break;
        }
    }
}
