package client;

import server.entity.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomerRewardActivitiesCreation extends JFrame implements ActionListener {
    private JComboBox rewardActivities;
    private JPanel CustomerRewardActivitiesPanel;
    User user;

    enum RewardOptions {
        PURCHASE(1, "Purchase"),
        LEAVE_A_REVIEW(2, "Write a review"),
        REFER_A_FRIEND(3, "Refer a friend"),
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

    public void selectRewardActivity(User user) {
        this.user = user;
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
            // todo: have to change
            RewardOptions selectedItem = (RewardOptions) rewardActivities.getSelectedItem();
            System.out.println(selectedItem.getMenuDescription());
            assert selectedItem != null;
            performMenuOperation(selectedItem);
        }
    }

    private void performMenuOperation(RewardOptions selectedItem) {
        switch(selectedItem.getMenuId()) {
            case 0:
                CustomerLanding customerLanding = new CustomerLanding();
                customerLanding.showInput(user);
                this.setVisible(false);
                break;
            case 1:
                CustomerPurchaseForm customerPurchaseForm = new CustomerPurchaseForm();
                customerPurchaseForm.selectPurchase(user);
                this.setVisible(false);
                break;
            case 2:
                CustomerReviewForm customerReviewForm = new CustomerReviewForm();
                customerReviewForm.leaveAReviewForm(user);
                this.setVisible(false);
                break;
            case 3:
                ReferAFriend referAFriend = new ReferAFriend();
                referAFriend.showReferAFriend(user);
                this.setVisible(false);
                break;
        }
    }
}
