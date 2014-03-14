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
package com.esofthead.mycollab.premium.module.user.accountsettings.billing.view;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esofthead.mycollab.eventmanager.EventBus;
import com.esofthead.mycollab.module.billing.service.BillingService;
import com.esofthead.mycollab.module.ecm.ResourceUtils;
import com.esofthead.mycollab.module.ecm.service.DriveInfoService;
import com.esofthead.mycollab.module.project.service.ProjectService;
import com.esofthead.mycollab.module.user.accountsettings.view.events.AccountBillingEvent;
import com.esofthead.mycollab.module.user.domain.BillingPlan;
import com.esofthead.mycollab.module.user.service.UserService;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.mvp.AbstractPageView;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.esofthead.mycollab.vaadin.ui.MyCollabResource;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.vaadin.server.Sizeable;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

/**
 * 
 * @author MyCollab Ltd.
 * @since 1.0
 * 
 */
@SuppressWarnings("serial")
@ViewComponent
public class BillingSummaryViewImpl extends AbstractPageView implements
        BillingSummaryView {
    private static Logger log = LoggerFactory
            .getLogger(BillingSummaryViewImpl.class);

    private final BillingService billingService;

    private VerticalLayout currentPlan = null;

    private Integer numOfActiveProjects = 0;

    private Integer numOfActiveUsers = 0;

    private Long usedStorageVolume = 0L;

    public BillingSummaryViewImpl() {
        super();

        this.setMargin(true);
        this.billingService = ApplicationContextUtil
                .getSpringBean(BillingService.class);
        initUI();
        this.setImmediate(true);
    }

    private void initUI() {
        CssLayout layout = new CssLayout();
        layout.addStyleName("billing-setting");
        layout.setWidth("100%");

        HorizontalLayout topLayout = new HorizontalLayout();
        topLayout.setWidth("100%");
        topLayout.setMargin(true);

        currentPlan = new VerticalLayout();
        currentPlan.setWidth("100%");
        currentPlan.setSpacing(true);
        currentPlan.addStyleName("current-plan-information");
        currentPlan.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);

        topLayout.addComponent(currentPlan);
        topLayout.setComponentAlignment(currentPlan, Alignment.MIDDLE_CENTER);
        topLayout.setExpandRatio(currentPlan, 1.0f);

        VerticalLayout FAQLayout = new VerticalLayout();
        FAQLayout.setMargin(new MarginInfo(false, true, false, true));
        FAQLayout.setSpacing(true);

        if (AppContext.isAdmin()) {
            Button cancelBtn = new Button("Cancel entire account",
                    new Button.ClickListener() {

                        @Override
                        public void buttonClick(ClickEvent event) {
                            EventBus.getInstance().fireEvent(
                                    new AccountBillingEvent.CancelAccount(
                                            BillingSummaryViewImpl.this, null));
                        }
                    });
            cancelBtn.setStyleName(UIConstants.THEME_BLANK_LINK);

            FAQLayout.addComponent(cancelBtn);
        }

        FAQLayout.setWidth("285px");
        FAQLayout.addStyleName("faq-layout");
        Label header = new Label("Question?");
        header.addStyleName("faq-header");
        FAQLayout.addComponent(header);

        Label contentText = new Label(
                "For specific questions related to billing, features, plans, upgrades, downgrades or cancellations, please send email to <a href=\"mailto:support@mycollab.com\">support@mycollab.com</a>",
                ContentMode.HTML);
        contentText.addStyleName("faq-content");
        FAQLayout.addComponent(contentText);

        topLayout.addComponent(FAQLayout);
        topLayout.setComponentAlignment(FAQLayout, Alignment.MIDDLE_CENTER);

        layout.addComponent(topLayout);

        HorizontalLayout plansList = new HorizontalLayout();
        plansList.setWidth("100%");
        plansList.addStyleName("billing-plan-list");

        List<BillingPlan> availablePlans = this.billingService
                .getAvailablePlans();
        int listSize = availablePlans.size();

        for (int i = 0; i < listSize; i++) {
            VerticalLayout singlePlan = new VerticalLayout();
            singlePlan.addStyleName("billing-plan");
            singlePlan.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);

            if ((i + 1) % 2 == 0) {
                singlePlan.addStyleName("even");
            }

            final BillingPlan plan = availablePlans.get(i);

            Label billingType = new Label(plan.getBillingtype());
            billingType.addStyleName("billing-type");
            // billingType.setWidth(Sizeable.SIZE_UNDEFINED,
            // Sizeable.Unit.PIXELS);
            singlePlan.addComponent(billingType);

            Label billingPrice = new Label("<span class='billing-price'>$"
                    + plan.getPricing() + "</span>/month", ContentMode.HTML);
            billingPrice.addStyleName("billing-price-lbl");
            billingPrice
                    .setWidth(Sizeable.SIZE_UNDEFINED, Sizeable.Unit.PIXELS);
            singlePlan.addComponent(billingPrice);

            Label billingUser = new Label("<span class='billing-user'>"
                    + plan.getNumusers() + "</span>&nbsp;Users",
                    ContentMode.HTML);
            billingUser.setWidth(Sizeable.SIZE_UNDEFINED, Sizeable.Unit.PIXELS);
            singlePlan.addComponent(billingUser);

            String planVolume = ResourceUtils
                    .getVolumeDisplay(plan.getVolume());

            Label billingStorage = new Label("<span class='billing-storage'>"
                    + planVolume + "</span>&nbsp;Storage", ContentMode.HTML);
            billingStorage.setWidth(Sizeable.SIZE_UNDEFINED,
                    Sizeable.Unit.PIXELS);
            singlePlan.addComponent(billingStorage);

            Label billingProject = new Label("<span class='billing-project'>"
                    + plan.getNumprojects() + "</span>&nbsp;Project"
                    + (plan.getNumprojects() > 1 ? "s" : ""), ContentMode.HTML);
            billingProject.setWidth(Sizeable.SIZE_UNDEFINED,
                    Sizeable.Unit.PIXELS);
            singlePlan.addComponent(billingProject);

            Label billingBugTracking;
            if (plan.getHasbugenable()) {
                billingBugTracking = new Label("Bugs Tracking",
                        ContentMode.HTML);
            } else {
                billingBugTracking = new Label("&nbsp;", ContentMode.HTML);
            }
            billingBugTracking.addStyleName("billing-bug-feature");
            billingBugTracking.setWidth(Sizeable.SIZE_UNDEFINED,
                    Sizeable.Unit.PIXELS);
            singlePlan.addComponent(billingBugTracking);

            Label billingTimeTracking;
            if (plan.getHastimetracking()) {
                billingTimeTracking = new Label("Time Tracking");
            } else {
                billingTimeTracking = new Label("&nbsp;", ContentMode.HTML);
            }
            billingTimeTracking.addStyleName("billing-timetrack-feature");
            billingTimeTracking.setWidth(Sizeable.SIZE_UNDEFINED,
                    Sizeable.Unit.PIXELS);
            singlePlan.addComponent(billingTimeTracking);

            Label billingStandup;
            if (plan.getHasstandupmeetingenable()) {
                billingStandup = new Label("Standup Meeting", ContentMode.TEXT);
            } else {
                billingStandup = new Label("&nbsp;", ContentMode.HTML);
            }
            billingStandup.addStyleName("billing-standup-feature");
            billingStandup.setWidth(Sizeable.SIZE_UNDEFINED,
                    Sizeable.Unit.PIXELS);
            singlePlan.addComponent(billingStandup);

            Button selectThisPlan = new Button("Select", new ClickListener() {

                @Override
                public void buttonClick(ClickEvent event) {
                    UI.getCurrent()
                            .addWindow(new UpdateBillingPlanWindow(plan));
                }
            });
            selectThisPlan.addStyleName(UIConstants.THEME_GREEN_LINK);
            singlePlan.addComponent(selectThisPlan);

            plansList.addComponent(singlePlan);
            plansList.setExpandRatio(singlePlan, 1.0f);
        }

        layout.addComponent(plansList);

        String billingFAQText = "<div class='prig-bottom'><div class='prig-bottom-cnt'><div class='pri-bott-coll pri-coll-1'><div class='pri-bott-block'><h2>How does the 30-day trial work?</h2><p>When you sign up, you are automatically enrolled in a free30-day trial that gives you unrestricted access to all the greatfeatures MyCollab has to offer. During your free trial, you havethe option to cancel at any time. When your trial ends, you canchoose to remain on your current package, upgrade to another onewith more users and storages, downgrade, or cancel.</p></div><div class='pri-bott-block'><h2>Can I upgrade my plan at any time?</h2><p>Yes, you may upgrade your plan at any time. Choose a planthat suits your needs today, and upgrade as the numbers of usersand spaces grow. After you upgrade the changes will be updatedon your next billing cycle.</p></div><div class='pri-bott-block'><h2>What if I want to downgrade my plan?</h2><p>You can downgrade your package at any time as long as theone you select is consistent with your current usage. Forexample, if you currently have 30 users, you must delete 10 ofthem before you can downgrade to a Compact package that allowsfor up to 20 users.</p></div></div><div class='pri-bott-coll'><div class='pri-bott-block prig-block-1'><h2>Do I have to provide payment information up front?</h2><p>No, you can choose the \"manual payment\" option in thebilling information panel. At the end of the trial, we will sendyou an email to remind you to submit payment information. Youcan then choose if and how you want to pay.</p></div><div class='pri-bott-block prig-block-2'><h2>What payment options are available?</h2><p>We accept Visa, Mastercard, and American Express forautomatic payments. We also accept PayPal, checks, and bankwires for manual payments.</p></div><div class='pri-bott-block'><h2>Do I have to sign a long-term contract?</h2><p>No, there are no contracts. You can choose to pay monthly,or you can pay in advance for a year and get 2 months free (12months for the price of 10). By paying in advance, you cansubmit one expense report or purchase order for the year.</p></div></div></div><div class='clear'></div></div>";
        Label billingFAQ = new Label(billingFAQText, ContentMode.HTML);

        this.addComponent(layout);
        this.addComponent(billingFAQ);
    }

    @Override
    public void loadCurrentPlan() {
        currentPlan.removeAllComponents();

        BillingPlan currentBillingPlan = AppContext.getBillingAccount()
                .getBillingPlan();

        Label introText = new Label("Your current plan: "
                + currentBillingPlan.getBillingtype());
        introText.addStyleName("intro-text");
        currentPlan.addComponent(introText);

        Label currentBillingPrice = new Label("<span class='current-price'>$"
                + currentBillingPlan.getPricing() + "</span>/Month",
                ContentMode.HTML);
        currentBillingPrice.setStyleName("current-price-lbl");
        currentBillingPrice.setWidth(Sizeable.SIZE_UNDEFINED,
                Sizeable.Unit.PIXELS);
        currentBillingPrice.setImmediate(true);
        currentPlan.addComponent(currentBillingPrice);

        String planInfo = "<div id='currentPlanInfo'><div class='infoBlock'><span class='infoTitle'>Projects:</span> %d of %d</div><div class='blockSeparator'>&nbsp;</div><div class='infoBlock'><span class='infoTitle'>Storage:</span> %s of %s</div><div class='blockSeparator'>&nbsp;</div><div class='infoBlock'><span class='infoTitle'>Users:</span> %d of %d</div></div>";
        log.debug("Get number of active users in account {}",
                AppContext.getAccountId());
        UserService userService = ApplicationContextUtil
                .getSpringBean(UserService.class);
        numOfActiveUsers = userService.getTotalActiveUsersInAccount(AppContext
                .getAccountId());

        log.debug("Get number of active projects in account {}",
                AppContext.getAccountId());
        ProjectService projectService = ApplicationContextUtil
                .getSpringBean(ProjectService.class);
        numOfActiveProjects = projectService
                .getTotalActiveProjectsInAccount(AppContext.getAccountId());

        log.debug("Get used storage volume");
        DriveInfoService driveInfoService = ApplicationContextUtil
                .getSpringBean(DriveInfoService.class);
        usedStorageVolume = driveInfoService.getUsedStorageVolume(AppContext
                .getAccountId());

        String usedStorageTxt = ResourceUtils
                .getVolumeDisplay(usedStorageVolume);

        planInfo = String.format(planInfo, numOfActiveProjects,
                currentBillingPlan.getNumprojects(), usedStorageTxt,
                ResourceUtils.getVolumeDisplay(currentBillingPlan.getVolume()),
                numOfActiveUsers, currentBillingPlan.getNumusers());

        Label currentUsage = new Label(planInfo, ContentMode.HTML);
        currentUsage.addStyleName("current-usage");
        currentUsage.setWidth(Sizeable.SIZE_UNDEFINED, Sizeable.Unit.PIXELS);
        currentPlan.addComponent(currentUsage);
    }

    private class UpdateBillingPlanWindow extends Window {
        private final BillingPlan chosenPlan;

        private VerticalLayout contentLayout;

        public UpdateBillingPlanWindow(BillingPlan billingPlan) {
            this.chosenPlan = billingPlan;
            this.addStyleName("updateplan-window");
            this.setWidth("400px");

            contentLayout = new VerticalLayout();
            contentLayout.setSpacing(true);
            contentLayout.setMargin(new MarginInfo(false, false, true, false));
            this.setContent(contentLayout);
            initUI();

            this.center();
            this.setCaption("Change your current billing plan");
        }

        private void initUI() {
            Label header = new Label(
                    "Are you sure you want to change your current billing plan to the following billing plan?");
            header.addStyleName("updateplan-hdr");
            header.setWidth("300px");

            contentLayout.addComponent(header);
            contentLayout
                    .setComponentAlignment(header, Alignment.MIDDLE_CENTER);

            VerticalLayout chosenPlanInfo = new VerticalLayout();
            chosenPlanInfo.setWidth("300px");

            Label chosenPlanType = new Label(this.chosenPlan.getBillingtype());
            chosenPlanType.addStyleName("billing-type");
            chosenPlanInfo.addComponent(chosenPlanType);

            Label chosenPlanPrice = new Label("<span class='billing-price'>$"
                    + this.chosenPlan.getPricing() + "</span>/month",
                    ContentMode.HTML);
            chosenPlanPrice.addStyleName("billing-price-lbl");
            chosenPlanInfo.addComponent(chosenPlanPrice);

            contentLayout.addComponent(chosenPlanInfo);
            contentLayout.setComponentAlignment(chosenPlanInfo,
                    Alignment.MIDDLE_CENTER);

            HorizontalLayout controlBtns = new HorizontalLayout();
            controlBtns.setSpacing(true);
            controlBtns.setMargin(true);
            final Button cancelBtn = new Button("Cancel",
                    new Button.ClickListener() {
                        private static final long serialVersionUID = 1L;

                        @Override
                        public void buttonClick(final ClickEvent event) {
                            UpdateBillingPlanWindow.this.close();
                        }
                    });

            cancelBtn.setStyleName(UIConstants.THEME_BLANK_LINK);
            controlBtns.addComponent(cancelBtn);
            controlBtns.setComponentAlignment(cancelBtn,
                    Alignment.MIDDLE_CENTER);

            final Button saveBtn = new Button("Ok", new Button.ClickListener() {
                private static final long serialVersionUID = 1L;

                @Override
                public void buttonClick(final ClickEvent event) {
                    log.debug("Check choose plan valid");

                    if (chosenPlan.getNumprojects() < numOfActiveProjects) {
                        UpdateBillingPlanWindow.this.close();
                        return;
                    }

                    if (chosenPlan.getNumusers() < numOfActiveUsers) {
                        UpdateBillingPlanWindow.this.close();
                        return;
                    }

                    if (chosenPlan.getVolume() * 1000 < usedStorageVolume) {
                        UpdateBillingPlanWindow.this.close();
                        return;
                    }

                    log.debug("It is possible to update plan");
                    billingService.updateBillingPlan(AppContext.getAccountId(),
                            chosenPlan.getId());
                    UpdateBillingPlanWindow.this.updateBillingPlan();
                    UpdateBillingPlanWindow.this.close();
                }
            });
            saveBtn.setStyleName(UIConstants.THEME_GREEN_LINK);
            saveBtn.setIcon(MyCollabResource.newResource("icons/16/save.png"));
            controlBtns.addComponent(saveBtn);
            controlBtns.setComponentAlignment(saveBtn, Alignment.MIDDLE_CENTER);

            contentLayout.addComponent(controlBtns);
            contentLayout.setComponentAlignment(controlBtns,
                    Alignment.MIDDLE_RIGHT);
        }

        private void updateBillingPlan() {
            AppContext.getBillingAccount().setBillingPlan(chosenPlan);
            BillingSummaryViewImpl.this.loadCurrentPlan();
        }
    }
}
