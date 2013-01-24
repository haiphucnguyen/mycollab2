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
import com.esofthead.mycollab.module.project.ProjectResources;
import com.esofthead.mycollab.module.project.domain.ProjectActivityStream;
import com.esofthead.mycollab.module.project.events.BugComponentEvent;
import com.esofthead.mycollab.module.project.events.BugEvent;
import com.esofthead.mycollab.module.project.events.BugVersionEvent;
import com.esofthead.mycollab.module.project.events.ProjectEvent;
import com.esofthead.mycollab.module.project.events.TaskEvent;
import com.esofthead.mycollab.module.project.events.TaskListEvent;
import com.esofthead.mycollab.module.project.service.ProjectService;
import com.esofthead.mycollab.module.project.view.ProjectPageAction;
import com.esofthead.mycollab.module.project.view.message.MessageReadPageAction;
import com.esofthead.mycollab.module.project.view.milestone.MilestoneReadPageAction;
import com.esofthead.mycollab.module.project.view.problem.ProblemReadPageAction;
import com.esofthead.mycollab.module.project.view.risk.RiskReadPageAction;
import com.esofthead.mycollab.module.project.view.task.TaskListReadPageAction;
import com.esofthead.mycollab.module.project.view.task.TaskReadPageAction;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.mvp.PageActionChain;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.ui.AbstractBeanPagedList;
import com.esofthead.mycollab.vaadin.ui.DefaultBeanPagedList;
import com.esofthead.mycollab.vaadin.ui.Depot;
import com.esofthead.mycollab.vaadin.ui.UserLink;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.lazyloadwrapper.LazyLoadWrapper;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import java.util.List;

/**
 *
 * @author haiphucnguyen
 */
public class ActivityStreamComponent extends Depot {

    private ProjectActivityStreamPagedList activityStreamList;

    public ActivityStreamComponent() {
        super("User Feeds", new VerticalLayout());
        activityStreamList = new ProjectActivityStreamPagedList();
        this.bodyContent.addComponent(new LazyLoadWrapper(activityStreamList));
        this.addStyleName("activity-panel");
        ((VerticalLayout) this.bodyContent).setMargin(false);
    }

    public void showFeeds() {
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
        public Component generateRow(final ProjectActivityStream activityStream, int rowIndex) {
            CssLayout layout = new CssLayout();
            layout.setWidth("100%");
            layout.setStyleName("activity-stream");

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
            header.addComponent(new ActivityStreamComponent.ActivitylLink(activityStream));

            Label prjLabel = new Label("in project ");
            header.addComponent(prjLabel);
            header.setComponentAlignment(prjLabel, Alignment.MIDDLE_CENTER);
            Button projectLink = new Button(activityStream.getProjectName(), new Button.ClickListener() {
                @Override
                public void buttonClick(ClickEvent event) {
                    EventBus.getInstance().fireEvent(new ProjectEvent.GotoMyProject(this, new PageActionChain(new ProjectPageAction(new ScreenData(activityStream.getProjectId())))));
                }
            });
            header.addComponent(projectLink);
            projectLink.setStyleName("link");
            layout.addComponent(header);

            CssLayout body = new CssLayout();
            body.setStyleName("activity-date");
            Label dateLbl = new Label(DateTimeUtils.getStringDateFromNow(activityStream.getCreatedtime()));
            body.addComponent(dateLbl);

            layout.addComponent(body);
            return layout;
        }
    }

    private static class ActivitylLink extends Button {

        public ActivitylLink(final ProjectActivityStream activityStream) {
            super(activityStream.getNamefield());
            
            final String type = activityStream.getType();
            final int typeid = activityStream.getTypeid();
            final int projectid = activityStream.getExtratypeid();
            
            this.setIcon(ProjectResources.getIconResource16size(type));
            this.setStyleName("link");
            this.addListener(new Button.ClickListener() {
                @Override
                public void buttonClick(Button.ClickEvent event) {
                    if (ProjectContants.PROJECT.equals(type)) {
                        EventBus.getInstance().fireEvent(new ProjectEvent.GotoMyProject(this, new PageActionChain(new ProjectPageAction(new ScreenData(projectid)))));
                    } else if (ProjectContants.MESSAGE.equals(type)) {
                        PageActionChain chain = new PageActionChain(new ProjectPageAction(new ScreenData(projectid)), new MessageReadPageAction(new ScreenData(typeid)));
                        EventBus.getInstance().fireEvent(new ProjectEvent.GotoMyProject(this, chain));
                    } else if (ProjectContants.MILESTONE.equals(type)) {
                        PageActionChain chain = new PageActionChain(new ProjectPageAction(new ScreenData(projectid)), new MilestoneReadPageAction(new ScreenData(typeid)));
                        EventBus.getInstance().fireEvent(new ProjectEvent.GotoMyProject(this, chain));
                    } else if (ProjectContants.PROBLEM.equals(type)) {
                        PageActionChain chain = new PageActionChain(new ProjectPageAction(new ScreenData(projectid)), new ProblemReadPageAction(new ScreenData(typeid)));
                        EventBus.getInstance().fireEvent(new ProjectEvent.GotoMyProject(this, chain));
                    } else if (ProjectContants.RISK.equals(type)) {
                        PageActionChain chain = new PageActionChain(new ProjectPageAction(new ScreenData(projectid)), new RiskReadPageAction(new ScreenData(typeid)));
                        EventBus.getInstance().fireEvent(new ProjectEvent.GotoMyProject(this, chain));
                    } else if (ProjectContants.TASK.equals(type)) {
                        PageActionChain chain = new PageActionChain(new ProjectPageAction(new ScreenData(projectid)), new TaskReadPageAction(new ScreenData(typeid)));
                        EventBus.getInstance().fireEvent(new ProjectEvent.GotoMyProject(this, chain));
                    } else if (ProjectContants.TASK_LIST.equals(type)) {
                        PageActionChain chain = new PageActionChain(new ProjectPageAction(new ScreenData(projectid)), new TaskListReadPageAction(new ScreenData(typeid)));
                        EventBus.getInstance().fireEvent(new ProjectEvent.GotoMyProject(this, chain));
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
