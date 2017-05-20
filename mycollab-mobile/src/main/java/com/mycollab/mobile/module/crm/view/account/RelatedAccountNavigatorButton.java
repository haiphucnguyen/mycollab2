/**
 * This file is part of mycollab-mobile.
 *
 * mycollab-mobile is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-mobile is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-mobile.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.mycollab.mobile.module.crm.view.account;

import com.mycollab.common.i18n.GenericI18Enum;
import com.mycollab.db.arguments.NumberSearchField;
import com.mycollab.module.crm.domain.criteria.AccountSearchCriteria;
import com.mycollab.module.crm.i18n.AccountI18nEnum;
import com.mycollab.module.crm.service.AccountService;
import com.mycollab.spring.AppContextUtil;
import com.mycollab.vaadin.UserUIContext;
import com.vaadin.addon.touchkit.ui.NavigationButton;

/**
 * @author MyCollab Ltd
 * @since 5.4.9
 */
public class RelatedAccountNavigatorButton extends NavigationButton {
    private AccountSearchCriteria criteria;

    public RelatedAccountNavigatorButton() {
        super(UserUIContext.getMessage(GenericI18Enum.OPT_ITEM_VALUE,
                UserUIContext.getMessage(AccountI18nEnum.SINGLE), 0));
        this.addClickListener(navigationButtonClickEvent -> {
            if (criteria != null) {
                getNavigationManager().navigateTo(new AccountListDisplayView(criteria));
            }
        });
    }

    void displayTotalAccounts(AccountSearchCriteria criteria) {
        this.criteria = criteria;
        AccountService accountService = AppContextUtil.getSpringBean(AccountService.class);
        this.setCaption(UserUIContext.getMessage(GenericI18Enum.OPT_ITEM_VALUE,
                UserUIContext.getMessage(AccountI18nEnum.SINGLE), accountService.getTotalCount(criteria)));
    }

    public void displayRelatedByCampaign(Integer campaignId) {
        AccountSearchCriteria searchCriteria = new AccountSearchCriteria();
        searchCriteria.setCampaignId(NumberSearchField.equal(campaignId));
        displayTotalAccounts(searchCriteria);
    }
}
