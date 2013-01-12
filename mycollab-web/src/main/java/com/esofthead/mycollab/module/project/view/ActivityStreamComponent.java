/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view;

import com.esofthead.mycollab.common.ActivityStreamConstants;
import com.esofthead.mycollab.common.ModuleNameConstants;
import com.esofthead.mycollab.common.domain.SimpleActivityStream;
import com.esofthead.mycollab.common.domain.criteria.ActivityStreamSearchCriteria;
import com.esofthead.mycollab.common.service.ActivityStreamService;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.SetSearchField;
import com.esofthead.mycollab.core.utils.DateTimeUtils;
import com.esofthead.mycollab.module.project.ProjectContants;
import com.esofthead.mycollab.module.project.domain.SimpleProject;
import com.esofthead.mycollab.module.project.events.MessageEvent;
import com.esofthead.mycollab.module.project.events.MilestoneEvent;
import com.esofthead.mycollab.module.project.events.ProblemEvent;
import com.esofthead.mycollab.module.project.events.RiskEvent;
import com.esofthead.mycollab.module.project.events.TaskListEvent;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.ui.BeanPagedList;
import com.esofthead.mycollab.vaadin.ui.UserLink;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

/**
 *
 * @author haiphucnguyen
 */
public class ActivityStreamComponent extends Panel {

    private BeanPagedList<ActivityStreamService, ActivityStreamSearchCriteria, SimpleActivityStream> activityStreamList;

    public ActivityStreamComponent() {
        super("Project Feeds");
        activityStreamList = new BeanPagedList<ActivityStreamService, ActivityStreamSearchCriteria, SimpleActivityStream>(AppContext.getSpringBean(ActivityStreamService.class), ActivityStreamRowDisplayHandler.class);
        this.addComponent(activityStreamList);
    }
    
    public void showProjectFeeds() {
        ActivityStreamSearchCriteria searchCriteria = new ActivityStreamSearchCriteria();
        searchCriteria.setModuleSet(new SetSearchField<String>(SearchField.AND, new String[]{ModuleNameConstants.PRJ}));

        SimpleProject project = (SimpleProject) AppContext.getVariable(ProjectContants.PROJECT_NAME);
        searchCriteria.setExtraTypeIds(new SetSearchField<Integer>(project.getId()));
        activityStreamList.setSearchCriteria(searchCriteria);
    }

    public static class ActivityStreamRowDisplayHandler implements BeanPagedList.RowDisplayHandler<SimpleActivityStream> {

        @Override
        public Component generateRow(SimpleActivityStream activityStream, int rowIndex) {
            VerticalLayout layout = new VerticalLayout();
            HorizontalLayout header = new HorizontalLayout();
            header.setSpacing(true);
            header.addComponent(new UserLink(activityStream.getCreateduser(), activityStream.getCreatedUserFullName()));
            StringBuilder action = new StringBuilder();

            if (ActivityStreamConstants.ACTION_CREATE.equals(activityStream.getAction())) {
                action.append("create a new ");
            } else if (ActivityStreamConstants.ACTION_UPDATE.equals(activityStream.getAction())) {
                action.append("update ");
            }

            action.append(activityStream.getType());
            Label actionLbl = new Label(action.toString());
            header.addComponent(actionLbl);
            header.setComponentAlignment(actionLbl, Alignment.MIDDLE_CENTER);
            header.addComponent(new ActivitylLink(activityStream.getType(), activityStream.getNamefield(), activityStream.getTypeid()));
            layout.addComponent(header);

            HorizontalLayout body = new HorizontalLayout();
            Label dateLbl = new Label(DateTimeUtils.getStringDateFromNow(activityStream.getCreatedtime()));
            body.addComponent(dateLbl);

            layout.addComponent(body);
            return layout;
        }
    }

    private static class ActivitylLink extends Button {

        public ActivitylLink(final String type, final String fieldName, final int typeid) {
            super(fieldName);

            if (ProjectContants.PROJECT.equals(type)) {
                this.setIcon(new ThemeResource("icons/16/project/project.png"));
            } else if (ProjectContants.MESSAGE.equals(type)) {
                this.setIcon(new ThemeResource("icons/16/project/message.png"));
            } else if (ProjectContants.MILESTONE.equals(type)) {
                this.setIcon(new ThemeResource("icons/16/project/milestone.png"));
            } else if (ProjectContants.PROBLEM.equals(type)) {
                this.setIcon(new ThemeResource("icons/16/project/problem.png"));
            } else if (ProjectContants.RISK.equals(type)) {
                this.setIcon(new ThemeResource("icons/16/project/risk.png"));
            } else if (ProjectContants.TASK.equals(type)) {
                this.setIcon(new ThemeResource("icons/16/project/task.png"));
            } else if (ProjectContants.TASK_LIST.equals(type)) {
                this.setIcon(new ThemeResource("icons/16/project/tasklist.png"));
            }

            this.setStyleName("link");
            this.addListener(new Button.ClickListener() {
                @Override
                public void buttonClick(Button.ClickEvent event) {
                    if (ProjectContants.PROJECT.equals(type)) {
                    } else if (ProjectContants.MESSAGE.equals(type)) {
                        EventBus.getInstance().fireEvent(new MessageEvent.GotoRead(this, typeid));
                    } else if (ProjectContants.MILESTONE.equals(type)) {
                        EventBus.getInstance().fireEvent(new MilestoneEvent.GotoRead(this, typeid));
                    } else if (ProjectContants.PROBLEM.equals(type)) {
                        EventBus.getInstance().fireEvent(new ProblemEvent.GotoRead(this, typeid));
                    } else if (ProjectContants.RISK.equals(type)) {
                        EventBus.getInstance().fireEvent(new RiskEvent.GotoRead(this, typeid));
                    } else if (ProjectContants.TASK.equals(type)) {
                    } else if (ProjectContants.TASK_LIST.equals(type)) {
                        EventBus.getInstance().fireEvent(new TaskListEvent.GotoRead(this, typeid));
                    }
                }
            });
        }
    }
}
