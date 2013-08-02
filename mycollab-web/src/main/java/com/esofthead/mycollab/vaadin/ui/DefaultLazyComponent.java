package com.esofthead.mycollab.vaadin.ui;

import com.esofthead.mycollab.vaadin.mvp.View;
import com.esofthead.mycollab.vaadin.mvp.ViewManager;
import com.vaadin.ui.VerticalLayout;

public class DefaultLazyComponent<V extends View> extends VerticalLayout
		implements ILazyComponent<V> {
	private static final long serialVersionUID = 1L;
	private boolean isInit = false;
	private Class<V> viewClass;

	public DefaultLazyComponent(Class<V> viewClass) {
		super();
		this.viewClass = viewClass;
	}

	@Override
	public void attach() {
		super.attach();
		
		if (!isInit) {
			V view = ViewManager.getView(viewClass);
			this.addComponent(view.getWidget());
			isInit = true;
		}
		
	}
}
