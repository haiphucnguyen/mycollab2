/**
 * mycollab-web - Parent pom providing dependency and plugin management for applications
		built with Maven
 * Copyright © 2017 MyCollab (support@mycollab.com)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
/**
 * mycollab-web - Parent pom providing dependency and plugin management for applications
		built with Maven
 * Copyright © ${project.inceptionYear} MyCollab (support@mycollab.com)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package com.mycollab.module.crm.view.campaign;

import com.mycollab.common.i18n.GenericI18Enum;
import com.mycollab.module.crm.CrmTooltipGenerator;
import com.mycollab.module.crm.domain.SimpleContact;
import com.mycollab.module.crm.domain.criteria.ContactSearchCriteria;
import com.mycollab.module.crm.fielddef.ContactTableFieldDef;
import com.mycollab.module.crm.i18n.ContactI18nEnum;
import com.mycollab.module.crm.ui.components.RelatedItemSelectionWindow;
import com.mycollab.module.crm.view.contact.ContactSearchPanel;
import com.mycollab.module.crm.view.contact.ContactTableDisplay;
import com.mycollab.vaadin.AppUI;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.ui.ELabel;
import com.mycollab.vaadin.web.ui.WebThemes;
import org.vaadin.viritin.button.MButton;

import java.util.Arrays;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
public class CampaignContactSelectionWindow extends RelatedItemSelectionWindow<SimpleContact, ContactSearchCriteria> {

    public CampaignContactSelectionWindow(CampaignContactListComp associateContactList) {
        super(UserUIContext.getMessage(GenericI18Enum.ACTION_SELECT_VALUE, UserUIContext.getMessage(ContactI18nEnum.LIST)),
                associateContactList);
        this.setWidth("1000px");
    }

    @Override
    protected void initUI() {
        tableItem = new ContactTableDisplay(ContactTableFieldDef.selected,
                Arrays.asList(ContactTableFieldDef.name, ContactTableFieldDef.email,
                        ContactTableFieldDef.phoneOffice, ContactTableFieldDef.account));

        tableItem.addGeneratedColumn("contactName", (source, itemId, columnId) -> {
            final SimpleContact contact = tableItem.getBeanByIndex(itemId);

            return new ELabel(contact.getContactName()).withStyleName(WebThemes.BUTTON_LINK)
                    .withDescription(CrmTooltipGenerator.generateToolTipContact(UserUIContext.getUserLocale(), AppUI.getDateFormat(),
                            contact, AppUI.getSiteUrl(), UserUIContext.getUserTimeZone()));
        });

        MButton selectBtn = new MButton(UserUIContext.getMessage(GenericI18Enum.BUTTON_SELECT), clickEvent -> close())
                .withStyleName(WebThemes.BUTTON_ACTION);

        ContactSearchPanel contactSimpleSearchPanel = new ContactSearchPanel();
        contactSimpleSearchPanel.addSearchHandler(criteria -> tableItem.setSearchCriteria(criteria));

        bodyContent.with(contactSimpleSearchPanel, selectBtn, tableItem);
    }
}
