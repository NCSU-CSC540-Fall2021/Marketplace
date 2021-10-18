import client.MenuOptions;
import server.Marketplace;

import java.util.Scanner;

public class Application {
    public static void main(String[] args) {

        Marketplace marketplace = new Marketplace();
        MenuOptions menuOptions = new MenuOptions();

        menuOptions.selectMenuOption();
//        while (true) {
//            try {
//
//
////                marketplace.printMenuOptions();
////                int menuOption = choice.getMenuId();
////                if (menuOption == 0) {
////                    System.out.println("Thanks for choosing the marketplace! Have a great day!");
////                    break;
////                }
//
////                marketplace.performOperation(menuOption);
//            } catch (Exception e) {
//                // todo: add separate exceptions
//                e.printStackTrace();
//            }
//        }
    }



    public static int getMenuOption() {
        Scanner scanner = new Scanner(System.in);
        int option = scanner.nextInt();
        System.out.println("The selected option is " + option);
        return option;
    }
}
