package com.esofthead.mycollab.web.main;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.vaadin.hene.popupbutton.PopupButton;

import com.esofthead.mycollab.module.crm.ui.CrmHome;
import com.esofthead.mycollab.module.project.ui.ProjectDashboardView;
import com.esofthead.mycollab.module.user.ui.AccountView;
import com.esofthead.mycollab.vaadin.mvp.ui.AbstractView;
import com.esofthead.mycollab.vaadin.ui.Hr;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.CustomLayout;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
@Scope("prototype")
@Component
public class MainPage extends AbstractView {

	private CustomComponent serviceComp = null;

	@Override
	protected ComponentContainer initMainLayout() {
		VerticalLayout layout = new VerticalLayout();
		layout.addComponent(createTopMenu());
		return layout;
	}

	private CustomLayout createTopMenu() {
		CustomLayout layout = new CustomLayout("topNavigation");
		layout.setStyleName("topNavigation");
		layout.setHeight("44px");
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
						CrmHome crmHome = AppContext.getView(CrmHome.class);
						if (serviceComp != null) {
							compContainer.removeComponent(serviceComp);
						}

						ComponentContainer createMainLayout = crmHome
								.getCompContainer();
						serviceComp = new CustomComponent(createMainLayout);
						MainPage.this.compContainer.addComponent(serviceComp);
					}
				});
		crmLink.setStyleName("link");
		vLayout.addComponent(crmLink);

		Button prjLink = new Button("Project Management",
				new Button.ClickListener() {
					@Override
					public void buttonClick(ClickEvent event) {
						serviceMenu.setPopupVisible(false);
						ProjectDashboardView projectDashboard = AppContext.getView(ProjectDashboardView.class);
						if (serviceComp != null) {
							compContainer.removeComponent(serviceComp);
						}

						ComponentContainer createMainLayout = projectDashboard
								.getCompContainer();
						serviceComp = new CustomComponent(createMainLayout);
						MainPage.this.compContainer.addComponent(serviceComp);
					}
				});
		prjLink.setStyleName("link");
		vLayout.addComponent(prjLink);

		serviceMenu.addComponent(vLayout);
		layout.addComponent(serviceMenu, "serviceMenu");

		PopupButton accountMenu = new PopupButton(AppContext.getSession()
				.getDisplayname());
		accountMenu.setStyleName("link");
		VerticalLayout accLayout = new VerticalLayout();
		accLayout.setWidth("120px");

		Button myAccountBtn = new Button("My Account", new Button.ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				serviceMenu.setPopupVisible(false);
				AccountView accountView = AppContext.getView(AccountView.class);
				if (serviceComp != null) {
					compContainer.removeComponent(serviceComp);
				}

				ComponentContainer createMainLayout = accountView
						.getCompContainer();
				serviceComp = new CustomComponent(createMainLayout);
				MainPage.this.compContainer.addComponent(serviceComp);
			}
		});
		myAccountBtn.setStyleName("link");
		accLayout.addComponent(myAccountBtn);

		accLayout.addComponent(new Hr());

		Button signoutBtn = new Button("Sign out");
		signoutBtn.setStyleName("link");
		accLayout.addComponent(signoutBtn);

		accountMenu.addComponent(accLayout);
		accountMenu.setStyleName("accountMenu");
		accountMenu.addStyleName("topNavPopup");
		layout.addComponent(accountMenu, "accountMenu");
		return layout;
	}
}
