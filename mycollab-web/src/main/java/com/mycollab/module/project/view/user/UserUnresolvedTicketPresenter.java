package com.mycollab.module.project.view.user;

import com.mycollab.core.utils.DateTimeUtils;
import com.mycollab.db.arguments.RangeDateSearchField;
import com.mycollab.db.arguments.SearchField;
import com.mycollab.db.arguments.SetSearchField;
import com.mycollab.db.arguments.StringSearchField;
import com.mycollab.module.project.domain.criteria.ProjectTicketSearchCriteria;
import com.mycollab.module.project.i18n.ProjectI18nEnum;
import com.mycollab.module.project.service.ProjectService;
import com.mycollab.vaadin.AppUI;
import com.mycollab.vaadin.UserUIContext;
import com.vaadin.spring.annotation.SpringComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.spring.annotation.PrototypeScope;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@SpringComponent
@PrototypeScope
public class UserUnresolvedTicketPresenter implements Serializable {

    @Autowired
    ProjectService projectService;

    private UserUnresolvedTicketWidget view;

    private String viewTitle = "";

    private ProjectTicketSearchCriteria searchCriteria;

    void initView(UserUnresolvedTicketWidget view) {
        this.view = view;
    }

    void showUnresolvedTickets(boolean isIncludedMe) {
        if (searchCriteria != null) {
            if (isIncludedMe) {
                searchCriteria.setAssignUser(StringSearchField.and(UserUIContext.getUsername()));
            } else {
                searchCriteria.setAssignUser(null);
            }
            updateSearchResult();
        }
    }

    void displayUnresolvedAssignmentsThisWeek() {
        viewTitle = UserUIContext.getMessage(ProjectI18nEnum.OPT_UNRESOLVED_TICKET_THIS_WEEK);
        searchCriteria = new ProjectTicketSearchCriteria();
        searchCriteria.setOpenned(new SearchField());

        List<Integer> prjKeys = projectService.getProjectKeysUserInvolved(UserUIContext.getUsername(), AppUI.getAccountId());
        if (!prjKeys.isEmpty()) {
            searchCriteria.setProjectIds(new SetSearchField<>(prjKeys));
            LocalDate now = LocalDate.now();
            LocalDate[] bounceDateOfWeek = DateTimeUtils.getBounceDatesOfWeek(now);
            RangeDateSearchField range = new RangeDateSearchField(bounceDateOfWeek[0], bounceDateOfWeek[1]);
            searchCriteria.setDateInRange(range);
            updateSearchResult();
        } else {
            viewTitle = UserUIContext.getMessage(ProjectI18nEnum.OPT_UNRESOLVED_TICKET_THIS_WEEK);
            view.setTitle(String.format(viewTitle, 0));
        }
    }

    void displayUnresolvedAssignmentsNextWeek() {
        viewTitle = UserUIContext.getMessage(ProjectI18nEnum.OPT_UNRESOLVED_TICKET_NEXT_WEEK);
        searchCriteria = new ProjectTicketSearchCriteria();
        List<Integer> prjKeys = projectService.getProjectKeysUserInvolved(UserUIContext.getUsername(), AppUI.getAccountId());
        if (!prjKeys.isEmpty()) {
            searchCriteria.setOpenned(new SearchField());
            searchCriteria.setProjectIds(new SetSearchField<>(prjKeys));
            LocalDate now = LocalDate.now();
            now = now.plusDays(7);
            LocalDate[] bounceDateOfWeek = DateTimeUtils.getBounceDatesOfWeek(now);
            RangeDateSearchField range = new RangeDateSearchField(bounceDateOfWeek[0], bounceDateOfWeek[1]);
            searchCriteria.setDateInRange(range);
            updateSearchResult();
        } else {
            viewTitle = UserUIContext.getMessage(ProjectI18nEnum.OPT_UNRESOLVED_TICKET_NEXT_WEEK);
            view.setTitle(String.format(viewTitle, 0));
        }
    }

    private void updateSearchResult() {
        view.getTicketList().setSearchCriteria(searchCriteria);
        view.setTitle(String.format(viewTitle, view.getTicketList().getTotalCount()));
    }
}
