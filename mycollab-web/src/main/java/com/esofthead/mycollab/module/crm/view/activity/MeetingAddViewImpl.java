package com.esofthead.mycollab.module.crm.view.activity;

import com.esofthead.mycollab.module.crm.CrmTypeConstants;
import com.esofthead.mycollab.module.crm.domain.Meeting;
import com.esofthead.mycollab.module.crm.ui.components.RelatedEditItemField;
import com.esofthead.mycollab.vaadin.events.HasEditFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.AdvancedEditBeanForm;
import com.esofthead.mycollab.vaadin.ui.DefaultEditFormFieldFactory;
import com.esofthead.mycollab.vaadin.ui.EditFormControlsGenerator;
import com.esofthead.mycollab.vaadin.ui.ValueComboBox;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Field;
import com.vaadin.ui.Layout;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import java.util.Collection;
import org.vaadin.addon.customfield.CustomField;

@ViewComponent
public class MeetingAddViewImpl extends AbstractView implements MeetingAddView {

    private static final long serialVersionUID = 1L;
    private EditForm editForm;
    private Meeting meeting;

    public MeetingAddViewImpl() {
        super();
        editForm = new EditForm();
        this.addComponent(editForm);
    }

    @Override
    public void editItem(Meeting item) {
        this.meeting = item;
        editForm.setItemDataSource(new BeanItem<Meeting>(meeting));
    }

    private class EditForm extends AdvancedEditBeanForm<Meeting> {

        private static final long serialVersionUID = 1L;

        @Override
        public void setItemDataSource(Item newDataSource,
                Collection<?> propertyIds) {
            this.setFormLayoutFactory(new FormLayoutFactory());
            this.setFormFieldFactory(new EditFormFieldFactory());
            super.setItemDataSource(newDataSource, propertyIds);
        }

        private class FormLayoutFactory extends MeetingFormLayoutFactory {

            private static final long serialVersionUID = 1L;

            public FormLayoutFactory() {
                super((meeting.getId() == null) ? "Create Meeting" : meeting.getSubject());
            }

            private Layout createButtonControls() {
                return (new EditFormControlsGenerator<Meeting>(EditForm.this))
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
                if (propertyId.equals("subject")) {
                    TextField tf = new TextField();
                    tf.setNullRepresentation("");
                    tf.setRequired(true);
                    tf.setRequiredError("Subject must not be null");
                    return tf;
                } else if (propertyId.equals("status")) {
                    return new MeetingStatusComboBox();
                } else if (propertyId.equals("startdate")) {
                    return new PopupDateField();
                } else if (propertyId.equals("enddate")) {
                    return new PopupDateField();
                } else if (propertyId.equals("description")) {
                    TextArea descArea = new TextArea();
                    descArea.setNullRepresentation("");
                    return descArea;
                } else if (propertyId.equals("type")) {
                    RelatedEditItemField field = new RelatedEditItemField(
                            new String[]{CrmTypeConstants.ACCOUNT, CrmTypeConstants.CAMPAIGN, CrmTypeConstants.CONTACT, CrmTypeConstants.LEAD,
                                CrmTypeConstants.OPPORTUNITY, CrmTypeConstants.CASE}, meeting);
                    field.setType(meeting.getType());
                    return field;
                } else if (propertyId.equals("isrecurrence")) {
                }
                return null;
            }
        }
    }

    @Override
    public HasEditFormHandlers<Meeting> getEditFormHandlers() {
        return editForm;
    }

    private class RecurringSettingPanel extends CustomField {

        public RecurringSettingPanel() {
            CheckBox isRecurringBox = new CheckBox();
        }

        @Override
        public Class<?> getType() {
            return Object.class;
        }
    }

    private class MeetingStatusComboBox extends ValueComboBox {

        private static final long serialVersionUID = 1L;

        public MeetingStatusComboBox() {
            super();
            setCaption(null);
            this.loadData(new String[]{"Planned", "Held", "Not Held"});
        }
    }
}
