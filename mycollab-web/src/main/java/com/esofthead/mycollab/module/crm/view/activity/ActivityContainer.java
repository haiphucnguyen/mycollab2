package com.esofthead.mycollab.module.crm.view.activity;

import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.github.wolfie.detachedtabs.DetachedTabs;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.VerticalLayout;

public class ActivityContainer extends AbstractView {
	private static final long serialVersionUID = 1L;

	private VerticalLayout root;
	
	private DetachedTabs activityTabs;
	
	private CssLayout activityDisplaySpace = new CssLayout();
	
	public ActivityContainer() {
		root = new VerticalLayout();
		root.setWidth("100%");
		
		activityDisplaySpace.setWidth("100%");
		activityTabs = new DetachedTabs.Horizontal(activityDisplaySpace);
		activityTabs.setWidth("200px");
		activityTabs.setHeight(null);
		
		this.addComponent(root);
	}
}
