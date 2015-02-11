package com.esofthead.mycollab.module.user.ui;

import com.vaadin.server.FontAwesome;

import java.util.HashMap;
import java.util.Map;

/**
 * @author MyCollab Ltd.
 * @since 5.0.0
 */
public class SettingAssetsManager {
    private static final Map<String, FontAwesome> resources;

    static {
        resources = new HashMap<>();
        resources.put(SettingUIConstants.PROFILE, FontAwesome.BOOK);
        resources.put(SettingUIConstants.BILLING, FontAwesome.CREDIT_CARD);
        resources.put(SettingUIConstants.USERS, FontAwesome.USERS);
        resources.put(SettingUIConstants.CUSTOMIZATION, FontAwesome.MAGIC);
    }

    public static FontAwesome getAsset(String resId) {
        return resources.get(resId);
    }
}
