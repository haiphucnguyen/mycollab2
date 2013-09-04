package com.esofthead.mycollab.module.crm.view;

import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.core.utils.ValuedBean;
import com.esofthead.mycollab.vaadin.mvp.ListSelectionPresenter;
import com.esofthead.mycollab.vaadin.mvp.ListView;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.vaadin.ui.ComponentContainer;

public abstract class CrmGenericListPresenter<V extends ListView<S, B>, S extends SearchCriteria, B extends ValuedBean>
		extends ListSelectionPresenter<V, S, B> {
	private static final long serialVersionUID = 1L;

	public CrmGenericListPresenter(Class<V> viewClass) {
		super(viewClass);
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		CrmModule crmModule = (CrmModule) container;
		crmModule.addView(view);
	}

}
