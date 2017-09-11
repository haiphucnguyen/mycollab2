package com.mycollab.module.project.view.ticket;

import com.mycollab.common.TableViewField;
import com.mycollab.db.query.VariableInjector;
import com.mycollab.module.project.ProjectTypeConstants;
import com.mycollab.module.project.domain.ProjectTicket;
import com.mycollab.module.project.domain.criteria.ProjectTicketSearchCriteria;
import com.mycollab.module.project.fielddef.TicketTableFieldDef;
import com.mycollab.module.project.i18n.OptionI18nEnum.Priority;
import com.mycollab.module.project.i18n.TicketI18nEnum;
import com.mycollab.module.project.service.ProjectTicketService;
import com.mycollab.vaadin.reporting.CustomizeReportOutputWindow;
import com.mycollab.spring.AppContextUtil;
import com.mycollab.vaadin.UserUIContext;
import org.joda.time.LocalDate;

import java.util.Arrays;
import java.util.Collection;

/**
 * @author MyCollab Ltd
 * @since 5.3.4
 */
public class TicketCustomizeReportOutputWindow extends CustomizeReportOutputWindow<ProjectTicketSearchCriteria, ProjectTicket> {
    public TicketCustomizeReportOutputWindow(VariableInjector<ProjectTicketSearchCriteria> variableInjector) {
        super(ProjectTypeConstants.INSTANCE.getTICKET(), UserUIContext.getMessage(TicketI18nEnum.LIST), ProjectTicket.class,
                AppContextUtil.getSpringBean(ProjectTicketService.class), variableInjector);
    }

    @Override
    protected Object[] buildSampleData() {
        return new Object[]{"Task A", "Description 1", UserUIContext.formatDate(new LocalDate().minusDays(2).toDate()),
                UserUIContext.formatDate(new LocalDate().plusDays(1).toDate()), UserUIContext.formatDate(new LocalDate().plusDays(1).toDate()),
                Priority.High.name(), "Will Smith", "Jonh Adam", "MVP", "3", "1"};
    }

    @Override
    protected Collection<TableViewField> getDefaultColumns() {
        return Arrays.asList(TicketTableFieldDef.name(), TicketTableFieldDef.startdate(), TicketTableFieldDef.duedate(),
                TicketTableFieldDef.priority(), TicketTableFieldDef.assignee(),
                TicketTableFieldDef.billableHours(), TicketTableFieldDef.nonBillableHours());
    }

    @Override
    protected Collection<TableViewField> getAvailableColumns() {
        return Arrays.asList(TicketTableFieldDef.name(), TicketTableFieldDef.description(), TicketTableFieldDef.startdate(),
                TicketTableFieldDef.enddate(), TicketTableFieldDef.duedate(),
                TicketTableFieldDef.priority(), TicketTableFieldDef.logUser(),
                TicketTableFieldDef.assignee(), TicketTableFieldDef.milestoneName(),
                TicketTableFieldDef.billableHours(), TicketTableFieldDef.nonBillableHours());
    }
}
