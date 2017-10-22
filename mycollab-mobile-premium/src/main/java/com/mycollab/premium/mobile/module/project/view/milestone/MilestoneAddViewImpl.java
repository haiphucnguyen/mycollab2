package com.mycollab.premium.mobile.module.project.view.milestone;

import com.mycollab.mobile.form.view.DynaFormLayout;
import com.mycollab.mobile.module.project.view.milestone.MilestoneAddView;
import com.mycollab.mobile.module.project.view.milestone.MilestoneDefaultFormLayoutFactory;
import com.mycollab.mobile.module.project.view.settings.ProjectMemberListSelect;
import com.mycollab.mobile.ui.AbstractEditItemComp;
import com.mycollab.mobile.ui.I18NValueListSelect;
import com.mycollab.module.project.ProjectTypeConstants;
import com.mycollab.module.project.domain.Milestone;
import com.mycollab.module.project.domain.SimpleMilestone;
import com.mycollab.module.project.i18n.MilestoneI18nEnum;
import com.mycollab.module.project.i18n.OptionI18nEnum;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.mvp.ViewComponent;
import com.mycollab.vaadin.ui.AbstractBeanFieldGroupEditFieldFactory;
import com.mycollab.vaadin.ui.GenericBeanForm;
import com.mycollab.vaadin.ui.IFormLayoutFactory;
import com.vaadin.addon.touchkit.ui.DatePicker;
import com.vaadin.ui.Field;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;

import java.util.Arrays;

/**
 * @author MyCollab Ltd.
 * @since 4.5.2
 */
@ViewComponent
public class MilestoneAddViewImpl extends AbstractEditItemComp<SimpleMilestone> implements MilestoneAddView {
    private static final long serialVersionUID = 5003180627691878220L;

    @Override
    protected String initFormTitle() {
        return (beanItem.getId() == null) ? UserUIContext.getMessage(MilestoneI18nEnum.NEW) : beanItem.getName();
    }

    @Override
    protected IFormLayoutFactory initFormLayoutFactory() {
        return new DynaFormLayout(ProjectTypeConstants.MILESTONE, MilestoneDefaultFormLayoutFactory.getForm());
    }

    @Override
    protected AbstractBeanFieldGroupEditFieldFactory<SimpleMilestone> initBeanFormFieldFactory() {
        return new EditFormFieldFactory<>(this.editForm);
    }

    class EditFormFieldFactory<B extends Milestone> extends AbstractBeanFieldGroupEditFieldFactory<B> {
        private static final long serialVersionUID = 1L;

        EditFormFieldFactory(GenericBeanForm<B> form) {
            super(form);
        }

        @Override
        protected Field<?> onCreateField(Object propertyId) {
            if (Milestone.Field.assignuser.equalTo(propertyId)) {
                final ProjectMemberListSelect userbox = new ProjectMemberListSelect(beanItem.getProjectid());
                userbox.setRequired(true);
                userbox.setRequiredError("Please select an assignee");
                return userbox;
            } else if (Milestone.Field.status.equalTo(propertyId)) {
                if (attachForm.getBean().getStatus() == null) {
                    attachForm.getBean().setStatus(OptionI18nEnum.MilestoneStatus.InProgress.toString());
                }
                return new ProgressStatusListSelect();
            } else if (Milestone.Field.name.equalTo(propertyId)) {
                final TextField tf = new TextField();
                if (isValidateForm) {
                    tf.setNullRepresentation("");
                    tf.setRequired(true);
                    tf.setRequiredError("Please enter name");
                }
                return tf;
            } else if (Milestone.Field.startdate.equalTo(propertyId) || Milestone.Field.enddate.equalTo(propertyId)) {
                return new DatePicker();
            } else if (Milestone.Field.description.equalTo(propertyId)) {
                final TextArea descArea = new TextArea();
                descArea.setNullRepresentation("");
                return descArea;
            }

            return null;
        }
    }

    private static class ProgressStatusListSelect extends I18NValueListSelect {
        private static final long serialVersionUID = 1L;

        private ProgressStatusListSelect() {
            setCaption(null);
            this.setNullSelectionAllowed(false);
            this.loadData(Arrays.asList(OptionI18nEnum.MilestoneStatus.InProgress, OptionI18nEnum.MilestoneStatus.Future, OptionI18nEnum.MilestoneStatus.Closed));
        }
    }
}
