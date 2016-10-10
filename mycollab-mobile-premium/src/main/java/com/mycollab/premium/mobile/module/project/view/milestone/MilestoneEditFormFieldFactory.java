package com.mycollab.premium.mobile.module.project.view.milestone;

import com.mycollab.mobile.module.project.view.settings.ProjectMemberListSelect;
import com.mycollab.mobile.ui.I18NValueListSelect;
import com.mycollab.module.project.domain.Milestone;
import com.mycollab.module.project.i18n.OptionI18nEnum.MilestoneStatus;
import com.mycollab.vaadin.ui.AbstractBeanFieldGroupEditFieldFactory;
import com.mycollab.vaadin.ui.GenericBeanForm;
import com.vaadin.addon.touchkit.ui.DatePicker;
import com.vaadin.ui.Field;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;

import java.util.Arrays;

/**
 * @param <B>
 * @author MyCollab Ltd.
 * @since 3.0
 */
public class MilestoneEditFormFieldFactory<B extends Milestone> extends AbstractBeanFieldGroupEditFieldFactory<B> {
    private static final long serialVersionUID = 1L;

    MilestoneEditFormFieldFactory(GenericBeanForm<B> form) {
        super(form);
    }

    @Override
    protected Field<?> onCreateField(Object propertyId) {
        if (Milestone.Field.assignuser.equalTo(propertyId)) {
            final ProjectMemberListSelect userbox = new ProjectMemberListSelect();
            userbox.setRequired(true);
            userbox.setRequiredError("Please select an assignee");
            return userbox;
        } else if (Milestone.Field.status.equalTo(propertyId)) {
            if (attachForm.getBean().getStatus() == null) {
                attachForm.getBean().setStatus(MilestoneStatus.InProgress.toString());
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

    private static class ProgressStatusListSelect extends I18NValueListSelect {
        private static final long serialVersionUID = 1L;

        private ProgressStatusListSelect() {
            super();
            setCaption(null);
            this.setNullSelectionAllowed(false);
            this.loadData(Arrays.asList(MilestoneStatus.InProgress, MilestoneStatus.Future, MilestoneStatus.Closed));
        }
    }

}
