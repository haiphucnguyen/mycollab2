package com.esofthead.mycollab.module.crm.view.lead;

import com.esofthead.mycollab.module.crm.domain.SimpleLead;
import com.esofthead.mycollab.vaadin.mvp.PageView;

/**
 * 
 * @author MyCollab Ltd.
 * @since 3.0
 * 
 */
public interface LeadConvertInfoView extends PageView {

	void displayConvertLead(SimpleLead lead);
}
