package com.esofthead.mycollab.module.crm.view.lead;

import java.util.Arrays;
import java.util.List;

import com.esofthead.mycollab.iexporter.CSVObjectEntityConverter.FieldMapperDef;
import com.esofthead.mycollab.iexporter.csv.CSVBooleanFormatter;
import com.esofthead.mycollab.module.crm.domain.SimpleLead;
import com.esofthead.mycollab.module.crm.domain.criteria.LeadSearchCriteria;
import com.esofthead.mycollab.module.crm.events.LeadEvent;
import com.esofthead.mycollab.module.crm.service.LeadService;
import com.esofthead.mycollab.module.crm.ui.components.EntityImportWindow;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.web.AppContext;

public class LeadImportWindow extends EntityImportWindow<SimpleLead> {
	private static final long serialVersionUID = 1L;

	public LeadImportWindow() {
		super(false, "Import Lead Import", AppContext
				.getSpringBean(LeadService.class), SimpleLead.class);
	}

	@Override
	protected List<FieldMapperDef> constructCSVFieldMapper() {
		FieldMapperDef[] fields = {
				new FieldMapperDef("leadsourcedesc", "Leader Source"),
				new FieldMapperDef("statusdesc", "Status"),
				new FieldMapperDef("referredby", "Referred By"),
				new FieldMapperDef("prefixname", "Prefix Name"),
				new FieldMapperDef("firstname", "First Name"),
				new FieldMapperDef("lastname", "Last Name"),
				new FieldMapperDef("accountname", "Account Name"),
				new FieldMapperDef("title", "Title"),
				new FieldMapperDef("department", "Department"),
				new FieldMapperDef("iscallable", "Callable",
						new CSVBooleanFormatter()),
				new FieldMapperDef("officephone", "Office Phone"),
				new FieldMapperDef("homephone", "Home phone"),
				new FieldMapperDef("otherphone", "Other Phone"),
				new FieldMapperDef("mobile", "Mobile"),
				new FieldMapperDef("fax", "Fax"),
				new FieldMapperDef("assignuser", "Assign User"),
				new FieldMapperDef("status", "Status"),
				new FieldMapperDef("source", "Source"),
				new FieldMapperDef("website", "Website"),
				new FieldMapperDef("industry", "Industry"),
				new FieldMapperDef("noemployees", "NoEmployess"),
				new FieldMapperDef("email", "Email"),
				new FieldMapperDef("primaddress", "Address"),
				new FieldMapperDef("primcity", "City"),
				new FieldMapperDef("primstate", "State"),
				new FieldMapperDef("primpostalcode", "Postal Code"),
				new FieldMapperDef("primcountry", "Country"),
				new FieldMapperDef("otheraddress", "Other Address"),
				new FieldMapperDef("othercity", "Other City"),
				new FieldMapperDef("otherstate", "Other State"),
				new FieldMapperDef("otherpostalcode", "Other Postal Code"),
				new FieldMapperDef("othercountry", "Other Country"),
				new FieldMapperDef("description", "Description") };
		return Arrays.asList(fields);
	}

	@Override
	protected void reloadWhenBackToListView() {
		EventBus.getInstance().fireEvent(
				new LeadEvent.GotoList(LeadListView.class,
						new LeadSearchCriteria()));
	}
}
