/**
 * Copyright © MyCollab
 * <p>
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * <p>
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.mycollab.module.crm.view.activity;

import com.mycollab.module.crm.CrmDataTypeFactory;
import com.mycollab.module.crm.CrmTypeConstants;
import com.mycollab.module.crm.domain.CrmTask;
import com.mycollab.module.crm.i18n.OptionI18nEnum.TaskPriority;
import com.mycollab.module.crm.i18n.OptionI18nEnum.TaskStatus;
import com.mycollab.module.crm.i18n.TaskI18nEnum;
import com.mycollab.module.crm.ui.CrmAssetsManager;
import com.mycollab.module.crm.ui.components.AbstractEditItemComp;
import com.mycollab.module.crm.ui.components.RelatedEditItemField;
import com.mycollab.module.crm.view.contact.ContactSelectionField;
import com.mycollab.module.user.ui.components.ActiveUserComboBox;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.mvp.ViewComponent;
import com.mycollab.vaadin.ui.*;
import com.mycollab.vaadin.web.ui.DefaultDynaFormLayout;
import com.mycollab.vaadin.web.ui.I18nValueComboBox;
import com.mycollab.vaadin.web.ui.field.DateTimeOptionField;
import com.vaadin.data.HasValue;
import com.vaadin.server.Resource;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.RichTextArea;
import com.vaadin.ui.TextField;

import static com.mycollab.vaadin.web.ui.utils.FormControlsGenerator.generateEditFormControls;

/**
 * @author MyCollab Ltd.
 * @since 2.0
 */
// TODO
@ViewComponent
public class AssignmentAddViewImpl extends AbstractEditItemComp<CrmTask> implements AssignmentAddView {
    private static final long serialVersionUID = 1L;

    @Override
    protected String initFormTitle() {
        return (beanItem.getId() == null) ? UserUIContext.getMessage(TaskI18nEnum.NEW) : beanItem.getSubject();
    }

    @Override
    protected Resource initFormIconResource() {
        return CrmAssetsManager.getAsset(CrmTypeConstants.TASK);
    }

    @Override
    protected ComponentContainer createButtonControls() {
        return generateEditFormControls(editForm);
    }

    @Override
    protected AdvancedEditBeanForm<CrmTask> initPreviewForm() {
        return new AdvancedEditBeanForm<>();
    }

    @Override
    protected IFormLayoutFactory initFormLayoutFactory() {
        return new DefaultDynaFormLayout(CrmTypeConstants.TASK, AssignmentDefaultFormLayoutFactory.getForm());
    }

    @Override
    protected AbstractBeanFieldGroupEditFieldFactory<CrmTask> initBeanFormFieldFactory() {
        return new AssignmentEditFormFieldFactory(editForm);
    }

    private static class AssignmentEditFormFieldFactory extends AbstractBeanFieldGroupEditFieldFactory<CrmTask> {
        private static final long serialVersionUID = 1L;

        AssignmentEditFormFieldFactory(GenericBeanForm<CrmTask> form) {
            super(form);
        }

        @Override
        protected HasValue<?> onCreateField(Object propertyId) {
            if (CrmTask.Field.startdate.equalTo(propertyId)) {
                return new DateTimeOptionField();
            } else if (CrmTask.Field.duedate.equalTo(propertyId)) {
                return new DateTimeOptionField();
            } else if (CrmTask.Field.status.equalTo(propertyId)) {
                return new TaskStatusComboBox();
            } else if (CrmTask.Field.priority.equalTo(propertyId)) {
                return new TaskPriorityComboBox();
            } else if (CrmTask.Field.description.equalTo(propertyId)) {
                return new RichTextArea();
            } else if (CrmTask.Field.contactid.equalTo(propertyId)) {
                return new ContactSelectionField();
            } else if (CrmTask.Field.subject.equalTo(propertyId)) {
                TextField tf = new TextField();
//                if (isValidateForm) {
//                    tf.setRequired(true);
//                    tf.setRequiredError("Subject must not be null");
//                    tf.setNullRepresentation("");
//                }
                return tf;
            } else if (CrmTask.Field.type.equalTo(propertyId)) {
                return new RelatedEditItemField(attachForm.getBean());
            } else if (CrmTask.Field.typeid.equalTo(propertyId)) {
                return new DummyCustomField<Integer>();
            } else if (CrmTask.Field.assignuser.equalTo(propertyId)) {
                ActiveUserComboBox userBox = new ActiveUserComboBox();
//                userBox.select(attachForm.getBean().getAssignuser());
                return userBox;
            }
            return null;
        }
    }

    private static class TaskPriorityComboBox extends I18nValueComboBox {
        private static final long serialVersionUID = 1L;

        TaskPriorityComboBox() {
            super(TaskPriority.class, CrmDataTypeFactory.taskPriorities);
            setCaption(null);
        }
    }

    private static class TaskStatusComboBox extends I18nValueComboBox {
        private static final long serialVersionUID = 1L;

        TaskStatusComboBox() {
            super(TaskStatus.class, CrmDataTypeFactory.taskStatuses);
            setCaption(null);
        }
    }
}
