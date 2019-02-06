package com.mycollab.module.project.view.reports;

import com.mycollab.core.Tuple2;
import com.mycollab.db.arguments.BasicSearchRequest;
import com.mycollab.db.arguments.SetSearchField;
import com.mycollab.module.project.domain.ProjectTicket;
import com.mycollab.module.project.domain.criteria.ProjectTicketSearchCriteria;
import com.mycollab.module.project.service.ProjectRoleService;
import com.mycollab.module.project.service.ProjectService;
import com.mycollab.module.project.service.ProjectTicketService;
import com.mycollab.security.PermissionMap;
import com.mycollab.spring.AppContextUtil;
import com.mycollab.vaadin.AppUI;
import com.mycollab.vaadin.AsyncInvoker;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.mvp.AbstractVerticalPageView;
import com.mycollab.vaadin.mvp.ViewComponent;
import com.mycollab.vaadin.ui.ELabel;
import org.vaadin.viritin.layouts.MVerticalLayout;

import java.util.List;

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
        List<Integer> projectKeys;
        if (UserUIContext.isAdmin()) {
            projectKeys = projectService.getProjectKeysUserInvolved(null, AppUI.getAccountId());
        } else {
            projectKeys = projectService.getProjectKeysUserInvolved(UserUIContext.getUsername(), AppUI.getAccountId());
        }

        AsyncInvoker.access(getUI(), new AsyncInvoker.PageCommand() {
            public void run() {
                ProjectTicketService projectTicketService = AppContextUtil.getSpringBean(ProjectTicketService.class);

                if (UserUIContext.isAdmin()) {
                    projectKeys.forEach(key -> {
                        ProjectTicketSearchCriteria ticketSearchCriteria = new ProjectTicketSearchCriteria();
                        ticketSearchCriteria.setProjectIds(new SetSearchField<>(key));
                        List<ProjectTicket> ticketsByCriteria = (List<ProjectTicket>) projectTicketService.findTicketsByCriteria(new BasicSearchRequest<>(ticketSearchCriteria));
                        ticketsByCriteria.forEach(ticket -> addComponent(new ELabel(ticket.getName())));
                        push();
                    });
                } else {
                    ProjectRoleService roleService = AppContextUtil.getSpringBean(ProjectRoleService.class);
                    List<Tuple2<Integer, PermissionMap>> projectsPermissions = roleService.findProjectsPermissions(UserUIContext.getUsername(), projectKeys, AppUI.getAccountId());
                    projectsPermissions.forEach(prjPermission -> {
                        Integer projectId = prjPermission.getItem1();
                        PermissionMap permissionMap = prjPermission.getItem2();

                        ProjectTicketSearchCriteria ticketSearchCriteria = new ProjectTicketSearchCriteria();
                        ticketSearchCriteria.setProjectIds(new SetSearchField<>(projectId));
                        List<ProjectTicket> ticketsByCriteria = (List<ProjectTicket>) projectTicketService.findTicketsByCriteria(new BasicSearchRequest<>(ticketSearchCriteria));
                        ticketsByCriteria.forEach(ticket -> addComponent(new ELabel(ticket.getName())));
                        push();
                    });
                }
            }
        });

    }

    @Override
    public ProjectTicketSearchCriteria getCriteria() {
        return baseCriteria;
    }
}
