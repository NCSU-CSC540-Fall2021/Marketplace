package server.constants;

public enum CustomerLandingOptions {

    ENROLL(1, "Enroll in Loyalty Program"),
    REWARD_ACTIVITIES(2, "Reward Activities"),
    VIEW_WALLET(3, "View Wallet"),
    REDEEM_POINTS(4, "Redeem Points"),
    LOGOUT(5, "Logout");

    public int optionId;
    public String optionDescription;

    CustomerLandingOptions (int optionId, String optionDescription) {
        this.optionId = optionId;
        this.optionDescription = optionDescription;
    }

    public int getOptionId() {
        return optionId;
    }

    public String getOptionDescription() {
        return optionDescription;
    }
}
