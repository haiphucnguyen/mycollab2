package com.esofthead.mycollab.module.project.view.task.calendar;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchRequest;
import com.esofthead.mycollab.core.arguments.SetSearchField;
import com.esofthead.mycollab.module.project.ProjectTypeConstants;
import com.esofthead.mycollab.module.project.domain.ProjectGenericTask;
import com.esofthead.mycollab.module.project.domain.criteria.ProjectGenericTaskSearchCriteria;
import com.esofthead.mycollab.module.project.service.ProjectGenericTaskService;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.vaadin.ui.components.calendar.event.CalendarEvent;
import com.vaadin.ui.components.calendar.event.CalendarEventProvider;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author MyCollab Ltd
 * @since 5.1.5
 */
public class GenericTaskProvider implements CalendarEventProvider {
    private List<Integer> projectIds;

    public GenericTaskProvider(Integer projectId) {
        projectIds = Arrays.asList(projectId);
    }

    public GenericTaskProvider(List<Integer> projectIds) {
        this.projectIds = projectIds;
    }

    @Override
    public List<CalendarEvent> getEvents(Date start, Date end) {
        ProjectGenericTaskSearchCriteria searchCriteria = new ProjectGenericTaskSearchCriteria();
        searchCriteria.setProjectIds(new SetSearchField<>(projectIds));
        searchCriteria.setSaccountid(new NumberSearchField(AppContext.getAccountId()));
        searchCriteria.setTypes(new SetSearchField<>(ProjectTypeConstants.BUG, ProjectTypeConstants.TASK));

        ProjectGenericTaskService genericTaskService = ApplicationContextUtil.getSpringBean(ProjectGenericTaskService.class);
        List<ProjectGenericTask> assignments = genericTaskService.findPagableListByCriteria(new SearchRequest<>
                (searchCriteria, 0, Integer.MAX_VALUE));
        List<CalendarEvent> events = new ArrayList();
        for (ProjectGenericTask assignment : assignments) {
            events.add(new GenericTaskEvent(assignment));
        }
        return events;
    }
}
