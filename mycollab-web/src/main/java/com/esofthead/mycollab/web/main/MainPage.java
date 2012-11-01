package com.esofthead.mycollab.web.main;

import org.springframework.stereotype.Component;
import org.vaadin.hene.popupbutton.PopupButton;

import com.esofthead.mycollab.module.crm.ui.CrmHome;
import com.esofthead.mycollab.vaadin.mvp.ui.AbstractView;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;

@SuppressWarnings("serial")
@Component
public class MainPage extends AbstractView {

	private CustomComponent serviceComp = null;

	@Override
	protected ComponentContainer initMainLayout() {
		VerticalLayout layout = new VerticalLayout();
		layout.addComponent(createTopMenu());
		return layout;
	}

	private HorizontalLayout createTopMenu() {
		HorizontalLayout layout = new HorizontalLayout();
		final PopupButton serviceMenu = new PopupButton("Services");
		VerticalLayout vLayout = new VerticalLayout();
		vLayout.setWidth("200px");

		Button crmLink = new Button("Customer Management",
				new Button.ClickListener() {

					@Override
					public void buttonClick(ClickEvent event) {
						serviceMenu.setPopupVisible(false);
						CrmHome crmHome = AppContext
								.getSpringBean(CrmHome.class);
						if (serviceComp != null) {
							compContainer.removeComponent(serviceComp);
						}

						ComponentContainer createMainLayout = crmHome
								.createMainLayout();
						serviceComp = new CustomComponent(createMainLayout);
						MainPage.this.compContainer
								.addComponent(serviceComp);
					}
				});
		crmLink.setStyleName("link");
		vLayout.addComponent(crmLink);

		Button prjLink = new Button("Project Management",
				new Button.ClickListener() {

					@Override
					public void buttonClick(ClickEvent event) {
						// TODO Auto-generated method stub

					}
				});
		prjLink.setStyleName("link");
		vLayout.addComponent(prjLink);

		serviceMenu.addComponent(vLayout);
		layout.addComponent(serviceMenu);
		return layout;
	}
}
