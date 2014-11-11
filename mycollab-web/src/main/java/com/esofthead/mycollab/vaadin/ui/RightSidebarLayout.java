/**
 * 
 */
package com.esofthead.mycollab.vaadin.ui;

import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;

/**
 * @author supertux
 *
 */
public class RightSidebarLayout extends CssLayout {
	private static final long serialVersionUID = 6058720774092113093L;

	private final CssLayout contentWrap;
	private final CssLayout sidebarWrap;

	public RightSidebarLayout() {
		this.setStyleName("rightsidebar-layout");

		this.contentWrap = new CssLayout();
		this.contentWrap.setStyleName("content-wrap");
		this.contentWrap.setWidth("100%");
		this.addComponent(contentWrap);

		this.sidebarWrap = new CssLayout();
		this.sidebarWrap.setStyleName("sidebar-wrap");
		this.sidebarWrap.setWidth("250px");
		this.addComponent(sidebarWrap);
	}

	public void setContent(Component c) {
		this.contentWrap.removeAllComponents();
		this.contentWrap.addComponent(c);
	}

	public void setSidebar(Component c) {
		this.sidebarWrap.removeAllComponents();
		this.sidebarWrap.addComponent(c);
	}

}
