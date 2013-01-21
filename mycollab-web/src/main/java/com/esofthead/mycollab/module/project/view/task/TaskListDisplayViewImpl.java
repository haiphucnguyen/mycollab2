package com.esofthead.mycollab.module.project.view.task;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.module.project.ProjectContants;
import com.esofthead.mycollab.module.project.domain.SimpleProject;
import com.esofthead.mycollab.module.project.domain.SimpleTaskList;
import com.esofthead.mycollab.module.project.domain.criteria.TaskListSearchCriteria;
import com.esofthead.mycollab.module.project.service.ProjectTaskListService;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.BeanList;
import com.esofthead.mycollab.vaadin.ui.Depot;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;

@SuppressWarnings("serial")
@ViewComponent
public class TaskListDisplayViewImpl extends AbstractView implements
        TaskListDisplayView {

    private BeanList<ProjectTaskListService, TaskListSearchCriteria, SimpleTaskList> taskLists;

    public TaskListDisplayViewImpl() {
        super();

        constructHeader();
    }

    private void constructHeader() {
        HorizontalLayout header = new HorizontalLayout();
        header.setMargin(true);
        header.setSpacing(true);
        header.setWidth("100%");
        Label headerLbl = new Label("All Tasks");
        header.addComponent(headerLbl);
        header.setExpandRatio(headerLbl, 1.0f);

        Button newTaskListBtn = new Button("New Task List", new Button.ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                TaskListWindow taskListWindow = new TaskListWindow(TaskListDisplayViewImpl.this);
                TaskListDisplayViewImpl.this.getWindow().addWindow(taskListWindow);
            }
        });
        newTaskListBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
        header.addComponent(newTaskListBtn);
        header.setComponentAlignment(newTaskListBtn, Alignment.MIDDLE_RIGHT);

        this.addComponent(header);

        taskLists = new BeanList<ProjectTaskListService, TaskListSearchCriteria, SimpleTaskList>(AppContext.getSpringBean(ProjectTaskListService.class), TaskListRowDisplayHandler.class);
        this.addComponent(taskLists);
    }

    @Override
    public void displayTakLists() {
        TaskListSearchCriteria criteria = new TaskListSearchCriteria();
        SimpleProject project = (SimpleProject) AppContext.getVariable(ProjectContants.PROJECT_NAME);
        criteria.setProjectId(new NumberSearchField(project.getId()));
        taskLists.setSearchCriteria(criteria);
    }

    @Override
    public void insertTaskList(SimpleTaskList taskList) {
        taskLists.insertItemOnTop(taskList);
    }

    public static class TaskListRowDisplayHandler implements BeanList.RowDisplayHandler<SimpleTaskList> {

        @Override
        public Component generateRow(SimpleTaskList taskList, int rowIndex) {
            return new TaskListDepot(taskList);
        }
    }

    static class TaskListDepot extends Depot {

        public TaskListDepot(SimpleTaskList taskListParam) {
            super(taskListParam.getName(), new TaskDisplayComponent(taskListParam));
        }
    }
}
