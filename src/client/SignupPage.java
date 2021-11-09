package client;

import javax.swing.*;
import java.awt.*;

public class SignupPage extends JFrame {
    private JButton brandSignup;
    private JButton customerSignup;
    private JButton goBack;
    private JPanel signupPagePanel;
    private JFrame jFrame;

    public SignupPage() {
        brandSignup.addActionListener(e -> {
            jFrame.setVisible(false);
            BrandCreation brandCreation = new BrandCreation();
            brandCreation.showFormForInput(null);
        });

        customerSignup.addActionListener(e -> {
            jFrame.setVisible(false);
            CustomerCreation customerCreation = new CustomerCreation();
            customerCreation.showFormForInput(null);
        });

        goBack.addActionListener(e -> {
            jFrame.setVisible(false);
            Homepage homepage = new Homepage();
            homepage.showHomePage();
        });
    }

    public void showSignUp() {
        jFrame = new JFrame("Customer Landing Page");
        jFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        jFrame.setPreferredSize(new Dimension(500, 500));
        jFrame.setResizable(false);
        jFrame.add(signupPagePanel);
        jFrame.pack();
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);
    }
}
