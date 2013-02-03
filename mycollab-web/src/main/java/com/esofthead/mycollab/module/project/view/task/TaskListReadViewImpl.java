/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.task;

import com.esofthead.mycollab.common.CommentTypeConstants;
import com.esofthead.mycollab.common.ui.components.CommentListDepot;
import com.esofthead.mycollab.module.project.domain.SimpleTaskList;
import com.esofthead.mycollab.module.project.domain.TaskList;
import com.esofthead.mycollab.module.project.events.MilestoneEvent;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.events.HasPreviewFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.AdvancedPreviewBeanForm;
import com.esofthead.mycollab.vaadin.ui.DefaultFormViewFieldFactory;
import com.esofthead.mycollab.vaadin.ui.Depot;
import com.esofthead.mycollab.vaadin.ui.PreviewFormControlsGenerator;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.ProgressIndicator;
import com.vaadin.ui.VerticalLayout;

/**
 *
 * @author haiphucnguyen
 */
@ViewComponent
public class TaskListReadViewImpl extends AbstractView implements TaskListReadView {

    private static final long serialVersionUID = 1L;
    private PreviewForm previewForm;
    private SimpleTaskList taskList;

    public TaskListReadViewImpl() {
        super();
        previewForm = new PreviewForm();
        this.addComponent(previewForm);
    }

    @Override
    public void previewItem(SimpleTaskList taskList) {
        this.taskList = taskList;
        previewForm.setItemDataSource(new BeanItem<TaskList>(taskList));
    }

    @Override
    public HasPreviewFormHandlers<SimpleTaskList> getPreviewFormHandlers() {
        return previewForm;
    }

    private class PreviewForm extends AdvancedPreviewBeanForm<SimpleTaskList> {

        private static final long serialVersionUID = 1L;

        @Override
        public void setItemDataSource(Item newDataSource) {
            this.setFormLayoutFactory(new FormLayoutFactory());
            this.setFormFieldFactory(new DefaultFormViewFieldFactory() {
                private static final long serialVersionUID = 1L;

                @Override
                protected Field onCreateField(Item item, Object propertyId,
                        Component uiContext) {
                    if (propertyId.equals("milestoneid")) {
                        return new FormLinkViewField(taskList.getMilestoneName(), new Button.ClickListener() {
                            @Override
                            public void buttonClick(ClickEvent event) {
                                EventBus.getInstance().fireEvent(new MilestoneEvent.GotoRead(this, taskList.getMilestoneid()));
                            }
                        });
                    } else if (propertyId.equals("owner")) {
                        return new UserLinkViewField(taskList.getOwner(), taskList.getOwnerFullName());
                    } else if (propertyId.equals("percentageComplete")) {
                        FormContainerHorizontalViewField fieldContainer = new FormContainerHorizontalViewField();
                        ProgressIndicator progressField = new ProgressIndicator(new Float(taskList.getPercentageComplete()/100));
                        progressField.setWidth("100px");
                        fieldContainer.addComponentField(progressField);
                        return fieldContainer;
                    } else if (propertyId.equals("numOpenTasks")) {
                        FormContainerHorizontalViewField fieldContainer = new FormContainerHorizontalViewField();
                        ProgressIndicator progressField = new ProgressIndicator(new Float(1 - (float)taskList.getNumOpenTasks() / taskList.getNumAllTasks()));
                        progressField.setWidth("100px");
                        fieldContainer.addComponentField(progressField);
                        
                        Label numTaskLbl = new Label("(" + taskList.getNumOpenTasks() + "/" + taskList.getNumAllTasks() + ")");
                        fieldContainer.addComponentField(numTaskLbl);
                        return fieldContainer;
                    }

                    return null;
                }
            });
            super.setItemDataSource(newDataSource);
        }

        class FormLayoutFactory extends TaskListFormLayoutFactory {

            private static final long serialVersionUID = 1L;

            public FormLayoutFactory() {
                super(taskList.getName());
            }

            @Override
            protected Layout createTopPanel() {
                return (new PreviewFormControlsGenerator<SimpleTaskList>(PreviewForm.this))
                        .createButtonControls();
            }

            @Override
            protected Layout createBottomPanel() {
                VerticalLayout relatedItemsPanel = new VerticalLayout();
                relatedItemsPanel.addComponent(new CommentListDepot(CommentTypeConstants.PRJ_TASK_LIST, taskList.getId()));
                relatedItemsPanel.addComponent(new TaskDepot());
                return relatedItemsPanel;
            }
        }
    }

    private class TaskDepot extends Depot {

        public TaskDepot() {
            super("Tasks", new TaskDisplayComponent(taskList, false));
        }
    }

    @Override
    public SimpleTaskList getItem() {
        return taskList;
    }
}
