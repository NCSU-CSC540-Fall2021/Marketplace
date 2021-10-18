import client.MenuOptions;
import server.Marketplace;

public class Application {
    public static void main(String[] args) {

        Marketplace marketplace = new Marketplace();
        MenuOptions menuOptions = new MenuOptions();

        menuOptions.selectMenuOption();
    }

}
