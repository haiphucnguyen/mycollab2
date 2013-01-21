/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.task;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.module.project.domain.SimpleTask;
import com.esofthead.mycollab.module.project.domain.Task;
import com.esofthead.mycollab.module.project.domain.TaskList;
import com.esofthead.mycollab.module.project.domain.criteria.TaskSearchCriteria;
import com.esofthead.mycollab.module.project.service.ProjectTaskService;
import com.esofthead.mycollab.vaadin.ui.BeanList;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.VerticalLayout;

/**
 *
 * @author haiphucnguyen
 */
public class TaskDisplayComponent extends VerticalLayout {
    
    private BeanList<ProjectTaskService, TaskSearchCriteria, SimpleTask> taskDisplay;
    private Button createTaskBtn;
    
    public TaskDisplayComponent(final TaskList taskList) {
        this.setSpacing(true);
        taskDisplay = new BeanList<ProjectTaskService, TaskSearchCriteria, SimpleTask>(AppContext.getSpringBean(ProjectTaskService.class), TaskRowDisplayHandler.class);
        TaskSearchCriteria taskCriteria = new TaskSearchCriteria();
        taskCriteria.setTaskListId(new NumberSearchField(taskList.getId()));
        taskDisplay.setSearchCriteria(taskCriteria);
        
        this.addComponent(taskDisplay);
        
        createTaskBtn = new Button("Add Task", new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                Component comp = TaskDisplayComponent.this.getComponent(0);
                if (!(comp instanceof TaskAddPopup)) {
                    TaskAddPopup taskAddView = new TaskAddPopup(TaskDisplayComponent.this, taskList);
                    TaskDisplayComponent.this.addComponent(taskAddView, 0);
                    TaskDisplayComponent.this.removeComponent(createTaskBtn);
                }
            }
        });
        
        createTaskBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
        this.addComponent(createTaskBtn);
    }
    
    public void saveTaskSuccess(SimpleTask task) {
        taskDisplay.insertItemOnTop(task);
    }
    
    public void closeTaskAdd() {
        this.addComponent(createTaskBtn);
        Component comp = this.getComponent(0);
        if (comp instanceof TaskAddPopup) {
            this.removeComponent(comp);
        }
    }
    
    public static class TaskRowDisplayHandler implements BeanList.RowDisplayHandler<SimpleTask> {
        
        private TaskDisplayLayout taskLayout;
        
        private VerticalLayout taskContent;
        
        private SimpleTask task;
        
        @Override
        public Component generateRow(SimpleTask task, int rowIndex) {
            this.task = task;
            taskContent = new VerticalLayout();
            showSimpleTaskLayout();
            return taskContent;
        }
        
        void showSimpleTaskLayout() {
            taskContent.removeAllComponents();
            taskLayout = new SimpleTaskDisplay(this);
            taskContent.addComponent(taskLayout);
            taskLayout.showTask(task);
        }
        
        void showAdvancedTaskLayout() {
            taskContent.removeAllComponents();
            taskLayout = new AdvancedTaskDisplay(this);
            taskContent.addComponent(taskLayout);
            taskLayout.showTask(task);
        }
    }
    
    private static interface TaskDisplayLayout extends Component {

        void showTask(SimpleTask task);
    }
    
    private static class SimpleTaskDisplay extends VerticalLayout implements TaskDisplayLayout {
        private TaskRowDisplayHandler taskRowHandler;
        
        public SimpleTaskDisplay(TaskRowDisplayHandler taskRowHandler) {
            this.taskRowHandler = taskRowHandler;
        }
        
        @Override
        public void showTask(SimpleTask task) {
            Label taskLbl = new Label(task.getTaskname());
            this.addComponent(taskLbl);
            Button moreButton = new Button("More ...", new Button.ClickListener() {

                @Override
                public void buttonClick(ClickEvent event) {
                    taskRowHandler.showAdvancedTaskLayout();
                }
            });
            moreButton.setStyleName("link");
            this.addComponent(moreButton);
        }
    }
    
    private static class AdvancedTaskDisplay extends VerticalLayout implements TaskDisplayLayout {
        private TaskRowDisplayHandler taskRowHandler;
        
        public AdvancedTaskDisplay(TaskRowDisplayHandler taskRowHandler) {
            this.taskRowHandler = taskRowHandler;
        }
        
        @Override
        public void showTask(final SimpleTask task) {
            TaskFormComponent previewForm = new TaskFormComponent() {

                @Override
                TaskFormLayoutFactory getFormLayoutFactory() {
                    return new TaskFormLayoutFactory(task.getTaskname()) {

                        @Override
                        protected Layout createTopPanel() {
                            return null;
                        }

                        @Override
                        protected Layout createBottomPanel() {
                            return null;
                        }
                    };
                }
            };
            
            this.addComponent(previewForm);
            previewForm.setItemDataSource(new BeanItem<Task>(task));
            Button moreButton = new Button("Less ...", new Button.ClickListener() {

                @Override
                public void buttonClick(ClickEvent event) {
                    taskRowHandler.showSimpleTaskLayout();
                }
            });
            moreButton.setStyleName("link");
            this.addComponent(moreButton);
        }
        
        
    }
}
