package client;

import server.entity.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomerInfoOutput extends JFrame{

    private User user;
    private JFrame jFrame;
    private JPanel queryOutputPanel;
    private JTable queryOutputTable;
    private JButton goBack;

    public CustomerInfoOutput() {
        goBack.addActionListener(e -> {
            ShowCustomerInfo showCustomerInfo = new ShowCustomerInfo();
            showCustomerInfo.showCustomerInfo(user);
            jFrame.setVisible(false);
        });
    }

    public void showCustomerInfoOutput(JTable jTable, User user) {
        this.user = user;
        //create table
        queryOutputTable = jTable;
        queryOutputTable.setPreferredScrollableViewportSize(new Dimension(500, 70));
        queryOutputTable.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(queryOutputTable);

        queryOutputPanel = new JPanel();
        queryOutputPanel.add(scrollPane);


        jFrame = new JFrame("Show Queries Page");
        jFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        jFrame.setPreferredSize(new Dimension(500, 500));
        jFrame.setResizable(false);

        queryOutputPanel.add(goBack);
        queryOutputPanel.setLayout((new BoxLayout(queryOutputPanel, BoxLayout.Y_AXIS)));
        jFrame.add(queryOutputPanel);
        jFrame.pack();
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);

    }
}
