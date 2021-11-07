package client;

import server.entity.User;

import javax.swing.*;
import java.awt.*;

public class BrandInfoOutput extends JFrame{

    private JTable queryOutputTable;
    private JPanel queryOutputPanel;
    private JButton goBack;
    private JFrame jFrame;
    private User user;

    public BrandInfoOutput() {
        goBack.addActionListener(e -> {
            ShowBrandInfo showBrandInfo = new ShowBrandInfo();
            showBrandInfo.showBrandInfo(user);
            jFrame.setVisible(false);
        });
    }

    public void showBrandInfoOutput(JTable jTable, User user)
    {
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
