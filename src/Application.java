import client.CustomerRewardActivitiesCreation;
import client.Homepage;
import client.AdminLandingPage;
import server.Marketplace;

public class Application {
    public static void main(String[] args) {

        Marketplace marketplace = new Marketplace();
        AdminLandingPage adminLandingPage = new AdminLandingPage();
        Homepage homepage = new Homepage();
        homepage.showHomePage();

        CustomerRewardActivitiesCreation customerRewardActivitiesCreation = new CustomerRewardActivitiesCreation();
        customerRewardActivitiesCreation.selectRewardActivity();

//        menuOptions.selectMenuOption();
    }

}
