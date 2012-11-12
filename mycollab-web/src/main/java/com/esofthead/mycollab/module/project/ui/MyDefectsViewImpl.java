package com.esofthead.mycollab.module.project.ui;

import org.springframework.stereotype.Component;

import com.esofthead.mycollab.vaadin.mvp.ui.AbstractView;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
@Component
public class MyDefectsViewImpl extends AbstractView implements MyDefectsView {

	@Override
	protected ComponentContainer initMainLayout() {
		return new VerticalLayout();
	}

}
