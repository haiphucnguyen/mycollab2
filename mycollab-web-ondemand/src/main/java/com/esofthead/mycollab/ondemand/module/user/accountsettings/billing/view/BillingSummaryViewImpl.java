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
package com.esofthead.mycollab.ondemand.module.user.accountsettings.billing.view;

import com.esofthead.mycollab.common.i18n.GenericI18Enum;
import com.esofthead.mycollab.core.utils.FileUtils;
import com.esofthead.mycollab.eventmanager.EventBusFactory;
import com.esofthead.mycollab.module.billing.AccountStatusConstants;
import com.esofthead.mycollab.module.billing.service.BillingService;
import com.esofthead.mycollab.module.ecm.service.DriveInfoService;
import com.esofthead.mycollab.module.project.service.ProjectService;
import com.esofthead.mycollab.module.user.accountsettings.localization.BillingI18nEnum;
import com.esofthead.mycollab.module.user.accountsettings.view.events.AccountBillingEvent;
import com.esofthead.mycollab.module.user.domain.BillingPlan;
import com.esofthead.mycollab.module.user.domain.SimpleBillingAccount;
import com.esofthead.mycollab.module.user.service.UserService;
import com.esofthead.mycollab.spring.AppContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.mvp.AbstractPageView;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.esofthead.mycollab.vaadin.mvp.view.AbstractLazyPageView;
import com.esofthead.mycollab.vaadin.ui.ELabel;
import com.esofthead.mycollab.vaadin.ui.NotificationUtil;
import com.esofthead.mycollab.vaadin.web.ui.UIConstants;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.layouts.MCssLayout;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;

import java.util.List;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
@SuppressWarnings("serial")
@ViewComponent
public class BillingSummaryViewImpl extends AbstractLazyPageView implements BillingSummaryView {
    private static final Logger LOG = LoggerFactory.getLogger(BillingSummaryViewImpl.class);

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
        boolean isTrial = AccountStatusConstants.TRIAL.equals(billingAccount.getStatus());
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

            MButton selectPlanBtn;
            if (isTrial && currentBillingPlan.getId().equals(plan.getId())) {
                selectPlanBtn = new MButton(AppContext.getMessage(GenericI18Enum.BUTTON_SELECT),
                        clickEvent -> UI.getCurrent().addWindow(new UpdateBillingPlanWindow(plan))).withStyleName(UIConstants.BUTTON_DANGER);
            } else {
                selectPlanBtn = new MButton(AppContext.getMessage(GenericI18Enum.BUTTON_SELECT),
                        clickEvent -> UI.getCurrent().addWindow(new UpdateBillingPlanWindow(plan))).withStyleName(UIConstants.BUTTON_ACTION);
            }

            singlePlan.with(billingType, billingPrice, billingUser, billingStorage, billingProject, selectPlanBtn);
            plansLayout.with(singlePlan).expand(singlePlan);
        }

        layout.addComponent(plansLayout);

        String billingFAQText = "<div class='prig-bottom'><div class='prig-bottom-cnt'><div class='pri-bott-coll pri-coll-1'><div class='pri-bott-block'><h2>How does the 30-day trial work?</h2><p>When you sign up, you are automatically enrolled in a free30-day trial that gives you unrestricted access to all the great features MyCollab has to offer. During your free trial, you have the option to cancel at any time. When your trial ends, you can choose to remain on your current package, upgrade to another one with more users and storages, downgrade, or cancel.</p></div><div class='pri-bott-block'><h2>Can I upgrade my plan at any time?</h2><p>Yes, you may upgrade your plan at any time. Choose a plan that suits your needs today, and upgrade as the numbers of users and spaces grow. After you upgrade the changes will be updated on your next billing cycle.</p></div><div class='pri-bott-block'><h2>What if I want to downgrade my plan?</h2><p>You can downgrade your package at any time as long as the one you select is consistent with your current usage. For example, if you currently have 30 users, you must delete 10 of them before you can downgrade to a Compact package that allows for up to 20 users.</p></div></div><div class='pri-bott-coll'><div class='pri-bott-block prig-block-1'><h2>Do I have to provide payment information up front?</h2><p>No, you can choose the \"manual payment\" option in the billing information panel. At the end of the trial, we will send you an email to remind you to submit payment information. You can then choose if and how you want to pay.</p></div><div class='pri-bott-block prig-block-2'><h2>What payment options are available?</h2><p>We accept Visa, Mastercard, and American Express for automatic payments. We also accept PayPal, checks, and bankwires for manual payments.</p></div><div class='pri-bott-block'><h2>Do I have to sign a long-term contract?</h2><p>No, there are no contracts. You can choose to pay monthly,or you can pay in advance for a year and get 2 months free (12 months for the price of 10). By paying in advance, you can submit one expense report or purchase order for the year.</p></div></div></div><div class='clear'></div></div>";
        this.with(layout, ELabel.html(billingFAQText));

        loadCurrentPlan();
    }


    private void loadCurrentPlan() {
        BillingPlan currentBillingPlan = AppContext.getBillingAccount().getBillingPlan();

        ELabel introText = ELabel.h2("Your current plan: " + currentBillingPlan.getBillingtype());
        currentPlanLayout.addComponent(introText);

        ELabel currentBillingPrice = ELabel.h3("$" + currentBillingPlan.getPricing() + "/ Month");
        currentPlanLayout.addComponent(currentBillingPrice);

        ProjectService projectService = AppContextUtil.getSpringBean(ProjectService.class);
        numOfActiveProjects = projectService.getTotalActiveProjectsInAccount(AppContext.getAccountId());

        ELabel projectInfo = new ELabel(String.format("<span class='infoTitle'>Projects:</span> %d of %d</div>",
                numOfActiveProjects, currentBillingPlan.getNumprojects()), ContentMode.HTML).withStyleName(UIConstants.FIELD_NOTE);

        DriveInfoService driveInfoService = AppContextUtil.getSpringBean(DriveInfoService.class);
        usedStorageVolume = driveInfoService.getUsedStorageVolume(AppContext.getAccountId());
        String usedStorageTxt = FileUtils.getVolumeDisplay(usedStorageVolume);
        ELabel storageInfo = new ELabel(String.format("<span class='infoTitle'>Storage:</span> %s of %s</div>",
                usedStorageTxt, FileUtils.getVolumeDisplay(currentBillingPlan.getVolume())), ContentMode.HTML)
                .withStyleName(UIConstants.FIELD_NOTE);

        UserService userService = AppContextUtil.getSpringBean(UserService.class);
        numOfActiveUsers = userService.getTotalActiveUsersInAccount(AppContext.getAccountId());
        ELabel userInfo = new ELabel(String.format("<span class='infoTitle'>Users:</span> %d of %d</div>",
                numOfActiveUsers, currentBillingPlan.getNumusers()), ContentMode.HTML).withStyleName(UIConstants.FIELD_NOTE);

        currentPlanLayout.addComponent(new MHorizontalLayout(projectInfo, storageInfo, userInfo));
    }

    private class UpdateBillingPlanWindow extends Window {
        private BillingPlan chosenPlan;

        private MVerticalLayout contentLayout;

        UpdateBillingPlanWindow(BillingPlan billingPlan) {
            this.chosenPlan = billingPlan;
            this.setWidth("400px");
            this.setResizable(false);
            this.setModal(true);

            contentLayout = new MVerticalLayout();
            this.setContent(contentLayout);
            initUI();
            this.center();
            this.setCaption(AppContext.getMessage(BillingI18nEnum.VIEW_CHANGE_BILLING_PLAN_TITLE));
        }

        private void initUI() {
            Label header = new Label(AppContext.getMessage(BillingI18nEnum.QUESTION_CHANGE_PLAN));
            contentLayout.with(header).withAlign(header, Alignment.MIDDLE_CENTER);

            Label chosenPlanType = new Label(chosenPlan.getBillingtype());
            chosenPlanType.addStyleName("billing-type");
            contentLayout.addComponent(chosenPlanType);

            Label chosenPlanPrice = new Label(AppContext.getMessage(BillingI18nEnum.FORM_BILLING_PRICE, chosenPlan.getPricing()), ContentMode.HTML);
            chosenPlanPrice.addStyleName("billing-price-lbl");
            contentLayout.addComponent(chosenPlanPrice);


            final MButton cancelBtn = new MButton(AppContext.getMessage(GenericI18Enum.BUTTON_CANCEL), clickEvent ->
                    close()).withStyleName(UIConstants.BUTTON_OPTION);

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

                billingService.updateBillingPlan(AppContext.getAccountId(), chosenPlan.getId());

                updateBillingPlan();
                close();
            }).withStyleName(UIConstants.BUTTON_ACTION).withIcon(FontAwesome.SAVE);

            MHorizontalLayout controlBtns = new MHorizontalLayout(cancelBtn, saveBtn).withMargin(true);
            contentLayout.with(controlBtns).withAlign(controlBtns, Alignment.MIDDLE_RIGHT);
        }

        private void updateBillingPlan() {
            AppContext.getBillingAccount().setBillingPlan(chosenPlan);
            loadCurrentPlan();
        }
    }
}
