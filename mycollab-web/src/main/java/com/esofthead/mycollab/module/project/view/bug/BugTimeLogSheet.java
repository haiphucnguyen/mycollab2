package com.esofthead.mycollab.module.project.view.bug;

import com.esofthead.mycollab.common.MonitorTypeConstants;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.domain.criteria.ItemTimeLoggingSearchCriteria;
import com.esofthead.mycollab.module.project.ui.components.CompTimeLogSheet;
import com.esofthead.mycollab.module.tracker.domain.SimpleBug;

@SuppressWarnings("serial")
public class BugTimeLogSheet extends CompTimeLogSheet<SimpleBug> {

	protected BugTimeLogSheet(SimpleBug bean) {
		super(bean);
	}

	@Override
	protected void loadTimeInvestItem() {
		ItemTimeLoggingSearchCriteria searchCriteria = new ItemTimeLoggingSearchCriteria();
		searchCriteria.setProjectId(new NumberSearchField(CurrentProjectVariables.getProjectId()));
		searchCriteria.setType(new StringSearchField(MonitorTypeConstants.PRJ_BUG));
		searchCriteria.setTypeId(new NumberSearchField(bean.getId()));
		tableItem.setSearchCriteria(searchCriteria);
	}

	@Override
	protected void saveTimeInvest() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void updateTimeRemain() {
		// TODO Auto-generated method stub
		
	}
	
}
