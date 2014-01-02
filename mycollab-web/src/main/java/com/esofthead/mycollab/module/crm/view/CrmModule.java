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
package com.esofthead.mycollab.module.crm.view;

import com.esofthead.mycollab.eventmanager.EventBus;
import com.esofthead.mycollab.module.crm.events.CrmEvent;
import com.esofthead.mycollab.vaadin.mvp.AbstractPageView;
import com.esofthead.mycollab.vaadin.mvp.ControllerRegistry;
import com.esofthead.mycollab.vaadin.mvp.IModule;
import com.esofthead.mycollab.vaadin.mvp.PageView;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.esofthead.mycollab.vaadin.mvp.ViewManager;
import com.esofthead.mycollab.web.CustomLayoutLoader;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.CustomLayout;
import com.vaadin.ui.VerticalLayout;

@ViewComponent
public class CrmModule extends AbstractPageView implements IModule {

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
		currentView.setMargin(new MarginInfo(true, false, false, false));
		
		container.addComponent(currentView, "currentView");
		this.addComponent(container);
		this.setComponentAlignment(container, Alignment.MIDDLE_CENTER);
	}

	public void gotoCrmDashboard() {
		EventBus.getInstance().fireEvent(new CrmEvent.GotoHome(this, null));
	}

	public void addView(PageView view) {
		currentView.removeAllComponents();
		currentView.addComponent(view.getWidget());
	}

}
