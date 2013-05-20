package com.esofthead.mycollab.shell.view;

import org.vaadin.hene.popupbutton.PopupButton;

import com.esofthead.mycollab.shell.events.ShellEvent;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.mvp.ControllerRegistry;
import com.esofthead.mycollab.vaadin.mvp.IModule;
import com.esofthead.mycollab.vaadin.mvp.ModuleHelper;
import com.esofthead.mycollab.vaadin.ui.FeedbackWindow;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.UserAvatarControlFactory;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.lazyloadwrapper.LazyLoadWrapper;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
@ViewComponent
public final class MainView extends AbstractView {

	private final CssLayout bodyLayout;

	public MainView() {
		this.setSizeFull();
		this.addComponent(createTopMenu());
		bodyLayout = new CssLayout();
		bodyLayout.addStyleName("main-body");
		bodyLayout.setWidth("100%");
		bodyLayout.setHeight("100%");
		this.addComponent(bodyLayout);
		this.setExpandRatio(bodyLayout, 1.0f);
		this.addComponent(createFooter());
		this.setSizeFull();
		ControllerRegistry.addController(new MainViewController(this));
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
								new ShellEvent.GotoCrmModule(this, null));
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
								new ShellEvent.GotoProjectModule(this, null));
					}
				});
		prjLink.setStyleName("link");
		vLayout.addComponent(prjLink);

		serviceMenu.addComponent(vLayout);
		layout.addComponent(serviceMenu, "serviceMenu");

		HorizontalLayout accountLayout = new HorizontalLayout();
		accountLayout.addComponent(UserAvatarControlFactory
				.createUserAvatarEmbeddedControl(AppContext.getAccountId(),
						AppContext.getUsername(), 24));

		final PopupButton accountMenu = new PopupButton(AppContext.getSession()
				.getDisplayName());
		VerticalLayout accLayout = new VerticalLayout();
		accLayout.setWidth("120px");

		Button myProfileBtn = new Button("Profile", new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				accountMenu.setPopupVisible(false);
				EventBus.getInstance().fireEvent(
						new ShellEvent.GotoUserAccountModule(this,
								new String[] { "preview" }));
			}
		});
		myProfileBtn.setStyleName("link");
		accLayout.addComponent(myProfileBtn);

		Button myAccountBtn = new Button("Account", new Button.ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				accountMenu.setPopupVisible(false);
				EventBus.getInstance().fireEvent(
						new ShellEvent.GotoUserAccountModule(this,
								new String[] { "billing" }));
			}
		});
		myAccountBtn.setStyleName("link");
		accLayout.addComponent(myAccountBtn);

		Button signoutBtn = new Button("Sign out", new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				AppContext.setSession(null, null, null);
				EventBus.getInstance().fireEvent(
						new ShellEvent.LogOut(this, null));
			}
		});
		signoutBtn.setStyleName("link");
		accLayout.addComponent(signoutBtn);

		accountMenu.addComponent(accLayout);
		accountMenu.setStyleName("accountMenu");
		accountMenu.addStyleName("topNavPopup");
		accountLayout.addComponent(accountMenu);

		layout.addComponent(accountLayout, "accountMenu");

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

	public void addModule(IModule module) {
		ModuleHelper.setCurrentModule(module);
		bodyLayout.removeAllComponents();
		LazyLoadWrapper comp = new LazyLoadWrapper(module.getWidget());
		bodyLayout.addComponent(comp);
	}
}
