package com.esofthead.mycollab.module.crm.view.activity;

import com.esofthead.mycollab.module.crm.domain.criteria.EventSearchCriteria;
import com.esofthead.mycollab.vaadin.ui.GenericSearchPanel;
import com.vaadin.ui.HorizontalLayout;

public class EventSearchPanel extends GenericSearchPanel<EventSearchCriteria>{
	private static final long serialVersionUID = 1L;
	
	protected EventSearchCriteria searchCriteria;
	
	@Override
	public void attach() {
		super.attach();
		createBasicSearchLayout();
	}
	
	private void createBasicSearchLayout() {
		this.setCompositionRoot(new HorizontalLayout());
	}

}
