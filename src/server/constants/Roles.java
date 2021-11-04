package server.constants;

public enum Roles {
    BRAND("BRAND"),
    CUSTOMER("CUSTOMER"),
    ADMIN("ADMIN");

    public String desc;

    Roles(String desc){
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}
