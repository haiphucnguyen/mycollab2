package com.esofthead.mycollab.module.crm.view.opportunity;

import java.util.List;

import com.esofthead.mycollab.module.crm.domain.SimpleContactOpportunityRel;
import com.esofthead.mycollab.module.crm.domain.criteria.ContactSearchCriteria;
import com.esofthead.mycollab.module.crm.service.ContactOpportunityService;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.ui.table.DefaultPagedBeanTable;
import com.esofthead.mycollab.vaadin.ui.table.TableViewField;

/**
 * 
 * @author MyCollab Ltd.
 * @since 3.0
 * 
 */
public class OpportunityContactTableDisplay
		extends
		DefaultPagedBeanTable<ContactOpportunityService, ContactSearchCriteria, SimpleContactOpportunityRel> {
	private static final long serialVersionUID = 1L;

	public OpportunityContactTableDisplay(List<TableViewField> displayColumns) {
		super(ApplicationContextUtil
				.getSpringBean(ContactOpportunityService.class),
				SimpleContactOpportunityRel.class, displayColumns);

	}
}
