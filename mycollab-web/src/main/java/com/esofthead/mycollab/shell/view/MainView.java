package com.esofthead.mycollab.shell.view;

import org.vaadin.hene.popupbutton.PopupButton;

import com.esofthead.mycollab.module.user.accountsettings.view.AccountDashboardView;
import com.esofthead.mycollab.shell.events.ShellEvent;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.mvp.View;
import com.esofthead.mycollab.vaadin.mvp.ViewManager;
import com.esofthead.mycollab.vaadin.ui.FeedbackWindow;
import com.esofthead.mycollab.vaadin.ui.Hr;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.lazyloadwrapper.LazyLoadWrapper;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomLayout;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
@ViewComponent
public final class MainView extends AbstractView {

	private final CssLayout bodyLayout;
	private final MainViewController controller;

	public MainView() {
		this.addComponent(createTopMenu());
		bodyLayout = new CssLayout();
		bodyLayout.addStyleName("main-body");
		bodyLayout.setWidth("100%");
		bodyLayout.setHeight("100%");
		this.addComponent(bodyLayout);
		this.setExpandRatio(bodyLayout, 1.0f);
		this.addComponent(createFooter());
		this.setSizeFull();
		controller = new MainViewController(this);
	}

	private CustomLayout createTopMenu() {
		CustomLayout layout = new CustomLayout("topNavigation");
		layout.setStyleName("topNavigation");
		layout.setHeight("40px");
		layout.setWidth("100%");
		final PopupButton serviceMenu = new PopupButton("Services");
		serviceMenu.setStyleName("serviceMenu");
		serviceMenu.addStyleName("topNavPopup");
		VerticalLayout vLayout = new VerticalLayout();
		vLayout.setWidth("200px");

		Button crmLink = new Button("Customer Management",
				new Button.ClickListener() {
					@Override
					public void buttonClick(ClickEvent event) {
						serviceMenu.setPopupVisible(false);
						EventBus.getInstance().fireEvent(
								new ShellEvent.GotoCrmPage(this, null));
					}
				});
		crmLink.setStyleName("link");
		vLayout.addComponent(crmLink);

		Button prjLink = new Button("Project Management",
				new Button.ClickListener() {
					@Override
					public void buttonClick(ClickEvent event) {
						serviceMenu.setPopupVisible(false);
						EventBus.getInstance().fireEvent(
								new ShellEvent.GotoProjectPage(this, null));
					}
				});
		prjLink.setStyleName("link");
		vLayout.addComponent(prjLink);

		serviceMenu.addComponent(vLayout);
		layout.addComponent(serviceMenu, "serviceMenu");

		final PopupButton accountMenu = new PopupButton(AppContext.getSession()
				.getDisplayName());
		accountMenu.setStyleName("link");
		VerticalLayout accLayout = new VerticalLayout();
		accLayout.setWidth("120px");

		Button myAccountBtn = new Button("My Account",
				new Button.ClickListener() {
					@Override
					public void buttonClick(ClickEvent event) {
						accountMenu.setPopupVisible(false);
						AccountDashboardView accountView = ViewManager
								.getView(AccountDashboardView.class);
						bodyLayout.removeAllComponents();
						bodyLayout.addComponent(accountView);
					}
				});
		myAccountBtn.setStyleName("link");
		accLayout.addComponent(myAccountBtn);

		accLayout.addComponent(new Hr());

		Button signoutBtn = new Button("Sign out", new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				AppContext.setSession(null, null);
				EventBus.getInstance().fireEvent(
						new ShellEvent.LogOut(this, null));
			}
		});
		signoutBtn.setStyleName("link");
		accLayout.addComponent(signoutBtn);

		accountMenu.addComponent(accLayout);
		accountMenu.setStyleName("accountMenu");
		accountMenu.addStyleName("topNavPopup");
		layout.addComponent(accountMenu, "accountMenu");
		return layout;
	}

	private CustomLayout createFooter() {
		CustomLayout footer = new CustomLayout("footer");
		Button sendFeedback = new Button("Feedback");
		sendFeedback.setStyleName(UIConstants.THEME_ROUND_BUTTON);
		sendFeedback.addListener(new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				MainView.this.getWindow().addWindow(new FeedbackWindow());
			}
		});
		footer.addComponent(sendFeedback, "footer-right");
		return footer;
	}

	public void addView(View view) {
		bodyLayout.removeAllComponents();
		LazyLoadWrapper comp = new LazyLoadWrapper(view.getWidget());
		bodyLayout.addComponent(comp);
	}
}
