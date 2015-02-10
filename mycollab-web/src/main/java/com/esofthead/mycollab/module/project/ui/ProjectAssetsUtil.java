package com.esofthead.mycollab.module.project.ui;

import com.esofthead.mycollab.module.project.i18n.OptionI18nEnum;
import com.vaadin.server.FontAwesome;

import java.util.Properties;

/**
 * @author MyCollab Ltd.
 * @since 5.0.0
 */
public class ProjectAssetsUtil {
    private static final String BUG_SEVERITY_CRITICAL = "<span class=\"v-icon bug-severity-critical\" " +
            "style=\"font-family: FontAwesome;\">&#xf005;</span>";

    private static final String BUG_SEVERITY_MAJOR = "<span class=\"v-icon bug-severity-major\" " +
            "style=\"font-family: FontAwesome;\">&#xf005;</span>";

    private static final String BUG_SEVERITY_MINOR = "<span class=\"v-icon bug-severity-minor\" " +
            "style=\"font-family: FontAwesome;\">&#xf005;</span>";

    private static final String BUG_SEVERITY_TRIVIAL = "<span class=\"v-icon bug-severity-trivial\" " +
            "style=\"font-family: FontAwesome;\">&#xf005;</span>";

    private static final String BUG_PRIORITY_BLOCKER = "<span class=\"v-icon bug-priority-blocker\" " +
            "style=\"font-family: FontAwesome;\">&#xf062;</span>";

    private static final String BUG_PRIORITY_CRITICAL = "<span class=\"v-icon bug-priority-critical\" " +
            "style=\"font-family: FontAwesome;\">&#xf062;</span>";

    private static final String BUG_PRIORITY_MAJOR = "<span class=\"v-icon bug-priority-major\" " +
            "style=\"font-family: FontAwesome;\">&#xf062;</span>";

    private static final String BUG_PRIORITY_MINOR = "<span class=\"v-icon bug-priority-minor\" " +
            "style=\"font-family: FontAwesome;\">&#xf063;</span>";

    private static final String BUG_PRIORITY_TRIVIAL = "<span class=\"v-icon bug-priority-trivial\" " +
            "style=\"font-family: FontAwesome;\">&#xf063;</span>";

    private static final Properties severityMap;

    private static final Properties priorityMap;

    static {
        severityMap = new Properties();
        severityMap.put(OptionI18nEnum.BugSeverity.Critical.name(), BUG_SEVERITY_CRITICAL);
        severityMap.put(OptionI18nEnum.BugSeverity.Major.name(), BUG_SEVERITY_MAJOR);
        severityMap.put(OptionI18nEnum.BugSeverity.Minor.name(), BUG_SEVERITY_MINOR);
        severityMap.put(OptionI18nEnum.BugSeverity.Trivial.name(), BUG_SEVERITY_TRIVIAL);

        priorityMap = new Properties();
        priorityMap.put(OptionI18nEnum.BugPriority.Blocker.name(), BUG_PRIORITY_BLOCKER);
        priorityMap.put(OptionI18nEnum.BugPriority.Critical.name(), BUG_PRIORITY_CRITICAL);
        priorityMap.put(OptionI18nEnum.BugPriority.Major.name(), BUG_PRIORITY_MAJOR);
        priorityMap.put(OptionI18nEnum.BugPriority.Minor.name(), BUG_PRIORITY_MINOR);
        priorityMap.put(OptionI18nEnum.BugPriority.Trivial.name(), BUG_PRIORITY_TRIVIAL);
    }


    public static FontAwesome getPhaseIcon(String status) {
        if (OptionI18nEnum.MilestoneStatus.Closed.name().equals(status)) {
            return FontAwesome.MINUS;
        } else if (OptionI18nEnum.MilestoneStatus.Future.name().equals(status)) {
            return FontAwesome.CLOCK_O;
        } else {
            return FontAwesome.SPINNER;
        }
    }

    public static String getBugSeverityHtml(String severity) {
        return severityMap.getProperty(severity, "");
    }

    public static String getBugPriorityHtml(String priority) {
        return priorityMap.getProperty(priority, "");
    }

    public static void main(String[] args) {
        System.out.println(FontAwesome.ARROW_DOWN.getHtml());
    }
}
