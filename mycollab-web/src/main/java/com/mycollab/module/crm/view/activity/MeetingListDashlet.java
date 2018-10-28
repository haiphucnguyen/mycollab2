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
import com.mycollab.db.arguments.NumberSearchField;
import com.mycollab.db.arguments.SetSearchField;
import com.mycollab.module.crm.domain.criteria.MeetingSearchCriteria;
import com.mycollab.module.crm.i18n.MeetingI18nEnum;
import com.mycollab.vaadin.AppUI;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.web.ui.Depot;
import com.mycollab.vaadin.web.ui.WebThemes;
import com.mycollab.vaadin.web.ui.WebUIConstants;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.VerticalLayout;
import org.vaadin.viritin.button.MButton;

import java.util.Arrays;

/**
 * @author MyCollab Ltd.
 * @since 1.0.0
 */
public class MeetingListDashlet extends Depot {

    private MeetingTableDisplay tableItem;

    public MeetingListDashlet() {
        super(UserUIContext.getMessage(MeetingI18nEnum.MY_ITEMS), new VerticalLayout());
        this.setMargin(new MarginInfo(true, false, false, false));

        tableItem = new MeetingTableDisplay(Arrays.asList(
                new GridFieldMeta(MeetingI18nEnum.FORM_SUBJECT, "subject", WebUIConstants.TABLE_X_LABEL_WIDTH),
                new GridFieldMeta(GenericI18Enum.FORM_START_DATE, "startdate", WebUIConstants.TABLE_DATE_TIME_WIDTH),
                new GridFieldMeta(GenericI18Enum.FORM_STATUS, "status", WebUIConstants.TABLE_S_LABEL_WIDTH)));

        bodyContent.addComponent(tableItem);

        MButton customizeViewBtn = new MButton("", clickEvent -> {
        }).withIcon(VaadinIcons.ADJUST).withStyleName(WebThemes.BUTTON_SMALL_PADDING)
                .withDescription(UserUIContext.getMessage(GenericI18Enum.OPT_LAYOUT_OPTIONS));

        this.addHeaderElement(customizeViewBtn);
    }

    public void display() {
        final MeetingSearchCriteria criteria = new MeetingSearchCriteria();
        criteria.setSaccountid(new NumberSearchField(AppUI.getAccountId()));
        criteria.setAssignUsers(new SetSearchField<>(UserUIContext.getUsername()));
        tableItem.setSearchCriteria(criteria);
    }
}
