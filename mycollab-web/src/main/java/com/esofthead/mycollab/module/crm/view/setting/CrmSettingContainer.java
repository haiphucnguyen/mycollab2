package com.esofthead.mycollab.module.crm.view.setting;

import com.esofthead.mycollab.eventmanager.ApplicationEvent;
import com.esofthead.mycollab.eventmanager.ApplicationEventListener;
import com.esofthead.mycollab.vaadin.mvp.View;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.CssLayout;

@ViewComponent
public class CrmSettingContainer extends CssLayout implements View {
	private static final long serialVersionUID = 1L;

	@Override
	public ComponentContainer getWidget() {
		return this;
	}

	@Override
	public void addViewListener(
			ApplicationEventListener<? extends ApplicationEvent> listener) {
		// TODO Auto-generated method stub

	}

}
