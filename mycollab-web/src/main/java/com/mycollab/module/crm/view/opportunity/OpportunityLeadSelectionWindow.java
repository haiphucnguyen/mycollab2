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
package com.mycollab.module.crm.view.opportunity;

import com.mycollab.common.i18n.GenericI18Enum;
import com.mycollab.module.crm.domain.SimpleLead;
import com.mycollab.module.crm.domain.criteria.LeadSearchCriteria;
import com.mycollab.module.crm.fielddef.LeadTableFieldDef;
import com.mycollab.module.crm.i18n.LeadI18nEnum;
import com.mycollab.module.crm.ui.components.RelatedItemSelectionWindow;
import com.mycollab.module.crm.view.lead.LeadSearchPanel;
import com.mycollab.module.crm.view.lead.LeadTableDisplay;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.web.ui.WebThemes;
import org.vaadin.viritin.button.MButton;

import java.util.Arrays;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
// TODO
public class OpportunityLeadSelectionWindow extends RelatedItemSelectionWindow<SimpleLead, LeadSearchCriteria> {

    public OpportunityLeadSelectionWindow(OpportunityLeadListComp associateLeadList) {
        super(UserUIContext.getMessage(GenericI18Enum.ACTION_SELECT_VALUE, UserUIContext.getMessage(LeadI18nEnum.LIST)), associateLeadList);
        this.setWidth("1000px");
    }

    @Override
    protected void initUI() {
        tableItem = new LeadTableDisplay(LeadTableFieldDef.selected,
                Arrays.asList(LeadTableFieldDef.name, LeadTableFieldDef.status,
                        LeadTableFieldDef.email, LeadTableFieldDef.phoneoffice));

//        gridItem.addGeneratedColumn("leadName", (source, itemId, columnId) -> {
//            final SimpleLead lead = gridItem.getBeanByIndex(itemId);
//            return new ELabel(lead.getLeadName()).withStyleName(WebThemes.BUTTON_LINK)
//                    .withDescription(CrmTooltipGenerator.generateTooltipLead(UserUIContext.getUserLocale(), lead,
//                            AppUI.getSiteUrl(), UserUIContext.getUserTimeZone()));
//        });

        MButton selectBtn = new MButton(UserUIContext.getMessage(GenericI18Enum.BUTTON_SELECT), clickEvent -> close())
                .withStyleName(WebThemes.BUTTON_ACTION);

        LeadSearchPanel searchPanel = new LeadSearchPanel(false);
        searchPanel.addSearchHandler(criteria -> tableItem.setSearchCriteria(criteria));

        bodyContent.with(searchPanel, selectBtn, tableItem);
    }
}
