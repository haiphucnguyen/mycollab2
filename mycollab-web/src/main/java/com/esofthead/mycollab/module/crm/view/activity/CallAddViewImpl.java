package com.esofthead.mycollab.module.crm.view.activity;

import com.esofthead.mycollab.module.crm.CrmTypeConstants;
import com.esofthead.mycollab.module.crm.domain.Call;
import com.esofthead.mycollab.module.crm.ui.components.RelatedEditItemField;
import com.esofthead.mycollab.module.user.ui.components.UserComboBox;
import com.esofthead.mycollab.vaadin.events.HasEditFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.AdvancedEditBeanForm;
import com.esofthead.mycollab.vaadin.ui.DefaultEditFormFieldFactory;
import com.esofthead.mycollab.vaadin.ui.EditFormControlsGenerator;
import com.esofthead.mycollab.vaadin.ui.ValueComboBox;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Field;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import java.util.Collection;
import org.vaadin.addon.customfield.CustomField;

@ViewComponent
public class CallAddViewImpl extends AbstractView implements CallAddView {

    private static final long serialVersionUID = 1L;
    private EditForm editForm;
    private Call call;

    public CallAddViewImpl() {
        super();
        editForm = new EditForm();
        this.addComponent(editForm);
    }

    @Override
    public void editItem(Call item) {
        this.call = item;
        editForm.setItemDataSource(new BeanItem<Call>(call));
    }

    private class EditForm extends AdvancedEditBeanForm<Call> {

        private static final long serialVersionUID = 1L;

        @Override
        public void setItemDataSource(Item newDataSource,
                Collection<?> propertyIds) {
            this.setFormLayoutFactory(new FormLayoutFactory());
            this.setFormFieldFactory(new EditFormFieldFactory());
            super.setItemDataSource(newDataSource, propertyIds);
        }

        private class FormLayoutFactory extends CallFormLayoutFactory {

            private static final long serialVersionUID = 1L;

            public FormLayoutFactory() {
                super("Create Call");
            }

            private Layout createButtonControls() {
                return (new EditFormControlsGenerator<Call>(EditForm.this))
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
                } else if (propertyId.equals("assignuser")) {
                    UserComboBox userBox = new UserComboBox();
                    return userBox;
                } else if (propertyId.equals("description")) {
                    TextArea descArea = new TextArea();
                    descArea.setNullRepresentation("");
                    return descArea;
                } else if (propertyId.equals("result")) {
                    TextArea resultArea = new TextArea();
                    resultArea.setNullRepresentation("");
                    return resultArea;
                } else if (propertyId.equals("durationinseconds")) {
                    CallDurationControl durationField = new CallDurationControl();
                    return durationField;
                } else if (propertyId.equals("purpose")) {
                    CallPurposeComboBox purposeField = new CallPurposeComboBox();
                    return purposeField;
                } else if (propertyId.equals("status")) {
                    CallStatusTypeField field = new CallStatusTypeField();
                    return field;
                } else if (propertyId.equals("type")) {
                    RelatedEditItemField field = new RelatedEditItemField(
                            new String[]{CrmTypeConstants.ACCOUNT, CrmTypeConstants.CAMPAIGN, CrmTypeConstants.CONTACT, CrmTypeConstants.LEAD,
                                CrmTypeConstants.OPPORTUNITY, CrmTypeConstants.CASE}, call);
                    field.setType(call.getType());
                    return field;
                } else if (propertyId.equals("startdate")) {
                    return new PopupDateField();
                }
                return null;
            }
        }
    }

    @Override
    public HasEditFormHandlers<Call> getEditFormHandlers() {
        return editForm;
    }

    private class CallPurposeComboBox extends ValueComboBox {

        private static final long serialVersionUID = 1L;

        public CallPurposeComboBox() {
            super();
            setCaption(null);
            this.loadData(new String[]{"Prospecting", "Administrative",
                        "Negotiation", "Project", "Support"});
        }
    }

    private class CallDurationControl extends CustomField {

        private static final long serialVersionUID = 1L;
        private TextField hourField;
        private ValueComboBox minutesField;

        public CallDurationControl() {
            HorizontalLayout layout = new HorizontalLayout();
            layout.setSpacing(true);
            hourField = new TextField();
            hourField.setWidth("30px");
            hourField.addListener(new Property.ValueChangeListener() {
                private static final long serialVersionUID = 1L;

                @Override
                public void valueChange(Property.ValueChangeEvent event) {
                    calculateDurationInSeconds();
                }
            });

            layout.addComponent(hourField);

            minutesField = new ValueComboBox();
            minutesField.loadData(new String[]{"0", "15", "30", "45"});
            minutesField.setWidth("40px");
            minutesField.addListener(new Property.ValueChangeListener() {
                private static final long serialVersionUID = 1L;

                @Override
                public void valueChange(Property.ValueChangeEvent event) {
                    calculateDurationInSeconds();

                }
            });

            Integer duration = call.getDurationinseconds();
            if (duration != null && duration != 0) {
                int hours = duration / 3600;
                int minutes = (duration % 3600) / 60;
                hourField.setValue("" + hours);
                minutesField.select("" + minutes);
            }

            layout.addComponent(minutesField);

            layout.addComponent(new Label("(hours/minutes)"));

            this.setCompositionRoot(layout);
        }

        @Override
        public Class<?> getType() {
            return Integer.class;
        }

        private void calculateDurationInSeconds() {
            String hourValue = (String) hourField.getValue();
            String minuteValue = (String) minutesField.getValue();
            int hourVal = 0, minutesVal = 0;
            try {
                hourVal = Integer.parseInt(hourValue);
            } catch (NumberFormatException e) {
                hourField.setValue("");
                hourVal = 0;
            }

            try {
                minutesVal = Integer.parseInt(minuteValue);
            } catch (NumberFormatException e) {
                minutesField.select(null);
                minutesVal = 0;
            }

            if (hourVal != 0 || minutesVal != 0) {
                int seconds = minutesVal * 60 + hourVal * 3600;
                call.setDurationinseconds(seconds);
            }
        }
    }

    private class CallStatusTypeField extends CustomField {

        private static final long serialVersionUID = 1L;

        public CallStatusTypeField() {
            HorizontalLayout layout = new HorizontalLayout();
            layout.setSpacing(true);

            CallTypeComboBox typeField = new CallTypeComboBox();
            layout.addComponent(typeField);
            typeField.select(call.getCalltype());

            CallStatusComboBox statusField = new CallStatusComboBox();
            layout.addComponent(statusField);
            statusField.select(call.getStatus());

            this.setCompositionRoot(layout);
        }

        @Override
        public Class<?> getType() {
            return String.class;
        }
    }

    private class CallTypeComboBox extends ValueComboBox {

        private static final long serialVersionUID = 1L;

        public CallTypeComboBox() {
            super();
            setCaption(null);
            this.setWidth("80px");
            this.loadData(new String[]{"Inbound", "Outbound"});
            this.addListener(new Property.ValueChangeListener() {
                private static final long serialVersionUID = 1L;

                @Override
                public void valueChange(
                        com.vaadin.data.Property.ValueChangeEvent event) {
                    call.setCalltype((String) CallTypeComboBox.this.getValue());
                }
            });
        }
    }

    private class CallStatusComboBox extends ValueComboBox {

        private static final long serialVersionUID = 1L;

        public CallStatusComboBox() {
            super();
            setCaption(null);
            this.setWidth("100px");
            this.loadData(new String[]{"Planned", "Held", "Not Held"});
            this.addListener(new Property.ValueChangeListener() {
                private static final long serialVersionUID = 1L;

                @Override
                public void valueChange(
                        com.vaadin.data.Property.ValueChangeEvent event) {
                    call.setStatus((String) CallStatusComboBox.this.getValue());
                }
            });
        }
    }
}
