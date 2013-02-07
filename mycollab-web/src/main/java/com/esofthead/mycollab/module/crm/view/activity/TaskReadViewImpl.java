package com.esofthead.mycollab.module.crm.view.activity;

import com.esofthead.mycollab.module.crm.domain.SimpleTask;
import com.esofthead.mycollab.module.crm.domain.Task;
import com.esofthead.mycollab.module.crm.ui.components.RelatedReadItemField;
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
                        return new FormLinkViewField(task
                                .getAssignUserFullName(),
                                new Button.ClickListener() {
                                    private static final long serialVersionUID = 1L;

                                    @Override
                                    public void buttonClick(ClickEvent event) {
                                    }
                                });
                    } else if (propertyId.equals("startdate")) {
                        return new FormViewField(AppContext.formatDateTime(task
                                .getStartdate()));
                    } else if (propertyId.equals("duedate")) {
                        return new FormViewField(AppContext.formatDateTime(task
                                .getDuedate()));
                    } else if (propertyId.equals("contactid")) {
                        return new FormViewField(task.getContactName());
                    } else if (propertyId.equals("type")) {
                        return new RelatedReadItemField(task);

                    }

                    return null;
                }
            });
            super.setItemDataSource(newDataSource);
        }

        class FormLayoutFactory extends TaskFormLayoutFactory {

            private static final long serialVersionUID = 1L;

            public FormLayoutFactory() {
                super(task.getSubject());
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
