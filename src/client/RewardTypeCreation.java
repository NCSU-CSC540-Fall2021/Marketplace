package client;

import server.service.ActivityTypeService;
import server.service.RewardTypeService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;

public class RewardTypeCreation extends JFrame{
    private JTextField rewardName;
    private JButton submit;
    private JButton goBack;
    private JPanel rewardTypeCreation;
    private JFrame jFrame;

    public RewardTypeCreation() {
        submit.addActionListener(e -> {
            try {
                submit();
            } catch (ParseException ex) {
                ex.printStackTrace();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
    }

    public void showFormForInput() {
        jFrame = new JFrame("Create Reward Type");
        jFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        jFrame.setPreferredSize(new Dimension(500, 500));
        jFrame.setResizable(false);

        jFrame.add(rewardTypeCreation);
        jFrame.pack();
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);
    }

    public void submit() throws ParseException, SQLException {
        String rewardNameText = rewardName.getText();

        RewardTypeService rewardTypeService = new RewardTypeService();
        String response = rewardTypeService.createRewardType(rewardNameText);

        JOptionPane.showMessageDialog(this, response);
        jFrame.setVisible(false);
    }
}
