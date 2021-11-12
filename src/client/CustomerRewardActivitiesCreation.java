package client;

import server.entity.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomerRewardActivitiesCreation extends JFrame implements ActionListener {
    private JComboBox rewardActivities;
    private JPanel CustomerRewardActivitiesPanel;
    private JButton goBackButton;
    private JFrame jFrame;
    User user;

    enum RewardOptions {
        PURCHASE(1, "Purchase"),
        LEAVE_A_REVIEW(2, "Write a review"),
        REFER_A_FRIEND(3, "Refer a friend");

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

    public CustomerRewardActivitiesCreation() {
        goBackButton.addActionListener(e-> jFrame.setVisible(false));
    }

    public void selectRewardActivity(User user) {
        this.user = user;
        jFrame = new JFrame("Customer: Reward Activities");
        jFrame.setPreferredSize(new Dimension(500, 500));
        jFrame.setResizable(false);

        RewardOptions[] rewardOptions = RewardOptions.values();
        rewardActivities = new JComboBox(rewardOptions);
        rewardActivities.addActionListener(this);

        CustomerRewardActivitiesPanel = new JPanel();
        CustomerRewardActivitiesPanel.setLayout((new BoxLayout(CustomerRewardActivitiesPanel, BoxLayout.Y_AXIS)));
        CustomerRewardActivitiesPanel.add(rewardActivities);
        CustomerRewardActivitiesPanel.add(goBackButton);
        jFrame.add(CustomerRewardActivitiesPanel);
        jFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        jFrame.pack();
        jFrame.setVisible(true);
        jFrame.setLocationRelativeTo(null);
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
        switch (selectedItem.getMenuId()) {
            case 1 -> {
                CustomerPurchaseForm customerPurchaseForm = new CustomerPurchaseForm();
                customerPurchaseForm.selectPurchase(user);
                this.setVisible(false);
            }
            case 2 -> {
                CustomerReviewForm customerReviewForm = new CustomerReviewForm();
                customerReviewForm.leaveAReviewForm(user);
                this.setVisible(false);
            }
            case 3 -> {
                ReferAFriend referAFriend = new ReferAFriend();
                referAFriend.showReferAFriend(user);
                this.setVisible(false);
            }
        }
    }
}
