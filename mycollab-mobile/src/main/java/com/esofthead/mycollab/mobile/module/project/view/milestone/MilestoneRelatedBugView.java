package com.esofthead.mycollab.mobile.module.project.view.milestone;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.SetSearchField;
import com.esofthead.mycollab.mobile.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.mobile.module.project.view.bug.BugListDisplay;
import com.esofthead.mycollab.mobile.ui.AbstractRelatedListView;
import com.esofthead.mycollab.module.project.domain.SimpleMilestone;
import com.esofthead.mycollab.module.project.i18n.BugI18nEnum;
import com.esofthead.mycollab.module.tracker.domain.SimpleBug;
import com.esofthead.mycollab.module.tracker.domain.criteria.BugSearchCriteria;
import com.esofthead.mycollab.vaadin.AppContext;
import com.vaadin.ui.Component;

/**
 * @author MyCollab Ltd.
 *
 * @since 4.5.2
 */
public class MilestoneRelatedBugView extends
		AbstractRelatedListView<SimpleBug, BugSearchCriteria> {

	private static final long serialVersionUID = -7918478404907275472L;

	private SimpleMilestone milestone;

	public MilestoneRelatedBugView() {
		this.setCaption(AppContext.getMessage(BugI18nEnum.M_VIEW_RELATED_TITLE));
		itemList = new BugListDisplay();
		this.setContent(itemList);
	}

	@Override
	public void refresh() {
		loadBugs();
	}

	public void displayBugs(final SimpleMilestone targetMilestone) {
		this.milestone = targetMilestone;
		loadBugs();
	}

	@Override
	protected Component createRightComponent() {
		return null;
	}

	private void loadBugs() {
		BugSearchCriteria criteria = new BugSearchCriteria();

		criteria.setProjectId(new NumberSearchField(SearchField.AND,
				CurrentProjectVariables.getProjectId()));
		criteria.setMilestoneIds(new SetSearchField<Integer>(this.milestone
				.getId()));
		setSearchCriteria(criteria);
	}

}
