package com.mycollab.premium.mobile.module.crm.view.account;

import com.mycollab.mobile.ui.AbstractSelectionCustomField;
import com.mycollab.module.crm.domain.Account;
import com.mycollab.module.crm.domain.SimpleAccount;
import com.mycollab.module.crm.service.AccountService;
import com.mycollab.spring.AppContextUtil;
import com.mycollab.vaadin.MyCollabUI;
import com.mycollab.vaadin.ui.FieldSelection;
import com.vaadin.data.Property;

/**
 * @author MyCollab Ltd.
 * @since 4.0
 */
public class AccountSelectionField extends AbstractSelectionCustomField<Integer, Account> implements
        FieldSelection<Account> {
    private static final long serialVersionUID = 1L;

    public AccountSelectionField() {
        super(AccountSelectionView.class);
    }

    @Override
    public void setPropertyDataSource(Property newDataSource) {
        Object value = newDataSource.getValue();
        if (value instanceof Integer) {
            setAccountByVal((Integer) value);
        }
        super.setPropertyDataSource(newDataSource);
    }

    @Override
    public void setValue(Integer value) {
        this.setAccountByVal(value);
        super.setValue(value);
    }

    private void setAccountByVal(Integer accountId) {
        AccountService accountService = AppContextUtil.getSpringBean(AccountService.class);
        SimpleAccount account = accountService.findById(accountId, MyCollabUI.getAccountId());
        if (account != null) {
            setInternalAccount(account);
        }
    }

    private void setInternalAccount(Account account) {
        this.beanItem = account;
        navButton.setCaption(account.getAccountname());
    }

    public Account getAccount() {
        return beanItem;
    }

    @Override
    public void fireValueChange(Account data) {
        setInternalAccount(data);
        setInternalValue(beanItem.getId());
    }

    @Override
    public Class<Integer> getType() {
        return Integer.class;
    }
}