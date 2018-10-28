/**
 * Copyright © MyCollab
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.mycollab.module.crm.view.activity;

import com.mycollab.common.GridFieldMeta;
import com.mycollab.common.i18n.GenericI18Enum;
import com.mycollab.module.crm.domain.SimpleActivity;
import com.mycollab.module.crm.domain.criteria.ActivitySearchCriteria;
import com.mycollab.module.crm.i18n.ActivityI18nEnum;
import com.mycollab.module.crm.i18n.CallI18nEnum;
import com.mycollab.module.crm.i18n.MeetingI18nEnum;
import com.mycollab.module.crm.i18n.TaskI18nEnum;
import com.mycollab.module.crm.ui.components.RelatedListComp;
import com.mycollab.security.RolePermissionCollections;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.web.ui.WebThemes;
import com.mycollab.vaadin.web.ui.WebUIConstants;
import com.vaadin.icons.VaadinIcons;
import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.layouts.MHorizontalLayout;

import java.util.Arrays;

/**
 * @author MyCollab Ltd.
 * @since 2.0
 */
public class ActivityRelatedItemListComp extends RelatedListComp<SimpleActivity, ActivitySearchCriteria> {
    private static final long serialVersionUID = 1L;

    private final boolean allowCreateNew;

    public ActivityRelatedItemListComp(final boolean allowCreateNew) {
        this.allowCreateNew = allowCreateNew;
        initUI();
    }

    private void initUI() {
        if (allowCreateNew) {
            final MButton newTaskBtn = new MButton(UserUIContext.getMessage(TaskI18nEnum.NEW), clickEvent -> fireNewRelatedItem("task"))
                    .withIcon(VaadinIcons.PLUS).withStyleName(WebThemes.BUTTON_ACTION)
                    .withVisible(UserUIContext.canWrite(RolePermissionCollections.CRM_TASK));

            final MButton newCallBtn = new MButton(UserUIContext.getMessage(CallI18nEnum.NEW), clickEvent -> fireNewRelatedItem("call"))
                    .withIcon(VaadinIcons.PLUS).withStyleName(WebThemes.BUTTON_ACTION)
                    .withVisible(UserUIContext.canWrite(RolePermissionCollections.CRM_CALL));

            final MButton newMeetingBtn = new MButton(UserUIContext.getMessage(MeetingI18nEnum.NEW), clickEvent -> fireNewRelatedItem("meeting"))
                    .withIcon(VaadinIcons.PLUS).withStyleName(WebThemes.BUTTON_ACTION)
                    .withVisible(UserUIContext.canWrite(RolePermissionCollections.CRM_MEETING));

            this.addComponent(new MHorizontalLayout(newTaskBtn, newCallBtn, newMeetingBtn));
            this.addStyleName("activity-related-content");
        }

        tableItem = new ActivityTableDisplay(Arrays.asList(
                new GridFieldMeta(ActivityI18nEnum.FORM_SUBJECT, "subject", WebUIConstants.TABLE_EX_LABEL_WIDTH),
                new GridFieldMeta(GenericI18Enum.FORM_STATUS, "status", WebUIConstants.TABLE_S_LABEL_WIDTH),
                new GridFieldMeta(GenericI18Enum.FORM_START_DATE, "startDate", WebUIConstants.TABLE_DATE_TIME_WIDTH),
                new GridFieldMeta(GenericI18Enum.FORM_END_DATE, "endDate", WebUIConstants.TABLE_DATE_TIME_WIDTH)));

        this.addComponent(tableItem);
    }

    @Override
    public void refresh() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
