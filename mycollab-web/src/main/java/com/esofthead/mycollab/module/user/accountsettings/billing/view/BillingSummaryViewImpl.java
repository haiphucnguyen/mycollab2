package com.esofthead.mycollab.module.user.accountsettings.billing.view;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esofthead.mycollab.module.billing.service.BillingService;
import com.esofthead.mycollab.module.ecm.VolumeUtils;
import com.esofthead.mycollab.module.ecm.service.DriveInfoService;
import com.esofthead.mycollab.module.project.service.ProjectService;
import com.esofthead.mycollab.module.user.domain.BillingPlan;
import com.esofthead.mycollab.module.user.service.UserService;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

@SuppressWarnings("serial")
@ViewComponent
public class BillingSummaryViewImpl extends AbstractView implements
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

		topLayout.addComponent(currentPlan);
		topLayout.setComponentAlignment(currentPlan, Alignment.MIDDLE_CENTER);
		topLayout.setExpandRatio(currentPlan, 1.0f);

		VerticalLayout FAQLayout = new VerticalLayout();
		FAQLayout.setWidth("285px");
		FAQLayout.addStyleName("faq-layout");
		Label header = new Label("Question?");
		header.addStyleName("faq-header");
		FAQLayout.addComponent(header);

		Label contentText = new Label(
				"For specific questions related to billing, features, plans, upgrades, downgrades or cancellations, please send email to <a href=\"mailto:support@esofthead.com\">support@esofthead.com</a>",
				Label.CONTENT_XHTML);
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

			if ((i + 1) % 2 == 0) {
				singlePlan.addStyleName("even");
			}

			final BillingPlan plan = availablePlans.get(i);

			Label billingType = new Label(plan.getBillingtype());
			billingType.addStyleName("billing-type");
			singlePlan.addComponent(billingType);

			Label billingPrice = new Label("<span class='billing-price'>$"
					+ plan.getPricing() + "</span>/month", Label.CONTENT_XHTML);
			billingPrice.addStyleName("billing-price-lbl");
			singlePlan.addComponent(billingPrice);

			Label billingUser = new Label("<span class='billing-user'>"
					+ plan.getNumusers() + "</span>&nbsp;Users",
					Label.CONTENT_XHTML);
			singlePlan.addComponent(billingUser);

			String planVolume = "";
			if (plan.getVolume() > 1024) {
				planVolume = String.format("%.2f",
						(((float) plan.getVolume()) / 1024)) + "GB";
			} else {
				planVolume = plan.getVolume() + "MB";
			}

			Label billingStorage = new Label("<span class='billing-storage'>"
					+ planVolume + "</span>&nbsp;Storage", Label.CONTENT_XHTML);
			singlePlan.addComponent(billingStorage);

			Label billingProject = new Label("<span class='billing-project'>"
					+ plan.getNumprojects() + "</span>&nbsp;Project"
					+ (plan.getNumprojects() > 1 ? "s" : ""),
					Label.CONTENT_XHTML);
			singlePlan.addComponent(billingProject);

			Label billingBugTracking;
			if (plan.getHasbugenable()) {
				billingBugTracking = new Label("Bugs Tracking",
						Label.CONTENT_DEFAULT);
			} else {
				billingBugTracking = new Label("&nbsp;", Label.CONTENT_XHTML);
			}
			billingBugTracking.addStyleName("billing-bug-feature");
			singlePlan.addComponent(billingBugTracking);

			Label billingTimeTracking;
			if (plan.getHastimetracking()) {
				billingTimeTracking = new Label("Time Tracking",
						Label.CONTENT_DEFAULT);
			} else {
				billingTimeTracking = new Label("&nbsp;", Label.CONTENT_XHTML);
			}
			billingTimeTracking.addStyleName("billing-timetrack-feature");
			singlePlan.addComponent(billingTimeTracking);

			Label billingStandup;
			if (plan.getHasstandupmeetingenable()) {
				billingStandup = new Label("Standup Meeting",
						Label.CONTENT_DEFAULT);
			} else {
				billingStandup = new Label("&nbsp;", Label.CONTENT_XHTML);
			}
			billingStandup.addStyleName("billing-standup-feature");
			singlePlan.addComponent(billingStandup);

			Button selectThisPlan = new Button("Select", new ClickListener() {

				@Override
				public void buttonClick(ClickEvent event) {
					BillingSummaryViewImpl.this.getWidget().getWindow()
							.addWindow(new UpdateBillingPlanWindow(plan));
				}
			});
			selectThisPlan.addStyleName(UIConstants.THEME_BLUE_LINK);
			singlePlan.addComponent(selectThisPlan);
			singlePlan.setComponentAlignment(selectThisPlan,
					Alignment.MIDDLE_CENTER);

			plansList.addComponent(singlePlan);
			plansList.setExpandRatio(singlePlan, 1.0f);
		}

		layout.addComponent(plansList);

		String billingFAQText = "<div class='prig-bottom'><div class='prig-bottom-cnt'><div class='pri-bott-coll pri-coll-1'><div class='pri-bott-block'><h2>How does the 30-day trial work?</h2><p>When you sign up, you are automatically enrolled in a free30-day trial that gives you unrestricted access to all the greatfeatures MyCollab has to offer. During your free trial, you havethe option to cancel at any time. When your trial ends, you canchoose to remain on your current package, upgrade to another onewith more users and storages, downgrade, or cancel.</p></div><div class='pri-bott-block'><h2>Can I upgrade my plan at any time?</h2><p>Yes, you may upgrade your plan at any time. Choose a planthat suits your needs today, and upgrade as the numbers of usersand spaces grow. After you upgrade the changes will be updatedon your next billing cycle.</p></div><div class='pri-bott-block'><h2>What if I want to downgrade my plan?</h2><p>You can downgrade your package at any time as long as theone you select is consistent with your current usage. Forexample, if you currently have 30 users, you must delete 10 ofthem before you can downgrade to a Compact package that allowsfor up to 20 users.</p></div></div><div class='pri-bott-coll'><div class='pri-bott-block prig-block-1'><h2>Do I have to provide payment information up front?</h2><p>No, you can choose the \"manual payment\" option in thebilling information panel. At the end of the trial, we will sendyou an email to remind you to submit payment information. Youcan then choose if and how you want to pay.</p></div><div class='pri-bott-block prig-block-2'><h2>What payment options are available?</h2><p>We accept Visa, Mastercard, and American Express forautomatic payments. We also accept PayPal, checks, and bankwires for manual payments.</p></div><div class='pri-bott-block'><h2>Do I have to sign a long-term contract?</h2><p>No, there are no contracts. You can choose to pay monthly,or you can pay in advance for a year and get 2 months free (12months for the price of 10). By paying in advance, you cansubmit one expense report or purchase order for the year.</p></div></div></div><div class='clear'></div></div>";
		Label billingFAQ = new Label(billingFAQText, Label.CONTENT_XHTML);

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
				Label.CONTENT_XHTML);
		currentBillingPrice.setStyleName("current-price-lbl");
		currentBillingPrice.setImmediate(true);
		currentPlan.addComponent(currentBillingPrice);

		String planInfo = "<div id='currentPlanInfo'><div class='infoBlock'><span class='infoTitle'>Projects:</span> %d of %d</div><div class='blockSeparator'>&nbsp;</div><div class='infoBlock'><span class='infoTitle'>Storage:</span> %s of %d Gb</div><div class='blockSeparator'>&nbsp;</div><div class='infoBlock'><span class='infoTitle'>Users:</span> %d of %d</div></div>";
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

		log.debug("Get used storage volum");
		DriveInfoService driveInfoService = ApplicationContextUtil
				.getSpringBean(DriveInfoService.class);
		usedStorageVolume = driveInfoService.getUsedStorageVolume(AppContext
				.getAccountId());

		String usedStorageTxt = VolumeUtils.getVolumeDisplay(usedStorageVolume);

		planInfo = String.format(planInfo, numOfActiveProjects,
				currentBillingPlan.getNumprojects(), usedStorageTxt,
				currentBillingPlan.getVolume() / 1024, numOfActiveUsers,
				currentBillingPlan.getNumusers());

		Label currentUsage = new Label(planInfo, Label.CONTENT_XHTML);
		currentUsage.addStyleName("current-usage");
		currentUsage.setWidth(SIZE_UNDEFINED, 0);
		currentPlan.addComponent(currentUsage);
		currentPlan
				.setComponentAlignment(currentUsage, Alignment.MIDDLE_CENTER);
	}

	private class UpdateBillingPlanWindow extends Window {
		private final BillingPlan chosenPlan;

		public UpdateBillingPlanWindow(BillingPlan billingPlan) {
			this.chosenPlan = billingPlan;
			this.addStyleName("updateplan-window");
			this.setWidth("400px");

			this.initUI(((VerticalLayout) this.getContent()));
			this.center();
			this.setCaption("Change your current billing plan");
		}

		private void initUI(VerticalLayout thisContent) {
			Label header = new Label(
					"Are you sure you want to change your current billing plan to the following billing plan?");
			header.addStyleName("updateplan-hdr");
			header.setWidth("300px");

			this.addComponent(header);
			thisContent.setComponentAlignment(header, Alignment.MIDDLE_CENTER);

			VerticalLayout chosenPlanInfo = new VerticalLayout();
			chosenPlanInfo.setWidth("300px");

			Label chosenPlanType = new Label(this.chosenPlan.getBillingtype());
			chosenPlanType.addStyleName("billing-type");
			chosenPlanInfo.addComponent(chosenPlanType);

			Label chosenPlanPrice = new Label("<span class='billing-price'>$"
					+ this.chosenPlan.getPricing() + "</span>/month",
					Label.CONTENT_XHTML);
			chosenPlanPrice.addStyleName("billing-price-lbl");
			chosenPlanInfo.addComponent(chosenPlanPrice);

			this.addComponent(chosenPlanInfo);
			thisContent.setComponentAlignment(chosenPlanInfo,
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

			cancelBtn.setStyleName("link");
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
			saveBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
			controlBtns.addComponent(saveBtn);
			controlBtns.setComponentAlignment(saveBtn, Alignment.MIDDLE_CENTER);

			this.addComponent(controlBtns);
			thisContent.setComponentAlignment(controlBtns,
					Alignment.MIDDLE_RIGHT);
			thisContent.setSpacing(true);
			thisContent.setMargin(false, false, true, false);
		}

		private void updateBillingPlan() {
			AppContext.getBillingAccount().setBillingPlan(chosenPlan);
			BillingSummaryViewImpl.this.loadCurrentPlan();
		}
	}
}
