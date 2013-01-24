/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.task;

import com.esofthead.mycollab.module.project.domain.SimpleTask;
import com.esofthead.mycollab.module.project.domain.SimpleTaskList;
import com.esofthead.mycollab.module.project.domain.TaskList;
import com.esofthead.mycollab.module.project.domain.criteria.TaskSearchCriteria;
import com.esofthead.mycollab.module.project.events.TaskEvent;
import com.esofthead.mycollab.vaadin.events.ApplicationEvent;
import com.esofthead.mycollab.vaadin.events.ApplicationEventListener;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.ui.GridFormLayoutHelper;
import com.esofthead.mycollab.vaadin.ui.IPagedBeanTable;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;

/**
 *
 * @author haiphucnguyen
 */
public class TaskDisplayComponent extends CssLayout {

    private TaskSearchCriteria criteria;
    private TaskTableDisplay taskDisplay;
    private Button createTaskBtn;
    
    public TaskDisplayComponent(final SimpleTaskList taskList) {
        // this.setSpacing(true);
        this.setStyleName("taskdisplay-component");
        
        GridFormLayoutHelper layoutHelper = new GridFormLayoutHelper(2, 2);
        this.addComponent(layoutHelper.getLayout());
        
        Label descLbl = (Label) layoutHelper.addComponent(new Label(),
                "Description", 0, 0, 2, "100%", Alignment.TOP_LEFT);
        descLbl.setValue(taskList.getDescription());
        
        Button userLink = (Button) layoutHelper.addComponent(new Button(
                taskList.getOwnerFullName()), "Responsible User", 0, 1,
                Alignment.TOP_LEFT);
        userLink.setStyleName("link");
        
        Button milestoneLink = (Button) layoutHelper.addComponent(new Button(
                taskList.getMilestoneName()), "Milestone", 1, 1,
                Alignment.TOP_LEFT);
        milestoneLink.setStyleName("link");
        Layout taskInfo = layoutHelper.getLayout();
        taskInfo.setMargin(false, false, true, false);
        
        taskDisplay = new TaskTableDisplay(new String[]{"taskname",
                    "startdate", "deadline", "assignUserFullName"}, new String[]{
                    "Task Name", "Start", "Due", "Owner"});
        this.addComponent(taskDisplay);
        
        taskDisplay
                .addTableListener(new ApplicationEventListener<IPagedBeanTable.TableClickEvent>() {
            @Override
            public Class<? extends ApplicationEvent> getEventType() {
                return IPagedBeanTable.TableClickEvent.class;
            }
            
            @Override
            public void handle(IPagedBeanTable.TableClickEvent event) {
                SimpleTask task = (SimpleTask) event.getData();
                if ("taskname".equals(event.getFieldName())) {
                    EventBus.getInstance().fireEvent(
                            new TaskEvent.GotoRead(
                            TaskDisplayComponent.this, task
                            .getId()));
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
