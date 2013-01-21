/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.task;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.module.project.domain.SimpleTask;
import com.esofthead.mycollab.module.project.domain.SimpleTaskList;
import com.esofthead.mycollab.module.project.domain.Task;
import com.esofthead.mycollab.module.project.domain.TaskList;
import com.esofthead.mycollab.module.project.domain.criteria.TaskSearchCriteria;
import com.esofthead.mycollab.module.project.events.TaskEvent;
import com.esofthead.mycollab.vaadin.events.ApplicationEvent;
import com.esofthead.mycollab.vaadin.events.ApplicationEventListener;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.ui.BeanList;
import com.esofthead.mycollab.vaadin.ui.GridFormLayoutHelper;
import com.esofthead.mycollab.vaadin.ui.IPagedBeanTable;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
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
    
    private TaskTableDisplay taskDisplay;
    private TaskList taskList;
    private Button createTaskBtn;
    
    public TaskDisplayComponent(final SimpleTaskList taskList) {
        this.setSpacing(true);
        this.taskList = taskList;
        
        GridFormLayoutHelper layoutHelper = new GridFormLayoutHelper(2, 2);
        this.addComponent(layoutHelper.getLayout());
        
        Label descLbl = (Label)layoutHelper.addComponent(new Label(), "Description", 0, 0, 2, "100%");
        descLbl.setValue(taskList.getDescription());
        
        Button userLink = (Button)layoutHelper.addComponent(new Button(taskList.getOwnerFullName()), "Responsible User", 0, 1);
        userLink.setStyleName("link");
        
        Button milestoneLink = (Button)layoutHelper.addComponent(new Button(taskList.getMilestoneName()), "Milestone", 1, 1);
        milestoneLink.setStyleName("link");
        
        taskDisplay = new TaskTableDisplay(new String[] {"taskname", "startdate", "deadline", "assignUserFullName"}, new String[]{"Task Name", "Start", "Due", "Owner"});
        this.addComponent(taskDisplay);
        displayTasks();
        
        taskDisplay.addTableListener(new ApplicationEventListener<IPagedBeanTable.TableClickEvent>() {
            @Override
            public Class<? extends ApplicationEvent> getEventType() {
                return IPagedBeanTable.TableClickEvent.class;
            }

            @Override
            public void handle(IPagedBeanTable.TableClickEvent event) {
                SimpleTask task = (SimpleTask) event.getData();
                if ("taskname".equals(event.getFieldName())) {
                    EventBus.getInstance().fireEvent(new TaskEvent.GotoRead(TaskDisplayComponent.this, task.getId()));
                }
            }
        });
        
        createTaskBtn = new Button("Add Task", new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                Component comp = TaskDisplayComponent.this.getComponent(0);
                if (!(comp instanceof TaskAddPopup)) {
                    TaskAddPopup taskAddView = new TaskAddPopup(TaskDisplayComponent.this, taskList);
                    TaskDisplayComponent.this.addComponent(taskAddView, 1);
                    TaskDisplayComponent.this.removeComponent(createTaskBtn);
                }
            }
        });
        
        createTaskBtn.setStyleName(UIConstants.THEME_ROUND_BUTTON);
        this.addComponent(createTaskBtn);
    }
    
    private void displayTasks() {
        TaskSearchCriteria taskCriteria = new TaskSearchCriteria();
        taskCriteria.setTaskListId(new NumberSearchField(taskList.getId()));
        taskDisplay.setSearchCriteria(taskCriteria);
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
