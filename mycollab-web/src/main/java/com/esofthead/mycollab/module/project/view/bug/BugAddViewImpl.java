package com.esofthead.mycollab.module.project.view.bug;

import com.esofthead.mycollab.module.tracker.domain.Bug;
import com.esofthead.mycollab.module.tracker.domain.SimpleBug;
import com.esofthead.mycollab.vaadin.events.HasEditFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.AdvancedEditBeanForm;
import com.esofthead.mycollab.vaadin.ui.DefaultEditFormFieldFactory;
import com.esofthead.mycollab.vaadin.ui.EditFormControlsGenerator;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Field;
import com.vaadin.ui.Layout;
import com.vaadin.ui.TextArea;

@ViewComponent
public class BugAddViewImpl extends AbstractView implements BugAddView {

    private static final long serialVersionUID = 1L;
    private EditForm editForm;
    private SimpleBug bug;

    public BugAddViewImpl() {
        super();
        editForm = new EditForm();
        this.addComponent(editForm);
    }

    @Override
    public void editItem(SimpleBug item) {
        this.bug = item;
        editForm.setItemDataSource(new BeanItem<Bug>(item));
    }

    private class EditForm extends AdvancedEditBeanForm<SimpleBug> {

        private static final long serialVersionUID = 1L;

        @Override
        public void setItemDataSource(Item newDataSource) {
            this.setFormLayoutFactory(new FormLayoutFactory());
            this.setFormFieldFactory(new EditFormFieldFactory());
            super.setItemDataSource(newDataSource);
        }

        class FormLayoutFactory extends BugAddFormLayoutFactory {

            private static final long serialVersionUID = 1L;
            
            public FormLayoutFactory() {
                super("Create Bug");
            }

            private Layout createButtonControls() {
                return (new EditFormControlsGenerator<SimpleBug>(EditForm.this))
                        .createButtonControls();
            }

            @Override
            protected Layout createTopPanel() {
                return createButtonControls();
            }

            @Override
            protected Layout createBottomPanel() {
                return createButtonControls();
            }
        }

        private class EditFormFieldFactory extends DefaultEditFormFieldFactory {

            private static final long serialVersionUID = 1L;

            @Override
            protected Field onCreateField(Item item, Object propertyId,
                    com.vaadin.ui.Component uiContext) {

                if (propertyId.equals("environment")) {
                    TextArea field = new TextArea("", "");
                    field.setNullRepresentation("");
                    return field;
                } else if (propertyId.equals("description")) {
                    TextArea field = new TextArea("", "");
                    field.setNullRepresentation("");
                    return field;
                } else if (propertyId.equals("priority")) {
                    return new BugPriorityComboBox();
                }
                return null;
            }
        }
    }

    @Override
    public HasEditFormHandlers<SimpleBug> getEditFormHandlers() {
        return editForm;
    }
}
