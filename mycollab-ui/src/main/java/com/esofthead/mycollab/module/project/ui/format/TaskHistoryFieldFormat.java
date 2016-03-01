package com.esofthead.mycollab.module.project.ui.format;

import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectLinkBuilder;
import com.esofthead.mycollab.module.project.ProjectTypeConstants;
import com.esofthead.mycollab.module.project.domain.SimpleTask;
import com.esofthead.mycollab.module.project.service.ProjectTaskService;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.utils.HistoryFieldFormat;
import com.esofthead.mycollab.vaadin.AppContext;
import com.hp.gagawa.java.elements.Span;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author MyCollab Ltd
 * @since 5.2.9
 */
public final class TaskHistoryFieldFormat implements HistoryFieldFormat {
    private static final Logger LOG = LoggerFactory.getLogger(TaskHistoryFieldFormat.class);

    @Override
    public String toString(String value) {
        if (StringUtils.isBlank(value)) {
            return new Span().write();
        }

        try {
            Integer taskId = Integer.parseInt(value);
            ProjectTaskService taskService = ApplicationContextUtil.getSpringBean(ProjectTaskService.class);
            SimpleTask task = taskService.findById(taskId, AppContext.getAccountId());

            if (task != null) {
                return ProjectLinkBuilder.generateProjectItemHtmlLinkAndTooltip(CurrentProjectVariables.getShortName(),
                        task.getProjectid(), task.getTaskname(), ProjectTypeConstants.TASK, task.getId() + "");
            }
        } catch (Exception e) {
            LOG.error("Error", e);
        }

        return value;
    }
}
