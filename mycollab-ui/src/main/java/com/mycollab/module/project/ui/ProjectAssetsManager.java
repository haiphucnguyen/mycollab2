package com.mycollab.module.project.ui;

import com.mycollab.core.utils.StringUtils;
import com.mycollab.module.project.ProjectTypeConstants;
import com.mycollab.module.project.i18n.OptionI18nEnum.MilestoneStatus;
import com.mycollab.module.project.i18n.OptionI18nEnum.Priority;
import com.vaadin.server.FontAwesome;

import java.util.HashMap;
import java.util.Map;

/**
 * @author MyCollab Ltd.
 * @since 5.0.0
 */
public class ProjectAssetsManager {
    private static final Map<String, FontAwesome> resources;

    static {
        resources = new HashMap<>();
        resources.put(ProjectTypeConstants.INSTANCE.getDASHBOARD(), FontAwesome.DASHBOARD);
        resources.put(ProjectTypeConstants.INSTANCE.getMESSAGE(), FontAwesome.COMMENT);
        resources.put(ProjectTypeConstants.INSTANCE.getMILESTONE(), FontAwesome.FLAG_CHECKERED);
        resources.put(ProjectTypeConstants.INSTANCE.getTASK(), FontAwesome.TASKS);
        resources.put(ProjectTypeConstants.INSTANCE.getTICKET(), FontAwesome.TICKET);
        resources.put(ProjectTypeConstants.INSTANCE.getPAGE(), FontAwesome.FILE);
        resources.put(ProjectTypeConstants.INSTANCE.getBUG(), FontAwesome.BUG);
        resources.put(ProjectTypeConstants.INSTANCE.getBUG_COMPONENT(), FontAwesome.CUBE);
        resources.put(ProjectTypeConstants.INSTANCE.getBUG_VERSION(), FontAwesome.LEAF);
        resources.put(ProjectTypeConstants.INSTANCE.getFILE(), FontAwesome.BRIEFCASE);
        resources.put(ProjectTypeConstants.INSTANCE.getRISK(), FontAwesome.SHIELD);
        resources.put(ProjectTypeConstants.INSTANCE.getFINANCE(), FontAwesome.MONEY);
        resources.put(ProjectTypeConstants.INSTANCE.getTIME(), FontAwesome.CLOCK_O);
        resources.put(ProjectTypeConstants.INSTANCE.getINVOICE(), FontAwesome.CREDIT_CARD);
        resources.put(ProjectTypeConstants.INSTANCE.getSTANDUP(), FontAwesome.CUBES);
        resources.put(ProjectTypeConstants.INSTANCE.getMEMBER(), FontAwesome.USERS);
        resources.put(ProjectTypeConstants.INSTANCE.getPROJECT(), FontAwesome.CALENDAR_O);
    }

    public static FontAwesome getAsset(String resId) {
        FontAwesome font = resources.get(resId);
        return (font != null) ? font : FontAwesome.DASHBOARD;
    }

    public static String toHexString(String resId) {
        return "&#x" + Integer.toHexString(resources.get(resId).getCodepoint());
    }

    public static FontAwesome getPriority(String priority) {
        if (Priority.Urgent.name().equals(priority) || Priority.High.name()
                .equals(priority) || Priority.Medium.name().equals(priority) || priority == null) {
            return FontAwesome.ARROW_UP;
        } else {
            return FontAwesome.ARROW_DOWN;
        }
    }

    public static FontAwesome getMilestoneStatus(String status) {
        if (MilestoneStatus.Closed.name().equals(status)) {
            return FontAwesome.MINUS_CIRCLE;
        } else if (MilestoneStatus.InProgress.name().equals(status)) {
            return FontAwesome.CLOCK_O;
        } else {
            return FontAwesome.SPINNER;
        }
    }

    public static String getPriorityHtml(String priority) {
        if (StringUtils.isBlank(priority)) {
            priority = Priority.Medium.name();
        }
        FontAwesome fontAwesome = getPriority(priority);
        return String.format("<span class=\"priority-%s v-icon\" style=\"font-family: FontAwesome;\">&#x%s;</span>",
                priority.toLowerCase(), Integer.toHexString(fontAwesome.getCodepoint()));
    }
}
