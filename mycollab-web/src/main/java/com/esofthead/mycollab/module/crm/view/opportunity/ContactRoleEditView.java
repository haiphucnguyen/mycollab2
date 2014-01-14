package com.esofthead.mycollab.module.crm.view.opportunity;

import com.esofthead.mycollab.module.crm.domain.Opportunity;
import com.esofthead.mycollab.vaadin.mvp.PageView;

/**
 * 
 * @author MyCollab Ltd.
 * @since 3.0
 * 
 */
public interface ContactRoleEditView extends PageView {
	void display(Opportunity opportunity);
}
