package server.constants;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum BrandLandingOptions {
    ADD_LOYALTY_PROGRAM(1, "Add Loyalty Program"),
    ADD_RE_RULES(2, "Add RE Rules"),
    UPDATE_RE_RULES(3, "Update RE Rules"),
    ADD_RR_RULES(4, "Add RR Rules"),
    UPDATE_RR_RULES(5, "Update RR Rules"),
    VALIDATE_LOYALTY_PROGRAM(6, "Validate Loyalty Program"),
    LOGOUT(7, "Logout");

    public int optionId;
    public String optionDescription;

    BrandLandingOptions (int optionId, String optionDescription) {
        this.optionId = optionId;
        this.optionDescription = optionDescription;
    }

    public int getOptionId() {
        return optionId;
    }

    public String getOptionDescription() {
        return optionDescription;
    }

    public static List<String> getOptionDescriptionList() {
        List<String> optionDescriptionList = Arrays.stream(values()).map(BrandLandingOptions::getOptionDescription).collect(Collectors.toList());
        return optionDescriptionList;
    }

    public static BrandLandingOptions getBrandOptionByDescription(String optionDescription) {
        return Arrays.stream(values()).filter(value -> value.getOptionDescription().equals(optionDescription))
                .findFirst().orElse(null);
    }
}
