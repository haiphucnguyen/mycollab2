package com.esofthead.mycollab.community.module.crm.view.opportunity;

import com.esofthead.mycollab.eventmanager.ApplicationEvent;
import com.esofthead.mycollab.eventmanager.ApplicationEventListener;
import com.esofthead.mycollab.module.crm.view.opportunity.IOpportunityPipelineFunnelChartDashlet;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.CssLayout;

@ViewComponent
public class OpportunityPipelineFunnelChartDashlet extends CssLayout implements
		IOpportunityPipelineFunnelChartDashlet {
	private static final long serialVersionUID = 1L;

	@Override
	public ComponentContainer getWidget() {
		return this;
	}

	@Override
	public void addViewListener(
			ApplicationEventListener<? extends ApplicationEvent> listener) {

	}

	@Override
	public void display() {

	}

}
