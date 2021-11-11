package client;

import server.entity.Brand;
import server.entity.Customer;
import server.entity.User;
import server.service.BrandService;
import server.service.CustomerService;
import server.service.MembershipEnrollService;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.text.ParseException;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class CustomerLoyaltySignUp extends JFrame {
    private JButton goBackButton;
    private JButton enrollButton;
    private JTextField brandName;
    private JPanel loyaltySignupPanel;
    JFrame jFrame;
    User user;

    public CustomerLoyaltySignUp() {
        goBackButton.addActionListener(
                e-> {
                    CustomerLanding customerLanding = new CustomerLanding();
                    customerLanding.showInput(user);
                    jFrame.setVisible(false);
                });
        enrollButton.addActionListener(e-> {
            try {
                submit();
            } catch (SQLException|ParseException ex) {
                ex.printStackTrace();
            }
        });
    }

    public void customerLoyaltyForm(User user) {
        this.user = user;
        jFrame = new JFrame("Customer: Enroll in Loyalty Program");
        jFrame.setPreferredSize(new Dimension(500, 500));
        jFrame.setResizable(false);
        jFrame.add(loyaltySignupPanel);
        jFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        jFrame.pack();
        jFrame.setVisible(true);
        jFrame.setLocationRelativeTo(null);
    }

    public void submit() throws SQLException, ParseException {
        BrandService brandService = new BrandService();
        Brand brand = brandService.getBrandInfoByUserName(brandName.getText());

        if(brand.getLoyaltyProgramId() == null) {
            JOptionPane.showMessageDialog(this, "No loyalty program setup for " + brand.getBrand_name());
            CustomerLanding customerLanding = new CustomerLanding();
            customerLanding.showInput(user);
            jFrame.setVisible(false);
            return;
        }

        CustomerService customerService = new CustomerService();
        Customer customer = customerService.getCustomerInfoByUserName(user.getUserName());

        MembershipEnrollService membershipEnrollService = new MembershipEnrollService();
        String resp = membershipEnrollService.enrollCustomer(customer.getId(), brand.getLoyaltyProgramId());

        JOptionPane.showMessageDialog(this, resp);
        CustomerLanding customerLanding = new CustomerLanding();
        customerLanding.showInput(user);
        jFrame.setVisible(false);
    }
}
