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
import com.esofthead.mycollab.web.CustomLayoutLoader;
import com.vaadin.lazyloadwrapper.LazyLoadWrapper;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomLayout;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
@ViewComponent
public final class MainView extends AbstractView {

	private final CssLayout bodyLayout;

	public MainView() {
		this.setSizeFull();
		this.addComponent(this.createTopMenu());
		this.bodyLayout = new CssLayout();
		this.bodyLayout.addStyleName("main-body");
		this.bodyLayout.setWidth("100%");
		this.bodyLayout.setHeight("100%");
		this.addComponent(this.bodyLayout);
		this.setExpandRatio(this.bodyLayout, 1.0f);
		this.addComponent(this.createFooter());
		this.setSizeFull();
		ControllerRegistry.addController(new MainViewController(this));
	}

	public void addModule(final IModule module) {
		ModuleHelper.setCurrentModule(module);
		this.bodyLayout.removeAllComponents();
		final LazyLoadWrapper comp = new LazyLoadWrapper(module.getWidget());
		this.bodyLayout.addComponent(comp);
	}

	private CustomLayout createFooter() {
		final CustomLayout footer = CustomLayoutLoader.createLayout("footer");
		final Button sendFeedback = new Button("Feedback");
		sendFeedback.setStyleName(UIConstants.THEME_BLUE_LINK);
		sendFeedback.addListener(new ClickListener() {

			@Override
			public void buttonClick(final ClickEvent event) {
				MainView.this.getWindow().addWindow(new FeedbackWindow());
			}
		});
		footer.addComponent(sendFeedback, "footer-right");
		return footer;
	}

	private CustomLayout createTopMenu() {
		final CustomLayout layout = CustomLayoutLoader.createLayout("topNavigation");
		layout.setStyleName("topNavigation");
		layout.setHeight("40px");
		layout.setWidth("100%");
		final PopupButton serviceMenu = new PopupButton("Services");
		serviceMenu.setStyleName("serviceMenu");
		serviceMenu.addStyleName("topNavPopup");
		final VerticalLayout vLayout = new VerticalLayout();
		vLayout.setWidth("200px");

		final Button crmLink = new Button("Customer Management",
				new Button.ClickListener() {
					@Override
					public void buttonClick(final ClickEvent event) {
						serviceMenu.setPopupVisible(false);
						EventBus.getInstance().fireEvent(
								new ShellEvent.GotoCrmModule(this, null));
					}
				});
		crmLink.setStyleName("link");
		vLayout.addComponent(crmLink);

		final Button prjLink = new Button("Project Management",
				new Button.ClickListener() {
					@Override
					public void buttonClick(final ClickEvent event) {
						serviceMenu.setPopupVisible(false);
						EventBus.getInstance().fireEvent(
								new ShellEvent.GotoProjectModule(this, null));
					}
				});
		prjLink.setStyleName("link");
		vLayout.addComponent(prjLink);

		serviceMenu.addComponent(vLayout);
		layout.addComponent(serviceMenu, "serviceMenu");

		final HorizontalLayout accountLayout = new HorizontalLayout();
		final Embedded userAvatar = UserAvatarControlFactory
				.createUserAvatarEmbeddedComponent(
						AppContext.getUserAvatarId(), 24);
		accountLayout.addComponent(userAvatar);
		accountLayout.setComponentAlignment(userAvatar, Alignment.MIDDLE_LEFT);

		final PopupButton accountMenu = new PopupButton(AppContext.getSession()
				.getDisplayName());
		final VerticalLayout accLayout = new VerticalLayout();
		accLayout.setWidth("120px");

		final Button myProfileBtn = new Button("Profile",
				new Button.ClickListener() {
					@Override
					public void buttonClick(final ClickEvent event) {
						accountMenu.setPopupVisible(false);
						EventBus.getInstance().fireEvent(
								new ShellEvent.GotoUserAccountModule(this,
										new String[] { "preview" }));
					}
				});
		myProfileBtn.setStyleName("link");
		accLayout.addComponent(myProfileBtn);

		final Button myAccountBtn = new Button("Account",
				new Button.ClickListener() {

					@Override
					public void buttonClick(final ClickEvent event) {
						accountMenu.setPopupVisible(false);
						EventBus.getInstance().fireEvent(
								new ShellEvent.GotoUserAccountModule(this,
										new String[] { "billing" }));
					}
				});
		myAccountBtn.setStyleName("link");
		accLayout.addComponent(myAccountBtn);

		final Button signoutBtn = new Button("Sign out",
				new Button.ClickListener() {
					@Override
					public void buttonClick(final ClickEvent event) {
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
}
