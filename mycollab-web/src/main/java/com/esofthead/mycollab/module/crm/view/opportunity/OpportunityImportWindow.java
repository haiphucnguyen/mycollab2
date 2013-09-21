package com.esofthead.mycollab.module.crm.view.opportunity;

import java.util.Arrays;
import java.util.List;

import com.esofthead.mycollab.common.localization.GenericI18Enum;
import com.esofthead.mycollab.core.utils.LocalizationHelper;
import com.esofthead.mycollab.iexporter.CSVObjectEntityConverter.FieldMapperDef;
import com.esofthead.mycollab.iexporter.csv.CSVDateFormatter;
import com.esofthead.mycollab.module.crm.domain.SimpleOpportunity;
import com.esofthead.mycollab.module.crm.domain.criteria.OpportunitySearchCriteria;
import com.esofthead.mycollab.module.crm.events.OpportunityEvent;
import com.esofthead.mycollab.module.crm.service.OpportunityService;
import com.esofthead.mycollab.module.crm.ui.components.EntityImportWindow;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.events.EventBus;

public class OpportunityImportWindow extends
		EntityImportWindow<SimpleOpportunity> {
	private static final long serialVersionUID = 1L;

	public OpportunityImportWindow() {
		super(false, "Import Opportunity Window", ApplicationContextUtil
				.getSpringBean(OpportunityService.class),
				SimpleOpportunity.class);
	}

	@Override
	protected List<FieldMapperDef> constructCSVFieldMapper() {
		FieldMapperDef[] fields = {
				new FieldMapperDef("opportunityname", "Opportunity Name"),
				new FieldMapperDef("amount", "Amount"),
				new FieldMapperDef("type", "Type"),
				new FieldMapperDef("source", "Source"),
				new FieldMapperDef("expectedcloseddate",
						"Expected Closed Date", new CSVDateFormatter()),
				new FieldMapperDef("nextstep", "Next Step"),
				new FieldMapperDef("probability", "Probability"),
				new FieldMapperDef("assignuser",
						LocalizationHelper
								.getMessage(GenericI18Enum.FORM_ASSIGNEE_FIELD)),
				new FieldMapperDef("opportunitytype", "Opportunity Type"),
				new FieldMapperDef("salesstage", "Sales Stage"),
				new FieldMapperDef("description", "Description") };
		return Arrays.asList(fields);
	}

	@Override
	protected void reloadWhenBackToListView() {
		EventBus.getInstance().fireEvent(
				new OpportunityEvent.GotoList(OpportunityListView.class,
						new OpportunitySearchCriteria()));
	}

}
