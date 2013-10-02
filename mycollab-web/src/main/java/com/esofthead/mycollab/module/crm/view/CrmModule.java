package com.esofthead.mycollab.module.crm.view;

import com.esofthead.mycollab.eventmanager.EventBus;
import com.esofthead.mycollab.module.crm.events.CrmEvent;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.mvp.ControllerRegistry;
import com.esofthead.mycollab.vaadin.mvp.IModule;
import com.esofthead.mycollab.vaadin.mvp.View;
import com.esofthead.mycollab.vaadin.mvp.ViewManager;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.esofthead.mycollab.web.CustomLayoutLoader;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.CustomLayout;
import com.vaadin.ui.VerticalLayout;

@ViewComponent
public class CrmModule extends AbstractView implements IModule {

	private static final long serialVersionUID = 1L;

	private final VerticalLayout currentView;

	private final CrmToolbar toolbar;

	public CrmModule() {
		ControllerRegistry.addController(new CrmController(this));
		CustomLayout container = CustomLayoutLoader
				.createLayout("crmContainer");
		container.setStyleName("crmContainer");
		container.setWidth("100%");

		toolbar = ViewManager.getView(CrmToolbar.class);
		container.addComponent(toolbar, "crmToolbar");

		currentView = new VerticalLayout();
		container.addComponent(currentView, "currentView");
		this.addComponent(container);
		this.setComponentAlignment(container, Alignment.MIDDLE_CENTER);
	}

	public void gotoCrmDashboard() {
		EventBus.getInstance().fireEvent(new CrmEvent.GotoHome(this, null));
	}

	public void addView(View view) {
		currentView.removeAllComponents();
		currentView.addComponent(view.getWidget());
	}

}
