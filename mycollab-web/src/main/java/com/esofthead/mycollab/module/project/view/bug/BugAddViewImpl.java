package com.esofthead.mycollab.module.project.view.bug;

import com.esofthead.mycollab.module.tracker.domain.Bug;
import com.esofthead.mycollab.module.tracker.domain.SimpleBug;
import com.esofthead.mycollab.module.user.ui.components.UserComboBox;
import com.esofthead.mycollab.vaadin.events.HasEditFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.AdvancedEditBeanForm;
import com.esofthead.mycollab.vaadin.ui.DefaultEditFormFieldFactory;
import com.esofthead.mycollab.vaadin.ui.DefaultFormViewFieldFactory.AttachmentUploadField;
import com.esofthead.mycollab.vaadin.ui.DefaultFormViewFieldFactory.FormAttachmentUploadField;
import com.esofthead.mycollab.vaadin.ui.EditFormControlsGenerator;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Field;
import com.vaadin.ui.Layout;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ViewComponent
public class BugAddViewImpl extends AbstractView implements BugAddView {

    private static final long serialVersionUID = 1L;
    private static Logger log = LoggerFactory.getLogger(BugAddViewImpl.class);
    
    private EditForm editForm;
    private SimpleBug bug;
    private FormAttachmentUploadField attachmentUploadField;

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

    @Override
    public AttachmentUploadField getAttachUploadField() {
        return attachmentUploadField;
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
                super((bug.getId() == null) ? "Create Bug" : bug.getSummary());
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
                } else if (propertyId.equals("assignuser")) {
                    return new UserComboBox();
                } else if (propertyId.equals("id")) {
                    attachmentUploadField = new FormAttachmentUploadField();
                    return attachmentUploadField;
                } else if (propertyId.equals("severity")) {
                    return new BugSeverityComboBox();
                } else if (propertyId.equals("components")) {
                    return new ComponentMultiSelectComp();
                } else if (propertyId.equals("affectedVersions")) {
                    return new VersionMultiSelectField();
                } else if (propertyId.equals("fixedVersions")) {
                    return new VersionMultiSelectField();
                }
                else if (propertyId.equals("summary")) {
                    TextField tf = new TextField();
                    tf.setNullRepresentation("");
                    tf.setRequired(true);
                    tf.setRequiredError("Please enter a Summary");
                    return tf;
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
