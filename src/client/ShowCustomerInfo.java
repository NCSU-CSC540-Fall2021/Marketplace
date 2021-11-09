package client;

import server.entity.Brand;
import server.entity.Customer;
import server.entity.User;
import server.service.BrandService;
import server.service.CustomerService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShowCustomerInfo extends JFrame {
    private JTextField customerUserName;
    private JButton showCustomerInfoButton;
    private JButton goBackButton;
    private JPanel customerInfoPanel;
    private User user;
    private JFrame jFrame;

    public ShowCustomerInfo() {
        goBackButton.addActionListener(e -> {
            AdminLandingPage adminLandingPage = new AdminLandingPage();
            adminLandingPage.selectMenuOption(user);
            jFrame.setVisible(false);
        });
        showCustomerInfoButton.addActionListener(e -> {
            try {
                submitCustomerInfo();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
    }

    public void showCustomerInfo(User user)
    {
        this.user = user;
        jFrame = new JFrame("Create Customer Info");
        jFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        jFrame.setPreferredSize(new Dimension(500, 500));
        jFrame.setResizable(false);

        jFrame.add(customerInfoPanel);
        jFrame.pack();
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);
    }

    public void submitCustomerInfo() throws SQLException {
        String customerUserNameText = customerUserName.getText();

        // select brand by username
        CustomerService customerService = new CustomerService();
        Customer customer = customerService.getCustomerInfoByUserName(customerUserNameText);
        if(customer.getId() == null) {
            JOptionPane.showMessageDialog(this, "No Customer information present for Username " + customerUserNameText);
            customerUserName.setText("");
            return;
        }

        // open new page and show table with back button
        // create table object for customer info
        JTable jTable = createTableForCustomerInfo(customer);

        CustomerInfoOutput customerInfoOutput = new CustomerInfoOutput();
        customerInfoOutput.showCustomerInfoOutput(jTable, user);
        jFrame.setVisible(false);

    }

    private JTable createTableForCustomerInfo(Customer customer) {
        List<String[]> customerInfo = new ArrayList<>();
        String[] values = new String[5];
        values[0] = String.valueOf(customer.getId());
        values[1] = customer.getCname();
        values[2] = customer.getAddress();
        values[3] = customer.getPhone_no();
        values[4] = customer.getUserName();

        customerInfo.add(values);

        List<String> columns = new ArrayList<String>();
        columns.add("customer_id");
        columns.add("customer_name");
        columns.add("address");
        columns.add("phone_number");
        columns.add("username");

        TableModel tableModel = new DefaultTableModel(customerInfo.toArray(new Object[][] {}), columns.toArray());
        JTable table = new JTable(tableModel);
        return table;
    }

}
