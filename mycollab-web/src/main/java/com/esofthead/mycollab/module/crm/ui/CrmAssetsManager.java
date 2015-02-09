package com.esofthead.mycollab.module.crm.ui;

import com.esofthead.mycollab.module.crm.CrmTypeConstants;
import com.vaadin.server.FontAwesome;

import java.util.HashMap;
import java.util.Map;

/**
 * @author MyCollab Ltd.
 * @since 5.0.0
 */
public class CrmAssetsManager {
    private static final Map<String, FontAwesome> resources;

    static {
        resources = new HashMap<>();
        resources.put(CrmTypeConstants.ACCOUNT, FontAwesome.INSTITUTION);
        resources.put(CrmTypeConstants.CONTACT, FontAwesome.USER);
    }

    public static FontAwesome getAsset(String resId) {
        return resources.get(resId);
    }
}
