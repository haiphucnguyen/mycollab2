package com.esofthead.mycollab.module.project.view.task;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.module.project.ProjectContants;
import com.esofthead.mycollab.module.project.domain.SimpleProject;
import com.esofthead.mycollab.module.project.domain.SimpleTaskList;
import com.esofthead.mycollab.module.project.domain.criteria.TaskListSearchCriteria;
import com.esofthead.mycollab.module.project.events.TaskListEvent;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;

@SuppressWarnings("serial")
@ViewComponent
public class TaskGroupDisplayViewImpl extends AbstractView implements
        TaskGroupDisplayView {

    private TaskGroupDisplayWidget taskLists;
    private Button reOrderBtn;

    public TaskGroupDisplayViewImpl() {
        super();

        constructHeader();
    }

    private void constructHeader() {
        HorizontalLayout header = new HorizontalLayout();
        header.setMargin(true);
        header.setSpacing(true);
        header.setWidth("100%");
        Label headerLbl = new Label("All Tasks");
        headerLbl.setStyleName("h2");
        header.addComponent(headerLbl);
        header.setExpandRatio(headerLbl, 1.0f);

        reOrderBtn = new Button("Reorder", new Button.ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                EventBus.getInstance().fireEvent(
                        new TaskListEvent.ReoderTaskList(this, null));
            }
        });
        reOrderBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
        header.addComponent(reOrderBtn);
        header.setComponentAlignment(reOrderBtn, Alignment.MIDDLE_RIGHT);

        Button newTaskListBtn = new Button("New Task List",
                new Button.ClickListener() {
                    @Override
                    public void buttonClick(ClickEvent event) {
                        TaskGroupAddWindow taskListWindow = new TaskGroupAddWindow(
                                TaskGroupDisplayViewImpl.this);
                        TaskGroupDisplayViewImpl.this.getWindow().addWindow(
                                taskListWindow);
                    }
                });
        newTaskListBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
        header.addComponent(newTaskListBtn);
        header.setComponentAlignment(newTaskListBtn, Alignment.MIDDLE_RIGHT);

        this.addComponent(header);
        taskLists = new TaskGroupDisplayWidget();
        this.addComponent(taskLists);
    }

    @Override
    public void displayTakLists() {
        TaskListSearchCriteria criteria = new TaskListSearchCriteria();
        SimpleProject project = (SimpleProject) AppContext
                .getVariable(ProjectContants.PROJECT_NAME);
        criteria.setProjectId(new NumberSearchField(project.getId()));
        criteria.setStatus(new StringSearchField("Open"));
        taskLists.setSearchCriteria(criteria);
    }

    @Override
    public void insertTaskList(SimpleTaskList taskList) {
        taskLists.insertItemOnTop(taskList);
    }
}
