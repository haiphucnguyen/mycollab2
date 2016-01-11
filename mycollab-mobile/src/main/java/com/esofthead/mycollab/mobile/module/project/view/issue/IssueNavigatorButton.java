package com.esofthead.mycollab.mobile.module.project.view.issue;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SetSearchField;
import com.esofthead.mycollab.module.project.ProjectTypeConstants;
import com.esofthead.mycollab.module.project.domain.criteria.ProjectGenericTaskSearchCriteria;
import com.esofthead.mycollab.module.project.i18n.TicketI18nEnum;
import com.esofthead.mycollab.module.project.service.ProjectGenericTaskService;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.vaadin.addon.touchkit.ui.NavigationButton;

/**
 * @author MyCollab Ltd
 * @since 5.2.5
 */
public class IssueNavigatorButton extends NavigationButton {
    private Integer milestoneId;

    public IssueNavigatorButton() {
        super(AppContext.getMessage(TicketI18nEnum.M_TICKET_NUM, 0));
        this.addClickListener(new NavigationButtonClickListener() {
            @Override
            public void buttonClick(NavigationButtonClickEvent event) {
                if (milestoneId != null) {
                    getNavigationManager().navigateTo(new IssueListView(milestoneId));
                }
            }
        });
    }

    public void displayTotalIssues(Integer milestoneId) {
        this.milestoneId = milestoneId;
        ProjectGenericTaskSearchCriteria criteria = new ProjectGenericTaskSearchCriteria();
        criteria.setMilestoneId(NumberSearchField.and(milestoneId));
        criteria.setTypes(new SetSearchField<>(ProjectTypeConstants.BUG, ProjectTypeConstants.TASK));
        ProjectGenericTaskService ticketService = ApplicationContextUtil.getSpringBean(ProjectGenericTaskService.class);
        this.setCaption(AppContext.getMessage(TicketI18nEnum.M_TICKET_NUM, ticketService.getTotalCount(criteria)));
    }
}
