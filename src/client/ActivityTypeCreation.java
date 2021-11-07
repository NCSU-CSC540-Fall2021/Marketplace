package client;

import server.service.ActivityTypeService;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.text.ParseException;

public class ActivityTypeCreation extends JFrame{
    private JPanel activityTypeCreation;
    private JTextField activityName;
    private JButton submitButton;
    private JButton goBack;
    private JFrame jFrame;

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
    }

    public void showFormForInput() {
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

        ActivityTypeService activityTypeService = new ActivityTypeService();
        String response = activityTypeService.createActivityType(activityNameText);

        JOptionPane.showMessageDialog(this, response);
        jFrame.setVisible(false);
    }
}

