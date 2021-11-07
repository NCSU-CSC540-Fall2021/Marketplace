import client.Homepage;
import client.AdminLandingPage;

public class Application {
    public static void main(String[] args) {

        AdminLandingPage adminLandingPage = new AdminLandingPage();
        Homepage homepage = new Homepage();
        homepage.showHomePage();
    }

}
