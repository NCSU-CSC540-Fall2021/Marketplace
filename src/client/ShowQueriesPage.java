package client;

import server.constants.ShowQueriesOptions;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ShowQueriesPage extends JFrame implements ActionListener {
    private JComboBox queryComboBox;
    private JPanel showQueriesPanel;
    private JButton goBack;
    private JFrame jFrame;
    private JScrollPane scrollPane;

    public ShowQueriesPage() {
        goBack.addActionListener(e -> {
            Homepage homepage = new Homepage();
            homepage.showHomePage();
        });
    }

    public void selectQueryMenu() {
        jFrame = new JFrame("Show Queries Page");
        jFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        jFrame.setPreferredSize(new Dimension(500, 500));
        jFrame.setResizable(false);

        Object[] objects = ShowQueriesOptions.getOptionDescriptionList().toArray();
        queryComboBox = new JComboBox(objects);
        queryComboBox.addActionListener(this);


        showQueriesPanel = new JPanel();
        showQueriesPanel.setLayout((new BoxLayout(showQueriesPanel, BoxLayout.Y_AXIS)));
        showQueriesPanel.add(queryComboBox);
        showQueriesPanel.add(goBack);
        jFrame.add(showQueriesPanel);
        jFrame.pack();
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == queryComboBox) {

            String selectedItem = (String) queryComboBox.getSelectedItem();
            System.out.println(selectedItem);
            ShowQueriesOptions queryOptionByDescription = ShowQueriesOptions.getQueryOptionByDescription(selectedItem);
            performMenuOperation(queryOptionByDescription);
//            JTable jTable = performMenuOperation(queryOptionByDescription);
//
//            //create table
//            jTable.setPreferredScrollableViewportSize(new Dimension(500, 70));
//            jTable.setFillsViewportHeight(true);
//            JScrollPane scrollPane = new JScrollPane(jTable);
//            showQueriesPanel.add(scrollPane);
//            jFrame.add(showQueriesPanel);
//            jFrame.pack();
//            jFrame.setLocationRelativeTo(null);
//            jFrame.setVisible(true);
        }
    }

    private JTable performMenuOperation(ShowQueriesOptions queryOptionByDescription) {
        int optionId = queryOptionByDescription.getOptionId();
        QueryOutputPage queryOutputPage = new QueryOutputPage();

        System.out.println(optionId);
        switch (optionId) {
            case 1 :
                // LIST_CUSTOMERS_NOT_IN_BRAND_02
                // open next page and render
                JTable jTable = populateTableForQuery1();
                queryOutputPage.showQuery(jTable);

            case 2 :
                // CUSTOMER_LOYALTY_NOT_ACTIVITY
                return populateTableForQuery2();
            case 3 :
                // REWARDS_BRAND_01
                break;
            case 4 :
                // REFER_FRIEND
                break;
            case 5 :
                // BRAND_01_ACTIVITY_LIST
                break;
            case 6 :
                // CUSTOMER_REDEEMED_BRAND_01
                break;
            case 7 :
                // BRANDS_TOTAL_REDEEMED_LT_500
                break;
            case 8 :
                // CUSTOMER_BRAND_NO_OF_ACTIVITIES_DATE
                break;
        }
        return null;
    }

    private JTable populateTableForQuery2() {
        List<String[]> temp = new ArrayList<>();
        temp.add(new String[]{"aa", "hello"});
        temp.add(new String[]{"bb", "hello"});
        temp.add(new String[]{"cc", "hello"});

        List<String> columns = new ArrayList<String>();
        columns.add("col1");
        columns.add("col2");

        TableModel tableModel = new DefaultTableModel(temp.toArray(new Object[][] {}), columns.toArray());
        JTable table = new JTable(tableModel);
        return table;
    }

    private JTable populateTableForQuery1() {
        List<String[]> temp = new ArrayList<>();
        temp.add(new String[]{"hi", "hello"});
        temp.add(new String[]{"hi", "hello"});
        temp.add(new String[]{"hi", "hello"});

        List<String> columns = new ArrayList<String>();
        columns.add("col1");
        columns.add("col2");

        TableModel tableModel = new DefaultTableModel(temp.toArray(new Object[][] {}), columns.toArray());
        JTable table = new JTable(tableModel);
        return table;
    }
}
