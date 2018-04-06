package com.mycollab.premium.mobile.module.crm.view.activity;

import com.mycollab.premium.mobile.module.crm.view.contact.ContactSelectionField;
import com.mycollab.mobile.module.user.ui.components.ActiveUserComboBox;
import com.mycollab.mobile.ui.I18NValueListSelect;
import com.mycollab.module.crm.CrmDataTypeFactory;
import com.mycollab.module.crm.domain.CrmTask;
import com.mycollab.vaadin.ui.AbstractBeanFieldGroupEditFieldFactory;
import com.mycollab.vaadin.ui.DummyCustomField;
import com.mycollab.vaadin.ui.GenericBeanForm;
import com.vaadin.addon.touchkit.ui.DatePicker;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;

import java.util.Arrays;

/**
 * @author MyCollab Ltd.
 * @since 4.1
 */
public class AssignmentEditFormFieldFactory extends AbstractBeanFieldGroupEditFieldFactory<CrmTask> {
    private static final long serialVersionUID = 1L;

    public AssignmentEditFormFieldFactory(GenericBeanForm<CrmTask> form) {
        super(form);
    }

    @Override
    protected AbstractField<?> onCreateField(Object propertyId) {
        if (propertyId.equals("startdate")) {
            return new DatePicker();
        } else if (propertyId.equals("duedate")) {
            return new DatePicker();
        } else if (propertyId.equals("status")) {
            return new TaskStatusListSelect();
        } else if (propertyId.equals("priority")) {
            return new TaskPriorityListSelect();
        } else if (propertyId.equals("description")) {
            TextArea descArea = new TextArea();
            descArea.setNullRepresentation("");
            return descArea;
        } else if (propertyId.equals("contactid")) {
            return new ContactSelectionField();
        } else if (propertyId.equals("subject")) {
            TextField tf = new TextField();

            if (isValidateForm) {
                tf.setRequired(true);
                tf.setRequiredError("Subject must not be null");
                tf.setNullRepresentation("");
            }

            return tf;
        } else if (propertyId.equals("typeid")) {
            return new RelatedItemSelectionField(attachForm.getBean());
        } else if (propertyId.equals("type")) {
            return new DummyCustomField<String>();
        } else if (propertyId.equals("assignuser")) {
            ActiveUserComboBox userBox = new ActiveUserComboBox();
            userBox.select(attachForm.getBean().getAssignuser());
            return userBox;
        }
        return null;
    }

    static class TaskPriorityListSelect extends I18NValueListSelect {
        private static final long serialVersionUID = 1L;

        private TaskPriorityListSelect() {
            setCaption(null);
            this.loadData(Arrays.asList(CrmDataTypeFactory.taskPriorities));
        }
    }

    static class TaskStatusListSelect extends I18NValueListSelect {
        private static final long serialVersionUID = 1L;

        private TaskStatusListSelect() {
            setCaption(null);
            this.loadData(Arrays.asList(CrmDataTypeFactory.taskStatuses));
        }
    }
}
