package client;

import javax.swing.*;
import java.awt.*;

public class QueryOutputPage extends JFrame{

    private JTable queryOutputTable;
    private JPanel queryOutputPanel;
    private JButton goBack;
    private JFrame jFrame;

    public QueryOutputPage() {
        goBack.addActionListener(e -> {
            ShowQueriesPage showQueriesPage = new ShowQueriesPage();
            showQueriesPage.selectQueryMenu();
        });
    }

    public void showQuery(JTable jTable) {
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
