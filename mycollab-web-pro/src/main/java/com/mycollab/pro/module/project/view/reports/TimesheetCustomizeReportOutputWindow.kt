package com.mycollab.pro.module.project.view.reports

import com.google.common.collect.Sets.newHashSet
import com.mycollab.common.TableViewField
import com.mycollab.common.i18n.SecurityI18nEnum
import com.mycollab.db.query.VariableInjector
import com.mycollab.module.project.ProjectTypeConstants
import com.mycollab.module.project.domain.SimpleItemTimeLogging
import com.mycollab.module.project.domain.criteria.ItemTimeLoggingSearchCriteria
import com.mycollab.module.project.fielddef.TimeTableFieldDef.billable
import com.mycollab.module.project.fielddef.TimeTableFieldDef.logForDate
import com.mycollab.module.project.fielddef.TimeTableFieldDef.logUser
import com.mycollab.module.project.fielddef.TimeTableFieldDef.logValue
import com.mycollab.module.project.fielddef.TimeTableFieldDef.overtime
import com.mycollab.module.project.fielddef.TimeTableFieldDef.project
import com.mycollab.module.project.fielddef.TimeTableFieldDef.summary
import com.mycollab.module.project.i18n.TimeTrackingI18nEnum
import com.mycollab.module.project.service.ItemTimeLoggingService
import com.mycollab.spring.AppContextUtil
import com.mycollab.vaadin.UserUIContext
import com.mycollab.vaadin.reporting.CustomizeReportOutputWindow
import java.time.LocalDateTime

/**
 * @author MyCollab Ltd
 * @since 5.3.4
 */
class TimesheetCustomizeReportOutputWindow(variableInjector: VariableInjector<ItemTimeLoggingSearchCriteria>) :
        CustomizeReportOutputWindow<ItemTimeLoggingSearchCriteria, SimpleItemTimeLogging>(ProjectTypeConstants.TIME,
                UserUIContext.getMessage(TimeTrackingI18nEnum.SEARCH_TIME_TITLE), SimpleItemTimeLogging::class.java,
                AppContextUtil.getSpringBean(ItemTimeLoggingService::class.java), variableInjector) {

    override fun getDefaultColumns(): Set<TableViewField> =
            newHashSet(summary, logUser, logValue, logForDate, billable, overtime, project)

    override fun getAvailableColumns(): Set<TableViewField> =
            newHashSet(summary, logUser, logValue, logForDate, billable, overtime, project)

    override fun getSampleMap(): Map<String, String> = mapOf(
            summary.field to "Meeting",
            logUser.field to "John Adam",
            logValue.field to "2",
            logForDate.field to UserUIContext.formatDate(LocalDateTime.now().minusDays(2)),
            billable.field to UserUIContext.getMessage(SecurityI18nEnum.YES),
            overtime.field to UserUIContext.getMessage(SecurityI18nEnum.NO),
            project.field to "MyCollab"
    )
}
