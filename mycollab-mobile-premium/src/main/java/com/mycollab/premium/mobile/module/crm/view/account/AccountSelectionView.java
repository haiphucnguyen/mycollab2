package com.mycollab.premium.mobile.module.crm.view.account;

import com.mycollab.db.arguments.NumberSearchField;
import com.mycollab.mobile.module.crm.view.account.AccountListDisplay;
import com.mycollab.mobile.ui.AbstractSelectionView;
import com.mycollab.module.crm.domain.Account;
import com.mycollab.module.crm.domain.SimpleAccount;
import com.mycollab.module.crm.domain.criteria.AccountSearchCriteria;
import com.mycollab.module.crm.i18n.AccountI18nEnum;
import com.mycollab.vaadin.MyCollabUI;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.ui.IBeanList;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;

/**
 * @author MyCollab Ltd.
 * @since 4.0
 */
public class AccountSelectionView extends AbstractSelectionView<Account> {
    private static final long serialVersionUID = 1L;

    private AccountListDisplay itemList;

    private AccountRowDisplayHandler rowHandler = new AccountRowDisplayHandler();

    public AccountSelectionView() {
        createUI();
        this.setCaption(UserUIContext.getMessage(AccountI18nEnum.M_VIEW_ACCOUNT_NAME_LOOKUP));
    }

    private void createUI() {
        itemList = new AccountListDisplay();
        itemList.setWidth("100%");
        itemList.setRowDisplayHandler(rowHandler);
        this.setContent(itemList);
    }

    @Override
    public void load() {
        AccountSearchCriteria searchCriteria = new AccountSearchCriteria();
        searchCriteria.setSaccountid(new NumberSearchField(MyCollabUI.getAccountId()));
        itemList.search(searchCriteria);

        SimpleAccount clearAccount = new SimpleAccount();
        itemList.addComponentAtTop(rowHandler.generateRow(itemList, clearAccount, 0));
    }

    private class AccountRowDisplayHandler implements IBeanList.RowDisplayHandler<SimpleAccount> {

        @Override
        public Component generateRow(IBeanList<SimpleAccount> host, final SimpleAccount account, int rowIndex) {
            Button b = new Button(account.getAccountname(), clickEvent -> {
                selectionField.fireValueChange(account);
                getNavigationManager().navigateBack();
            });
            if (account.getId() == null)
                b.addStyleName("blank-item");
            return b;
        }

    }
}
