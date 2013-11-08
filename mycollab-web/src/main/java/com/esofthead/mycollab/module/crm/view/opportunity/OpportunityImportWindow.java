/**
 * This file is part of mycollab-web.
 *
 * mycollab-web is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-web is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-web.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.module.crm.view.opportunity;

import java.util.Arrays;
import java.util.List;

import com.esofthead.mycollab.common.localization.GenericI18Enum;
import com.esofthead.mycollab.core.utils.LocalizationHelper;
import com.esofthead.mycollab.eventmanager.EventBus;
import com.esofthead.mycollab.iexporter.CSVObjectEntityConverter.FieldMapperDef;
import com.esofthead.mycollab.iexporter.csv.CSVDateFormatter;
import com.esofthead.mycollab.module.crm.domain.SimpleOpportunity;
import com.esofthead.mycollab.module.crm.domain.criteria.OpportunitySearchCriteria;
import com.esofthead.mycollab.module.crm.events.OpportunityEvent;
import com.esofthead.mycollab.module.crm.service.OpportunityService;
import com.esofthead.mycollab.module.crm.ui.components.EntityImportWindow;
import com.esofthead.mycollab.spring.ApplicationContextUtil;

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
