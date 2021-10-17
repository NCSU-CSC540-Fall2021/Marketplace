package server.constants;

public enum MenuOptions {

    CREATE_BRAND(1, "Create a brand"),
    CREATE_CUSTOMER(2, "Onboard a customer");

    public int menuId;
    public String menuDescription;

    MenuOptions(int menuId, String menuDescription){
        this.menuId = menuId;
        this.menuDescription = menuDescription;
    }

    public int getMenuId() {
        return menuId;
    }

    public String getMenuDescription() {
        return menuDescription;
    }

}
