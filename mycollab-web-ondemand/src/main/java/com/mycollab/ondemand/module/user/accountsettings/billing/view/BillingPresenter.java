package com.mycollab.ondemand.module.user.accountsettings.billing.view;

import com.mycollab.core.MyCollabException;
import com.mycollab.module.user.accountsettings.billing.view.IBillingContainer;
import com.mycollab.module.user.accountsettings.billing.view.IBillingPresenter;
import com.mycollab.module.user.accountsettings.view.AccountModule;
import com.mycollab.module.user.accountsettings.view.parameters.BillingScreenData;
import com.mycollab.module.user.ui.SettingUIConstants;
import com.mycollab.vaadin.mvp.PresenterResolver;
import com.mycollab.vaadin.mvp.ScreenData;
import com.mycollab.vaadin.web.ui.AbstractPresenter;
import com.vaadin.ui.HasComponents;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
public class BillingPresenter extends AbstractPresenter<IBillingContainer> implements IBillingPresenter {
    private static final long serialVersionUID = 1L;

    public BillingPresenter() {
        super(IBillingContainer.class);
    }

    @Override
    protected void onGo(HasComponents container, ScreenData<?> data) {
        AccountModule accountContainer = (AccountModule) container;
        accountContainer.gotoSubView(SettingUIConstants.INSTANCE.getBILLING());

        AbstractPresenter<?> presenter;

        if (data instanceof BillingScreenData.BillingSummary) {
            presenter = PresenterResolver.getPresenter(BillingSummaryPresenter.class);
        } else if (data instanceof BillingScreenData.CancelAccount) {
            presenter = PresenterResolver.getPresenter(CancelAccountPresenter.class);
        } else if (data instanceof BillingScreenData.BillingHistory) {
            presenter = PresenterResolver.getPresenter(BillingHistoryPresenter.class);
        } else {
            throw new MyCollabException("Do not support screen data " + data);
        }

        presenter.go(view, data);
    }

}
