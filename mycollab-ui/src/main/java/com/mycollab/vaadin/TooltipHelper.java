package com.mycollab.vaadin;

/**
 * @author MyCollab Ltd.
 * @since 4.6.0
 */
public class TooltipHelper {
    public static final String TOOLTIP_ID = "mycollabtip";

    public static String userHoverJsFunction(String username) {
        String uidVal = "'" + TOOLTIP_ID + "'";
        String usernameVal = "'" + username + "'";
        String urlVal = "'" + AppUI.getSiteUrl() + "tooltip/'";
        String siteUrlVal = "'" + AppUI.getSiteUrl() + "'";
        String timezoneVal = "'" + UserUIContext.getUser().getTimezone() + "'";
        String accountIdVal = "'" + AppUI.getAccountId() + "'";
        String localeVal = "'" + UserUIContext.getUserLocale().toLanguageTag() + "'";

        return String.format("return showUserTooltip(%s,%s,%s,%s,%s,%s,%s);", uidVal, usernameVal, urlVal,
                siteUrlVal, timezoneVal, accountIdVal, localeVal);
    }

    public static String projectHoverJsFunction(String type, String typeId) {
        String uidVal = "'" + TOOLTIP_ID + "'";
        String typeVal = "'" + type + "'";
        String typeIdVal = "'" + typeId + "'";
        String urlVal = "'" + AppUI.getSiteUrl() + "tooltip/'";
        String accountIdVal = "'" + AppUI.getAccountId() + "'";
        String siteUrlVal = "'" + AppUI.getSiteUrl() + "'";
        String timezoneVal = "'" + AppUI.getAccountId() + "'";
        String localeVal = "'" + UserUIContext.getUserLocale().toLanguageTag() + "'";
        String dateFormatVal = "'" + AppUI.getDateFormat() + "'";

        return String.format("return overIt(%s,%s,%s,%s,%s,%s,%s,%s,%s);", uidVal, typeVal, typeIdVal,
                urlVal, accountIdVal, siteUrlVal, timezoneVal, localeVal, dateFormatVal);
    }

    public static String crmHoverJsFunction(String type, String typeId) {
        String uidVal = "'" + TOOLTIP_ID + "'";
        String typeVal = "'" + type + "'";
        String typeIdVal = "'" + typeId + "'";
        String urlVal = "'" + AppUI.getSiteUrl() + "tooltip/'";
        String accountIdVal = "'" + AppUI.getAccountId() + "'";
        String siteUrlVal = "'" + AppUI.getSiteUrl() + "'";
        String timezoneVal = "'" + AppUI.getAccountId() + "'";
        String localeVal = "'" + UserUIContext.getUserLocale().toLanguageTag() + "'";
        String dateFormatVal = "'" + AppUI.getDateFormat() + "'";
        return String.format("return crmActivityOverIt(%s,%s,%s,%s,%s,%s,%s,%s,%s);",
                uidVal, typeVal, typeIdVal, urlVal, accountIdVal, siteUrlVal, timezoneVal, localeVal, dateFormatVal);
    }

    public static String itemMouseLeaveJsFunction() {
        return String.format("hideTooltip('%s')", TOOLTIP_ID);
    }
}
