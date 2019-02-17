package com.mycollab.ondemand.module.user.accountsettings.billing.view;

import com.mycollab.common.i18n.GenericI18Enum;
import com.mycollab.configuration.EnDecryptHelper;
import com.mycollab.core.utils.FileUtils;
import com.mycollab.module.user.accountsettings.localization.BillingI18nEnum;
import com.mycollab.module.user.domain.BillingPlan;
import com.mycollab.vaadin.AppUI;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.ui.ELabel;
import com.mycollab.vaadin.web.ui.WebThemes;
import com.vaadin.server.BrowserWindowOpener;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Label;
import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;
import org.vaadin.viritin.layouts.MWindow;

/**
 * @author MyCollab Ltd
 * @since 5.4.2
 */
class BankwireSelectionWindow extends MWindow {
    private BillingPlan selectedPlan;
    private MVerticalLayout planLayout;

    BankwireSelectionWindow(BillingPlan billingPlan) {
        super(UserUIContext.getMessage(BillingI18nEnum.OPT_PAYMENT_BANKWIRE));
        this.selectedPlan = billingPlan;
        MVerticalLayout contentLayout = new MVerticalLayout();
        withModal(true).withResizable(false).withWidth("500px").withContent(contentLayout);
        ELabel billingType = ELabel.h3(billingPlan.getBillingtype()).withStyleName("billing-type");
        Label billingPrice = ELabel.html("<span class='billing-price'>$" + billingPlan.getPricing() * 10 + "</span>/year")
                .withStyleName("billing-price-lbl").withUndefinedWidth();
        Label billingUser = ELabel.html("<span class='billing-user'>" + billingPlan.getNumusers() + "</span>&nbsp;" +
                "Users").withUndefinedWidth();
        String planVolume = FileUtils.getVolumeDisplay(billingPlan.getVolume());
        Label billingStorage = ELabel.html("<span class='billing-storage'>" + planVolume + "</span>&nbsp;Storage").withUndefinedWidth();
        Label billingProject = ELabel.html("<span class='billing-project'>" + billingPlan.getNumprojects() +
                "</span>&nbsp;Projects").withUndefinedWidth();
        MButton chargeBtn = new MButton(UserUIContext.getMessage(GenericI18Enum.ACTION_CHARGE), event -> close()).withStyleName(WebThemes.BUTTON_ACTION);
        BrowserWindowOpener opener = new BrowserWindowOpener(billingPlan.getBanktransferpath() +
                "?referrer=" + EnDecryptHelper.encryptTextWithEncodeFriendly(AppUI.getAccountId() + ";" + billingPlan.getId()));

        opener.extend(chargeBtn);
        planLayout = new MVerticalLayout(billingType, billingPrice, billingUser, billingStorage, billingProject)
                .withWidth("200px");
        MVerticalLayout bankwireNoteLbl = new MVerticalLayout(ELabel.html(UserUIContext.getMessage(BillingI18nEnum.OPT_PAYMENT_BANKWIRE_NOTE)))
                .withStyleName("left-border-dotted-layout").withFullHeight();
        MHorizontalLayout planWrapper = new MHorizontalLayout(planLayout, bankwireNoteLbl).expand(bankwireNoteLbl).withFullWidth()
                .alignAll(Alignment.TOP_LEFT);
        contentLayout.with(planWrapper, chargeBtn).withAlign(chargeBtn, Alignment.TOP_RIGHT);
    }
}
