package com.mycollab.db.arguments;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
public abstract class GroupIdProvider {
    private static GroupIdProvider instance;

    abstract public Integer getGroupId();

    abstract public String getGroupRequestedUser();

    public static void registerAccountIdProvider(GroupIdProvider provider) {
        instance = provider;
    }

    public static Integer getAccountId() {
        if (instance != null) {
            try {
                return instance.getGroupId();
            } catch (Exception e) {
                return 0;
            }
        } else {
            return 0;
        }
    }

    public static String getRequestedUser() {
        if (instance != null) {
            try {
                return instance.getGroupRequestedUser();
            } catch (Exception e) {
                return "";
            }
        } else {
            return "";
        }
    }
}
