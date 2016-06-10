package com.esofthead.mycollab.module.project.view.bug;

import com.esofthead.mycollab.common.TableViewField;
import com.esofthead.mycollab.core.db.query.VariableInjector;
import com.esofthead.mycollab.module.project.ProjectTypeConstants;
import com.esofthead.mycollab.module.project.i18n.BugI18nEnum;
import com.esofthead.mycollab.module.project.i18n.OptionI18nEnum;
import com.esofthead.mycollab.module.tracker.domain.SimpleBug;
import com.esofthead.mycollab.module.tracker.domain.criteria.BugSearchCriteria;
import com.esofthead.mycollab.module.tracker.service.BugService;
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
public class BugCustomizeReportOutputWindow extends CustomizeReportOutputWindow<BugSearchCriteria, SimpleBug> {
    public BugCustomizeReportOutputWindow(VariableInjector<BugSearchCriteria> variableInjector) {
        super(ProjectTypeConstants.BUG, AppContext.getMessage(BugI18nEnum.LIST), SimpleBug.class,
                AppContextUtil.getSpringBean(BugService.class), variableInjector);
    }

    @Override
    protected Collection<TableViewField> getDefaultColumns() {
        return Arrays.asList(BugTableFieldDef.summary(), BugTableFieldDef.environment(), BugTableFieldDef.priority(),
                BugTableFieldDef.severity(), BugTableFieldDef.status(), BugTableFieldDef.resolution(),
                BugTableFieldDef.logBy(), BugTableFieldDef.duedate(), BugTableFieldDef.assignUser(),
                BugTableFieldDef.billableHours(), BugTableFieldDef.nonBillableHours());
    }

    @Override
    protected Collection<TableViewField> getAvailableColumns() {
        return Arrays.asList(BugTableFieldDef.summary(), BugTableFieldDef.environment(), BugTableFieldDef.priority(),
                BugTableFieldDef.severity(), BugTableFieldDef.status(), BugTableFieldDef.resolution(),
                BugTableFieldDef.logBy(), BugTableFieldDef.duedate(), BugTableFieldDef.assignUser(),
                BugTableFieldDef.billableHours(), BugTableFieldDef.nonBillableHours());
    }

    @Override
    protected Object[] buildSampleData() {
        return new Object[]{"Bug A", "Virtual Environment", OptionI18nEnum.BugPriority.Critical.name(),
                OptionI18nEnum.BugSeverity.Major.name(), OptionI18nEnum.BugStatus.Open.name(), OptionI18nEnum.BugResolution.None.name(),
                "John Adam", AppContext.formatDate(new LocalDate().plusDays(2).toDate()), "Will Smith", "10", "2"};
    }
}
