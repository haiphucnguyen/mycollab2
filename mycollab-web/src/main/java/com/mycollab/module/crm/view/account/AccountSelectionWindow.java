package com.mycollab.module.crm.view.account;

import com.mycollab.common.i18n.GenericI18Enum;
import com.mycollab.module.crm.CrmTooltipGenerator;
import com.mycollab.module.crm.domain.Account;
import com.mycollab.module.crm.domain.SimpleAccount;
import com.mycollab.module.crm.domain.criteria.AccountSearchCriteria;
import com.mycollab.module.crm.fielddef.AccountTableFieldDef;
import com.mycollab.module.crm.i18n.AccountI18nEnum;
import com.mycollab.vaadin.AppUI;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.ui.FieldSelection;
import com.mycollab.vaadin.web.ui.WebThemes;
import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.layouts.MVerticalLayout;
import org.vaadin.viritin.layouts.MWindow;

import java.util.Arrays;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
public class AccountSelectionWindow extends MWindow {
    private static final long serialVersionUID = 1L;

    private AccountTableDisplay tableItem;
    private FieldSelection<Account> fieldSelection;

    public AccountSelectionWindow(FieldSelection<Account> fieldSelection) {
        super(UserUIContext.getMessage(GenericI18Enum.ACTION_SELECT_VALUE, UserUIContext.getMessage(AccountI18nEnum.SINGLE)));
        this.fieldSelection = fieldSelection;
        this.withModal(true).withResizable(false).withWidth("900px").withCenter();
    }

    public void show() {
        createAccountList();

        AccountSearchPanel searchPanel = new AccountSearchPanel(false);
        searchPanel.addSearchHandler(criteria -> tableItem.setSearchCriteria(criteria));
        this.setContent(new MVerticalLayout(searchPanel, tableItem));
        tableItem.setSearchCriteria(new AccountSearchCriteria());
    }

    private void createAccountList() {
        tableItem = new AccountTableDisplay(Arrays.asList(AccountTableFieldDef.accountname(), AccountTableFieldDef.city(),
                AccountTableFieldDef.assignUser()));

        tableItem.setWidth("100%");
        tableItem.setDisplayNumItems(10);

        tableItem.addGeneratedColumn("accountname", (source, itemId, columnId) -> {
            final SimpleAccount account = tableItem.getBeanByIndex(itemId);

            return new MButton(account.getAccountname(), clickEvent -> {
                fieldSelection.fireValueChange(account);
                close();
            }).withStyleName(WebThemes.BUTTON_LINK).withDescription(CrmTooltipGenerator.INSTANCE.generateToolTipAccount(
                    UserUIContext.getUserLocale(), account, AppUI.Companion.getSiteUrl()));
        });
    }
}
