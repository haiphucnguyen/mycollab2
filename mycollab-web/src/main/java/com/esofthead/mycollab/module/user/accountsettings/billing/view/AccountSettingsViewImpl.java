package com.esofthead.mycollab.module.user.accountsettings.billing.view;

import java.util.List;

import com.esofthead.mycollab.module.billing.service.BillingService;
import com.esofthead.mycollab.module.user.domain.BillingPlan;
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
public class AccountSettingsViewImpl extends AbstractView implements
		AccountSettingsView {
	BillingService billingService;

	VerticalLayout currentPlan = null;

	public AccountSettingsViewImpl() {
		super();

		this.setMargin(true);
		this.billingService = AppContext.getSpringBean(BillingService.class);
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
				"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec a diam lectus. Sed sit amet ipsum mauris. Maecenas congue ligula ac quam viverra nec consectetur ante hendrerit...");
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
					+ plan.getNumusers() + "</span>&nbsp;User",
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

			Label billingStandup;
			if (plan.getHasstandupmeetingenable()) {
				billingStandup = new Label("Standup Meeting",
						Label.CONTENT_DEFAULT);
			} else {
				billingStandup = new Label("&nbsp;", Label.CONTENT_XHTML);
			}
			billingStandup.addStyleName("billing-standup-feature");
			singlePlan.addComponent(billingStandup);

			Label billingTimeTracking;
			if (plan.getHastimetracking()) {
				billingTimeTracking = new Label("Time Tracking",
						Label.CONTENT_DEFAULT);
			} else {
				billingTimeTracking = new Label("&nbsp;", Label.CONTENT_XHTML);
			}
			billingTimeTracking.addStyleName("billing-timetrack-feature");
			singlePlan.addComponent(billingTimeTracking);

			Button selectThisPlan = new Button("Select", new ClickListener() {

				@Override
				public void buttonClick(ClickEvent event) {
					AccountSettingsViewImpl.this.getWidget().getWindow()
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

		this.addComponent(layout);
	}

	@Override
	public void loadCurrentPlan() {
		currentPlan.removeAllComponents();

		Label introText = new Label("Your current plan:");
		introText.addStyleName("intro-text");
		currentPlan.addComponent(introText);

		BillingPlan currentBillingPlan = AppContext.getBillingAccount()
				.getBillingPlan();

		Label currentBillingPrice = new Label("<span class='current-price'>$"
				+ currentBillingPlan.getPricing() + "</span>/Month",
				Label.CONTENT_XHTML);
		currentBillingPrice.setStyleName("current-price-lbl");
		currentBillingPrice.setImmediate(true);
		currentPlan.addComponent(currentBillingPrice);

		Label currentUsage = new Label("Spaces: 1 of 1 | User: 1 of 3");
		currentUsage.addStyleName("current-usage");
		currentPlan.addComponent(currentUsage);
	}

	private class UpdateBillingPlanWindow extends Window {
		private BillingPlan chosenPlan;

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

			final Button saveBtn = new Button("Save",
					new Button.ClickListener() {
						private static final long serialVersionUID = 1L;

						@Override
						public void buttonClick(final ClickEvent event) {
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
			AccountSettingsViewImpl.this.loadCurrentPlan();
		}
	}
}
