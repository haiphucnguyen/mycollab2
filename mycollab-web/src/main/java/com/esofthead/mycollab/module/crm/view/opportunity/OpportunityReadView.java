package com.esofthead.mycollab.module.crm.view.opportunity;

import com.esofthead.mycollab.module.crm.domain.Opportunity;
import com.esofthead.mycollab.vaadin.events.HasPreviewFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.View;

public interface OpportunityReadView extends View {
	HasPreviewFormHandlers<Opportunity> getPreviewFormHandlers();

	void displayItem(Opportunity item);

}
