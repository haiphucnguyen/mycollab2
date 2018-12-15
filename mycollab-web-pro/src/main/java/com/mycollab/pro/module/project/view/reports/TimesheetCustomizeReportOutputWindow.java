package com.mycollab.pro.module.project.view.reports;

import com.mycollab.common.TableViewField;
import com.mycollab.common.i18n.SecurityI18nEnum;
import com.mycollab.db.query.VariableInjector;
import com.mycollab.module.project.ProjectTypeConstants;
import com.mycollab.module.project.domain.SimpleItemTimeLogging;
import com.mycollab.module.project.domain.criteria.ItemTimeLoggingSearchCriteria;
import com.mycollab.module.project.fielddef.TimeTableFieldDef;
import com.mycollab.module.project.i18n.TimeTrackingI18nEnum;
import com.mycollab.module.project.service.ItemTimeLoggingService;
import com.mycollab.spring.AppContextUtil;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.reporting.CustomizeReportOutputWindow;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;

/**
 * @author MyCollab Ltd
 * @since 5.3.4
 */
public class TimesheetCustomizeReportOutputWindow extends CustomizeReportOutputWindow<ItemTimeLoggingSearchCriteria, SimpleItemTimeLogging> {
    public TimesheetCustomizeReportOutputWindow(VariableInjector<ItemTimeLoggingSearchCriteria> variableInjector) {
        super(ProjectTypeConstants.TIME, UserUIContext.getMessage(TimeTrackingI18nEnum.SEARCH_TIME_TITLE),
                SimpleItemTimeLogging.class, AppContextUtil.getSpringBean(ItemTimeLoggingService.class), variableInjector);
    }

    @Override
    protected Collection<TableViewField> getDefaultColumns() {
        return Arrays.asList(TimeTableFieldDef.summary, TimeTableFieldDef.logUser, TimeTableFieldDef.logValue,
                TimeTableFieldDef.logForDate, TimeTableFieldDef.billable, TimeTableFieldDef.overtime,
                TimeTableFieldDef.project);
    }

    @Override
    protected Collection<TableViewField> getAvailableColumns() {
        return Arrays.asList(TimeTableFieldDef.summary, TimeTableFieldDef.logUser, TimeTableFieldDef.logValue,
                TimeTableFieldDef.logForDate, TimeTableFieldDef.billable, TimeTableFieldDef.overtime,
                TimeTableFieldDef.project);
    }

    @Override
    protected Object[] buildSampleData() {
        return new Object[]{"Meeting", "John Adams", "2", UserUIContext.formatDate(LocalDateTime.now().minusDays(2)
        ), UserUIContext.getMessage(SecurityI18nEnum.YES), UserUIContext.getMessage(SecurityI18nEnum.NO), "MyCollab"};
    }
}
