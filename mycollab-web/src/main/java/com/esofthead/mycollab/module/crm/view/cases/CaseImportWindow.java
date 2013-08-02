package com.esofthead.mycollab.module.crm.view.cases;

import java.util.Arrays;
import java.util.List;

import com.esofthead.mycollab.iexporter.CSVObjectEntityConverter.FieldMapperDef;
import com.esofthead.mycollab.module.crm.domain.SimpleCase;
import com.esofthead.mycollab.module.crm.domain.criteria.CaseSearchCriteria;
import com.esofthead.mycollab.module.crm.events.CaseEvent;
import com.esofthead.mycollab.module.crm.service.CaseService;
import com.esofthead.mycollab.module.crm.ui.components.EntityImportWindow;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.web.AppContext;

public class CaseImportWindow extends EntityImportWindow<SimpleCase> {
	private static final long serialVersionUID = 1L;

	public CaseImportWindow() {
		super(false, "Import Case Window", AppContext
				.getSpringBean(CaseService.class), SimpleCase.class);
	}

	@Override
	protected List<FieldMapperDef> constructCSVFieldMapper() {
		FieldMapperDef[] fields = { new FieldMapperDef("priority", "Priority"),
				new FieldMapperDef("status", "Status"),
				new FieldMapperDef("type", "Type"),
				new FieldMapperDef("subject", "Subject"),
				new FieldMapperDef("assignuser", "Assign User"),
				new FieldMapperDef("reason", "Reason"),
				new FieldMapperDef("origin", "Origin"),
				new FieldMapperDef("email", "Email"),
				new FieldMapperDef("phonenumber", "Phone Number") };
		return Arrays.asList(fields);
	}

	@Override
	protected void reloadWhenBackToListView() {
		EventBus.getInstance().fireEvent(
				new CaseEvent.GotoList(CaseListView.class,
						new CaseSearchCriteria()));
	}

}
