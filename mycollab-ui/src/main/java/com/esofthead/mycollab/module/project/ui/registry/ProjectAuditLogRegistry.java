package com.esofthead.mycollab.module.project.ui.registry;

import com.esofthead.mycollab.module.project.ProjectTypeConstants;
import com.esofthead.mycollab.module.project.ui.format.*;
import com.esofthead.mycollab.vaadin.ui.registry.AuditLogRegistry;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author MyCollab Ltd
 * @since 5.2.11
 */
@Component
public class ProjectAuditLogRegistry implements InitializingBean {
    @Autowired
    private AuditLogRegistry auditLogRegistry;

    @Override
    public void afterPropertiesSet() throws Exception {
        auditLogRegistry.registerAuditLogHandler(ProjectTypeConstants.BUG, BugFieldFormatter.instance());
        auditLogRegistry.registerAuditLogHandler(ProjectTypeConstants.TASK, TaskFieldFormatter.instance());
        auditLogRegistry.registerAuditLogHandler(ProjectTypeConstants.MILESTONE, MilestoneFieldFormatter.instance());
        auditLogRegistry.registerAuditLogHandler(ProjectTypeConstants.RISK, RiskFieldFormatter.instance());
        auditLogRegistry.registerAuditLogHandler(ProjectTypeConstants.BUG_COMPONENT, ComponentFieldFormatter.instance());
        auditLogRegistry.registerAuditLogHandler(ProjectTypeConstants.BUG_VERSION, VersionFieldFormatter.instance());
    }
}
