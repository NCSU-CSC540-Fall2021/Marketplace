package client;

import server.entity.User;
import server.service.ActivityTypeService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;

public class ActivityTypeCreation extends JFrame{
    private JPanel activityTypeCreation;
    private JTextField activityName;
    private JButton submitButton;
    private JButton goBack;
    private JFrame jFrame;
    private User user;

    public ActivityTypeCreation() {
        submitButton.addActionListener(e -> {
            try {
                submit();
            } catch (ParseException ex) {
                ex.printStackTrace();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
        goBack.addActionListener(e -> {
            AdminLandingPage adminLandingPage = new AdminLandingPage();
            adminLandingPage.selectMenuOption(user);
            jFrame.setVisible(false);
        });
    }

    public void showFormForInput(User user) {
        this.user = user;
        jFrame = new JFrame("Create Activity Type");
        jFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        jFrame.setPreferredSize(new Dimension(500, 500));
        jFrame.setResizable(false);

        jFrame.add(activityTypeCreation);
        jFrame.pack();
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);
    }
    public void submit() throws ParseException, SQLException {
        String activityNameText = activityName.getText();
        String userName = user.getUserName();

        ActivityTypeService activityTypeService = new ActivityTypeService();
        String response = activityTypeService.createActivityType(activityNameText, userName);

        JOptionPane.showMessageDialog(this, response);
        AdminLandingPage adminLandingPage = new AdminLandingPage();
        adminLandingPage.selectMenuOption(user);
        jFrame.setVisible(false);
    }
}

