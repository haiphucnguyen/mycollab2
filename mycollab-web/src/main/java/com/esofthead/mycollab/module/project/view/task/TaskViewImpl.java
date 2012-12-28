package com.esofthead.mycollab.module.project.view.task;

import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;

@SuppressWarnings("serial")
@ViewComponent
public class TaskViewImpl extends AbstractView implements
        TaskView {

    public TaskViewImpl() {
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
        
        Button newTaskBtn = new Button("New Task", new Button.ClickListener() {

            @Override
            public void buttonClick(ClickEvent event) {
                TaskListWindow taskListWindow = new TaskListWindow();
                TaskViewImpl.this.getWindow().addWindow(taskListWindow);
            }
        });
        newTaskBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
        header.addComponent(newTaskBtn);
        header.setComponentAlignment(newTaskBtn, Alignment.MIDDLE_RIGHT);
        
        Button newTaskListBtn = new Button("New Task List", new Button.ClickListener() {

            @Override
            public void buttonClick(ClickEvent event) {
                TaskListWindow taskListWindow = new TaskListWindow();
                TaskViewImpl.this.getWindow().addWindow(taskListWindow);
            }
        });
        newTaskListBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
        header.addComponent(newTaskListBtn);
        header.setComponentAlignment(newTaskListBtn, Alignment.MIDDLE_RIGHT);
        
        this.addComponent(header);
    }
}
