package com.mycollab.premium.mobile.module.project.view.bug;

import com.mycollab.common.i18n.ErrorI18nEnum;
import com.mycollab.common.i18n.GenericI18Enum;
import com.mycollab.mobile.module.project.ui.PriorityComboBox;
import com.mycollab.mobile.module.project.ui.form.field.ProjectFormAttachmentUploadField;
import com.mycollab.mobile.module.project.view.bug.BugAddView;
import com.mycollab.mobile.module.project.view.bug.BugSeverityComboBox;
import com.mycollab.mobile.module.project.view.milestone.MilestoneComboBox;
import com.mycollab.mobile.module.project.view.settings.ProjectMemberSelectionField;
import com.mycollab.mobile.ui.AbstractEditItemComp;
import com.mycollab.mobile.ui.FormSectionBuilder;
import com.mycollab.mobile.ui.grid.GridFormLayoutHelper;
import com.mycollab.module.project.ProjectTypeConstants;
import com.mycollab.module.project.i18n.BugI18nEnum;
import com.mycollab.module.project.i18n.OptionI18nEnum;
import com.mycollab.module.project.i18n.OptionI18nEnum.BugSeverity;
import com.mycollab.module.tracker.domain.SimpleBug;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.mvp.ViewComponent;
import com.mycollab.vaadin.ui.AbstractBeanFieldGroupEditFieldFactory;
import com.mycollab.vaadin.ui.AbstractFormLayoutFactory;
import com.mycollab.vaadin.ui.GenericBeanForm;
import com.mycollab.vaadin.ui.IFormLayoutFactory;
import com.vaadin.addon.touchkit.ui.DatePicker;
import com.vaadin.addon.touchkit.ui.NumberField;
import com.vaadin.data.Property;
import com.vaadin.ui.*;
import org.vaadin.viritin.fields.MTextField;

/**
 * @author MyCollab Ltd.
 * @since 4.5.2
 */
@ViewComponent
public class BugAddViewImpl extends AbstractEditItemComp<SimpleBug> implements BugAddView {
    private static final long serialVersionUID = -688386159095055595L;

    private ProjectFormAttachmentUploadField attachmentUploadField;

    @Override
    protected String initFormTitle() {
        return beanItem.getId() == null ? UserUIContext.getMessage(BugI18nEnum.NEW) : beanItem.getName();
    }

    @Override
    public void editItem(SimpleBug item) {
        attachmentUploadField = new ProjectFormAttachmentUploadField();
        if (item.getId() != null) {
            attachmentUploadField.getAttachments(item.getProjectid(), ProjectTypeConstants.BUG, item.getId());
        }
        super.editItem(item);
    }

    @Override
    protected IFormLayoutFactory initFormLayoutFactory() {
        return new EditFormLayoutFactory();
    }

    @Override
    protected AbstractBeanFieldGroupEditFieldFactory<SimpleBug> initBeanFormFieldFactory() {
        return new EditFormFieldFactory(this.editForm);
    }

    private class EditFormFieldFactory extends AbstractBeanFieldGroupEditFieldFactory<SimpleBug> {
        private static final long serialVersionUID = 1L;

        EditFormFieldFactory(GenericBeanForm<SimpleBug> form) {
            super(form);
        }

        @Override
        protected Field<?> onCreateField(final Object propertyId) {
            if (propertyId.equals("environment")) {
                final TextArea field = new TextArea("", "");
                field.setNullRepresentation("");
                return field;
            } else if (propertyId.equals("description")) {
                final TextArea field = new TextArea("", "");
                field.setNullRepresentation("");
                return field;
            } else if (propertyId.equals("duedate") || propertyId.equals("startdate") || propertyId.equals("enddate")) {
                return new DatePicker();
            } else if (propertyId.equals("priority")) {
                if (beanItem.getPriority() == null) {
                    beanItem.setPriority(OptionI18nEnum.Priority.Medium.name());
                }
                return new PriorityComboBox();
            } else if (propertyId.equals("assignuser")) {
                return new ProjectMemberSelectionField();
            } else if (propertyId.equals("severity")) {
                if (beanItem.getSeverity() == null) {
                    beanItem.setSeverity(BugSeverity.Major.name());
                }
                return new BugSeverityComboBox();
            } else if (propertyId.equals("name")) {
                final MTextField tf = new MTextField();
                if (isValidateForm) {
                    tf.withNullRepresentation("").withRequired(true).withRequiredError(UserUIContext.getMessage
                            (ErrorI18nEnum.FIELD_MUST_NOT_NULL, UserUIContext.getMessage(BugI18nEnum.FORM_SUMMARY)));
                }

                return tf;
            } else if (propertyId.equals("milestoneid")) {
                final MilestoneComboBox milestoneBox = new MilestoneComboBox();
                milestoneBox.addValueChangeListener(new Property.ValueChangeListener() {
                    private static final long serialVersionUID = 1L;

                    @Override
                    public void valueChange(Property.ValueChangeEvent event) {
                        String milestoneName = milestoneBox.getItemCaption(milestoneBox.getValue());
                        beanItem.setMilestoneName(milestoneName);
                    }
                });
                return milestoneBox;
            } else if (propertyId.equals("estimatetime") || (propertyId.equals("estimateremaintime"))) {
                return new NumberField();
            }

            return null;
        }
    }

    class EditFormLayoutFactory extends AbstractFormLayoutFactory {
        private static final long serialVersionUID = -9159483523170247666L;

        private GridFormLayoutHelper informationLayout;

        @Override
        public ComponentContainer getLayout() {
            final VerticalLayout layout = new VerticalLayout();
            layout.setMargin(false);
            layout.addComponent(FormSectionBuilder.build(UserUIContext.getMessage(BugI18nEnum.SINGLE)));

            informationLayout = GridFormLayoutHelper.defaultFormLayoutHelper(1, 12);
            layout.addComponent(informationLayout.getLayout());
            layout.setComponentAlignment(informationLayout.getLayout(), Alignment.BOTTOM_CENTER);
            layout.addComponent(attachmentUploadField);
            return layout;
        }

        @Override
        protected Component onAttachField(Object propertyId, Field<?> field) {
            if (propertyId.equals("name")) {
                return informationLayout.addComponent(field, UserUIContext.getMessage(BugI18nEnum.FORM_SUMMARY), 0, 0);
            } else if (propertyId.equals("milestoneid")) {
                return informationLayout.addComponent(field, UserUIContext.getMessage(BugI18nEnum.FORM_PHASE), 0, 1);
            } else if (propertyId.equals("environment")) {
                return informationLayout.addComponent(field, UserUIContext.getMessage(BugI18nEnum.FORM_ENVIRONMENT), 0, 2);
            } else if (propertyId.equals("priority")) {
                return informationLayout.addComponent(field, UserUIContext.getMessage(GenericI18Enum.FORM_PRIORITY), 0, 3);
            } else if (propertyId.equals("severity")) {
                return informationLayout.addComponent(field, UserUIContext.getMessage(BugI18nEnum.FORM_SEVERITY), 0, 4);
            } else if (propertyId.equals("startdate")) {
                return informationLayout.addComponent(field, UserUIContext.getMessage(GenericI18Enum.FORM_START_DATE), 0, 5);
            } else if (propertyId.equals("enddate")) {
                return informationLayout.addComponent(field, UserUIContext.getMessage(GenericI18Enum.FORM_END_DATE), 0, 6);
            } else if (propertyId.equals("duedate")) {
                return informationLayout.addComponent(field, UserUIContext.getMessage(GenericI18Enum.FORM_DUE_DATE), 0, 7);
            } else if (propertyId.equals("estimatetime")) {
                return informationLayout.addComponent(field, UserUIContext.getMessage(BugI18nEnum.FORM_ORIGINAL_ESTIMATE), 0, 8);
            } else if (propertyId.equals("estimateremaintime")) {
                return informationLayout.addComponent(field, UserUIContext.getMessage(BugI18nEnum.FORM_REMAIN_ESTIMATE), 0, 9);
            } else if (propertyId.equals("assignuser")) {
                return informationLayout.addComponent(field, UserUIContext.getMessage(GenericI18Enum.FORM_ASSIGNEE), 0, 10);
            } else if (propertyId.equals("description")) {
                return informationLayout.addComponent(field, UserUIContext.getMessage(GenericI18Enum.FORM_DESCRIPTION), 0, 11);
            }
            return null;
        }

    }

    @Override
    public ProjectFormAttachmentUploadField getAttachUploadField() {
        return attachmentUploadField;
    }
}
