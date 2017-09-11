package com.mycollab.module.crm.ui;

import com.mycollab.module.crm.CrmTypeConstants;
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
        resources.put(CrmTypeConstants.INSTANCE.getACCOUNT(), FontAwesome.INSTITUTION);
        resources.put(CrmTypeConstants.INSTANCE.getCONTACT(), FontAwesome.USER);
        resources.put(CrmTypeConstants.INSTANCE.getOPPORTUNITY(), FontAwesome.MONEY);
        resources.put(CrmTypeConstants.INSTANCE.getCASE(), FontAwesome.BUG);
        resources.put(CrmTypeConstants.INSTANCE.getLEAD(), FontAwesome.BUILDING);
        resources.put(CrmTypeConstants.INSTANCE.getACTIVITY(), FontAwesome.CALENDAR);
        resources.put(CrmTypeConstants.INSTANCE.getTASK(), FontAwesome.LIST_ALT);
        resources.put(CrmTypeConstants.INSTANCE.getCALL(), FontAwesome.PHONE);
        resources.put(CrmTypeConstants.INSTANCE.getMEETING(), FontAwesome.PLANE);
        resources.put(CrmTypeConstants.INSTANCE.getCAMPAIGN(), FontAwesome.TROPHY);
        resources.put(CrmTypeConstants.INSTANCE.getDETAIL(), FontAwesome.LIST);
        resources.put(CrmTypeConstants.INSTANCE.getNOTE(), FontAwesome.PENCIL);
    }

    public static FontAwesome getAsset(String resId) {
        return resources.get(resId);
    }

    public static String toHexString(String resId) {
        return "&#x" + Integer.toHexString(resources.get(resId).getCodepoint());
    }
}
