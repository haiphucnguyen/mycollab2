package com.esofthead.mycollab.mobile.module.crm.view;

import com.esofthead.mycollab.vaadin.mvp.AbstractMobilePageView;
import com.esofthead.mycollab.vaadin.mvp.PageView;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;

/**
 * 
 * @author MyCollab Ltd.
 * @since 3.0
 * 
 */
@ViewComponent
public class ActivityStreamView extends AbstractMobilePageView implements PageView {
	private static final long serialVersionUID = 1L;

	public ActivityStreamView() {
		this.setContent(new Label("AAAA"));
		this.setLeftComponent(new Button("Back"));
	}
}
