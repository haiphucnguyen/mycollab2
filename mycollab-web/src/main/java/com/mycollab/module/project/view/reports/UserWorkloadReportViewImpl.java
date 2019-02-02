package com.mycollab.module.project.view.reports;

import com.mycollab.module.project.domain.criteria.ProjectTicketSearchCriteria;
import com.mycollab.module.project.service.ProjectService;
import com.mycollab.spring.AppContextUtil;
import com.mycollab.vaadin.AppUI;
import com.mycollab.vaadin.AsyncInvoker;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.mvp.AbstractVerticalPageView;
import com.mycollab.vaadin.mvp.ViewComponent;
import org.vaadin.viritin.layouts.MVerticalLayout;

import java.util.List;
import java.util.concurrent.Future;

/**
 * @author MyCollab Ltd
 * @since 5.3.0
 */
@ViewComponent
public class UserWorkloadReportViewImpl extends AbstractVerticalPageView implements UserWorkloadReportView {

    private ProjectTicketSearchCriteria baseCriteria;

    private TicketCrossProjectsSearchPanel searchPanel;
    private MVerticalLayout wrapBody;

    public UserWorkloadReportViewImpl() {
        searchPanel = new TicketCrossProjectsSearchPanel();

        wrapBody = new MVerticalLayout();
        with(searchPanel, wrapBody).expand(wrapBody);
    }

    @Override
    public void display() {
        ProjectTicketSearchCriteria searchCriteria = new ProjectTicketSearchCriteria();
        queryTickets(searchCriteria);
    }

    @Override
    public void queryTickets(ProjectTicketSearchCriteria searchCriteria) {
        this.baseCriteria = searchCriteria;
        wrapBody.removeAllComponents();

        ProjectService projectService = AppContextUtil.getSpringBean(ProjectService.class);
        List<Integer> projects;
        if (UserUIContext.isAdmin()) {
            projects = projectService.getProjectKeysUserInvolved(null, AppUI.getAccountId());
        } else {
            projects = projectService.getProjectKeysUserInvolved(UserUIContext.getUsername(), AppUI.getAccountId());
        }

        

    }

    @Override
    public ProjectTicketSearchCriteria getCriteria() {
        return baseCriteria;
    }
}
