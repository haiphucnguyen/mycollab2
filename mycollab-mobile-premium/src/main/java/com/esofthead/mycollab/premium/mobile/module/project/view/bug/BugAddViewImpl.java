package com.esofthead.mycollab.premium.mobile.module.project.view.bug;

import com.esofthead.mycollab.common.i18n.GenericI18Enum;
import com.esofthead.mycollab.mobile.module.project.ui.form.field.ProjectFormAttachmentUploadField;
import com.esofthead.mycollab.mobile.module.project.view.bug.BugAddView;
import com.esofthead.mycollab.mobile.module.project.view.bug.BugPriorityComboBox;
import com.esofthead.mycollab.mobile.module.project.view.bug.BugSeverityComboBox;
import com.esofthead.mycollab.mobile.module.project.view.milestone.MilestoneComboBox;
import com.esofthead.mycollab.mobile.module.project.view.settings.ProjectMemberSelectionField;
import com.esofthead.mycollab.mobile.ui.AbstractEditItemComp;
import com.esofthead.mycollab.mobile.ui.FormSectionBuilder;
import com.esofthead.mycollab.mobile.ui.grid.GridFormLayoutHelper;
import com.esofthead.mycollab.module.project.ProjectTypeConstants;
import com.esofthead.mycollab.module.project.i18n.BugI18nEnum;
import com.esofthead.mycollab.module.project.i18n.OptionI18nEnum.BugPriority;
import com.esofthead.mycollab.module.project.i18n.OptionI18nEnum.BugSeverity;
import com.esofthead.mycollab.module.tracker.domain.SimpleBug;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.esofthead.mycollab.vaadin.ui.AbstractBeanFieldGroupEditFieldFactory;
import com.esofthead.mycollab.vaadin.ui.GenericBeanForm;
import com.esofthead.mycollab.vaadin.ui.IFormLayoutFactory;
import com.vaadin.addon.touchkit.ui.DatePicker;
import com.vaadin.addon.touchkit.ui.NumberField;
import com.vaadin.data.Property;
import com.vaadin.ui.*;

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
        return beanItem.getId() == null ? AppContext.getMessage(BugI18nEnum.FORM_NEW_BUG_TITLE) : beanItem.getSummary();
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

        public EditFormFieldFactory(GenericBeanForm<SimpleBug> form) {
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
                    beanItem.setPriority(BugPriority.Major.name());
                }
                return new BugPriorityComboBox();
            } else if (propertyId.equals("assignuser")) {
                return new ProjectMemberSelectionField();
            } else if (propertyId.equals("severity")) {
                if (beanItem.getSeverity() == null) {
                    beanItem.setSeverity(BugSeverity.Major.name());
                }
                return new BugSeverityComboBox();
            } else if (propertyId.equals("summary")) {
                final TextField tf = new TextField();
                if (isValidateForm) {
                    tf.setNullRepresentation("");
                    tf.setRequired(true);
                    tf.setRequiredError("Bug summary must be not null");
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

    public class EditFormLayoutFactory implements IFormLayoutFactory {
        private static final long serialVersionUID = -9159483523170247666L;

        private GridFormLayoutHelper informationLayout;

        @Override
        public ComponentContainer getLayout() {
            final VerticalLayout layout = new VerticalLayout();
            layout.setMargin(false);
            layout.addComponent(FormSectionBuilder.build(AppContext.getMessage(BugI18nEnum.M_FORM_READ_TITLE)));

            informationLayout = GridFormLayoutHelper.defaultFormLayoutHelper(1, 12);
            layout.addComponent(informationLayout.getLayout());
            layout.setComponentAlignment(informationLayout.getLayout(), Alignment.BOTTOM_CENTER);
            layout.addComponent(attachmentUploadField);
            return layout;
        }

        @Override
        public void attachField(Object propertyId, Field<?> field) {
            if (propertyId.equals("summary")) {
                informationLayout.addComponent(field, AppContext.getMessage(BugI18nEnum.FORM_SUMMARY), 0, 0);
            } else if (propertyId.equals("milestoneid")) {
                informationLayout.addComponent(field, AppContext.getMessage(BugI18nEnum.FORM_PHASE), 0, 1);
            } else if (propertyId.equals("environment")) {
                informationLayout.addComponent(field, AppContext.getMessage(BugI18nEnum.FORM_ENVIRONMENT), 0, 2);
            } else if (propertyId.equals("priority")) {
                informationLayout.addComponent(field, AppContext.getMessage(BugI18nEnum.FORM_PRIORITY), 0, 3);
            } else if (propertyId.equals("severity")) {
                informationLayout.addComponent(field, AppContext.getMessage(BugI18nEnum.FORM_SEVERITY), 0, 4);
            } else if (propertyId.equals("startdate")) {
                informationLayout.addComponent(field, AppContext.getMessage(BugI18nEnum.FORM_START_DATE), 0, 5);
            } else if (propertyId.equals("enddate")) {
                informationLayout.addComponent(field, AppContext.getMessage(BugI18nEnum.FORM_END_DATE), 0, 6);
            } else if (propertyId.equals("duedate")) {
                informationLayout.addComponent(field, AppContext.getMessage(BugI18nEnum.FORM_DUE_DATE), 0, 7);
            } else if (propertyId.equals("estimatetime")) {
                informationLayout.addComponent(field, AppContext.getMessage(BugI18nEnum.FORM_ORIGINAL_ESTIMATE), 0, 8);
            } else if (propertyId.equals("estimateremaintime")) {
                informationLayout.addComponent(field, AppContext.getMessage(BugI18nEnum.FORM_REMAIN_ESTIMATE), 0, 9);
            } else if (propertyId.equals("assignuser")) {
                informationLayout.addComponent(field, AppContext.getMessage(GenericI18Enum.FORM_ASSIGNEE), 0, 10);
            } else if (propertyId.equals("description")) {
                informationLayout.addComponent(field, AppContext.getMessage(GenericI18Enum.FORM_DESCRIPTION), 0, 11);
            }
        }

    }

    @Override
    public ProjectFormAttachmentUploadField getAttachUploadField() {
        return attachmentUploadField;
    }
}