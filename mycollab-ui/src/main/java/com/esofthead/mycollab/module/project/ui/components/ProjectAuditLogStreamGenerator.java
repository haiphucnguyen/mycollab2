package com.esofthead.mycollab.module.project.ui.components;

import com.esofthead.mycollab.common.domain.SimpleActivityStream;
import com.esofthead.mycollab.module.project.ProjectTypeConstants;
import com.esofthead.mycollab.module.project.ui.format.*;
import com.esofthead.mycollab.utils.AuditLogPrinter;

import java.util.HashMap;
import java.util.Map;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
public class ProjectAuditLogStreamGenerator {
    private static final Map<String, AuditLogPrinter> auditPrinters;

    static {
        auditPrinters = new HashMap<>();
        auditPrinters.put(ProjectTypeConstants.BUG, new AuditLogPrinter(BugFieldFormatter.instance()));
        auditPrinters.put(ProjectTypeConstants.TASK, new AuditLogPrinter(TaskFieldFormatter.instance()));
        auditPrinters.put(ProjectTypeConstants.MILESTONE, new AuditLogPrinter(MilestoneFieldFormatter.instance()));
        auditPrinters.put(ProjectTypeConstants.RISK, new AuditLogPrinter(RiskFieldFormatter.instance()));
        auditPrinters.put(ProjectTypeConstants.PROBLEM, new AuditLogPrinter(ProblemFieldFormatter.instance()));
        auditPrinters.put(ProjectTypeConstants.BUG_COMPONENT, new AuditLogPrinter(ComponentFieldFormatter.instance()));
        auditPrinters.put(ProjectTypeConstants.BUG_VERSION, new AuditLogPrinter(VersionFieldFormatter.instance()));
    }

    public static String generatorDetailChangeOfActivity(SimpleActivityStream activityStream) {

        if (activityStream.getAssoAuditLog() != null) {
            AuditLogPrinter auditLogHandler = auditPrinters.get(activityStream.getType());
            if (auditLogHandler != null) {
                return auditLogHandler.generateChangeSet(activityStream.getAssoAuditLog());
            }

        }
        return "";
    }
}
