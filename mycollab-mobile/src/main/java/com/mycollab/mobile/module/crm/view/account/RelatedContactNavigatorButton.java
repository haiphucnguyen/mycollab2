package com.mycollab.mobile.module.crm.view.account;

import com.mycollab.common.i18n.GenericI18Enum;
import com.mycollab.db.arguments.NumberSearchField;
import com.mycollab.mobile.module.project.view.ticket.TicketListDisplayView;
import com.mycollab.module.crm.domain.criteria.ContactSearchCriteria;
import com.mycollab.module.crm.i18n.ContactI18nEnum;
import com.mycollab.module.crm.service.ContactService;
import com.mycollab.spring.AppContextUtil;
import com.mycollab.vaadin.UserUIContext;
import com.vaadin.addon.touchkit.ui.NavigationButton;

/**
 * @author MyCollab Ltd
 * @since 5.4.9
 */
class RelatedContactNavigatorButton extends NavigationButton {
    private Integer accountId;

    RelatedContactNavigatorButton() {
        super(UserUIContext.getMessage(GenericI18Enum.OPT_ITEM_VALUE,
                UserUIContext.getMessage(ContactI18nEnum.SINGLE), 0));
        this.addClickListener(navigationButtonClickEvent -> {
            if (accountId != null) {
                getNavigationManager().navigateTo(new TicketListDisplayView(accountId));
            }
        });
    }

    void displayTotalContacts(Integer accountId) {
        this.accountId = accountId;
        ContactService contactService = AppContextUtil.getSpringBean(ContactService.class);
        ContactSearchCriteria criteria = new ContactSearchCriteria();
        criteria.setAccountId(NumberSearchField.equal(accountId));
        this.setCaption(UserUIContext.getMessage(GenericI18Enum.OPT_ITEM_VALUE,
                UserUIContext.getMessage(ContactI18nEnum.SINGLE), contactService.getTotalCount(criteria)));
    }
}
