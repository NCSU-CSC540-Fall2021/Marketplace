package server;

import client.BrandCreation;
import server.constants.MenuOptions;
import server.dao.BrandDao;

public class Marketplace {
    BrandDao brandDao;


    public Marketplace() {
        brandDao = new BrandDao();
    }

    public void printMenuOptions() {
        System.out.println("Select an option to be performed on marketplace!");
        MenuOptions[] menuOptions = MenuOptions.values();
        for (MenuOptions menuOption : menuOptions) {
            System.out.println(menuOption.getMenuId() + " ------ " + menuOption.getMenuDescription());
        }
    }


    public void performOperation(int menuOption) {
        switch (menuOption) {

            case 1:
                BrandCreation brandCreation = new BrandCreation();
                brandCreation.showFormForInput();
                // brandService.createBrand();
                break;
        }
    }
}
