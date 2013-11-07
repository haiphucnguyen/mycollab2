package com.esofthead.mycollab.community.module.project.view.risk;

import com.esofthead.mycollab.module.project.view.risk.IRiskContainer;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.vaadin.ui.Label;

@ViewComponent
public class RiskContainer extends AbstractView implements IRiskContainer {
	private static final long serialVersionUID = 1L;

	public RiskContainer() {
		this.addComponent(new Label(
				"Module is not presented for community edition"));
	}
}
