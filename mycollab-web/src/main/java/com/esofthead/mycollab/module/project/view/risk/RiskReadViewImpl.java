package com.esofthead.mycollab.module.project.view.risk;

import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.vaadin.ui.Label;

public class RiskReadViewImpl extends AbstractView implements RiskReadView {
	private static final long serialVersionUID = 1L;

	public RiskReadViewImpl() {
		this.addComponent(new Label("Risk REad"));
	}
}
