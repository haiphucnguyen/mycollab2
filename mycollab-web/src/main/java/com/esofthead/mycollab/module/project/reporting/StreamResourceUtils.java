package com.esofthead.mycollab.module.project.reporting;

import com.esofthead.mycollab.core.db.query.VariableInjector;
import com.esofthead.mycollab.module.project.domain.SimpleTask;
import com.esofthead.mycollab.module.project.domain.criteria.TaskSearchCriteria;
import com.esofthead.mycollab.module.project.service.ProjectTaskService;
import com.esofthead.mycollab.module.project.view.bug.BugTableFieldDef;
import com.esofthead.mycollab.module.project.view.task.TaskTableFieldDef;
import com.esofthead.mycollab.module.tracker.domain.SimpleBug;
import com.esofthead.mycollab.module.tracker.domain.criteria.BugSearchCriteria;
import com.esofthead.mycollab.module.tracker.service.BugService;
import com.esofthead.mycollab.reporting.ReportExportType;
import com.esofthead.mycollab.reporting.ReportStreamSource;
import com.esofthead.mycollab.reporting.RpFieldsBuilder;
import com.esofthead.mycollab.reporting.SimpleReportTemplateExecutor;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.vaadin.server.StreamResource;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author MyCollab Ltd
 * @since 5.2.12
 */
public class StreamResourceUtils {
    public static final StreamResource buildTaskStreamResource(ReportExportType exportType,
                                                               final VariableInjector<TaskSearchCriteria> variableInjector) {
        List fields = Arrays.asList(TaskTableFieldDef.taskname(), TaskTableFieldDef.status(), TaskTableFieldDef.priority(),
                TaskTableFieldDef.duedate(), TaskTableFieldDef.percentagecomplete(), TaskTableFieldDef.startdate(),
                TaskTableFieldDef.assignee(), TaskTableFieldDef.billableHours(), TaskTableFieldDef.nonBillableHours());
        SimpleReportTemplateExecutor reportTemplateExecutor = new SimpleReportTemplateExecutor.AllItems<>("Tasks",
                new RpFieldsBuilder(fields), exportType, SimpleTask.class, ApplicationContextUtil.getSpringBean(ProjectTaskService.class));
        ReportStreamSource streamSource = new ReportStreamSource(reportTemplateExecutor) {
            @Override
            protected Map<String, Object> initReportParameters() {
                Map<String, Object> parameters = new HashMap<>();
                parameters.put("siteUrl", AppContext.getSiteUrl());
                parameters.put(SimpleReportTemplateExecutor.CRITERIA, variableInjector.eval());
                return parameters;
            }
        };
        return new StreamResource(streamSource, exportType.getDefaultFileName());
    }

    public static final StreamResource buildBugStreamResource(ReportExportType exportType, final
    VariableInjector<BugSearchCriteria> variableInjector) {
        List fields = Arrays.asList(BugTableFieldDef.summary(), BugTableFieldDef.environment(), BugTableFieldDef.priority(),
                BugTableFieldDef.severity(), BugTableFieldDef.status(), BugTableFieldDef.resolution(),
                BugTableFieldDef.logBy(), BugTableFieldDef.duedate(), BugTableFieldDef.assignUser(),
                BugTableFieldDef.billableHours(), BugTableFieldDef.nonBillableHours());
        SimpleReportTemplateExecutor reportTemplateExecutor = new SimpleReportTemplateExecutor.AllItems<>("Bugs", new
                RpFieldsBuilder(fields), exportType, SimpleBug.class, ApplicationContextUtil.getSpringBean(BugService.class));
        ReportStreamSource streamSource = new ReportStreamSource(reportTemplateExecutor) {
            @Override
            protected Map<String, Object> initReportParameters() {
                Map<String, Object> parameters = new HashMap<>();
                parameters.put("siteUrl", AppContext.getSiteUrl());
                parameters.put(SimpleReportTemplateExecutor.CRITERIA, variableInjector.eval());
                return parameters;
            }
        };
        return new StreamResource(streamSource, exportType.getDefaultFileName());

    }
}
