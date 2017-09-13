package com.mycollab.ondemand.module.user.accountsettings.billing.view;

import com.mycollab.common.i18n.DayI18nEnum;
import com.mycollab.common.i18n.GenericI18Enum;
import com.mycollab.configuration.EnDecryptHelper;
import com.mycollab.core.utils.FileUtils;
import com.mycollab.eventmanager.EventBusFactory;
import com.mycollab.module.ecm.service.DriveInfoService;
import com.mycollab.module.file.service.AbstractStorageService;
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
import com.mycollab.vaadin.AppUI;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.mvp.ViewComponent;
import com.mycollab.vaadin.ui.ELabel;
import com.mycollab.vaadin.ui.MyCollabSession;
import com.mycollab.vaadin.ui.NotificationUtil;
import com.mycollab.vaadin.ui.UIConstants;
import com.mycollab.vaadin.web.ui.AbstractLazyPageView;
import com.mycollab.vaadin.web.ui.WebThemes;
import com.vaadin.server.BrowserWindowOpener;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Image;
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
    private MHorizontalLayout billingPlanListLayout;
    private MHorizontalLayout switchBillingModeLayout;

    private boolean isMonthlyView = true;
    private Integer numOfActiveProjects = 0;
    private Integer numOfActiveUsers = 0;
    private Long usedStorageVolume = 0L;

    public BillingSummaryViewImpl() {
        this.withMargin(true).withSpacing(true);
        this.billingService = AppContextUtil.getSpringBean(BillingService.class);
    }

    @Override
    protected void displayView() {
        removeAllComponents();
        MCssLayout layout = new MCssLayout().withStyleName("billing-setting").withFullWidth();
        MHorizontalLayout topLayout = new MHorizontalLayout().withFullWidth();

        currentPlanLayout = new MVerticalLayout().withMargin(new MarginInfo(true, false, true, false)).withStyleName
                ("current-plan-information").withFullWidth();
        currentPlanLayout.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        topLayout.with(currentPlanLayout).withAlign(currentPlanLayout, Alignment.MIDDLE_CENTER).expand(currentPlanLayout);

        MVerticalLayout faqLayout = new MVerticalLayout().withWidth("285px").withFullHeight().withStyleName("left-border-dotted-layout");

        if (UserUIContext.isAdmin()) {
            MButton cancelBtn = new MButton(UserUIContext.getMessage(BillingI18nEnum.BUTTON_CANCEL_ACCOUNT),
                    clickEvent -> EventBusFactory.getInstance().post(new AccountBillingEvent.CancelAccount(this, null)))
                    .withStyleName(WebThemes.BUTTON_DANGER);
            faqLayout.with(cancelBtn);
        }

        Label spaceLbl = new Label();
        faqLayout.with(ELabel.h3(UserUIContext.getMessage(BillingI18nEnum.HELP_QUESTION)), ELabel.html(UserUIContext
                .getMessage(BillingI18nEnum.HELP_INFO)), spaceLbl).expand(spaceLbl);

        topLayout.with(faqLayout).withAlign(faqLayout, Alignment.TOP_RIGHT);
        layout.addComponent(topLayout);
        billingPlanListLayout = new MHorizontalLayout().withSpacing(false).withFullWidth().withStyleName("billing-plan-list");
        layout.addComponent(billingPlanListLayout);
        ELabel bankWireTransfer = ELabel.h3(UserUIContext.getMessage(BillingI18nEnum.OPT_PAYMENT_BANKWIRE));
        BillingPlan currentBillingPlan = AppUI.Companion.getBillingAccount().getBillingPlan();
        MButton bankwireBtn = new MButton(UserUIContext.getMessage(GenericI18Enum.BUTTON_SELECT),
                event -> UI.getCurrent().addWindow(new BankwireSelectionWindow(currentBillingPlan)))
                .withIcon(FontAwesome.BANK).withStyleName(WebThemes.BUTTON_LINK);
        ELabel conditionLbl = ELabel.html(UserUIContext.getMessage(BillingI18nEnum.OPT_PAYMENT_BANKWIRE_DESC));
        this.with(layout, new MHorizontalLayout(bankWireTransfer, bankwireBtn).alignAll(Alignment.MIDDLE_CENTER)
                .withMargin(new MarginInfo(true, false, false, false)), conditionLbl, ELabel.html(UserUIContext.getMessage(OPTION_BILLING_FAQ)));
        loadCurrentPlan();
        displayBillingMonthly();
    }

    private void displayBillingMonthly() {
        billingPlanListLayout.removeAllComponents();

        BillingPlan currentBillingPlan = AppUI.Companion.getBillingAccount().getBillingPlan();
        SimpleBillingAccount billingAccount = AppUI.Companion.getBillingAccount();
        List<BillingPlan> availablePlans = billingService.getAvailablePlans();

        for (int i = 0; i < availablePlans.size(); i++) {
            MVerticalLayout singlePlan = new MVerticalLayout().withMargin(false).withStyleName("billing-plan");
            singlePlan.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);

            if ((i + 1) % 2 == 0) {
                singlePlan.addStyleName("even");
            }

            final BillingPlan plan = availablePlans.get(i);

            ELabel billingType = ELabel.h3(plan.getBillingtype()).withStyleName("billing-type");
            Label billingPrice = ELabel.html("<span class='billing-price'>$" + plan.getPricing() + "</span>/" + UserUIContext.getMessage(DayI18nEnum.OPT_MONTH))
                    .withStyleName("billing-price-lbl").withWidthUndefined();
            Label billingUser = ELabel.html("<span class='billing-user'>" + plan.getNumusers() + "</span>&nbsp;" +
                    "Users").withWidthUndefined();
            String planVolume = FileUtils.getVolumeDisplay(plan.getVolume());
            Label billingStorage = ELabel.html("<span class='billing-storage'>" + planVolume + "</span>&nbsp;Storage").withWidthUndefined();
            Label billingProject = ELabel.html("<span class='billing-project'>" + plan.getNumprojects() +
                    "</span>&nbsp;Projects").withWidthUndefined();

            if (currentBillingPlan.getId().equals(plan.getId())) {
                if (billingAccount.isNotActive()) {
                    MButton selectPlanBtn = new MButton(UserUIContext.getMessage(GenericI18Enum.ACTION_CHARGE))
                            .withStyleName(WebThemes.BUTTON_DANGER)
                            .withIcon(FontAwesome.CREDIT_CARD);
                    BrowserWindowOpener opener = new BrowserWindowOpener(plan.getShoppingurl() +
                            "?referrer=" + EnDecryptHelper.encryptTextWithEncodeFriendly(AppUI.getAccountId() + ";" + currentBillingPlan.getId()));

                    opener.extend(selectPlanBtn);
                    singlePlan.with(billingType, billingPrice, billingUser, billingStorage, billingProject, selectPlanBtn);
                } else {
                    singlePlan.with(billingType, billingPrice, billingUser, billingStorage, billingProject,
                            new MButton(UserUIContext.getMessage(GenericI18Enum.OPT_SELECTED))
                                    .withStyleName(WebThemes.BUTTON_OPTION).withIcon(FontAwesome.CREDIT_CARD));
                }
            } else {
                boolean isDowngrade = (plan.getPricing() < currentBillingPlan.getPricing());
                String actionTxt = isDowngrade ? UserUIContext.getMessage(BillingI18nEnum.ACTION_DOWNGRADE) : UserUIContext.getMessage(BillingI18nEnum.ACTION_UPGRADE);
                String style = isDowngrade ? WebThemes.BUTTON_OPTION : WebThemes.BUTTON_ACTION;
                MButton selectPlanBtn = new MButton(actionTxt,
                        clickEvent -> UI.getCurrent().addWindow(new UpdateBillingPlanWindow(plan)))
                        .withStyleName(style).withIcon(FontAwesome.CREDIT_CARD);
                singlePlan.with(billingType, billingPrice, billingUser, billingStorage, billingProject, selectPlanBtn);
            }

            billingPlanListLayout.with(singlePlan).expand(singlePlan);
        }
        showToggleBillingMode();
    }

    private void displayBillingYearly() {
        billingPlanListLayout.removeAllComponents();

        BillingPlan currentBillingPlan = AppUI.Companion.getBillingAccount().getBillingPlan();
        SimpleBillingAccount billingAccount = AppUI.Companion.getBillingAccount();
        List<BillingPlan> availablePlans = billingService.getAvailablePlans();

        for (int i = 0; i < availablePlans.size(); i++) {
            MVerticalLayout singlePlan = new MVerticalLayout().withMargin(false).withStyleName("billing-plan");
            singlePlan.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);

            if ((i + 1) % 2 == 0) {
                singlePlan.addStyleName("even");
            }

            final BillingPlan plan = availablePlans.get(i);

            ELabel billingType = ELabel.h3(plan.getBillingtype()).withStyleName("billing-type");
            Label billingPrice = ELabel.html("<span class='billing-price'>$" + Math.round(plan.getPricing() * 10) + "</span>/" +
                    UserUIContext.getMessage(DayI18nEnum.OPT_YEAR)).withStyleName("billing-price-lbl").withWidthUndefined();
            Label billingUser = ELabel.html("<span class='billing-user'>" + plan.getNumusers() + "</span>&nbsp;" +
                    "Users").withWidthUndefined();
            String planVolume = FileUtils.getVolumeDisplay(plan.getVolume());
            Label billingStorage = ELabel.html("<span class='billing-storage'>" + planVolume + "</span>&nbsp;Storage").withWidthUndefined();
            Label billingProject = ELabel.html("<span class='billing-project'>" + plan.getNumprojects() +
                    "</span>&nbsp;Projects").withWidthUndefined();

            if (currentBillingPlan.getId().equals(plan.getId())) {
                if (billingAccount.isNotActive()) {
                    MButton selectPlanBtn = new MButton(UserUIContext.getMessage(GenericI18Enum.ACTION_CHARGE))
                            .withStyleName(WebThemes.BUTTON_DANGER)
                            .withIcon(FontAwesome.CREDIT_CARD);
                    BrowserWindowOpener opener = new BrowserWindowOpener(plan.getYearlyshoppingurl() +
                            "?referrer=" + EnDecryptHelper.encryptTextWithEncodeFriendly(AppUI.getAccountId() + ";" + currentBillingPlan.getId()));

                    opener.extend(selectPlanBtn);
                    singlePlan.with(billingType, billingPrice, billingUser, billingStorage, billingProject, selectPlanBtn);
                } else {
                    singlePlan.with(billingType, billingPrice, billingUser, billingStorage, billingProject,
                            new MButton(UserUIContext.getMessage(GenericI18Enum.OPT_SELECTED))
                                    .withStyleName(WebThemes.BUTTON_OPTION).withIcon(FontAwesome.CREDIT_CARD));
                }
            } else {
                boolean isDowngrade = (plan.getPricing() < currentBillingPlan.getPricing());
                String actionTxt = isDowngrade ? UserUIContext.getMessage(BillingI18nEnum.ACTION_DOWNGRADE) : UserUIContext.getMessage(BillingI18nEnum.ACTION_UPGRADE);
                String style = isDowngrade ? WebThemes.BUTTON_OPTION : WebThemes.BUTTON_ACTION;
                MButton selectPlanBtn = new MButton(actionTxt,
                        clickEvent -> UI.getCurrent().addWindow(new UpdateBillingPlanWindow(plan)))
                        .withStyleName(style).withIcon(FontAwesome.CREDIT_CARD);
                singlePlan.with(billingType, billingPrice, billingUser, billingStorage, billingProject, selectPlanBtn);
            }

            billingPlanListLayout.with(singlePlan).expand(singlePlan);
        }
        showToggleBillingMode();
    }

    private void loadCurrentPlan() {
        currentPlanLayout.removeAllComponents();
        AbstractStorageService storageService = AppContextUtil.getSpringBean(AbstractStorageService.class);
        currentPlanLayout.with(new Image(null, new ExternalResource(storageService.generateAssetRelativeLink("icons/fs_button05.gif"))));
        BillingPlan currentBillingPlan = AppUI.Companion.getBillingAccount().getBillingPlan();

        ELabel introText = ELabel.h2(UserUIContext.getMessage(BillingI18nEnum.OPT_CURRENT_PLAN, currentBillingPlan.getBillingtype()));
        SimpleBillingSubscription subscription = (SimpleBillingSubscription) MyCollabSession.getCurrentUIVariable("subscription");
        if (subscription == null) {
            MButton selectPlanBtn = new MButton(UserUIContext.getMessage(GenericI18Enum.ACTION_CHARGE)).withStyleName(WebThemes.BUTTON_DANGER)
                    .withIcon(FontAwesome.CREDIT_CARD);
            BrowserWindowOpener opener = new BrowserWindowOpener(currentBillingPlan.getShoppingurl() + "?referrer=" +
                    EnDecryptHelper.encryptTextWithEncodeFriendly(AppUI.getAccountId() + ";" + currentBillingPlan.getId()));
            opener.extend(selectPlanBtn);
            currentPlanLayout.with(new MHorizontalLayout(introText, selectPlanBtn));
        } else {
            MButton historyBtn = new MButton(UserUIContext.getMessage(AdminI18nEnum.VIEW_BILLING_HISTORY),
                    clickEvent -> EventBusFactory.getInstance().post(new AccountBillingEvent.GotoHistory(this, null)))
                    .withStyleName(WebThemes.BUTTON_ACTION, ValoTheme.BUTTON_TINY);
            MHorizontalLayout buttonControls = new MHorizontalLayout(introText, historyBtn);
            currentPlanLayout.addComponent(buttonControls);
            if (!subscription.isValid()) {
                MButton retryChargeBtn = new MButton(UserUIContext.getMessage(GenericI18Enum.ACTION_CHARGE))
                        .withStyleName(WebThemes.BUTTON_DANGER, ValoTheme.BUTTON_TINY).withIcon(FontAwesome.CREDIT_CARD);
                BrowserWindowOpener paymentOpener = new BrowserWindowOpener(subscription.getSubscriptioncustomerurl());
                paymentOpener.extend(retryChargeBtn);
                buttonControls.with(retryChargeBtn);
            }
        }

        if (subscription != null) {
            ELabel expiredDateLbl = ELabel.h3(" | " + UserUIContext.getMessage(BillingI18nEnum.OPT_EXPIRED_DATE) + ": " +
                    UserUIContext.formatDate(subscription.getExpireDate()));
            if (!subscription.isValid()) {
                expiredDateLbl.withStyleName(WebThemes.LABEL_OVERDUE);
            }
            currentPlanLayout.addComponent(expiredDateLbl);
        }

        ProjectService projectService = AppContextUtil.getSpringBean(ProjectService.class);
        numOfActiveProjects = projectService.getTotalActiveProjectsInAccount(AppUI.getAccountId());

        ELabel projectInfo = ELabel.html(UserUIContext.getMessage(BillingI18nEnum.OPT_PLAN_NUM_PROJECTS,
                numOfActiveProjects, currentBillingPlan.getNumprojects())).withStyleName(UIConstants.FIELD_NOTE);

        DriveInfoService driveInfoService = AppContextUtil.getSpringBean(DriveInfoService.class);
        usedStorageVolume = driveInfoService.getUsedStorageVolume(AppUI.getAccountId());
        String usedStorageTxt = FileUtils.getVolumeDisplay(usedStorageVolume);
        ELabel storageInfo = ELabel.html(UserUIContext.getMessage(BillingI18nEnum.OPT_PLAN_STORAGE,
                usedStorageTxt, FileUtils.getVolumeDisplay(currentBillingPlan.getVolume())))
                .withStyleName(UIConstants.FIELD_NOTE);

        UserService userService = AppContextUtil.getSpringBean(UserService.class);
        numOfActiveUsers = userService.getTotalActiveUsersInAccount(AppUI.getAccountId());
        ELabel userInfo = ELabel.html(UserUIContext.getMessage(BillingI18nEnum.OPT_PLAN_USERS,
                numOfActiveUsers, currentBillingPlan.getNumusers())).withStyleName(UIConstants.FIELD_NOTE);

        switchBillingModeLayout = new MHorizontalLayout().withMargin(new MarginInfo(true, false, false, false));
        switchBillingModeLayout.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        currentPlanLayout.with(new MHorizontalLayout(projectInfo, storageInfo, userInfo), switchBillingModeLayout);
    }

    private void showToggleBillingMode() {
        switchBillingModeLayout.removeAllComponents();
        if (isMonthlyView) {
            switchBillingModeLayout.with(ELabel.h3(UserUIContext.getMessage(BillingI18nEnum.OPT_DISCOUNT_YEARLY_SUBSCRIPTION)),
                    new MButton(UserUIContext.getMessage(GenericI18Enum.ACTION_CHANGE), event -> {
                        isMonthlyView = false;
                        displayBillingYearly();
                    }).withStyleName(WebThemes.BUTTON_LINK));
        } else {
            switchBillingModeLayout.with(ELabel.h3(UserUIContext.getMessage(BillingI18nEnum.OPT_SWITCH_MONTHLY_SUBSCRIPTION)),
                    new MButton(UserUIContext.getMessage(GenericI18Enum.ACTION_CHANGE), event -> {
                        isMonthlyView = true;
                        displayBillingMonthly();
                    }).withStyleName(WebThemes.BUTTON_LINK));
        }
    }

    private class UpdateBillingPlanWindow extends MWindow {
        private BillingPlan chosenPlan;

        private MVerticalLayout contentLayout;

        UpdateBillingPlanWindow(BillingPlan billingPlan) {
            super(UserUIContext.getMessage(BillingI18nEnum.VIEW_CHANGE_BILLING_PLAN_TITLE));
            this.chosenPlan = billingPlan;
            contentLayout = new MVerticalLayout();
            initUI();

            this.withModal(true).withResizable(false).withWidth("400px").withContent(contentLayout).withCenter();
        }

        private void initUI() {
            Label header = new Label(UserUIContext.getMessage(BillingI18nEnum.QUESTION_CHANGE_PLAN));
            contentLayout.with(header).withAlign(header, Alignment.MIDDLE_CENTER);

            Label chosenPlanType = new Label(chosenPlan.getBillingtype());
            chosenPlanType.addStyleName("billing-type");
            contentLayout.addComponent(chosenPlanType);

            ELabel chosenPlanPrice = ELabel.html(UserUIContext.getMessage(BillingI18nEnum.FORM_BILLING_PRICE, chosenPlan.getPricing()))
                    .withStyleName("billing-price-lbl");
            contentLayout.addComponent(chosenPlanPrice);


            final MButton cancelBtn = new MButton(UserUIContext.getMessage(GenericI18Enum.BUTTON_CANCEL), clickEvent -> close())
                    .withStyleName(WebThemes.BUTTON_OPTION);

            final MButton saveBtn = new MButton(UserUIContext.getMessage(GenericI18Enum.BUTTON_OK), clickEvent -> {
                if (chosenPlan.getNumprojects() < numOfActiveProjects
                        || chosenPlan.getNumusers() < numOfActiveUsers
                        || chosenPlan.getVolume() * 1000 < usedStorageVolume
                        || chosenPlan.getVolume() * 1000 < usedStorageVolume) {
                    NotificationUtil.showErrorNotification(UserUIContext.getMessage(BillingI18nEnum.OPT_CANNOT_CHANGE_PLAN));
                    close();
                    return;
                }

                if (chosenPlan.getBillingtype().equals(AppUI.Companion.getBillingAccount().getBillingPlan().getBillingtype())) {
                    NotificationUtil.showErrorNotification("Selected plan is the same with the current plan");
                    return;
                }

                billingService.updateBillingPlan(AppUI.getAccountId(), AppUI.Companion.getBillingAccount().getBillingPlan(), chosenPlan);
                updateBillingPlan();
                close();
            }).withStyleName(WebThemes.BUTTON_ACTION).withIcon(FontAwesome.SAVE);

            SimpleBillingAccount billingAccount = AppUI.Companion.getBillingAccount();
            if (billingAccount.isNotActive()) {
                BrowserWindowOpener opener = new BrowserWindowOpener(chosenPlan.getShoppingurl() + "?referrer=" +
                        EnDecryptHelper.encryptTextWithEncodeFriendly(AppUI.getAccountId() + ";" + chosenPlan.getId()));
                opener.extend(saveBtn);
            }

            MHorizontalLayout controlBtns = new MHorizontalLayout(cancelBtn, saveBtn).withMargin(true);
            contentLayout.with(controlBtns).withAlign(controlBtns, Alignment.MIDDLE_RIGHT);
        }

        private void updateBillingPlan() {
            AppUI.Companion.getBillingAccount().setBillingPlan(chosenPlan);
            displayView();
        }
    }
}
