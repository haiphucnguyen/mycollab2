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
import com.esofthead.mycollab.eventmanager.EventBusFactory;
import com.esofthead.mycollab.module.billing.service.BillingService;
import com.esofthead.mycollab.module.ecm.ResourceUtils;
import com.esofthead.mycollab.module.ecm.service.DriveInfoService;
import com.esofthead.mycollab.module.project.service.ProjectService;
import com.esofthead.mycollab.module.user.accountsettings.localization.BillingI18nEnum;
import com.esofthead.mycollab.module.user.accountsettings.view.events.AccountBillingEvent;
import com.esofthead.mycollab.module.user.domain.BillingPlan;
import com.esofthead.mycollab.module.user.service.UserService;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.mvp.AbstractPageView;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.esofthead.mycollab.vaadin.ui.NotificationUtil;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.maddon.layouts.MHorizontalLayout;
import org.vaadin.maddon.layouts.MVerticalLayout;

import java.util.List;

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
	private static final Logger LOG = LoggerFactory
			.getLogger(BillingSummaryViewImpl.class);

	private BillingService billingService;

	private MVerticalLayout currentPlan;

	private Integer numOfActiveProjects = 0;

	private Integer numOfActiveUsers = 0;

	private Long usedStorageVolume = 0L;

	public BillingSummaryViewImpl() {
		super();
		this.setMargin(true);
		this.billingService = ApplicationContextUtil.getSpringBean(BillingService.class);
		initUI();
	}

	private void initUI() {
		CssLayout layout = new CssLayout();
		layout.addStyleName("billing-setting");
		layout.setWidth("100%");

		MHorizontalLayout topLayout = new MHorizontalLayout().withWidth("100%");

		currentPlan = new MVerticalLayout().withMargin(false).withWidth("100%");
		currentPlan.addStyleName("current-plan-information");
		currentPlan.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);

		topLayout.with(currentPlan).withAlign(currentPlan, Alignment.MIDDLE_CENTER).expand(currentPlan);

		MVerticalLayout faqLayout = new MVerticalLayout();

		if (AppContext.isAdmin()) {
			Button cancelBtn = new Button(
					AppContext.getMessage(BillingI18nEnum.BUTTON_CANCEL_ACCOUNT),
					new Button.ClickListener() {
						@Override
						public void buttonClick(ClickEvent event) {
							EventBusFactory.getInstance().post(
									new AccountBillingEvent.CancelAccount(BillingSummaryViewImpl.this, null));
						}
					});
			cancelBtn.setStyleName(UIConstants.THEME_RED_LINK);
			faqLayout.addComponent(cancelBtn);
		}

		faqLayout.setWidth("285px");
		faqLayout.addStyleName("faq-layout");
		Label header = new Label(
				AppContext.getMessage(BillingI18nEnum.HELP_QUESTION));
		header.addStyleName("faq-header");
		faqLayout.addComponent(header);

		Label contentText = new Label(
				AppContext.getMessage(BillingI18nEnum.HELP_INFO),
				ContentMode.HTML);
		contentText.addStyleName("faq-content");
		faqLayout.addComponent(contentText);

		topLayout.addComponent(faqLayout);
		topLayout.setComponentAlignment(faqLayout, Alignment.MIDDLE_CENTER);

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
			singlePlan.addComponent(billingType);

			Label billingPrice = new Label("<span class='billing-price'>$"
					+ plan.getPricing() + "</span>/month", ContentMode.HTML);
			billingPrice.addStyleName("billing-price-lbl");
			billingPrice.setWidthUndefined();
			singlePlan.addComponent(billingPrice);

			Label billingUser = new Label("<span class='billing-user'>"
					+ plan.getNumusers() + "</span>&nbsp;Users",
					ContentMode.HTML);
			billingUser.setWidthUndefined();
			singlePlan.addComponent(billingUser);

			String planVolume = ResourceUtils
					.getVolumeDisplay(plan.getVolume());

			Label billingStorage = new Label("<span class='billing-storage'>"
					+ planVolume + "</span>&nbsp;Storage", ContentMode.HTML);
			billingStorage.setWidthUndefined();
			singlePlan.addComponent(billingStorage);

			Label billingProject = new Label("<span class='billing-project'>"
					+ plan.getNumprojects() + "</span>&nbsp;Project"
					+ (plan.getNumprojects() > 1 ? "s" : ""), ContentMode.HTML);
			billingProject.setWidthUndefined();
			singlePlan.addComponent(billingProject);

			Label billingBugTracking;
			if (plan.getHasbugenable()) {
				billingBugTracking = new Label("Issues Tracker", ContentMode.HTML);
			} else {
				billingBugTracking = new Label("&nbsp;", ContentMode.HTML);
			}
			billingBugTracking.addStyleName("billing-bug-feature");
			billingBugTracking.setWidthUndefined();
			singlePlan.addComponent(billingBugTracking);

			Label billingTimeTracking;
			if (plan.getHastimetracking()) {
				billingTimeTracking = new Label("Time Tracker");
			} else {
				billingTimeTracking = new Label("&nbsp;", ContentMode.HTML);
			}
			billingTimeTracking.addStyleName("billing-timetrack-feature");
			billingTimeTracking.setWidthUndefined();
			singlePlan.addComponent(billingTimeTracking);

			Label billingStandup;
			if (plan.getHasstandupmeetingenable()) {
				billingStandup = new Label("Standup Meeting", ContentMode.TEXT);
			} else {
				billingStandup = new Label("&nbsp;", ContentMode.HTML);
			}
			billingStandup.addStyleName("billing-standup-feature");
			billingStandup.setWidthUndefined();
			singlePlan.addComponent(billingStandup);

			Button selectThisPlan = new Button(
					AppContext.getMessage(GenericI18Enum.BUTTON_SELECT),
					new ClickListener() {

						@Override
						public void buttonClick(ClickEvent event) {
							UI.getCurrent().addWindow(
									new UpdateBillingPlanWindow(plan));
						}
					});
			selectThisPlan.addStyleName(UIConstants.THEME_GREEN_LINK);
			singlePlan.addComponent(selectThisPlan);

			plansList.addComponent(singlePlan);
			plansList.setExpandRatio(singlePlan, 1.0f);
		}

		layout.addComponent(plansList);

		String billingFAQText = "<div class='prig-bottom'><div class='prig-bottom-cnt'><div class='pri-bott-coll pri-coll-1'><div class='pri-bott-block'><h2>How does the 30-day trial work?</h2><p>When you sign up, you are automatically enrolled in a free30-day trial that gives you unrestricted access to all the great features MyCollab has to offer. During your free trial, you have the option to cancel at any time. When your trial ends, you can choose to remain on your current package, upgrade to another one with more users and storages, downgrade, or cancel.</p></div><div class='pri-bott-block'><h2>Can I upgrade my plan at any time?</h2><p>Yes, you may upgrade your plan at any time. Choose a plan that suits your needs today, and upgrade as the numbers of users and spaces grow. After you upgrade the changes will be updated on your next billing cycle.</p></div><div class='pri-bott-block'><h2>What if I want to downgrade my plan?</h2><p>You can downgrade your package at any time as long as the one you select is consistent with your current usage. For example, if you currently have 30 users, you must delete 10 of them before you can downgrade to a Compact package that allows for up to 20 users.</p></div></div><div class='pri-bott-coll'><div class='pri-bott-block prig-block-1'><h2>Do I have to provide payment information up front?</h2><p>No, you can choose the \"manual payment\" option in the billing information panel. At the end of the trial, we will send you an email to remind you to submit payment information. You can then choose if and how you want to pay.</p></div><div class='pri-bott-block prig-block-2'><h2>What payment options are available?</h2><p>We accept Visa, Mastercard, and American Express for automatic payments. We also accept PayPal, checks, and bankwires for manual payments.</p></div><div class='pri-bott-block'><h2>Do I have to sign a long-term contract?</h2><p>No, there are no contracts. You can choose to pay monthly,or you can pay in advance for a year and get 2 months free (12 months for the price of 10). By paying in advance, you can submit one expense report or purchase order for the year.</p></div></div></div><div class='clear'></div></div>";
		Label billingFAQ = new Label(billingFAQText, ContentMode.HTML);

		this.addComponent(layout);
		this.addComponent(billingFAQ);
	}

	@Override
	public void loadCurrentPlan() {
		currentPlan.removeAllComponents();

		BillingPlan currentBillingPlan = AppContext.getBillingAccount().getBillingPlan();

		Label introText = new Label("Your current plan: " + currentBillingPlan.getBillingtype());
		introText.addStyleName("intro-text");
		currentPlan.addComponent(introText);

		Label currentBillingPrice = new Label("<span class='current-price'>$"
				+ currentBillingPlan.getPricing() + "</span>/Month",
				ContentMode.HTML);
		currentBillingPrice.setStyleName("current-price-lbl");
		currentBillingPrice.setWidthUndefined();
		currentBillingPrice.setImmediate(true);
		currentPlan.addComponent(currentBillingPrice);

		String planInfo = "<div id='currentPlanInfo'><div class='infoBlock'><span class='infoTitle'>Projects:</span> %d of %d</div><div class='blockSeparator'>&nbsp;</div><div class='infoBlock'><span class='infoTitle'>Storage:</span> %s of %s</div><div class='blockSeparator'>&nbsp;</div><div class='infoBlock'><span class='infoTitle'>Users:</span> %d of %d</div></div>";
		LOG.debug("Get number of active users in account {}",
				AppContext.getAccountId());
		UserService userService = ApplicationContextUtil.getSpringBean(UserService.class);
		numOfActiveUsers = userService.getTotalActiveUsersInAccount(AppContext.getAccountId());

		LOG.debug("Get number of active projects in account {}", AppContext.getAccountId());
		ProjectService projectService = ApplicationContextUtil.getSpringBean(ProjectService.class);
		numOfActiveProjects = projectService.getTotalActiveProjectsInAccount(AppContext.getAccountId());

		LOG.debug("Get used storage volume");
		DriveInfoService driveInfoService = ApplicationContextUtil.getSpringBean(DriveInfoService.class);
		usedStorageVolume = driveInfoService.getUsedStorageVolume(AppContext.getAccountId());

		String usedStorageTxt = ResourceUtils.getVolumeDisplay(usedStorageVolume);

		planInfo = String.format(planInfo, numOfActiveProjects,
				currentBillingPlan.getNumprojects(), usedStorageTxt,
				ResourceUtils.getVolumeDisplay(currentBillingPlan.getVolume()),
				numOfActiveUsers, currentBillingPlan.getNumusers());

		Label currentUsage = new Label(planInfo, ContentMode.HTML);
		currentUsage.addStyleName("current-usage");
		currentUsage.setWidthUndefined();
		currentPlan.addComponent(currentUsage);
	}

	private class UpdateBillingPlanWindow extends Window {
		private BillingPlan chosenPlan;

		private MVerticalLayout contentLayout;

		public UpdateBillingPlanWindow(BillingPlan billingPlan) {
			this.chosenPlan = billingPlan;
			this.addStyleName("updateplan-window");
			this.setWidth("400px");
			this.setResizable(false);
			this.setModal(true);

			contentLayout = new MVerticalLayout().withMargin(new MarginInfo(false, false, true, false));
			this.setContent(contentLayout);
			initUI();
			this.center();
			this.setCaption(AppContext
					.getMessage(BillingI18nEnum.VIEW_CHANGE_BILLING_PLAN_TITLE));
		}

		private void initUI() {
			Label header = new Label(AppContext.getMessage(BillingI18nEnum.QUESTION_CHANGE_PLAN));
			header.addStyleName("updateplan-hdr");
			header.setWidth("300px");

			contentLayout.with(header).withAlign(header, Alignment.MIDDLE_CENTER);

			VerticalLayout chosenPlanInfo = new VerticalLayout();
			chosenPlanInfo.setWidth("300px");

			Label chosenPlanType = new Label(chosenPlan.getBillingtype());
			chosenPlanType.addStyleName("billing-type");
			chosenPlanInfo.addComponent(chosenPlanType);

			Label chosenPlanPrice = new Label(AppContext.getMessage(
					BillingI18nEnum.FORM_BILLING_PRICE,
					chosenPlan.getPricing()), ContentMode.HTML);
			chosenPlanPrice.addStyleName("billing-price-lbl");
			chosenPlanInfo.addComponent(chosenPlanPrice);

			contentLayout.addComponent(chosenPlanInfo);
			contentLayout.setComponentAlignment(chosenPlanInfo,
					Alignment.MIDDLE_CENTER);

			MHorizontalLayout controlBtns = new MHorizontalLayout().withMargin(true);
			final Button cancelBtn = new Button(
					AppContext.getMessage(GenericI18Enum.BUTTON_CANCEL),
					new Button.ClickListener() {
						private static final long serialVersionUID = 1L;

						@Override
						public void buttonClick(final ClickEvent event) {
							UpdateBillingPlanWindow.this.close();
						}
					});

			cancelBtn.setStyleName(UIConstants.THEME_RED_LINK);
			controlBtns.with(cancelBtn).withAlign(cancelBtn, Alignment.MIDDLE_CENTER);

			final Button saveBtn = new Button(
					AppContext.getMessage(GenericI18Enum.BUTTON_OK),
					new Button.ClickListener() {
						private static final long serialVersionUID = 1L;

						@Override
						public void buttonClick(final ClickEvent event) {
							if (chosenPlan.getNumprojects() < numOfActiveProjects
									|| chosenPlan.getNumusers() < numOfActiveUsers
									|| chosenPlan.getVolume() * 1000 < usedStorageVolume
									|| chosenPlan.getVolume() * 1000 < usedStorageVolume) {
								NotificationUtil.showErrorNotification(AppContext
										.getMessage(BillingI18nEnum.OPT_CANNOT_CHANGE_PLAN));
								UpdateBillingPlanWindow.this.close();
								return;
							}

							if (chosenPlan.getBillingtype().equals(
									AppContext.getBillingAccount().getBillingPlan().getBillingtype())) {
								NotificationUtil
										.showErrorNotification("Selected plan is the same with the current plan");
								return;
							}

							LOG.debug("It is possible to update plan");
							billingService.updateBillingPlan(
									AppContext.getAccountId(),
									chosenPlan.getId());

							LOG.debug("Update the billing service");

							UpdateBillingPlanWindow.this.updateBillingPlan();
							UpdateBillingPlanWindow.this.close();
						}
					});
			saveBtn.setStyleName(UIConstants.THEME_GREEN_LINK);
			saveBtn.setIcon(FontAwesome.SAVE);
			controlBtns.with(saveBtn).withAlign(saveBtn, Alignment.MIDDLE_CENTER);

			contentLayout.with(controlBtns).withAlign(controlBtns, Alignment.MIDDLE_RIGHT);
		}

		private void updateBillingPlan() {
			AppContext.getBillingAccount().setBillingPlan(chosenPlan);
			loadCurrentPlan();
		}
	}
}
