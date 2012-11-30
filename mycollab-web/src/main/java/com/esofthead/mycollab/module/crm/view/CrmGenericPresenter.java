package com.esofthead.mycollab.module.crm.view;

import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.mvp.View;
import com.vaadin.ui.ComponentContainer;

public class CrmGenericPresenter<V extends View> extends AbstractPresenter{

	protected V view;

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		CrmContainer crmContainer = (CrmContainer) container;
		crmContainer.addView(view);
		
	}
}
