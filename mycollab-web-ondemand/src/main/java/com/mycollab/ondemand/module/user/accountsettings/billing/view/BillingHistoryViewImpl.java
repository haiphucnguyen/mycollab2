package com.mycollab.ondemand.module.user.accountsettings.billing.view;

import com.mycollab.common.i18n.GenericI18Enum;
import com.mycollab.form.view.LayoutType;
import com.mycollab.module.user.accountsettings.localization.BillingI18nEnum;
import com.mycollab.module.user.accountsettings.localization.UserI18nEnum;
import com.mycollab.ondemand.module.billing.dao.BillingSubscriptionHistoryMapper;
import com.mycollab.ondemand.module.billing.domain.BillingSubscriptionHistory;
import com.mycollab.ondemand.module.billing.domain.BillingSubscriptionHistoryExample;
import com.mycollab.ondemand.module.billing.domain.SimpleBillingSubscription;
import com.mycollab.spring.AppContextUtil;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.mvp.ViewComponent;
import com.mycollab.vaadin.ui.ELabel;
import com.mycollab.vaadin.ui.MyCollabSession;
import com.mycollab.vaadin.ui.field.EmailViewField;
import com.mycollab.vaadin.web.ui.AbstractLazyPageView;
import com.mycollab.vaadin.web.ui.WebThemes;
import com.mycollab.vaadin.web.ui.grid.GridFormLayoutHelper;
import com.vaadin.server.BrowserWindowOpener;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.themes.ValoTheme;
import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.layouts.MHorizontalLayout;

import java.util.List;

/**
 * @author MyCollab Ltd
 * @since 5.3.5
 */
// TODO
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
            MButton changeBillingInfoBtn = new MButton(UserUIContext.getMessage(BillingI18nEnum.ACTION_CHANGE_BILLING_INFORMATION),
                    clickEvent -> UI.getCurrent().addWindow(new ChangeBillingPlanInformationWindow(subscription)))
                    .withStyleName(WebThemes.BUTTON_ACTION, ValoTheme.BUTTON_SMALL);

            MButton updatePaymentMethodBtn = new MButton(UserUIContext.getMessage(BillingI18nEnum.ACTION_UPDATE_PAYMENT_METHOD))
                    .withStyleName(ValoTheme.BUTTON_SMALL);
            BrowserWindowOpener paymentOpener = new BrowserWindowOpener(subscription.getSubscriptioncustomerurl());
            paymentOpener.extend(updatePaymentMethodBtn);
            if (!subscription.isValid()) {
                updatePaymentMethodBtn.withStyleName(WebThemes.BUTTON_DANGER);
            } else {
                updatePaymentMethodBtn.withStyleName(WebThemes.BUTTON_ACTION);
            }

            headerLayout.with(changeBillingInfoBtn, updatePaymentMethodBtn);
            GridFormLayoutHelper gridFormLayoutHelper = GridFormLayoutHelper.defaultFormLayoutHelper(LayoutType.ONE_COLUMN);
            gridFormLayoutHelper.addComponent(new Label(subscription.getName()), UserUIContext.getMessage(GenericI18Enum.FORM_NAME), 0, 0);
            gridFormLayoutHelper.addComponent(new EmailViewField(subscription.getEmail()),
                    UserUIContext.getMessage(GenericI18Enum.FORM_EMAIL), 0, 1);
            gridFormLayoutHelper.addComponent(new Label(subscription.getPhone()), UserUIContext.getMessage
                    (GenericI18Enum.FORM_PHONE), 0, 2);
            gridFormLayoutHelper.addComponent(new Label(subscription.getCompany()),
                    UserUIContext.getMessage(UserI18nEnum.FORM_COMPANY), 0, 3);
            gridFormLayoutHelper.addComponent(new Label(subscription.getSubreference()), UserUIContext.getMessage
                    (BillingI18nEnum.OPT_SUBSCRIPTION_REFERENCE), 0, 4);
            ELabel nextExpiredDate = new ELabel(UserUIContext.formatDate(subscription.getExpireDate()));
            if (!subscription.isValid()) {
                nextExpiredDate.withStyleName(WebThemes.LABEL_OVERDUE);
            }
            gridFormLayoutHelper.addComponent(nextExpiredDate, UserUIContext.getMessage(BillingI18nEnum.OPT_NEXT_BILLING_DATE), 0, 5);
            with(gridFormLayoutHelper.getLayout());

            BillingSubscriptionHistoryMapper billingSubscriptionHistoryMapper = AppContextUtil.getSpringBean(BillingSubscriptionHistoryMapper.class);
            BillingSubscriptionHistoryExample ex = new BillingSubscriptionHistoryExample();
            ex.createCriteria().andSubscriptionidEqualTo(subscription.getId());
            ex.setOrderByClause("expiredDate DESC");
            List<BillingSubscriptionHistory> billingSubscriptionHistories = billingSubscriptionHistoryMapper.selectByExample(ex);
            with(new MHorizontalLayout(ELabel.h3("History")).withMargin(new MarginInfo(true, false, true, false)));
//            BeanItemContainer<BillingSubscriptionHistory> container = new BeanItemContainer<>(BillingSubscriptionHistory.class, billingSubscriptionHistories);
//            Grid grid = new Grid(container);
//            grid.setWidth("100%");
//            grid.setColumnOrder("productname", "orderid", "status", "expireddate", "totalprice", "createdtime");
//            grid.getColumn("productname").setHeaderCaption(UserUIContext.getMessage(GenericI18Enum.FORM_NAME));
//            grid.getColumn("orderid").setHeaderCaption(UserUIContext.getMessage(BillingI18nEnum.OPT_ORDER));
//            grid.getColumn("status").setHeaderCaption(UserUIContext.getMessage(GenericI18Enum.FORM_STATUS));
//            grid.getColumn("expireddate").setHeaderCaption(UserUIContext.getMessage(BillingI18nEnum.OPT_EXPIRED_DATE));
//            grid.getColumn("expireddate").setRenderer(new DateRenderer("%1$tB %1$te, %1$tY", UserUIContext.getUserLocale()));
//            grid.getColumn("totalprice").setHeaderCaption(UserUIContext.getMessage(BillingI18nEnum.OPT_PRICE_IN_USD));
//            grid.getColumn("createdtime").setHeaderCaption(UserUIContext.getMessage(GenericI18Enum.FORM_CREATED_TIME));
//            grid.getColumn("createdtime").setRenderer(new DateRenderer("%1$tB %1$te, %1$tY", UserUIContext.getUserLocale()));
//            grid.removeColumn("id");
//            grid.removeColumn("selected");
//            grid.removeColumn("subscriptionid");
//            grid.removeColumn("extraData");
//            grid.setSortOrder(Arrays.asList(new SortOrder("expireddate", SortDirection.DESCENDING), new SortOrder("createdtime", SortDirection.DESCENDING)));
//            with(grid);
        } else {
            with(new Label("Trial"));
        }
    }
}
