package com.esofthead.mycollab.module.crm.view;

import com.esofthead.mycollab.vaadin.mvp.Presenter;
import com.esofthead.mycollab.vaadin.mvp.View;
import com.vaadin.ui.ComponentContainer;

public class CrmGenericPresenter<V extends View> implements Presenter{

	protected V view;
	
	@Override
	public void go(ComponentContainer container) {
		CrmContainer crmContainer = (CrmContainer) container;
		crmContainer.addView(view);
	}

}
