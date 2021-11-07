package server.constants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum ShowQueriesOptions {
    LIST_CUSTOMERS_NOT_IN_BRAND_02(1, "List all customers that are not part of Brand02’s program."),
    CUSTOMER_LOYALTY_NOT_ACTIVITY(2, "List customers that have joined a loyalty program but have not participated in any activity\n" +
            "in that program (list the customerid and the loyalty program id)."),
    REWARDS_BRAND_01(3, "List the rewards that are part of Brand01 loyalty program."),
    REFER_FRIEND(4, "List all the loyalty programs that include “refer a friend” as an activity in at least one of\n" +
            "their reward rules."),
    BRAND_01_ACTIVITY_LIST(5, "For Brand01, list for each activity type in their loyalty program, the number instances that\n" +
            "have occurred."),
    CUSTOMER_REDEEMED_BRAND_01(6, "List customers of Brand01 that have redeemed at least twice."),
    BRANDS_TOTAL_REDEEMED_LT_500(7, "All brands where total number of points redeemed overall is less than 500 points"),
    CUSTOMER_BRAND_NO_OF_ACTIVITIES_DATE(8, "For Customer C0003, and Brand02, number of activities they have done in the period of\n" +
            "08/1/2021 and 9/30/2021.");

    public int optionId;
    public String optionDescription;

    ShowQueriesOptions(int optionId, String optionDescription) {
        this.optionId = optionId;
        this.optionDescription = optionDescription;
    }

    public static ShowQueriesOptions getQueryOptionByDescription(String optionDescription) {
        return Arrays.stream(values()).filter(value -> value.getOptionDescription().equals(optionDescription))
                .findFirst().orElse(null);
    }

    public int getOptionId() {
        return optionId;
    }

    public String getOptionDescription() {
        return optionDescription;
    }
    public static List<String> getOptionDescriptionList() {
        ShowQueriesOptions[] values = values();
        List<String> optionsDescriptionList = Arrays.stream(values).map(ShowQueriesOptions::getOptionDescription).collect(Collectors.toList());
        return optionsDescriptionList;
    }


}
