package com.mycollab.premium.mobile.module.crm.view.account;

import com.mycollab.common.UrlEncodeDecoder;
import com.mycollab.common.i18n.GenericI18Enum;
import com.mycollab.mobile.form.view.DynaFormLayout;
import com.mycollab.mobile.module.crm.view.account.AccountAddView;
import com.mycollab.mobile.module.crm.view.account.AccountDefaultDynaFormLayoutFactory;
import com.mycollab.mobile.ui.AbstractEditItemComp;
import com.mycollab.module.crm.CrmTypeConstants;
import com.mycollab.module.crm.domain.SimpleAccount;
import com.mycollab.module.crm.i18n.AccountI18nEnum;
import com.mycollab.vaadin.MyCollabUI;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.mvp.ViewComponent;
import com.mycollab.vaadin.ui.AbstractBeanFieldGroupEditFieldFactory;
import com.mycollab.vaadin.ui.IFormLayoutFactory;

/**
 * @author MyCollab Ltd.
 * @since 4.0
 */
@ViewComponent
public class AccountAddViewImpl extends AbstractEditItemComp<SimpleAccount> implements AccountAddView {
    private static final long serialVersionUID = -6760402062110610122L;

    @Override
    protected String initFormTitle() {
        return beanItem.getAccountname() != null ? beanItem.getAccountname() : UserUIContext.getMessage(AccountI18nEnum.NEW);
    }

    @Override
    public void onBecomingVisible() {
        super.onBecomingVisible();

        if (beanItem.getId() == null) {
            MyCollabUI.addFragment("crm/account/add", UserUIContext.getMessage(GenericI18Enum
                    .BROWSER_ADD_ITEM_TITLE, UserUIContext.getMessage(AccountI18nEnum.SINGLE)));

        } else {
            MyCollabUI.addFragment("crm/account/edit/" + UrlEncodeDecoder.encode(beanItem.getId()),
                    UserUIContext.getMessage(GenericI18Enum.BROWSER_EDIT_ITEM_TITLE,
                            UserUIContext.getMessage(AccountI18nEnum.SINGLE), beanItem.getAccountname()));
        }
    }

    @Override
    protected IFormLayoutFactory initFormLayoutFactory() {
        return new DynaFormLayout(CrmTypeConstants.ACCOUNT, AccountDefaultDynaFormLayoutFactory.getForm());
    }

    @Override
    protected AbstractBeanFieldGroupEditFieldFactory<SimpleAccount> initBeanFormFieldFactory() {
        return new AccountEditFormFieldFactory<>(editForm);
    }

}
