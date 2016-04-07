package com.esofthead.mycollab.ondemand.module.user.accountsettings.billing.view;

import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.module.user.accountsettings.billing.view.IBillingContainer;
import com.esofthead.mycollab.module.user.accountsettings.billing.view.IBillingPresenter;
import com.esofthead.mycollab.module.user.accountsettings.view.AccountModule;
import com.esofthead.mycollab.module.user.accountsettings.view.parameters.BillingScreenData;
import com.esofthead.mycollab.module.user.ui.SettingUIConstants;
import com.esofthead.mycollab.vaadin.mvp.PresenterResolver;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.web.ui.AbstractPresenter;
import com.vaadin.ui.ComponentContainer;

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
    public boolean go(ComponentContainer container, ScreenData<?> data) {
        return super.go(container, data, false);
    }

    @Override
    protected void onGo(ComponentContainer container, ScreenData<?> data) {
        AccountModule accountContainer = (AccountModule) container;

        accountContainer.gotoSubView(SettingUIConstants.BILLING);

        AbstractPresenter<?> presenter;

        if (data instanceof BillingScreenData.BillingSummary) {
            presenter = PresenterResolver.getPresenter(BillingSummaryPresenter.class);
        } else if (data instanceof BillingScreenData.CancelAccount) {
            presenter = PresenterResolver.getPresenter(CancelAccountPresenter.class);
        } else {
            throw new MyCollabException("Do not support screen data " + data);
        }

        presenter.go(view.getWidget(), data);
    }

}
