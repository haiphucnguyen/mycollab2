/**
 * This file is part of mycollab-web.
 *
 * mycollab-web is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-web is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-web.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.mycollab.ondemand.module.user.accountsettings.billing.view;

import com.mycollab.common.i18n.GenericI18Enum;
import com.mycollab.configuration.EnDecryptHelper;
import com.mycollab.core.utils.FileUtils;
import com.mycollab.eventmanager.EventBusFactory;
import com.mycollab.module.ecm.service.DriveInfoService;
import com.mycollab.module.project.service.ProjectService;
import com.mycollab.module.user.accountsettings.localization.AdminI18nEnum;
import com.mycollab.module.user.accountsettings.localization.BillingI18nEnum;
import com.mycollab.module.user.accountsettings.view.events.AccountBillingEvent;
import com.mycollab.module.user.domain.BillingPlan;
import com.mycollab.module.user.domain.SimpleBillingAccount;
import com.mycollab.module.user.service.UserService;
import com.mycollab.ondemand.module.billing.domain.SimpleBillingSubscription;
import com.mycollab.ondemand.module.billing.service.BillingService;
import com.mycollab.spring.AppContextUtil;
import com.mycollab.vaadin.AppContext;
import com.mycollab.vaadin.mvp.ViewComponent;
import com.mycollab.vaadin.mvp.view.AbstractLazyPageView;
import com.mycollab.vaadin.ui.ELabel;
import com.mycollab.vaadin.ui.MyCollabSession;
import com.mycollab.vaadin.ui.NotificationUtil;
import com.mycollab.vaadin.web.ui.UIConstants;
import com.vaadin.server.BrowserWindowOpener;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.themes.ValoTheme;
import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.layouts.MCssLayout;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;
import org.vaadin.viritin.layouts.MWindow;

import java.util.List;

import static com.mycollab.module.user.accountsettings.localization.BillingI18nEnum.OPTION_BILLING_FAQ;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
@ViewComponent
public class BillingSummaryViewImpl extends AbstractLazyPageView implements BillingSummaryView {

    private BillingService billingService;
    private MVerticalLayout currentPlanLayout;

    private Integer numOfActiveProjects = 0;
    private Integer numOfActiveUsers = 0;
    private Long usedStorageVolume = 0L;

    public BillingSummaryViewImpl() {
        super();
        this.setMargin(true);
        this.billingService = AppContextUtil.getSpringBean(BillingService.class);
    }

    @Override
    protected void displayView() {
        removeAllComponents();
        MCssLayout layout = new MCssLayout().withStyleName("billing-setting").withFullWidth();
        MHorizontalLayout topLayout = new MHorizontalLayout().withFullWidth();

        currentPlanLayout = new MVerticalLayout().withMargin(false).withStyleName("current-plan-information").withFullWidth();
        currentPlanLayout.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        topLayout.with(currentPlanLayout).withAlign(currentPlanLayout, Alignment.MIDDLE_CENTER).expand(currentPlanLayout);

        MVerticalLayout faqLayout = new MVerticalLayout().withWidth("285px").withStyleName("faq-layout");

        if (AppContext.isAdmin()) {
            MButton cancelBtn = new MButton(AppContext.getMessage(BillingI18nEnum.BUTTON_CANCEL_ACCOUNT),
                    clickEvent -> EventBusFactory.getInstance().post(new AccountBillingEvent.CancelAccount(this, null)))
                    .withStyleName(UIConstants.BUTTON_DANGER);
            faqLayout.addComponent(cancelBtn);
        }

        ELabel header = ELabel.h3(AppContext.getMessage(BillingI18nEnum.HELP_QUESTION));
        faqLayout.addComponent(header);

        faqLayout.addComponent(ELabel.html(AppContext.getMessage(BillingI18nEnum.HELP_INFO)));
        topLayout.with(faqLayout).withAlign(faqLayout, Alignment.TOP_RIGHT);
        layout.addComponent(topLayout);

        MHorizontalLayout plansLayout = new MHorizontalLayout().withSpacing(false).withFullWidth().withStyleName("billing-plan-list");

        BillingPlan currentBillingPlan = AppContext.getBillingAccount().getBillingPlan();
        SimpleBillingAccount billingAccount = AppContext.getBillingAccount();
        List<BillingPlan> availablePlans = billingService.getAvailablePlans();

        for (int i = 0; i < availablePlans.size(); i++) {
            MVerticalLayout singlePlan = new MVerticalLayout().withMargin(false).withStyleName("billing-plan");
            singlePlan.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);

            if ((i + 1) % 2 == 0) {
                singlePlan.addStyleName("even");
            }

            final BillingPlan plan = availablePlans.get(i);

            ELabel billingType = ELabel.h3(plan.getBillingtype()).withStyleName("billing-type");
            Label billingPrice = ELabel.html("<span class='billing-price'>$" + plan.getPricing() + "</span>/month")
                    .withStyleName("billing-price-lbl").withWidthUndefined();
            Label billingUser = ELabel.html("<span class='billing-user'>" + plan.getNumusers() + "</span>&nbsp;" +
                    "Users").withWidthUndefined();
            String planVolume = FileUtils.getVolumeDisplay(plan.getVolume());
            Label billingStorage = ELabel.html("<span class='billing-storage'>" + planVolume + "</span>&nbsp;Storage").withWidthUndefined();
            Label billingProject = ELabel.html("<span class='billing-project'>" + plan.getNumprojects() +
                    "</span>&nbsp;Projects").withWidthUndefined();

            if (currentBillingPlan.getId().equals(plan.getId())) {
                if (billingAccount.isNotActive()) {
                    MButton selectPlanBtn = new MButton(AppContext.getMessage(GenericI18Enum.ACTION_CHARGE)).withStyleName(UIConstants.BUTTON_ACTION);
                    BrowserWindowOpener opener = new BrowserWindowOpener(plan.getShoppingurl() +
                            "?referrer=" + EnDecryptHelper.encryptText(AppContext.getAccountId() + ""));
                    opener.extend(selectPlanBtn);
                    singlePlan.with(billingType, billingPrice, billingUser, billingStorage, billingProject, selectPlanBtn);
                } else {
                    singlePlan.with(billingType, billingPrice, billingUser, billingStorage, billingProject, new
                            MButton("Selected").withStyleName(UIConstants.BUTTON_OPTION));
                }
            } else {
                String actionTxt = (plan.getPricing() < currentBillingPlan.getPricing()) ? AppContext.getMessage
                        (BillingI18nEnum.ACTION_DOWNGRADE) : AppContext.getMessage(BillingI18nEnum.ACTION_UPGRADE);
                MButton selectPlanBtn = new MButton(actionTxt,
                        clickEvent -> UI.getCurrent().addWindow(new UpdateBillingPlanWindow(plan)))
                        .withStyleName(UIConstants.BUTTON_ACTION);
                singlePlan.with(billingType, billingPrice, billingUser, billingStorage, billingProject, selectPlanBtn);
            }

            plansLayout.with(singlePlan).expand(singlePlan);
        }

        layout.addComponent(plansLayout);
        this.with(layout, ELabel.html(AppContext.getMessage(OPTION_BILLING_FAQ)));

        loadCurrentPlan();
    }


    private void loadCurrentPlan() {
        currentPlanLayout.removeAllComponents();
        BillingPlan currentBillingPlan = AppContext.getBillingAccount().getBillingPlan();

        ELabel introText = ELabel.h2(AppContext.getMessage(BillingI18nEnum.OPT_CURRENT_PLAN, currentBillingPlan.getBillingtype()));
        SimpleBillingSubscription subscription = (SimpleBillingSubscription) MyCollabSession.getCurrentUIVariable("subscription");
        if (subscription == null) {
            MButton selectPlanBtn = new MButton(AppContext.getMessage(GenericI18Enum.ACTION_CHARGE)).withStyleName(UIConstants.BUTTON_DANGER);
            BrowserWindowOpener opener = new BrowserWindowOpener(currentBillingPlan.getShoppingurl() + "?referrer=" +
                    EnDecryptHelper.encryptText(AppContext.getAccountId() + ""));
            opener.extend(selectPlanBtn);
            currentPlanLayout.with(new MHorizontalLayout(introText, selectPlanBtn));
        } else {
            MButton historyBtn = new MButton(AppContext.getMessage(AdminI18nEnum.VIEW_BILLING_HISTORY),
                    clickEvent -> EventBusFactory.getInstance().post(new AccountBillingEvent.GotoHistory(this, null)))
                    .withStyleName(UIConstants.BUTTON_ACTION, ValoTheme.BUTTON_TINY);
            MHorizontalLayout buttonControls = new MHorizontalLayout(introText, historyBtn);
            currentPlanLayout.addComponent(buttonControls);
            if (!subscription.isValid()) {
                MButton retryChargeBtn = new MButton(AppContext.getMessage(GenericI18Enum.ACTION_CHARGE))
                        .withStyleName(UIConstants.BUTTON_DANGER, ValoTheme.BUTTON_TINY);
                BrowserWindowOpener paymentOpener = new BrowserWindowOpener(subscription.getSubscriptioncustomerurl());
                paymentOpener.extend(retryChargeBtn);
                buttonControls.with(retryChargeBtn);
            }
        }

        if (subscription == null) {
            ELabel currentBillingPrice = ELabel.h3(AppContext.getMessage(BillingI18nEnum.OPT_PRICING_MONTH, currentBillingPlan.getPricing()));
            currentPlanLayout.addComponent(currentBillingPrice);
        } else {
            ELabel currentBillingPrice = ELabel.h3(AppContext.getMessage(BillingI18nEnum.OPT_PRICING_MONTH, currentBillingPlan.getPricing()));
            ELabel expiredDateLbl = ELabel.h3(" | Expired Date: " + AppContext.formatDate(subscription.getExpireDate()));
            if (!subscription.isValid()) {
                expiredDateLbl.withStyleName(UIConstants.LABEL_OVERDUE);
            }
            currentPlanLayout.addComponent(new MHorizontalLayout(currentBillingPrice, expiredDateLbl));
        }

        ProjectService projectService = AppContextUtil.getSpringBean(ProjectService.class);
        numOfActiveProjects = projectService.getTotalActiveProjectsInAccount(AppContext.getAccountId());

        ELabel projectInfo = ELabel.html(AppContext.getMessage(BillingI18nEnum.OPT_PLAN_NUM_PROJECTS,
                numOfActiveProjects, currentBillingPlan.getNumprojects())).withStyleName(UIConstants.FIELD_NOTE);

        DriveInfoService driveInfoService = AppContextUtil.getSpringBean(DriveInfoService.class);
        usedStorageVolume = driveInfoService.getUsedStorageVolume(AppContext.getAccountId());
        String usedStorageTxt = FileUtils.getVolumeDisplay(usedStorageVolume);
        ELabel storageInfo = ELabel.html(AppContext.getMessage(BillingI18nEnum.OPT_PLAN_STORAGE,
                usedStorageTxt, FileUtils.getVolumeDisplay(currentBillingPlan.getVolume())))
                .withStyleName(UIConstants.FIELD_NOTE);

        UserService userService = AppContextUtil.getSpringBean(UserService.class);
        numOfActiveUsers = userService.getTotalActiveUsersInAccount(AppContext.getAccountId());
        ELabel userInfo = ELabel.html(AppContext.getMessage(BillingI18nEnum.OPT_PLAN_USERS,
                numOfActiveUsers, currentBillingPlan.getNumusers())).withStyleName(UIConstants.FIELD_NOTE);

        currentPlanLayout.addComponent(new MHorizontalLayout(projectInfo, storageInfo, userInfo));
    }

    private class UpdateBillingPlanWindow extends MWindow {
        private BillingPlan chosenPlan;

        private MVerticalLayout contentLayout;

        UpdateBillingPlanWindow(BillingPlan billingPlan) {
            super(AppContext.getMessage(BillingI18nEnum.VIEW_CHANGE_BILLING_PLAN_TITLE));
            this.chosenPlan = billingPlan;
            contentLayout = new MVerticalLayout();
            initUI();

            this.withModal(true).withResizable(false).withWidth("400px").withContent(contentLayout).withCenter();
        }

        private void initUI() {
            Label header = new Label(AppContext.getMessage(BillingI18nEnum.QUESTION_CHANGE_PLAN));
            contentLayout.with(header).withAlign(header, Alignment.MIDDLE_CENTER);

            Label chosenPlanType = new Label(chosenPlan.getBillingtype());
            chosenPlanType.addStyleName("billing-type");
            contentLayout.addComponent(chosenPlanType);

            ELabel chosenPlanPrice = ELabel.html(AppContext.getMessage(BillingI18nEnum.FORM_BILLING_PRICE, chosenPlan.getPricing()))
                    .withStyleName("billing-price-lbl");
            contentLayout.addComponent(chosenPlanPrice);


            final MButton cancelBtn = new MButton(AppContext.getMessage(GenericI18Enum.BUTTON_CANCEL), clickEvent -> close())
                    .withStyleName(UIConstants.BUTTON_OPTION);

            final MButton saveBtn = new MButton(AppContext.getMessage(GenericI18Enum.BUTTON_OK), clickEvent -> {
                if (chosenPlan.getNumprojects() < numOfActiveProjects
                        || chosenPlan.getNumusers() < numOfActiveUsers
                        || chosenPlan.getVolume() * 1000 < usedStorageVolume
                        || chosenPlan.getVolume() * 1000 < usedStorageVolume) {
                    NotificationUtil.showErrorNotification(AppContext.getMessage(BillingI18nEnum.OPT_CANNOT_CHANGE_PLAN));
                    close();
                    return;
                }

                if (chosenPlan.getBillingtype().equals(AppContext.getBillingAccount().getBillingPlan().getBillingtype())) {
                    NotificationUtil.showErrorNotification("Selected plan is the same with the current plan");
                    return;
                }

                billingService.updateBillingPlan(AppContext.getAccountId(), AppContext.getBillingAccount().getBillingPlan(), chosenPlan);
                updateBillingPlan();
                close();
            }).withStyleName(UIConstants.BUTTON_ACTION).withIcon(FontAwesome.SAVE);

            SimpleBillingAccount billingAccount = AppContext.getBillingAccount();
            if (billingAccount.isNotActive()) {
                BrowserWindowOpener opener = new BrowserWindowOpener(chosenPlan.getShoppingurl() + "?referrer=" +
                        EnDecryptHelper.encryptText(AppContext.getAccountId() + ""));
                opener.extend(saveBtn);
            }

            MHorizontalLayout controlBtns = new MHorizontalLayout(cancelBtn, saveBtn).withMargin(true);
            contentLayout.with(controlBtns).withAlign(controlBtns, Alignment.MIDDLE_RIGHT);
        }

        private void updateBillingPlan() {
            AppContext.getBillingAccount().setBillingPlan(chosenPlan);
            displayView();
        }
    }
}
