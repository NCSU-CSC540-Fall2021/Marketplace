package client;

import server.entity.Brand;
import server.entity.User;
import server.service.BrandService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShowBrandInfo extends JFrame{
    private JTextField brandUserName;
    private JButton showBrandInfoButton;
    private JPanel brandInfoPanel;
    private JButton goBackButton;
    private JFrame jFrame;
    private User user;

    public ShowBrandInfo() {
        showBrandInfoButton.addActionListener(e -> {
            try {
                submitBrandInfo();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
        goBackButton.addActionListener(e -> {
            AdminLandingPage adminLandingPage = new AdminLandingPage();
            adminLandingPage.selectMenuOption(user);
            jFrame.setVisible(false);
        });
    }

    public void showBrandInfo(User user) {
        this.user = user;
        jFrame = new JFrame("Show Brand Info");
        jFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        jFrame.setPreferredSize(new Dimension(500, 500));
        jFrame.setResizable(false);

        jFrame.add(brandInfoPanel);
        jFrame.pack();
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);
    }

    public void submitBrandInfo() throws SQLException {
        String brandUserNameText = brandUserName.getText();

        // select brand by username
        BrandService brandService = new BrandService();
        Brand brand = brandService.getBrandInfoByUserName(brandUserNameText);

        if(brand.getBrand_id() == null) {
            JOptionPane.showMessageDialog(this, "No Brand information present for Username " + brandUserNameText);
            brandUserName.setText("");
            return;
        }

        // open new page and show table with back button
        // create table object for brand info
        JTable jTable = createTableForBrandInfo(brand);

        BrandInfoOutput brandInfoOutput = new BrandInfoOutput();
        brandInfoOutput.showBrandInfoOutput(jTable, user);
        jFrame.setVisible(false);

    }

    public JTable createTableForBrandInfo(Brand brand)
    {
        List<String[]> brandInfo = new ArrayList<>();
        String[] values = new String[5];
        values[0] = String.valueOf(brand.getBrand_id());
        values[1] = brand.getBrand_name();
        values[2] = brand.getAddress();
        values[3] = String.valueOf(brand.getJoindate());
        values[4] = brand.getUsername();

        brandInfo.add(values);


        List<String> columns = new ArrayList<String>();
        columns.add("brand_id");
        columns.add("brand_name");
        columns.add("address");
        columns.add("join_date");
        columns.add("username");

        TableModel tableModel = new DefaultTableModel(brandInfo.toArray(new Object[][] {}), columns.toArray());
        JTable table = new JTable(tableModel);
        return table;
    }
}
