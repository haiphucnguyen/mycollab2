package com.esofthead.mycollab.module.crm.view.cases;

import com.esofthead.mycollab.vaadin.mvp.AbstractPageView;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.vaadin.ui.Label;

/**
 * 
 * @author MyCollab Ltd.
 * @since 4.0
 * 
 */
@ViewComponent
public class CaseListNoItemView extends AbstractPageView {
	private static final long serialVersionUID = 1L;

	public CaseListNoItemView() {
		this.addComponent(new Label("No Cases"));
	}
}
