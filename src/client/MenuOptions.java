package client;

import server.Marketplace;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuOptions extends JFrame implements ActionListener {

    private JPanel mainMenuPanel;
    private JComboBox menuCombo;

    public void selectMenuOption() {

        this.setTitle("hii welcome to marketplace");
        this.setPreferredSize(new Dimension(500, 500));
        this.setResizable(false);

        server.constants.MenuOptions[] menuOptions = server.constants.MenuOptions.values();
        menuCombo = new JComboBox(menuOptions);
        menuCombo.addActionListener(this);

        this.add(menuCombo);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == menuCombo) {
            System.out.println(menuCombo.getSelectedItem());
            server.constants.MenuOptions selectedItem = (server.constants.MenuOptions) menuCombo.getSelectedItem();
            assert selectedItem != null;
            System.out.println("after resolving --- " + selectedItem.getMenuId() + " --- " + selectedItem.getMenuDescription());
            performMenuOperation(selectedItem);
        }
    }

    private void performMenuOperation(server.constants.MenuOptions menuOption) {
        Marketplace marketplace = new Marketplace();
        if(menuOption.getMenuId() == 0) {
            System.exit(0);
            return;
        }
        marketplace.performOperation(menuOption.getMenuId());

    }
}
