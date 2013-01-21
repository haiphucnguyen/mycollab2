/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.task;

import com.esofthead.mycollab.module.project.domain.SimpleTask;
import com.esofthead.mycollab.module.project.domain.Task;
import com.esofthead.mycollab.module.project.events.TaskListEvent;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.events.HasPreviewFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.AdvancedPreviewBeanForm;
import com.esofthead.mycollab.vaadin.ui.DefaultFormViewFieldFactory;
import com.esofthead.mycollab.vaadin.ui.PreviewFormControlsGenerator;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.Layout;
import com.vaadin.ui.VerticalLayout;

/**
 *
 * @author haiphucnguyen
 */
@ViewComponent
public class TaskReadViewImpl extends AbstractView implements TaskReadView {

    private static final long serialVersionUID = 1L;
    private PreviewForm previewForm;
    private SimpleTask task;

    public TaskReadViewImpl() {
        super();
        previewForm = new PreviewForm();
        this.addComponent(previewForm);
    }

    @Override
    public void previewItem(SimpleTask task) {
        this.task = task;
        previewForm.setItemDataSource(new BeanItem<Task>(task));
    }

    @Override
    public HasPreviewFormHandlers<Task> getPreviewFormHandlers() {
        return previewForm;
    }

    private class PreviewForm extends AdvancedPreviewBeanForm<Task> {

        private static final long serialVersionUID = 1L;

        @Override
        public void setItemDataSource(Item newDataSource) {
            this.setFormLayoutFactory(new FormLayoutFactory());
            this.setFormFieldFactory(new DefaultFormViewFieldFactory() {
                private static final long serialVersionUID = 1L;

                @Override
                protected Field onCreateField(Item item, Object propertyId,
                        Component uiContext) {

                    if (propertyId.equals("assignuser")) {
                        return new UserLinkViewField(task.getAssignuser(), task.getAssignUserFullName());
                    } else if (propertyId.equals("taskListName")) {
                        return new FormViewField(task.getTaskListName());
                    } else if (propertyId.equals("startdate")) {
                        return new FormViewField(AppContext.formatDate(task.getStartdate()));
                    } else if (propertyId.equals("enddate")) {
                        return new FormViewField(AppContext.formatDate(task.getEnddate()));
                    } else if (propertyId.equals("actualstartdate")) {
                        return new FormViewField(AppContext.formatDate(task.getActualstartdate()));
                    } else if (propertyId.equals("actualenddate")) {
                        return new FormViewField(AppContext.formatDate(task.getActualenddate()));
                    } else if (propertyId.equals("deadline")) {
                        return new FormViewField(AppContext.formatDate(task.getDeadline()));
                    } else if (propertyId.equals("tasklistid")) {
                        return new FormLinkViewField(task.getTaskListName(), new Button.ClickListener() {

                            @Override
                            public void buttonClick(ClickEvent event) {
                                EventBus.getInstance().fireEvent(new TaskListEvent.GotoRead(this, task.getTasklistid()));
                            }
                        });
                    }
                    return null;
                }
            });
            super.setItemDataSource(newDataSource);
        }

        class FormLayoutFactory extends TaskFormLayoutFactory {

            private static final long serialVersionUID = 1L;

            public FormLayoutFactory() {
                super(task.getTaskname());
            }

            @Override
            protected Layout createTopPanel() {
                return (new PreviewFormControlsGenerator<Task>(PreviewForm.this))
                        .createButtonControls();
            }

            @Override
            protected Layout createBottomPanel() {
                VerticalLayout relatedItemsPanel = new VerticalLayout();

                return relatedItemsPanel;
            }
        }
    }

    @Override
    public SimpleTask getItem() {
        return task;
    }
}
