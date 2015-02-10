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
        resources.put(CrmTypeConstants.OPPORTUNITY, FontAwesome.MONEY);
        resources.put(CrmTypeConstants.CASE, FontAwesome.BUG);
        resources.put(CrmTypeConstants.LEAD, FontAwesome.BUILDING);
        resources.put(CrmTypeConstants.ACTIVITY, FontAwesome.CALENDAR);
        resources.put(CrmTypeConstants.TASK, FontAwesome.LIST_ALT);
        resources.put(CrmTypeConstants.CALL, FontAwesome.PHONE);
        resources.put(CrmTypeConstants.MEETING, FontAwesome.PLANE);
        resources.put(CrmTypeConstants.CAMPAIGN, FontAwesome.TROPHY);
        resources.put(CrmTypeConstants.DETAIL, FontAwesome.LIST);
    }

    public static FontAwesome getAsset(String resId) {
        return resources.get(resId);
    }
}
