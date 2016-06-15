package com.esofthead.mycollab.pro.module.project.view.reports;

import com.esofthead.mycollab.common.TableViewField;
import com.esofthead.mycollab.common.i18n.SecurityI18nEnum;
import com.esofthead.mycollab.core.db.query.VariableInjector;
import com.esofthead.mycollab.module.project.ProjectTypeConstants;
import com.esofthead.mycollab.module.project.domain.SimpleItemTimeLogging;
import com.esofthead.mycollab.module.project.domain.criteria.ItemTimeLoggingSearchCriteria;
import com.esofthead.mycollab.module.project.i18n.TimeTrackingI18nEnum;
import com.esofthead.mycollab.module.project.service.ItemTimeLoggingService;
import com.esofthead.mycollab.module.project.view.time.TimeTableFieldDef;
import com.esofthead.mycollab.reporting.CustomizeReportOutputWindow;
import com.esofthead.mycollab.spring.AppContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import org.joda.time.LocalDate;

import java.util.Arrays;
import java.util.Collection;

/**
 * @author MyCollab Ltd
 * @since 5.3.4
 */
public class TimesheetCustomizeReportOutputWindow extends CustomizeReportOutputWindow<ItemTimeLoggingSearchCriteria, SimpleItemTimeLogging> {
    public TimesheetCustomizeReportOutputWindow(VariableInjector<ItemTimeLoggingSearchCriteria> variableInjector) {
        super(ProjectTypeConstants.TIME, AppContext.getMessage(TimeTrackingI18nEnum.SEARCH_TIME_TITLE),
                SimpleItemTimeLogging.class, AppContextUtil.getSpringBean(ItemTimeLoggingService.class), variableInjector);
    }

    @Override
    protected Collection<TableViewField> getDefaultColumns() {
        return Arrays.asList(TimeTableFieldDef.summary(), TimeTableFieldDef.logUser(), TimeTableFieldDef.logValue(),
                TimeTableFieldDef.logForDate(), TimeTableFieldDef.billable(), TimeTableFieldDef.overtime(),
                TimeTableFieldDef.project());
    }

    @Override
    protected Collection<TableViewField> getAvailableColumns() {
        return Arrays.asList(TimeTableFieldDef.summary(), TimeTableFieldDef.logUser(), TimeTableFieldDef.logValue(),
                TimeTableFieldDef.logForDate(), TimeTableFieldDef.billable(), TimeTableFieldDef.overtime(),
                TimeTableFieldDef.project());
    }

    @Override
    protected Object[] buildSampleData() {
        return new Object[]{"Meeting", "John Adams", "2", AppContext.formatDate(new LocalDate().minusDays(2).toDate()
        ), AppContext.getMessage(SecurityI18nEnum.YES), AppContext.getMessage(SecurityI18nEnum.NO), "MyCollab"};
    }
}
