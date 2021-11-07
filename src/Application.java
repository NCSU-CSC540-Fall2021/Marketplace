import client.Homepage;
import client.AdminLandingPage;
import server.Marketplace;

public class Application {
    public static void main(String[] args) {

        Marketplace marketplace = new Marketplace();
        AdminLandingPage adminLandingPage = new AdminLandingPage();
        Homepage homepage = new Homepage();
        homepage.showHomePage();

//        menuOptions.selectMenuOption();
    }

}
