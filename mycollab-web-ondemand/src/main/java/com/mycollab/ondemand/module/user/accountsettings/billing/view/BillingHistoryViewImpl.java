package com.mycollab.ondemand.module.user.accountsettings.billing.view;

import com.mycollab.module.user.accountsettings.localization.BillingI18nEnum;
import com.mycollab.ondemand.module.billing.dao.BillingSubscriptionHistoryMapper;
import com.mycollab.ondemand.module.billing.dao.BillingSubscriptionMapperExt;
import com.mycollab.ondemand.module.billing.domain.BillingSubscriptionHistory;
import com.mycollab.ondemand.module.billing.domain.BillingSubscriptionHistoryExample;
import com.mycollab.ondemand.module.billing.domain.SimpleBillingSubscription;
import com.mycollab.spring.AppContextUtil;
import com.mycollab.vaadin.AppContext;
import com.mycollab.vaadin.mvp.ViewComponent;
import com.mycollab.vaadin.mvp.view.AbstractLazyPageView;
import com.mycollab.vaadin.ui.ELabel;
import com.mycollab.vaadin.web.ui.UIConstants;
import com.mycollab.vaadin.web.ui.field.EmailViewField;
import com.mycollab.vaadin.web.ui.grid.GridFormLayoutHelper;
import com.vaadin.data.sort.SortOrder;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.BrowserWindowOpener;
import com.vaadin.shared.data.sort.SortDirection;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.renderers.DateRenderer;
import com.vaadin.ui.themes.ValoTheme;
import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.layouts.MHorizontalLayout;

import java.util.Arrays;
import java.util.List;

/**
 * @author MyCollab Ltd
 * @since 5.3.5
 */
@ViewComponent
public class BillingHistoryViewImpl extends AbstractLazyPageView implements BillingHistoryView {
    public BillingHistoryViewImpl() {
        withMargin(true);
    }

    @Override
    protected void displayView() {
        MHorizontalLayout headerLayout = new MHorizontalLayout(ELabel.h2("Billing Overview"));
        with(headerLayout);

        BillingSubscriptionMapperExt billingSubscriptionMapperExt = AppContextUtil.getSpringBean(BillingSubscriptionMapperExt.class);
        SimpleBillingSubscription subscription = billingSubscriptionMapperExt.findSubscription(AppContext.getAccountId());
        if (subscription != null) {
            MButton changeBillingInfoBtn = new MButton(AppContext.getMessage(BillingI18nEnum.ACTION_CHANGE_BILLING_INFORMATION),
                    clickEvent -> UI.getCurrent().addWindow(new ChangeBillingPlanInformationWindow(subscription)))
                    .withStyleName(UIConstants.BUTTON_ACTION, ValoTheme.BUTTON_SMALL);

            MButton updatePaymentMethodBtn = new MButton(AppContext.getMessage(BillingI18nEnum.ACTION_UPDATE_PAYMENT_METHOD))
                    .withStyleName(UIConstants.BUTTON_ACTION, ValoTheme.BUTTON_SMALL);
            BrowserWindowOpener paymentOpener = new BrowserWindowOpener(subscription.getSubscriptioncustomerurl());
            paymentOpener.extend(updatePaymentMethodBtn);

            headerLayout.with(changeBillingInfoBtn, updatePaymentMethodBtn);
            GridFormLayoutHelper gridFormLayoutHelper = GridFormLayoutHelper.defaultFormLayoutHelper(1, 6);
            gridFormLayoutHelper.addComponent(new Label(subscription.getName()), "Name", 0, 0);
            gridFormLayoutHelper.addComponent(new EmailViewField(subscription.getEmail()), "Email", 0, 1);
            gridFormLayoutHelper.addComponent(new Label(subscription.getPhone()), "Phone", 0, 2);
            gridFormLayoutHelper.addComponent(new Label(subscription.getCompany()), "Company", 0, 3);
            gridFormLayoutHelper.addComponent(new Label(subscription.getSubreference()), "Reference", 0, 4);
            gridFormLayoutHelper.addComponent(new Label(AppContext.formatDate(subscription.getExpireDate())), "Next Billing Date", 0, 5);
            with(gridFormLayoutHelper.getLayout());

            BillingSubscriptionHistoryMapper billingSubscriptionHistoryMapper = AppContextUtil.getSpringBean(BillingSubscriptionHistoryMapper.class);
            BillingSubscriptionHistoryExample ex = new BillingSubscriptionHistoryExample();
            ex.createCriteria().andSubscriptionidEqualTo(subscription.getId());
            ex.setOrderByClause("expiredDate DESC");
            List<BillingSubscriptionHistory> billingSubscriptionHistories = billingSubscriptionHistoryMapper.selectByExample(ex);
            with(new MHorizontalLayout(ELabel.h3("History")).withMargin(new MarginInfo(true, false, true, false)));
            BeanItemContainer<BillingSubscriptionHistory> container = new BeanItemContainer<>(BillingSubscriptionHistory.class, billingSubscriptionHistories);
            Grid grid = new Grid(container);
            grid.setWidth("100%");
            grid.setColumnOrder("productname", "orderid", "status", "expireddate", "totalprice", "createdtime");
            grid.getColumn("productname").setHeaderCaption("Name");
            grid.getColumn("orderid").setHeaderCaption("Order");
            grid.getColumn("status").setHeaderCaption("Status");
            grid.getColumn("expireddate").setHeaderCaption("Expired Date");
            grid.getColumn("expireddate").setRenderer(new DateRenderer("%1$tB %1$te, %1$tY", AppContext.getUserLocale()));
            grid.getColumn("totalprice").setHeaderCaption("Price (USD)");
            grid.getColumn("createdtime").setHeaderCaption("Created");
            grid.getColumn("createdtime").setRenderer(new DateRenderer("%1$tB %1$te, %1$tY", AppContext.getUserLocale()));
            grid.removeColumn("id");
            grid.removeColumn("selected");
            grid.removeColumn("subscriptionid");
            grid.removeColumn("extraData");
            grid.setSortOrder(Arrays.asList(new SortOrder("expireddate", SortDirection.DESCENDING), new SortOrder("createdtime", SortDirection.DESCENDING)));
            with(grid);
        } else {
            with(new Label("Trial"));
        }
    }
}
