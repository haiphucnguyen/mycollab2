package com.mycollab.ondemand.module.user.accountsettings.billing.view;

import com.mycollab.common.i18n.GenericI18Enum;
import com.mycollab.module.user.accountsettings.localization.BillingI18nEnum;
import com.mycollab.module.user.accountsettings.localization.UserI18nEnum;
import com.mycollab.ondemand.module.billing.dao.BillingSubscriptionHistoryMapper;
import com.mycollab.ondemand.module.billing.domain.BillingSubscriptionHistory;
import com.mycollab.ondemand.module.billing.domain.BillingSubscriptionHistoryExample;
import com.mycollab.ondemand.module.billing.domain.SimpleBillingSubscription;
import com.mycollab.spring.AppContextUtil;
import com.mycollab.vaadin.AppContext;
import com.mycollab.vaadin.mvp.ViewComponent;
import com.mycollab.vaadin.mvp.view.AbstractLazyPageView;
import com.mycollab.vaadin.ui.ELabel;
import com.mycollab.vaadin.ui.MyCollabSession;
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

        SimpleBillingSubscription subscription = (SimpleBillingSubscription) MyCollabSession.getCurrentUIVariable("subscription");
        if (subscription != null) {
            MButton changeBillingInfoBtn = new MButton(AppContext.getMessage(BillingI18nEnum.ACTION_CHANGE_BILLING_INFORMATION),
                    clickEvent -> UI.getCurrent().addWindow(new ChangeBillingPlanInformationWindow(subscription)))
                    .withStyleName(UIConstants.BUTTON_ACTION, ValoTheme.BUTTON_SMALL);

            MButton updatePaymentMethodBtn = new MButton(AppContext.getMessage(BillingI18nEnum.ACTION_UPDATE_PAYMENT_METHOD))
                    .withStyleName(ValoTheme.BUTTON_SMALL);
            BrowserWindowOpener paymentOpener = new BrowserWindowOpener(subscription.getSubscriptioncustomerurl());
            paymentOpener.extend(updatePaymentMethodBtn);
            if (!subscription.isValid()) {
                updatePaymentMethodBtn.withStyleName(UIConstants.BUTTON_DANGER);
            } else {
                updatePaymentMethodBtn.withStyleName(UIConstants.BUTTON_ACTION);
            }

            headerLayout.with(changeBillingInfoBtn, updatePaymentMethodBtn);
            GridFormLayoutHelper gridFormLayoutHelper = GridFormLayoutHelper.defaultFormLayoutHelper(1, 6);
            gridFormLayoutHelper.addComponent(new Label(subscription.getName()), AppContext.getMessage(GenericI18Enum.FORM_NAME), 0, 0);
            gridFormLayoutHelper.addComponent(new EmailViewField(subscription.getEmail()),
                    AppContext.getMessage(GenericI18Enum.FORM_EMAIL), 0, 1);
            gridFormLayoutHelper.addComponent(new Label(subscription.getPhone()), AppContext.getMessage
                    (GenericI18Enum.FORM_PHONE), 0, 2);
            gridFormLayoutHelper.addComponent(new Label(subscription.getCompany()),
                    AppContext.getMessage(UserI18nEnum.FORM_COMPANY), 0, 3);
            gridFormLayoutHelper.addComponent(new Label(subscription.getSubreference()), AppContext.getMessage
                    (BillingI18nEnum.OPT_SUBSCRIPTION_REFERENCE), 0, 4);
            ELabel nextExpiredDate = new ELabel(AppContext.formatDate(subscription.getExpireDate()));
            if (!subscription.isValid()) {
                nextExpiredDate.withStyleName(UIConstants.LABEL_OVERDUE);
            }
            gridFormLayoutHelper.addComponent(nextExpiredDate, AppContext.getMessage(BillingI18nEnum.OPT_NEXT_BILLING_DATE), 0, 5);
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
            grid.getColumn("productname").setHeaderCaption(AppContext.getMessage(GenericI18Enum.FORM_NAME));
            grid.getColumn("orderid").setHeaderCaption(AppContext.getMessage(BillingI18nEnum.OPT_ORDER));
            grid.getColumn("status").setHeaderCaption(AppContext.getMessage(GenericI18Enum.FORM_STATUS));
            grid.getColumn("expireddate").setHeaderCaption(AppContext.getMessage(BillingI18nEnum.OPT_EXPIRED_DATE));
            grid.getColumn("expireddate").setRenderer(new DateRenderer("%1$tB %1$te, %1$tY", AppContext.getUserLocale()));
            grid.getColumn("totalprice").setHeaderCaption(AppContext.getMessage(BillingI18nEnum.OPT_PRICE_IN_USD));
            grid.getColumn("createdtime").setHeaderCaption(AppContext.getMessage(GenericI18Enum.FORM_CREATED_TIME));
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
