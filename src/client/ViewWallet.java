package client;

import server.dao.WalletDao;
import server.entity.Customer;
import server.entity.User;
import server.entity.Wallet;
import server.service.CustomerService;
import server.service.WalletService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class ViewWallet {
    private JButton goBackButton;
    private JPanel viewWalletPanel;
    private JLabel noDataFound;
    private JFrame jFrame;
    private JTable viewWalletTable;
    private User user;

    public ViewWallet() {
        goBackButton.addActionListener(e -> {
            CustomerLanding customerLanding = new CustomerLanding();
            customerLanding.showInput(user);
            jFrame.setVisible(false);
        });
    }

    public void showCustomerWallet(User user) throws SQLException {
        this.user = user;
        jFrame = new JFrame("Customer: View Wallet");
        jFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        jFrame.setPreferredSize(new Dimension(600, 500));
        jFrame.setResizable(false);
        viewWalletPanel = new JPanel();
        viewWalletPanel.add(goBackButton);

        Customer customer = new CustomerService().getCustomerInfoByUserName(user.getUserName());
        try {
            Wallet[] wallets = new WalletDao().viewWallet(customer.getId());
            if(wallets.length > 0) {
                List<String> columns = new ArrayList<String>();
                columns.add("loyalty_id");
                columns.add("net_points");
                columns.add("tier_status");
                columns.add("deleted");
                columns.add("updatedAt");

                List<String[]> walletInfo = new ArrayList<>();
                for (int i = 0; i < wallets.length; i++) {
                    String[] values = new String[5];
                    values[0] = String.valueOf(wallets[i].getLoyalty_id());
                    values[1] = String.valueOf(wallets[i].getNet_points());
                    values[2] = String.valueOf(wallets[i].getTier_status());
                    values[3] = String.valueOf(wallets[i].getDeleted());
                    values[4] = String.valueOf(wallets[i].getUpdatedAt());
                    walletInfo.add(values);
                }

                TableModel tableModel = new DefaultTableModel(walletInfo.toArray(new Object[][] {}), columns.toArray());
                viewWalletPanel.setLayout((new BoxLayout(viewWalletPanel, BoxLayout.Y_AXIS)));
                viewWalletTable = new JTable(tableModel);
                viewWalletTable.setPreferredScrollableViewportSize(new Dimension(500, 70));
                viewWalletTable.setFillsViewportHeight(true);
                JScrollPane scrollPane = new JScrollPane(viewWalletTable);

                viewWalletPanel.add(scrollPane);
            }
        } catch (SQLException exception) {
            JOptionPane.showMessageDialog(jFrame, exception.getMessage());
            jFrame.setVisible(false);
//            noDataFound = new JLabel("No data Found for Customer Id: " + customer.getId());
//            viewWalletPanel.add(noDataFound);
        }
        jFrame.add(viewWalletPanel);
        jFrame.pack();
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);
    }
}
