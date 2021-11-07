package client;

import javax.swing.*;
import java.awt.*;

public class Homepage extends JFrame {
    private JButton login;
    private JButton signup;
    private JButton showQueries;
    private JPanel homepage;
    private JButton exit;
    private JFrame jFrame;

    Loginpage loginpage = new Loginpage();
    ShowQueriesPage showQueriesPage = new ShowQueriesPage();
    SignupPage signupPage = new SignupPage();

    public Homepage() {
        login.addActionListener(e -> navigateToLoginPage());
        exit.addActionListener(e -> System.exit(0));
        showQueries.addActionListener(e -> navigateToShowQueriesPage());
        signup.addActionListener(e -> navigateToShowSignUpPage());
    }

    private void navigateToShowSignUpPage() {
        signupPage.showSignUp();
    }

    private void navigateToShowQueriesPage() {
        showQueriesPage.selectQueryMenu();
    }

    public void navigateToLoginPage() {
        loginpage.showInput();
    }

    public void showHomePage() {
        jFrame = new JFrame("Welcome to market place");
        jFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        jFrame.setPreferredSize(new Dimension(500, 500));
        jFrame.setResizable(false);

        jFrame.add(homepage);
        jFrame.pack();
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);
    }
}
