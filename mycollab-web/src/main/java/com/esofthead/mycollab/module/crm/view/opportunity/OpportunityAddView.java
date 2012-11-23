package com.esofthead.mycollab.module.crm.view.opportunity;

import com.esofthead.mycollab.module.crm.domain.Opportunity;
import com.esofthead.mycollab.vaadin.events.HasEditFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.TemplateEditItemView;

public interface OpportunityAddView extends TemplateEditItemView<Opportunity> {
	HasEditFormHandlers<Opportunity> getEditFormHandlers();

}
