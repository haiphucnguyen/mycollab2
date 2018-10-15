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

import com.mycollab.module.crm.CrmLinkGenerator;
import com.mycollab.module.crm.CrmTypeConstants;
import com.mycollab.module.crm.domain.CrmTask;
import com.mycollab.module.crm.domain.SimpleCrmTask;
import com.mycollab.module.crm.i18n.OptionI18nEnum.TaskStatus;
import com.mycollab.module.crm.ui.CrmAssetsManager;
import com.mycollab.module.crm.ui.components.RelatedReadItemField;
import com.mycollab.vaadin.ui.AbstractBeanFieldGroupViewFieldFactory;
import com.mycollab.vaadin.ui.GenericBeanForm;
import com.mycollab.vaadin.ui.UIConstants;
import com.mycollab.vaadin.ui.field.DateTimeViewField;
import com.mycollab.vaadin.ui.field.I18nFormViewField;
import com.mycollab.vaadin.ui.field.RichTextViewField;
import com.mycollab.vaadin.web.ui.field.LinkViewField;
import com.mycollab.vaadin.web.ui.field.UserLinkViewField;
import com.vaadin.data.HasValue;

/**
 * @author MyCollab Ltd.
 * @since 3.0
 */
class AssignmentReadFormFieldFactory extends AbstractBeanFieldGroupViewFieldFactory<SimpleCrmTask> {
    private static final long serialVersionUID = 1L;

    public AssignmentReadFormFieldFactory(GenericBeanForm<SimpleCrmTask> form) {
        super(form);
    }

    @Override
    protected HasValue<?> onCreateField(Object propertyId) {
        SimpleCrmTask task = attachForm.getBean();

        if (propertyId.equals("assignuser")) {
            return new UserLinkViewField(task.getAssignuser(), task.getAssignUserAvatarId(), task.getAssignUserFullName());
        } else if (propertyId.equals("startdate")) {
            return new DateTimeViewField(task.getStartdate());
        } else if (propertyId.equals("duedate")) {
            return new DateTimeViewField(task.getDuedate());
        } else if (propertyId.equals("contactid")) {
            return new LinkViewField(task.getContactName(), CrmLinkGenerator.generateContactPreviewLink(task.getContactid()),
                    CrmAssetsManager.getAsset(CrmTypeConstants.CONTACT));
        } else if (propertyId.equals("type")) {
            return new RelatedReadItemField(task);

        } else if (propertyId.equals("description")) {
            return new RichTextViewField(task.getDescription());
        } else if (CrmTask.Field.status.equalTo(propertyId)) {
            return new I18nFormViewField(task.getStatus(), TaskStatus.class).withStyleName(UIConstants.FIELD_NOTE);
        }

        return null;
    }

}
