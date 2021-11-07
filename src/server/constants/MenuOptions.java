package server.constants;

public enum MenuOptions {

    CREATE_BRAND(1, "Create a brand"),
    CREATE_CUSTOMER(2, "Onboard a customer"),
    SHOW_BRAND_INFO(3, "Show brand info"),
    SHOW_CUSTOMER_INFO(4, "Show customer info"),

    CREATE_ACTIVITY_TYPE(5, "Create activity type"),
    CREATE_REWARD_TYPE(6, "Create reward type"),
    LOGOUT(7, "Logout");

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
