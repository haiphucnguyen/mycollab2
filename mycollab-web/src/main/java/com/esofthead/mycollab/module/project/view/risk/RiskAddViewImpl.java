package com.esofthead.mycollab.module.project.view.risk;

import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.vaadin.ui.Label;

public class RiskAddViewImpl extends AbstractView implements RiskAddView {
	private static final long serialVersionUID = 1L;

	public RiskAddViewImpl() {
		this.addComponent(new Label("Risk Add"));
	}
}
