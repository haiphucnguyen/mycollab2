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
        String urlVal = "'" + MyCollabUI.getSiteUrl() + "tooltip/'";
        String siteUrlVal = "'" + MyCollabUI.getSiteUrl() + "'";
        String timezoneVal = "'" + UserUIContext.getUser().getTimezone() + "'";
        String accountIdVal = "'" + MyCollabUI.getAccountId() + "'";
        String localeVal = "'" + UserUIContext.getUserLocale().toLanguageTag() + "'";

        return String.format("return showUserTooltip(%s,%s,%s,%s,%s,%s,%s);", uidVal, usernameVal, urlVal,
                siteUrlVal, timezoneVal, accountIdVal, localeVal);
    }

    public static String projectHoverJsFunction(String type, String typeId) {
        String uidVal = "'" + TOOLTIP_ID + "'";
        String typeVal = "'" + type + "'";
        String typeIdVal = "'" + typeId + "'";
        String urlVal = "'" + MyCollabUI.getSiteUrl() + "tooltip/'";
        String accountIdVal = "'" + MyCollabUI.getAccountId() + "'";
        String siteUrlVal = "'" + MyCollabUI.getSiteUrl() + "'";
        String timezoneVal = "'" + MyCollabUI.getAccountId() + "'";
        String localeVal = "'" + UserUIContext.getUserLocale().toLanguageTag() + "'";
        String dateFormatVal = "'" + MyCollabUI.getDateFormat() + "'";

        return String.format("return overIt(%s,%s,%s,%s,%s,%s,%s,%s,%s);", uidVal, typeVal, typeIdVal,
                urlVal, accountIdVal, siteUrlVal, timezoneVal, localeVal, dateFormatVal);
    }

    public static String crmHoverJsFunction(String type, String typeId) {
        String uidVal = "'" + TOOLTIP_ID + "'";
        String typeVal = "'" + type + "'";
        String typeIdVal = "'" + typeId + "'";
        String urlVal = "'" + MyCollabUI.getSiteUrl() + "tooltip/'";
        String accountIdVal = "'" + MyCollabUI.getAccountId() + "'";
        String siteUrlVal = "'" + MyCollabUI.getSiteUrl() + "'";
        String timezoneVal = "'" + MyCollabUI.getAccountId() + "'";
        String localeVal = "'" + UserUIContext.getUserLocale().toLanguageTag() + "'";
        String dateFormatVal = "'" + MyCollabUI.getDateFormat() + "'";
        return String.format("return crmActivityOverIt(%s,%s,%s,%s,%s,%s,%s,%s,%s);",
                uidVal, typeVal, typeIdVal, urlVal, accountIdVal, siteUrlVal, timezoneVal, localeVal, dateFormatVal);
    }

    public static String itemMouseLeaveJsFunction() {
        return String.format("hideTooltip('%s')", TOOLTIP_ID);
    }
}
