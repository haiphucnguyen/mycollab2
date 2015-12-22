package com.esofthead.mycollab.module.project.view.user;

import com.esofthead.mycollab.core.arguments.RangeDateSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.SetSearchField;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.core.utils.DateTimeUtils;
import com.esofthead.mycollab.module.project.domain.ProjectGenericTask;
import com.esofthead.mycollab.module.project.domain.criteria.ProjectGenericTaskSearchCriteria;
import com.esofthead.mycollab.module.project.service.ProjectGenericTaskService;
import com.esofthead.mycollab.module.project.view.UserDashboardView;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.ui.DefaultBeanPagedList;
import com.esofthead.mycollab.vaadin.ui.Depot;
import com.esofthead.mycollab.vaadin.ui.UIUtils;
import com.vaadin.data.Property;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.CssLayout;
import org.joda.time.LocalDate;

import java.util.Date;

/**
 * @author MyCollab Ltd
 * @since 5.2.4
 */
public class UserUnresolvedAssignmentWidget extends Depot {
    private ProjectGenericTaskSearchCriteria searchCriteria;
    private DefaultBeanPagedList<ProjectGenericTaskService, ProjectGenericTaskSearchCriteria, ProjectGenericTask> taskList;
    private String title = "";

    public UserUnresolvedAssignmentWidget() {
        super("", new CssLayout());
        this.setWidth("100%");
        final CheckBox myItemsSelection = new CheckBox("My Items");
        myItemsSelection.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent valueChangeEvent) {
                boolean isMyItemsOption = myItemsSelection.getValue();
                if (isMyItemsOption) {
                    searchCriteria.setAssignUser(new StringSearchField(AppContext.getUsername()));
                } else {
                    searchCriteria.setAssignUser(null);
                }
                updateSearchResult();
            }
        });
        taskList = new DefaultBeanPagedList(ApplicationContextUtil.getSpringBean(ProjectGenericTaskService.class),
                new GenericTaskRowDisplayHandler(), 10) {
            @Override
            protected String stringWhenEmptyList() {
                return "No assignment";
            }
        };
        this.addHeaderElement(myItemsSelection);
        this.bodyContent.addComponent(taskList);
    }

    public void displayUnresolvedAssignmentsThisWeek() {
        title = "Unresolved assignments in this week (%d)";
        searchCriteria = new ProjectGenericTaskSearchCriteria();
        searchCriteria.setIsOpenned(new SearchField());
        UserDashboardView userDashboardView = UIUtils.getRoot(this, UserDashboardView.class);
        searchCriteria.setProjectIds(new SetSearchField<>(userDashboardView.getInvoledProjKeys()));
        LocalDate now = new LocalDate();
        Date[] bounceDateofWeek = DateTimeUtils.getBounceDateofWeek(now.toDate());
        RangeDateSearchField range = new RangeDateSearchField(bounceDateofWeek[0], bounceDateofWeek[1]);
        searchCriteria.setDateInRange(range);
        updateSearchResult();
    }

    public void displayUnresolvedAssignmentsNextWeek() {
        title = "Unresolved assignments in next week (%d)";
        searchCriteria = new ProjectGenericTaskSearchCriteria();
        UserDashboardView userDashboardView = UIUtils.getRoot(this, UserDashboardView.class);
        searchCriteria.setIsOpenned(new SearchField());
        searchCriteria.setProjectIds(new SetSearchField<>(userDashboardView.getInvoledProjKeys()));
        LocalDate now = new LocalDate();
        now = now.plusDays(7);
        Date[] bounceDateofWeek = DateTimeUtils.getBounceDateofWeek(now.toDate());
        RangeDateSearchField range = new RangeDateSearchField(bounceDateofWeek[0], bounceDateofWeek[1]);
        searchCriteria.setDateInRange(range);
        updateSearchResult();
    }

    private void updateSearchResult() {
        taskList.setSearchCriteria(searchCriteria);
        this.setTitle(String.format(title, taskList.getTotalCount()));
    }
}
