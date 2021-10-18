package client;

import server.service.BrandService;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.text.ParseException;

public class BrandCreation extends JFrame{
    private JPanel brandCreate;
    private JTextField brandName;
    private JTextField brandAddress;
    private JTextField brandJoinDate;
    private JTextField brandCreatorId;
    private JButton createButton;
    private JFrame jFrame;


    public BrandCreation()
    {
        createButton.addActionListener(e -> {
            try {
                submit();
            } catch (ParseException | SQLException ex) {
                ex.printStackTrace();
            }
        });
    }

    public void showFormForInput() {
        jFrame = new JFrame("Create Brand");
        jFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        jFrame.setPreferredSize(new Dimension(500, 500));
        jFrame.setResizable(false);

        jFrame.add(brandCreate);
        jFrame.pack();
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);
    }

    public void submit() throws ParseException, SQLException {
        String brandNameText = brandName.getText();
        String brandAddressText = brandAddress.getText();
        String brandJoinDateText = brandJoinDate.getText();
        String brandCreatorIdText = brandCreatorId.getText();

        System.out.println("Submitted successfully " + brandNameText + " "
                + brandAddressText + " " + brandJoinDateText + " " + brandCreatorIdText);

        BrandService brandService = new BrandService();
        String response = brandService.createBrand(brandNameText, brandAddressText, brandJoinDateText, brandCreatorIdText);

        JOptionPane.showMessageDialog(this, response);
        jFrame.setVisible(false);
    }


}
