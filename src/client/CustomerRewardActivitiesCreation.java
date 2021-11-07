package client;

import server.Marketplace;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomerRewardActivitiesCreation extends JFrame implements ActionListener {
    private JComboBox rewardActivities;
    private JPanel CustomerRewardActivitiesPanel;

    enum RewardOptions {
        PURCHASE(1, "Purchase"),
        LEAVE_A_REVIEW(2, "Leave a Review"),
        REFER_A_FRIEND(3, "Refer a Friend"),
        BACK(0, "Exit");

        public int menuId;
        public String menuDescription;
        RewardOptions(int menuId, String menuDescription) {
            this.menuId = menuId;
            this.menuDescription = menuDescription;
        }

        public int getMenuId() {
            return menuId;
        }
        public String getMenuDescription() {return menuDescription;}
    }

    public void selectRewardActivity() {
        this.setTitle("Customer: Reward Activities");
        this.setPreferredSize(new Dimension(500, 500));
        this.setResizable(false);

        RewardOptions[] rewardOptions = RewardOptions.values();
        rewardActivities = new JComboBox(rewardOptions);
        rewardActivities.addActionListener(this);
        this.add(rewardActivities);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == rewardActivities) {
            RewardOptions selectedItem = (RewardOptions) rewardActivities.getSelectedItem();
            System.out.println(selectedItem.getMenuDescription());
            assert selectedItem != null;
            performMenuOperation(selectedItem);
        }
    }

    private void performMenuOperation(RewardOptions selectedItem) {
        Marketplace marketplace = new Marketplace();
        if(selectedItem.getMenuId() == 0) {
            System.exit(0);
            return;
        }
        marketplace.performRewardActivityLog(selectedItem.getMenuId());
    }
}
