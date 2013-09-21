package com.esofthead.mycollab.module.crm.view.campaign;

import java.util.Arrays;
import java.util.List;

import com.esofthead.mycollab.common.localization.GenericI18Enum;
import com.esofthead.mycollab.core.utils.LocalizationHelper;
import com.esofthead.mycollab.iexporter.CSVObjectEntityConverter.FieldMapperDef;
import com.esofthead.mycollab.iexporter.csv.CSVDateFormatter;
import com.esofthead.mycollab.module.crm.domain.SimpleCampaign;
import com.esofthead.mycollab.module.crm.domain.criteria.CampaignSearchCriteria;
import com.esofthead.mycollab.module.crm.events.CampaignEvent;
import com.esofthead.mycollab.module.crm.service.CampaignService;
import com.esofthead.mycollab.module.crm.ui.components.EntityImportWindow;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.events.EventBus;

public class CampaignImportWindow extends EntityImportWindow<SimpleCampaign> {
	private static final long serialVersionUID = 1L;

	public CampaignImportWindow() {
		super(false, "Import Campaign Window", ApplicationContextUtil
				.getSpringBean(CampaignService.class), SimpleCampaign.class);
	}

	@Override
	protected List<FieldMapperDef> constructCSVFieldMapper() {
		FieldMapperDef[] fields = {
				new FieldMapperDef("campaignname", "Campaign Name"),
				new FieldMapperDef("startdate", "Start Date",
						new CSVDateFormatter()),
				new FieldMapperDef("enddate", "End Date",
						new CSVDateFormatter()),
				new FieldMapperDef("impressionnote", "Impression Note"),
				new FieldMapperDef("budget", "Budget"),
				new FieldMapperDef("actualcost", "Actual Cost"),
				new FieldMapperDef("expectedrevenue", "Expected Revenue"),
				new FieldMapperDef("expectedcost", "Expected Cost"),
				new FieldMapperDef("impression", "Impression"),
				new FieldMapperDef("status", "Status"),
				new FieldMapperDef("type", "Type"),
				new FieldMapperDef("assignuser",
						LocalizationHelper
								.getMessage(GenericI18Enum.FORM_ASSIGNEE_FIELD)) };
		return Arrays.asList(fields);
	}

	@Override
	protected void reloadWhenBackToListView() {
		EventBus.getInstance().fireEvent(
				new CampaignEvent.GotoList(CampaignListView.class,
						new CampaignSearchCriteria()));
	}

}
