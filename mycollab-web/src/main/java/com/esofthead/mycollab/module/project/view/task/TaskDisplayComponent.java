/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.task;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.module.project.ProjectContants;
import com.esofthead.mycollab.module.project.domain.SimpleProject;
import com.esofthead.mycollab.module.project.domain.SimpleTask;
import com.esofthead.mycollab.module.project.domain.SimpleTaskList;
import com.esofthead.mycollab.module.project.domain.criteria.TaskSearchCriteria;
import com.esofthead.mycollab.module.project.events.TaskEvent;
import com.esofthead.mycollab.module.project.service.ProjectTaskService;
import com.esofthead.mycollab.vaadin.events.ApplicationEvent;
import com.esofthead.mycollab.vaadin.events.ApplicationEventListener;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.ui.GridFormLayoutHelper;
import com.esofthead.mycollab.vaadin.ui.table.TableClickEvent;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.ProgressIndicator;

/**
 *
 * @author haiphucnguyen
 */
public class TaskDisplayComponent extends CssLayout {

    private TaskSearchCriteria criteria;
    private TaskTableDisplay taskDisplay;
    private Button createTaskBtn;
    private SimpleTaskList taskList;

    public TaskDisplayComponent(final SimpleTaskList taskList, boolean isDisplayTaskListInfo) {
        this.taskList = taskList;
        this.setStyleName("taskdisplay-component");

        if (isDisplayTaskListInfo) {
            GridFormLayoutHelper layoutHelper = new GridFormLayoutHelper(2, 3);
            layoutHelper.getLayout().setWidth("100%");
            this.addComponent(layoutHelper.getLayout());

            Label descLbl = (Label) layoutHelper.addComponent(new Label(),
                    "Description", 0, 0, 2, "100%", Alignment.TOP_RIGHT);
            descLbl.setValue(taskList.getDescription());

            Button userLink = (Button) layoutHelper.addComponent(new Button(
                    taskList.getOwnerFullName()), "Responsible User", 0, 1,
                    Alignment.TOP_RIGHT);
            userLink.setStyleName("link");

            Button milestoneLink = (Button) layoutHelper.addComponent(new Button(
                    taskList.getMilestoneName()), "Milestone", 1, 1,
                    Alignment.TOP_RIGHT);
            milestoneLink.setStyleName("link");

            ProgressIndicator taskListProgress = (ProgressIndicator) layoutHelper.addComponent(new ProgressIndicator(new Float(taskList.getPercentageComplete())), "Progress", 0, 2);
            taskListProgress.setWidth("100px");
            taskListProgress.setValue(taskList.getPercentageComplete() / 100);
            taskListProgress.setPollingInterval(1000000000);

            HorizontalLayout taskNumberProgress = new HorizontalLayout();
            taskNumberProgress.setSpacing(true);
            taskNumberProgress = (HorizontalLayout) layoutHelper.addComponent(taskNumberProgress, "% Task Complete", 1, 2);

            Label taskNumberLbl = new Label("(" + taskList.getNumOpenTasks() + "/" + taskList.getNumAllTasks() + ")");
            taskNumberProgress.addComponent(taskNumberLbl);

            Layout taskInfo = layoutHelper.getLayout();
            taskInfo.setMargin(false, false, true, false);
        }


        taskDisplay = new TaskTableDisplay(new String[]{"id", "taskname",
                    "startdate", "deadline", "percentagecomplete", "assignUserFullName"}, new String[]{
                    "", "Task Name", "Start", "Due", "% Complete", "Owner"});
        this.addComponent(taskDisplay);

        taskDisplay
                .addTableListener(new ApplicationEventListener<TableClickEvent>() {
            @Override
            public Class<? extends ApplicationEvent> getEventType() {
                return TableClickEvent.class;
            }

            @Override
            public void handle(TableClickEvent event) {
                SimpleTask task = (SimpleTask) event.getData();
                if ("taskname".equals(event.getFieldName())) {
                    EventBus.getInstance().fireEvent(
                            new TaskEvent.GotoRead(
                            TaskDisplayComponent.this, task
                            .getId()));
                } else if ("id".equals(event.getFieldName())) {
                    task.setStatus("Closed");
                    task.setPercentagecomplete(100d);
                    
                    ProjectTaskService projectTaskService = AppContext.getSpringBean(ProjectTaskService.class);
                    projectTaskService.updateWithSession(task, AppContext.getUsername());
                }
            }
        });

        createTaskBtn = new Button("Add Task", new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                Component comp = TaskDisplayComponent.this.getComponent(0);
                if (!(comp instanceof TaskAddPopup)) {
                    TaskAddPopup taskAddView = new TaskAddPopup(
                            TaskDisplayComponent.this, taskList);
                    TaskDisplayComponent.this.addComponent(taskAddView, 1);
                    TaskDisplayComponent.this.removeComponent(createTaskBtn);
                }
            }
        });
        createTaskBtn.setIcon(new ThemeResource("icons/16/addRecordGreen.png"));
        createTaskBtn.setStyleName("link");
        this.addComponent(createTaskBtn);

        taskDisplay.setItems(taskList.getSubTasks());
    }

    public void setSearchCriteria(TaskSearchCriteria criteria) {
        this.criteria = criteria;
        displayTasks();
    }

    private void displayTasks() {
    	if (criteria == null) {
    		SimpleProject project = (SimpleProject) AppContext
                    .getVariable(ProjectContants.PROJECT_NAME);
            TaskSearchCriteria criteria = new TaskSearchCriteria();
            criteria.setProjectid(new NumberSearchField(project.getId()));
            criteria.setTaskListId(new NumberSearchField(taskList.getId()));
            criteria.setStatus(new StringSearchField("Open"));
            this.criteria = criteria;
    	} 
    	
    	taskDisplay.setSearchCriteria(criteria);
    }

    public void saveTaskSuccess(SimpleTask task) {
        displayTasks();
    }

    public void closeTaskAdd() {
        this.addComponent(createTaskBtn);
        Component comp = this.getComponent(1);
        if (comp instanceof TaskAddPopup) {
            this.removeComponent(comp);
        }
    }
}
