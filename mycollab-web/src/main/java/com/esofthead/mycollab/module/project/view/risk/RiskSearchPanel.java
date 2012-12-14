package com.esofthead.mycollab.module.project.view.risk;

import com.esofthead.mycollab.module.crm.ui.components.GenericSearchPanel;
import com.esofthead.mycollab.module.project.domain.criteria.RiskSearchCriteria;
import com.vaadin.ui.VerticalLayout;

public class RiskSearchPanel extends GenericSearchPanel<RiskSearchCriteria> {
	private static final long serialVersionUID = 1L;
	
	protected RiskSearchCriteria searchCriteria;

	@Override
	public void attach() {
		super.attach();
		createBasicSearchLayout();
	}

	private void createBasicSearchLayout() {

		this.setCompositionRoot(new VerticalLayout());
	}

}
