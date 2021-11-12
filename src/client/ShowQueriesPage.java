package client;

import server.constants.ShowQueriesOptions;
import server.service.ShowQueriesService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
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
            jFrame.setVisible(false);
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
        }
    }

    private JTable performMenuOperation(ShowQueriesOptions queryOptionByDescription) {
        int optionId = queryOptionByDescription.getOptionId();
        QueryOutputPage queryOutputPage = new QueryOutputPage();
        ShowQueriesService showQueriesService = new ShowQueriesService();
        System.out.println(optionId);
        JTable jTable;
        switch (optionId) {
            case 1 :
                // LIST_CUSTOMERS_NOT_IN_BRAND_02
                // open next page and render
                Map<String, List<String[]>> resultForQuery1 = showQueriesService.getResultForQuery1();
                jTable = populateTableForQuery1(resultForQuery1);
                queryOutputPage.showQuery(jTable);
                jFrame.setVisible(false);
                break;
            case 2 :
                // CUSTOMER_LOYALTY_NOT_ACTIVITY
                Map<String, List<String[]>> resultForQuery2 = showQueriesService.getResultForQuery2();
                jTable = populateTableForQuery2(resultForQuery2);
                queryOutputPage.showQuery(jTable);
                jFrame.setVisible(false);
                break;
            case 3 :
                // REWARDS_BRAND_01
                Map<String, List<String>> resultForQuery3 = showQueriesService.getResultForQuery3();
                jTable = populateTableForQuery3(resultForQuery3);
                queryOutputPage.showQuery(jTable);
                jFrame.setVisible(false);
                break;
            case 4 :
                // REFER_FRIEND
                Map<String, List<String>> resultForQuery4 = showQueriesService.getResultForQuery4();
                jTable = populateTableForQuery4(resultForQuery4);
                queryOutputPage.showQuery(jTable);
                jFrame.setVisible(false);
                break;
            case 5 :
                // BRAND_01_ACTIVITY_LIST
                Map<String, List<String[]>> resultForQuery5 = showQueriesService.getResultForQuery5();
                jTable = populateTableForQuery5(resultForQuery5);
                queryOutputPage.showQuery(jTable);
                jFrame.setVisible(false);
                break;
            case 6 :
                // CUSTOMER_REDEEMED_BRAND_01
                break;
            case 7 :
                // BRANDS_TOTAL_REDEEMED_LT_500
                Map<String, List<String[]>> resultForQuery7 = showQueriesService.getResultForQuery7();
                jTable = populateTableForQuery7(resultForQuery7);
                queryOutputPage.showQuery(jTable);
                jFrame.setVisible(false);
                break;
            case 8 :
                // CUSTOMER_BRAND_NO_OF_ACTIVITIES_DATE
                Map<String, List<String[]>> resultForQuery8 = showQueriesService.getResultForQuery8();
                jTable = populateTableForQuery8(resultForQuery8);
                queryOutputPage.showQuery(jTable);
                jFrame.setVisible(false);
                break;
        }
        return null;
    }

    private JTable populateTableForQuery8(Map<String, List<String[]>> resultForQuery8) {
        List<String> columns = new ArrayList<String>();
        List<String[]> values = new ArrayList<>();

        List<String[]> column_names = resultForQuery8.getOrDefault("column_names", new ArrayList<>());
        for (String[] column_name : column_names) {
            columns.addAll(Arrays.asList(column_name));
        }
        System.out.println(columns);

        List<String[]> valuesList = resultForQuery8.getOrDefault("values", new ArrayList<>());
        values.addAll(valuesList);

        TableModel tableModel = new DefaultTableModel(values.toArray(new Object[][] {}), columns.toArray());
        JTable table = new JTable(tableModel);
        return table;
    }

    private JTable populateTableForQuery7(Map<String, List<String[]>> resultForQuery7) {
        List<String> columns = new ArrayList<String>();
        List<String[]> values = new ArrayList<>();

        List<String[]> column_names = resultForQuery7.getOrDefault("column_names", new ArrayList<>());
        for (String[] column_name : column_names) {
            columns.addAll(Arrays.asList(column_name));
        }
        System.out.println(columns);

        List<String[]> valuesList = resultForQuery7.getOrDefault("values", new ArrayList<>());
        values.addAll(valuesList);

        TableModel tableModel = new DefaultTableModel(values.toArray(new Object[][] {}), columns.toArray());
        JTable table = new JTable(tableModel);
        return table;
    }

    private JTable populateTableForQuery5(Map<String, List<String[]>> resultForQuery5) {
        List<String> columns = new ArrayList<String>();
        List<String[]> values = new ArrayList<>();

        List<String[]> column_names = resultForQuery5.getOrDefault("column_names", new ArrayList<>());
        for (String[] column_name : column_names) {
            columns.addAll(Arrays.asList(column_name));
        }
        System.out.println(columns);

        List<String[]> valuesList = resultForQuery5.getOrDefault("values", new ArrayList<>());
        values.addAll(valuesList);

        TableModel tableModel = new DefaultTableModel(values.toArray(new Object[][] {}), columns.toArray());
        JTable table = new JTable(tableModel);
        return table;
    }

    private JTable populateTableForQuery1(Map<String, List<String[]>> resultForQuery1) {
        List<String> columns = new ArrayList<String>();
        List<String[]> values = new ArrayList<>();

        List<String[]> column_names = resultForQuery1.getOrDefault("column_names", new ArrayList<>());
        for (String[] column_name : column_names) {
            columns.addAll(Arrays.asList(column_name));
        }
        System.out.println(columns);

        List<String[]> valuesList = resultForQuery1.getOrDefault("values", new ArrayList<>());
        values.addAll(valuesList);

        TableModel tableModel = new DefaultTableModel(values.toArray(new Object[][] {}), columns.toArray());
        JTable table = new JTable(tableModel);
        return table;
    }

    private JTable populateTableForQuery2(Map<String, List<String[]>> resultForQuery2) {
        List<String> columns = new ArrayList<String>();
        List<String[]> values = new ArrayList<>();

        List<String[]> column_names = resultForQuery2.getOrDefault("column_names", new ArrayList<>());
        for (String[] column_name : column_names) {
            columns.addAll(Arrays.asList(column_name));
        }

        List<String[]> valuesList = resultForQuery2.getOrDefault("values", new ArrayList<>());
        values.addAll(valuesList);

        TableModel tableModel = new DefaultTableModel(values.toArray(new Object[][] {}), columns.toArray());
        JTable table = new JTable(tableModel);
        return table;
    }

    private JTable populateTableForQuery3(Map<String, List<String>> resultForQuery3) {
        List<String> columns = new ArrayList<String>();
        List<String[]> values = new ArrayList<>();
        Set<String> strings = resultForQuery3.keySet();

        for (String string : strings) {
            columns.add(string);
            List<String> results = resultForQuery3.getOrDefault(string, new ArrayList<>());
            for(int i=0;i<results.size(); i++) {
                values.add(new String[]{results.get(i)});
            }
        }
        System.out.println(values);
        TableModel tableModel = new DefaultTableModel(values.toArray(new Object[][] {}), columns.toArray());
        JTable table = new JTable(tableModel);
        System.out.println(table);
        return table;
    }

    private JTable populateTableForQuery4(Map<String, List<String>> resultForQuery4) {
        List<String> columns = new ArrayList<String>();
        List<String[]> values = new ArrayList<>();
        Set<String> strings = resultForQuery4.keySet();

        for (String string : strings) {
            columns.add(string);
            List<String> results = resultForQuery4.getOrDefault(string, new ArrayList<>());
            for(int i=0;i<results.size(); i++) {
                values.add(new String[]{results.get(i)});
            }
        }
        System.out.println(values);
        TableModel tableModel = new DefaultTableModel(values.toArray(new Object[][] {}), columns.toArray());
        JTable table = new JTable(tableModel);
        System.out.println(table);
        return table;
    }
}
