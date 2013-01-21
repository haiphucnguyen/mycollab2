/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.user;

import com.esofthead.mycollab.common.ActivityStreamConstants;
import com.esofthead.mycollab.common.ModuleNameConstants;
import com.esofthead.mycollab.common.domain.criteria.ActivityStreamSearchCriteria;
import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.SetSearchField;
import com.esofthead.mycollab.core.utils.DateTimeUtils;
import com.esofthead.mycollab.module.project.ProjectContants;
import com.esofthead.mycollab.module.project.domain.ProjectActivityStream;
import com.esofthead.mycollab.module.project.events.BugComponentEvent;
import com.esofthead.mycollab.module.project.events.BugEvent;
import com.esofthead.mycollab.module.project.events.BugVersionEvent;
import com.esofthead.mycollab.module.project.events.MessageEvent;
import com.esofthead.mycollab.module.project.events.MilestoneEvent;
import com.esofthead.mycollab.module.project.events.ProblemEvent;
import com.esofthead.mycollab.module.project.events.RiskEvent;
import com.esofthead.mycollab.module.project.events.TaskEvent;
import com.esofthead.mycollab.module.project.events.TaskListEvent;
import com.esofthead.mycollab.module.project.service.ProjectService;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.ui.AbstractBeanPagedList;
import com.esofthead.mycollab.vaadin.ui.DefaultBeanPagedList;
import com.esofthead.mycollab.vaadin.ui.UserLink;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.lazyloadwrapper.LazyLoadWrapper;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import java.util.List;

/**
 *
 * @author haiphucnguyen
 */
public class ActivityStreamComponent extends Panel {
    
    public ActivityStreamComponent() {
        super("User Feeds");
    }

    private ProjectActivityStreamPagedList activityStreamList;

    public void showFeeds() {
        this.removeAllComponents();

        activityStreamList = new ProjectActivityStreamPagedList();
        this.addComponent(new LazyLoadWrapper(activityStreamList));
        ActivityStreamSearchCriteria searchCriteria = new ActivityStreamSearchCriteria();
        searchCriteria.setModuleSet(new SetSearchField<String>(SearchField.AND, new String[]{ModuleNameConstants.PRJ}));

        activityStreamList.setSearchCriteria(searchCriteria);
    }

    static class ProjectActivityStreamPagedList extends AbstractBeanPagedList<ActivityStreamSearchCriteria, ProjectActivityStream> {

        private ProjectService projectService;

        public ProjectActivityStreamPagedList() {
            super(ActivityStreamComponent.ActivityStreamRowDisplayHandler.class, 15);

            projectService = AppContext.getSpringBean(ProjectService.class);
        }

        @Override
        public void doSearch() {
            totalCount = projectService.getTotalActivityStream(searchRequest
                    .getSearchCriteria());
            totalPage = (totalCount - 1) / searchRequest.getNumberOfItems() + 1;
            if (searchRequest.getCurrentPage() > totalPage) {
                searchRequest.setCurrentPage(totalPage);
            }

            this.setCurrentPage(currentPage);
            this.setTotalPage(totalPage);

            List<ProjectActivityStream> currentListData = (List<ProjectActivityStream>) projectService
                    .getProjectActivityStreams(searchRequest);
            listContainer.removeAllComponents();
            int i = 0;
            try {
                for (ProjectActivityStream item : currentListData) {
                    AbstractBeanPagedList.RowDisplayHandler<ProjectActivityStream> rowHandler = (AbstractBeanPagedList.RowDisplayHandler<ProjectActivityStream>) rowDisplayHandler
                            .newInstance();
                    Component row = rowHandler.generateRow(item, i);
                    listContainer.addComponent(row);
                    i++;
                }
            } catch (Exception e) {
                throw new MyCollabException(e);
            }
        }
    }

    public static class ActivityStreamRowDisplayHandler implements DefaultBeanPagedList.RowDisplayHandler<ProjectActivityStream> {

        @Override
        public Component generateRow(ProjectActivityStream activityStream, int rowIndex) {
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
            header.addComponent(new ActivityStreamComponent.ActivitylLink(activityStream.getType(), activityStream.getNamefield(), activityStream.getTypeid()));
            
            Label prjLabel = new Label("in project ");
            header.addComponent(prjLabel);
            header.setComponentAlignment(prjLabel, Alignment.MIDDLE_CENTER);
            Button projectLink = new Button(activityStream.getProjectName(), new Button.ClickListener() {
                @Override
                public void buttonClick(ClickEvent event) {
                    
                }
            });
            header.addComponent(projectLink);
            projectLink.setStyleName("link");
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
            } else if (ProjectContants.BUG.equals(type)) {
                this.setIcon(new ThemeResource("icons/16/project/bug.png"));
            } else if (ProjectContants.BUG_COMPONENT.equals(type)) {
                this.setIcon(new ThemeResource("icons/16/project/component.png"));
            } else if (ProjectContants.BUG_VERSION.equals(type)) {
                this.setIcon(new ThemeResource("icons/16/project/version.png"));
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
                        EventBus.getInstance().fireEvent(new TaskEvent.GotoRead(this, typeid));
                    } else if (ProjectContants.TASK_LIST.equals(type)) {
                        EventBus.getInstance().fireEvent(new TaskListEvent.GotoRead(this, typeid));
                    } else if (ProjectContants.BUG.equals(type)) {
                        EventBus.getInstance().fireEvent(new BugEvent.GotoRead(this, typeid));
                    } else if (ProjectContants.BUG_COMPONENT.equals(type)) {
                        EventBus.getInstance().fireEvent(new BugComponentEvent.GotoRead(this, typeid));
                    } else if (ProjectContants.BUG_VERSION.equals(type)) {
                        EventBus.getInstance().fireEvent(new BugVersionEvent.GotoRead(this, typeid));
                    }
                }
            });
        }
    }
}
